package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.entities.Ticket;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.TicketDTO;
import riccardo.BACKEND.payloads.UserDTO;
import riccardo.BACKEND.repositories.TicketDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Page<Ticket> getAllTickets(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.ticketDAO.findAll(pageable);
    }

    public Ticket getTicketById (long id){
        return this.ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Ticket saveTicket (User user, TicketDTO payload, List<Seat> seats){
        ShowService showService = serviceLocator.getService(ShowService.class);
        Ticket ticket = new Ticket( payload.price(),payload.selectedShowTime(), payload.assignedSeats(), user, showService.getShowById(payload.idShow()), seats);
        for (Seat seat : seats){
            seatService.saveSeat(seat);
        }
        return this.ticketDAO.save(ticket);
    }
    public Ticket updateTicket (long id, TicketDTO payload){
        ShowService showService = serviceLocator.getService(ShowService.class);
        Ticket ticket = this.ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        ticket.setPrice(payload.price());
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
    public List<Ticket> findTicketsByUser (User user){
        return this.ticketDAO.findByUser(user);
    }
public List<Long> getBookedSeatsForShow(long showId, LocalDate showDate, List<LocalTime> showTime) {
    List<Ticket> tickets = ticketDAO.findAllByShowIdAndShowDateTime(showId, showDate, showTime);
    return tickets.stream()
            .flatMap(ticket -> ticket.getSeat().stream())
            .map(Seat::getId)
            .collect(Collectors.toList());
}
}
