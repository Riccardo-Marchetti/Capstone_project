package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.services.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    // This method is used to get all cinemas
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Page<Cinema> getAllCinemas (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "name") String sortBy){
        return this.cinemaService.getAllCinema(page, size, sortBy);
    }

    // This method is used to get a cinema by its ID
    @GetMapping ("/{cinemaId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Cinema getCinemaById (@PathVariable long cinemaId){
        return this.cinemaService.getCinemaById(cinemaId);
    }

    // This method is used to save a new cinema
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Cinema saveCinema (@RequestBody @Validated CinemaDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.cinemaService.saveCinema(payload);
    }

    // This method is used to update an existing cinema
    @PutMapping ("/{cinemaId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Cinema updateCinema (@PathVariable long cinemaId, @RequestBody @Validated CinemaDTO payload , BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.cinemaService.updateCinema(cinemaId, payload);
    }

    //  This method is used to delete a cinema
    @DeleteMapping ("/{cinemaId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCinema (@PathVariable long cinemaId){
        this.cinemaService.deleteCinema(cinemaId);
    }
}
