package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.repositories.CinemaDAO;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaDAO cinemaDAO;

    @Autowired
    private CinemaRoomService cinemaRoomService;

    public List<Cinema> getAllCinema(){
        return this.cinemaDAO.findAll();
    }

    public Cinema getCinemaById (long id){
        return this.cinemaDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Cinema saveCinema (CinemaDTO payload){
        Cinema cinema = new Cinema(payload.name(), payload.city(), payload.address());
        return this.cinemaDAO.save(cinema);
    }
    public Cinema updateCinema (long id, CinemaDTO payload){
        Cinema cinema = this.cinemaDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        cinema.setName(payload.name());
        cinema.setCity(payload.city());
        cinema.setAddress(payload.address());
        cinema.setCinemaRoom(cinemaRoomService.getCinemaRoomsByIds(payload.idCinemaRoom()));
        return this.cinemaDAO.save(cinema);
    }
    public void deleteCinema (long id){
        Cinema cinema = this.cinemaDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.cinemaDAO.delete(cinema);
    }
}
