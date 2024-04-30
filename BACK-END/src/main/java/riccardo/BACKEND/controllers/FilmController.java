package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Comment;
import riccardo.BACKEND.entities.Film;
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

    @GetMapping
    public List<Film> getAllFilms(){
       return this.filmService.getAllFilms();
    }

    @GetMapping ("/{filmId}")
    public Film getFilmById (@PathVariable long filmId){
        return this.filmService.getFilmById(filmId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Film saveFilm (@RequestBody FilmDTO payload){
        return this.filmService.saveFilm(payload);
    }

    @PutMapping ("/{filmId}")
    public Film updateFilm (@PathVariable long filmId, @RequestBody FilmDTO payload ){
        return this.filmService.updateFilm(filmId, payload);
    }

    @DeleteMapping ("/{filmId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteFilm (@PathVariable long filmId){
        this.filmService.deleteFilm(filmId);
    }
}
