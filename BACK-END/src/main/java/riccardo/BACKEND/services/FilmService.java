package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.enums.FilmState;
import riccardo.BACKEND.enums.FilmType;
import riccardo.BACKEND.enums.SeatType;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.FilmDTO;
import riccardo.BACKEND.repositories.FilmDAO;

import java.util.List;


@Service
public class FilmService {

    @Autowired
    private FilmDAO filmDAO;

    // Returns a page of film
    public Page<Film> getAllFilms(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.filmDAO.findAll(pageable);
    }

    // Returns a film from its id
    public Film getFilmById (long id){
        return this.filmDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    // Saves a new film to the database
    public Film saveFilm (FilmDTO payload){
        Film film = new Film(payload.title(), payload.director(), payload.type(), payload.duration(), payload.rating(), payload.description(), payload.exitDate(), payload.trailer(), payload.cover(), payload.filmState());
        return this.filmDAO.save(film);
    }

    // Saves a film to the database
    public Film saveFilm (Film payload){
        Film film = new Film(payload.getTitle(), payload.getDirector(), payload.getType(), payload.getDuration(), payload.getRating(), payload.getDescription(), payload.getExitDate(), payload.getTrailer(), payload.getCover(), payload.getFilmState());
        return this.filmDAO.save(film);
    }

    // Updates a film in the database
    public Film updateFilm (long id, Film payload){
        Film film = this.filmDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        film.setTitle(payload.getTitle());
        film.setDirector(payload.getDirector());
        film.setType(payload.getType());
        film.setDuration(payload.getDuration());
        film.setRating(payload.getRating());
        film.setDescription(payload.getDescription());
        film.setExitDate(payload.getExitDate());
        film.setTrailer(payload.getTrailer());
        return this.filmDAO.save(film);
    }

    // Deletes a film from the database
    public void deleteFilm (long id){
        Film film = this.filmDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.filmDAO.delete(film);
    }

    // Returns a list of film by type
    public List<Film> findFilmByType (FilmType type){
        return this.filmDAO.findByType(type);
    }

    // Returns a list of film by state
    public List<Film> findByFilmState (FilmState filmState){
        return this.filmDAO.findByFilmState(filmState);
    }

    // Returns a list of film by title
    public List<Film> findFilmByTitleFragment(String titleFragment) {
        return filmDAO.findByTitleContainingIgnoreCase(titleFragment);
    }

}
