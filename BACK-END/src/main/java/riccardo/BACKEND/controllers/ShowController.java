package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Show;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.ShowDTO;
import riccardo.BACKEND.services.FilmService;
import riccardo.BACKEND.services.ShowService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    // This method is used to get all shows
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Page<Show> getAllShows(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "30") int size, @RequestParam (defaultValue = "film") String sortBy){
        return this.showService.getAllShows(page, size, sortBy);
    }

    // This method is used to get a show by its ID
    @GetMapping ("/{showId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public Show getShowById (@PathVariable long showId){
        return this.showService.getShowById(showId);
    }

    // This method is used to get all shows by film ID
    @GetMapping("/film/{filmId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Show> getAllShowsByFilmId(@PathVariable long filmId) {
        return this.showService.getAllShowsByFilmId(filmId);
    }

    // This method is used to get showtimes by city and film
    @GetMapping("/available/{city}/{filmId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Show> getShowtimesByCityAndFilm(@PathVariable String city, @PathVariable long filmId) {
        return this.showService.getShowtimesByCityAndFilm(city, filmId);
    }

    // This method is used to get a show by city, date and film
    @GetMapping("/{city}/{date}/{filmId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Show> getShowByCityDateAndFilm(@PathVariable String city, @PathVariable LocalDate date, @PathVariable long filmId) {
        return this.showService.getShowByCityDateAndFilm(city, date, filmId);
    }

    // This method is used to save a new show
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') ")
    public Show saveShow (@RequestBody @Validated ShowDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.showService.saveShow(payload);
    }

    // This method is used to update an existing show
    @PutMapping ("/{showId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') ")
    public Show updateShow (@PathVariable long showId, @RequestBody @Validated ShowDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.showService.updateShow(showId, payload);
    }

    // This method is used to delete a show
    @DeleteMapping ("/{showId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') ")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteShow (@PathVariable long showId){
        this.showService.deleteShow(showId);
    }
}
