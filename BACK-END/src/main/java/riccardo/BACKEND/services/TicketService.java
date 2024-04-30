package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.entities.Ticket;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.TicketDTO;
import riccardo.BACKEND.repositories.TicketDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private UserService userService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private ServiceLocator serviceLocator;

    public List<Ticket> getAllTickets(){
        return this.ticketDAO.findAll();
    }

    public Ticket getTicketById (long id){
        return this.ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Ticket saveTicket (TicketDTO payload){
        ShowService showService = serviceLocator.getService(ShowService.class);
        Ticket ticket = new Ticket(payload.assignedSeats(), payload.price(), payload.bookingDate(), userService.getUserById(payload.idUser()), showService.getShowById(payload.idShow()), seatService.getSeatsByIds(payload.idSeat()));
        return this.ticketDAO.save(ticket);
    }
    public Ticket updateTicket (long id, TicketDTO payload){
        ShowService showService = serviceLocator.getService(ShowService.class);
        Ticket ticket = this.ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        ticket.setAssignedSeats(payload.assignedSeats());
        ticket.setPrice(payload.price());
        ticket.setBookingDate(payload.bookingDate());
        ticket.setUser(userService.getUserById(payload.idUser()));
        ticket.setShow( showService.getShowById(payload.idShow()));
        ticket.setSeat(seatService.getSeatsByIds(payload.idSeat()));
        return this.ticketDAO.save(ticket);
    }
    public void deleteTicket (long id){
        Ticket ticket = this.ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.ticketDAO.delete(ticket);
    }
    public List<Ticket> getTicketsByIds(List<Long> ticketsIds) {
        List<Ticket> ticketsList = new ArrayList<>();
        for (Long id : ticketsIds) {
            Ticket ticket = ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
            ticketsList.add(ticket);
        }
        return ticketsList;
    }
}
