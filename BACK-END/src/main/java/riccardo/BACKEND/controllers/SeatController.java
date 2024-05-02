package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public Page<Seat> getAllSeats(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "20") int size, @RequestParam (defaultValue = "id") String sortBy){
        return this.seatService.getAllSeats(page, size, sortBy);
    }

    @GetMapping ("/{seatId}")
    public Seat getSeatById (@PathVariable long seatId){
        return this.seatService.getSeatById(seatId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Seat saveSeat (@RequestBody @Validated SeatDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.seatService.saveSeat(payload);
    }

    @PutMapping ("/{seatId}")
    public Seat updateSeat (@PathVariable long seatId, @RequestBody @Validated SeatDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.seatService.updateSeat(seatId, payload);
    }

    @DeleteMapping ("/{seatId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteSeat (@PathVariable long seatId){
        this.seatService.deleteSeat(seatId);
    }
}
