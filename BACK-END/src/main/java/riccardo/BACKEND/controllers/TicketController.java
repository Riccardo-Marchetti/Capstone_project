package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.entities.Ticket;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.TicketDTO;
import riccardo.BACKEND.services.FilmService;
import riccardo.BACKEND.services.SeatService;
import riccardo.BACKEND.services.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private SeatService seatService;

    // This method is used to get all tickets
    @GetMapping
    public Page<Ticket> getAllTickets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String sortBy){
        return ticketService.getAllTickets(page, size, sortBy);
    }

    // This method is used to get a ticket by its ID
    @GetMapping ("/{ticketId}")
    public Ticket getTicketById (@PathVariable long ticketId){
        return ticketService.getTicketById(ticketId);
    }

    // This method is used to get booked seats for a show
    @GetMapping("/bookedSeats/{showId}/{showDate}/{showTime}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Long> getBookedSeatsForShow(@PathVariable long showId, @PathVariable LocalDate showDate, @PathVariable List<LocalTime> showTime) {
        return ticketService.getBookedSeatsForShow(showId, showDate, showTime);
    }

    // This method is used to save a new ticket
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public Ticket saveTicket (@AuthenticationPrincipal User currentUser,@RequestBody @Validated TicketDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        List<Seat> seats = seatService.getSeatsByIds(payload.idSeat());
        return ticketService.saveTicket(currentUser, payload, seats);
    }

    // This method is used to update an existing ticket
    @PutMapping ("/{ticketId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Ticket updateTicket (@PathVariable long ticketId, @RequestBody @Validated TicketDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return ticketService.updateTicket(ticketId, payload);
    }

    // This method is used to delete a ticket
    @DeleteMapping ("/{ticketId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteTicket (@PathVariable long ticketId){
        this.ticketService.deleteTicket(ticketId);
    }
}
