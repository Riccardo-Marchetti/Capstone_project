package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // Returns a paginated list of all cinemas
    public Page<Cinema> getAllCinema(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.cinemaDAO.findAll(pageable);
    }

    // Returns all cinemas
    public List<Cinema> findAll() {
        return this.cinemaDAO.findAll();
    }

    // Returns a cinema by its id
    public Cinema getCinemaById (long id){
        return this.cinemaDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // Saves a new cinema to the database
    public Cinema saveCinema (CinemaDTO payload){
        Cinema cinema = new Cinema(payload.name(), payload.city(), payload.address());
        return this.cinemaDAO.save(cinema);
    }

    // Saves a cinema to the database
    public Cinema saveCinema (Cinema cinema){
        Cinema newCinema = new Cinema(cinema.getName(), cinema.getCity(), cinema.getAddress());
        return this.cinemaDAO.save(cinema);
    }

    // Updates a cinema in the database
    public Cinema updateCinema (long id, CinemaDTO payload){
        Cinema cinema = this.cinemaDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        cinema.setName(payload.name());
        cinema.setCity(payload.city());
        cinema.setAddress(payload.address());
        cinema.setCinemaRoom(cinemaRoomService.getCinemaRoomsByIds(payload.idCinemaRoom()));
        return this.cinemaDAO.save(cinema);
    }

    // Deletes a cinema from the database
    public void deleteCinema (long id){
        Cinema cinema = this.cinemaDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.cinemaDAO.delete(cinema);
    }

    // Returns a list of cinemas by their name and city
    public List<Cinema> findByNameAndCity (String name, String city){
       return this.cinemaDAO.findByNameAndCity(name, city);
    }
}
