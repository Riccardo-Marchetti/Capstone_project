package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Seat> getAllSeats(){
        return this.seatDAO.findAll();
    }

    public Seat getSeatById (long id){
        return this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Seat saveSeat (SeatDTO payload){
        Seat seat = new Seat(payload.type(), payload.booked(), payload.bookingDate());
        return this.seatDAO.save(seat);
    }
    public Seat updateSeat (long id, SeatDTO payload){
        Seat seat = this.seatDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        seat.setType(payload.type());
        seat.setBooked(payload.booked());
        seat.setBookingDate(payload.bookingDate());
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
