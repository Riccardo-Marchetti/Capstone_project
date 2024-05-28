package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.SeatDTO;
import riccardo.BACKEND.services.FilmService;
import riccardo.BACKEND.services.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // This method is used to get all seats
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public Page<Seat> getAllSeats(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "64") int size, @RequestParam (defaultValue = "id") String sortBy){
        return this.seatService.getAllSeats(page, size, sortBy);
    }

    // This method is used to get a seat by its ID
    @GetMapping ("/{seatId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public Seat getSeatById (@PathVariable long seatId){
        return this.seatService.getSeatById(seatId);
    }

    // This method is used to save a new seat
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') ")
    public Seat saveSeat (@RequestBody @Validated SeatDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.seatService.saveSeat(payload);
    }

    // This method is used to update an existing seat
    @PutMapping ("/{seatId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') ")
    public Seat updateSeat (@PathVariable long seatId, @RequestBody @Validated SeatDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.seatService.updateSeat(seatId, payload);
    }

    // This method is used to delete a seat
    @DeleteMapping ("/{seatId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') ")
    public void deleteSeat (@PathVariable long seatId){
        this.seatService.deleteSeat(seatId);
    }
}
