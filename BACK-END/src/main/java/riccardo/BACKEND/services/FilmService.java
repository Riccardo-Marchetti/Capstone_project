package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.FilmDTO;
import riccardo.BACKEND.repositories.FilmDAO;


@Service
public class FilmService {

    @Autowired
    private FilmDAO filmDAO;

    public Page<Film> getAllFilms(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.filmDAO.findAll(pageable);
    }

    public Film getFilmById (long id){
        return this.filmDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Film saveFilm (FilmDTO payload){
        Film film = new Film(payload.title(), payload.director(), payload.type(), payload.duration(), payload.rating(), payload.description(), payload.exitDate(), payload.trailer());
        return this.filmDAO.save(film);
    }
    public Film updateFilm (long id, FilmDTO payload){
        Film film = this.filmDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        film.setTitle(payload.title());
        film.setDirector(payload.director());
        film.setType(payload.type());
        film.setDuration(payload.duration());
        film.setRating(payload.rating());
        film.setDescription(payload.description());
        film.setExitDate(payload.exitDate());
        film.setTrailer(payload.trailer());
        return this.filmDAO.save(film);
    }
    public void deleteFilm (long id){
        Film film = this.filmDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.filmDAO.delete(film);
    }
}
