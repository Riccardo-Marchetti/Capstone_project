package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Ticket;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.TicketDTO;
import riccardo.BACKEND.services.FilmService;
import riccardo.BACKEND.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping ("/{ticketId}")
    public Ticket getTicketById (@PathVariable long ticketId){
        return ticketService.getTicketById(ticketId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Ticket saveTicket (@RequestBody TicketDTO payload){
        return ticketService.saveTicket(payload);
    }

    @PutMapping ("/{ticketId}")
    public Ticket updateTicket (@PathVariable long ticketId, @RequestBody TicketDTO payload ){
        return ticketService.updateTicket(ticketId, payload);
    }

    @DeleteMapping ("/{ticketId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteTicket (@PathVariable long ticketId){
        this.ticketService.deleteTicket(ticketId);
    }
}
