package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.SeatDTO;
import riccardo.BACKEND.repositories.SeatDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatDAO seatDAO;

    // Returns a paginated list of all seats
    public Page<Seat> getAllSeats(int page, int size, String sortBy){
        if (size > 64) size = 64;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.seatDAO.findAll(pageable);
    }

    // Returns all seats
    public List<Seat> findAll() {
        return this.seatDAO.findAll();
    }

    // Returns a seat by its id
    public Seat getSeatById (long id){
        return this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    // Saves a new seat to the database
    public Seat saveSeat (SeatDTO payload){
        Seat seat = new Seat(payload.type());
        return this.seatDAO.save(seat);
    }

    // Saves a seat to the database
    public Seat saveSeat (Seat seat){
        Seat newSeat = new Seat(seat.getType(), seat.getPrice());
        return this.seatDAO.save(seat);
    }

    // Updates a seat in the database
    public Seat updateSeat (long id, SeatDTO payload){
        Seat seat = this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        seat.setType(payload.type());
        return this.seatDAO.save(seat);
    }

    // Deletes a seat from the database
    public void deleteSeat (long id){
        Seat seat = this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.seatDAO.delete(seat);
    }

    // Returns a list of seats by their id
    public List<Seat> getSeatsByIds(List<Long> seatIds) {
        List<Seat> seatsList = new ArrayList<>();
        for (Long id : seatIds) {
            Seat seat = seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
            seatsList.add(seat);
        }
        return seatsList;
    }
}
