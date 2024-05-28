package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.CinemaRoom;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.entities.Show;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.ShowDTO;
import riccardo.BACKEND.repositories.ShowDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowDAO showDAO;

    @Autowired
    private FilmService filmService;
    @Autowired
    private CinemaRoomService cinemaRoomService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ServiceLocator serviceLocator;

    // Returns a page of show
    public Page<Show> getAllShows(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.showDAO.findAll(pageable);
    }

    // Returns all show
    public List<Show> getAllShows(){
        return this.showDAO.findAll();
    }

    // Returns a show from its id
    public Show getShowById (long id){
        return this.showDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    // Saves a new show to the database
    public Show saveShow (ShowDTO payload){
        Show show = new Show(payload.showDate(), payload.showTime(), filmService.getFilmById(payload.idFilm()), cinemaRoomService.getCinemaRoomById(payload.idCinemaRoom()));
        return this.showDAO.save(show);
    }

    // Saves a show to the database
    public Show saveShow (Show payload){
        Show show = new Show(payload.getShowDate(), payload.getShowTime(), filmService.getFilmById(payload.getFilm().getId()), cinemaRoomService.getCinemaRoomById(payload.getCinemaRoom().getNumber()));
        return this.showDAO.save(show);
    }

    // Updates an existing show in the database
    public Show updateShow (long id, ShowDTO payload){
        Show show = this.showDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        show.setShowDate(payload.showDate());
        show.setShowTime(payload.showTime());
        show.setFilm(filmService.getFilmById(payload.idFilm()));
        show.setCinemaRoom(cinemaRoomService.getCinemaRoomById(payload.idCinemaRoom()));
        return this.showDAO.save(show);
    }

    // Deletes a show from the database
    public void deleteShow (long id){
        Show show = this.showDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.showDAO.delete(show);
    }

    // Returns a show from its date, time, and cinema room
    public Show findShowByDateTimeAndRoom(LocalDate showDate, LocalTime showTime, CinemaRoom cinemaRoom) {
        List<Show> shows = showDAO.findShowByDateTimeAndRoom(showDate, cinemaRoom);
        for (Show show : shows) {
            if (show.getShowTime().contains(showTime)) {
                return show;
            }
        }
        return null;
    }

    // Returns a list of shows from a film id
    public List<Show> getAllShowsByFilmId(long filmId) {
        Film film = filmService.getFilmById(filmId);
        return this.showDAO.findAllByFilm(film);
    }

    // Returns a list of shows from the city and film id
    public List<Show> getShowtimesByCityAndFilm(String city, long filmId) {

        List<Show> allShowtimes = showDAO.findAll();


        List<Show> showtimesByCityAndFilm = allShowtimes.stream()
                .filter(showtime -> showtime.getCinemaRoom().getCinema().getCity().equals(city) && showtime.getFilm().getId() == (filmId))
                .collect(Collectors.toList());

        return showtimesByCityAndFilm;
    }

    // Returns a list of shows by city, date and movie id
    public List<Show> getShowByCityDateAndFilm(String city, LocalDate date, long filmId) {

        List<Show> allShows  = showDAO.findAll();

        List<Show> showsByCityDateAndFilm = allShows.stream()
                .filter(show -> show.getCinemaRoom().getCinema().getCity().equals(city)
                        && show.getShowDate().equals(date)
                        && show.getFilm().getId() == filmId)
                .collect(Collectors.toList());

        return showsByCityDateAndFilm;
    }
}

