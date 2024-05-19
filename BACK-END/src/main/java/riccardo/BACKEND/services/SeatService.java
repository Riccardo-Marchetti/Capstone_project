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

    public Page<Seat> getAllSeats(int page, int size, String sortBy){
        if (size > 64) size = 64;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.seatDAO.findAll(pageable);
    }
    public List<Seat> findAll() {
        return this.seatDAO.findAll();
    }
    public Seat getSeatById (long id){
        return this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Seat saveSeat (SeatDTO payload){
        Seat seat = new Seat(payload.type());
        return this.seatDAO.save(seat);
    }
    public Seat saveSeat (Seat seat){
        Seat newSeat = new Seat(seat.getType(), seat.getPrice());
        return this.seatDAO.save(seat);
    }
    public Seat updateSeat (long id, SeatDTO payload){
        Seat seat = this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        seat.setType(payload.type());
//        seat.setBooked(payload.booked());
//        seat.setBookingDate(payload.bookingDate());
        return this.seatDAO.save(seat);
    }
    public void deleteSeat (long id){
        Seat seat = this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.seatDAO.delete(seat);
    }
    public List<Seat> getSeatsByIds(List<Long> seatIds) {
        List<Seat> seatsList = new ArrayList<>();
        for (Long id : seatIds) {
            Seat seat = seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
            seatsList.add(seat);
        }
        return seatsList;
    }
}
