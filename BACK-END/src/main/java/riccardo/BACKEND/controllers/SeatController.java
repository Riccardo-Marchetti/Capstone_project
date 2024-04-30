package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Seat;
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

    @GetMapping
    public List<Seat> getAllSeats(){
        return this.seatService.getAllSeats();
    }

    @GetMapping ("/{seatId}")
    public Seat getSeatById (@PathVariable long seatId){
        return this.seatService.getSeatById(seatId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Seat saveSeat (@RequestBody SeatDTO payload){
        return this.seatService.saveSeat(payload);
    }

    @PutMapping ("/{seatId}")
    public Seat updateSeat (@PathVariable long seatId, @RequestBody SeatDTO payload ){
        return this.seatService.updateSeat(seatId, payload);
    }

    @DeleteMapping ("/{seatId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteSeat (@PathVariable long seatId){
        this.seatService.deleteSeat(seatId);
    }
}
