package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.entities.Show;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.ShowDTO;
import riccardo.BACKEND.repositories.ShowDAO;

import java.util.List;

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

    public Page<Show> getAllShows(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.showDAO.findAll(pageable);
    }

    public Show getShowById (long id){
        return this.showDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Show saveShow (ShowDTO payload){
        Show show = new Show(payload.showDate(), payload.showTime(), filmService.getFilmById(payload.idFilm()), cinemaRoomService.getCinemaRoomById(payload.idCinemaRoom()));
        return this.showDAO.save(show);
    }
    public Show updateShow (long id, ShowDTO payload){
        Show show = this.showDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        show.setShowDate(payload.showDate());
        show.setShowTime(payload.showTime());
        show.setFilm(filmService.getFilmById(payload.idFilm()));
        show.setCinemaRoom(cinemaRoomService.getCinemaRoomById(payload.idCinemaRoom()));
        show.setTicket(ticketService.getTicketsByIds(payload.idTicket()));
        return this.showDAO.save(show);
    }
    public void deleteShow (long id){
        Show show = this.showDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.showDAO.delete(show);
    }
}
