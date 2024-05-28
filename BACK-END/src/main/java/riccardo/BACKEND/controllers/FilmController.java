package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Comment;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.enums.FilmState;
import riccardo.BACKEND.enums.FilmType;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.FilmDTO;
import riccardo.BACKEND.services.CommentService;
import riccardo.BACKEND.services.FilmService;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    // This method is used to get all films
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public Page<Film> getAllFilms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "exitDate") String sortBy){
       return this.filmService.getAllFilms(page, size, sortBy);
    }

    // This method is used to get films by type
    @GetMapping ("/type")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Film> getFilmsByType (@RequestParam FilmType type){
        return this.filmService.findFilmByType(type);
    }

    // This method is used to get films by title
    @GetMapping ("/search")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Film> getFilmsByTitle (@RequestParam String title){
        return this.filmService.findFilmByTitleFragment(title);
    }

    // This method is used to get films by state
    @GetMapping ("/filmState")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Film> getFilmsByState (@RequestParam FilmState filmState){
        return this.filmService.findByFilmState(filmState);
    }

    // This method is used to get a film by its ID
    @GetMapping ("/{filmId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public Film getFilmById (@PathVariable long filmId){
        return this.filmService.getFilmById(filmId);
    }

    // This method is used to save a new film
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Film saveFilm (@RequestBody @Validated FilmDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.filmService.saveFilm(payload);
    }

    // This method is used to update an existing film
    @PutMapping ("/{filmId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Film updateFilm (@PathVariable long filmId, @RequestBody @Validated Film payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.filmService.updateFilm(filmId, payload);
    }

    // This method is used to delete a film
    @DeleteMapping ("/{filmId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public void deleteFilm (@PathVariable long filmId){
        this.filmService.deleteFilm(filmId);
    }
}
