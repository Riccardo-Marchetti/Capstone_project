package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.services.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public Page<Cinema> getAllCinemas (@RequestParam int page, @RequestParam int size, @RequestParam String sortBy){
        return this.cinemaService.getAllCinema(page, size, sortBy);
    }

    @GetMapping ("/{cinemaId}")
    public Cinema getCinemaById (@PathVariable long cinemaId){
        return this.cinemaService.getCinemaById(cinemaId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Cinema saveCinema (@RequestBody CinemaDTO payload){
        return this.cinemaService.saveCinema(payload);
    }

    @PutMapping ("/{cinemaId}")
    public Cinema updateCinema (@PathVariable long cinemaId, @RequestBody CinemaDTO payload ){
        return this.cinemaService.updateCinema(cinemaId, payload);
    }

    @DeleteMapping ("/{cinemaId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCinema (@PathVariable long cinemaId){
        this.cinemaService.deleteCinema(cinemaId);
    }
}
