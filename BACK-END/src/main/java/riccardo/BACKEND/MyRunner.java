package riccardo.BACKEND;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import riccardo.BACKEND.entities.*;
import riccardo.BACKEND.enums.FilmType;
import riccardo.BACKEND.enums.SeatType;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.CinemaRoomDTO;
import riccardo.BACKEND.payloads.SeatDTO;
import riccardo.BACKEND.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Value("${admin.name}")
    private String name;

    @Value("${admin.surname}")
    private String surname;
    @Value("${admin.username}")
    private String username;
    @Value("${admin.email}")
    private String email;
    @Value("${admin.password}")
    private String password;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private FilmService filmService;
    @Autowired
    private CinemaRoomService cinemaRoomService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user = this.userService.existsByUsername(name, surname, username, email, password);

//      Creation shows
//      Recover the films and cinema rooms

//        Page<Film> films = filmService.getAllFilms(20, 20 , "type");
//        Page<CinemaRoom> cinemaRoomsList = cinemaRoomService.getAllRoom(20, 20 , "type");
//
//        Show show = new Show();



//     Creation of cinema
        if (cinemaService.findByNameAndCity("Dream Cinema", "Bergamo").isEmpty()){
        Cinema cinema1 = new Cinema("Dream Cinema", "Bergamo", "Via Arena");
        cinema1 = cinemaService.saveCinema(cinema1);
        }
        if (cinemaService.findByNameAndCity("Dream Cinema", "Milano").isEmpty()){
        Cinema cinema2 = new Cinema("Dream Cinema", "Milano", "Via Manzoni");
        cinema2 = cinemaService.saveCinema(cinema2);

        }
        if (cinemaService.findByNameAndCity("Dream Cinema", "Torino").isEmpty()){
        Cinema cinema3 = new Cinema("Dream Cinema", "Torino", "Via San Donato");
        cinema3 = cinemaService.saveCinema(cinema3);
        }


        // Creation of the rooms for each cinema
        List<Cinema> cinemas = cinemaService.findAll();
        for (Cinema cinema : cinemas) {
            if (cinema.getCinemaRoom() == null || cinema.getCinemaRoom().isEmpty()){
            List<CinemaRoom> cinemaRooms = createCinemaRooms(cinema);
            cinema.setCinemaRoom(cinemaRooms);
            cinemaService.saveCinema(cinema); // Updating the cinema with the associated rooms
            }
        }

        if (seatService.findAll().isEmpty()){
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            Seat seat = new Seat(SeatType.CLASSIC);
            seat = seatService.saveSeat(seat); // Saving the seat
            seats.add(seat);
        }
        for (int i = 0; i < 16; i++) {
            Seat seat = new Seat(SeatType.PREMIUM);
            seat = seatService.saveSeat(seat); // Saving the seat
            seats.add(seat);
        }
        for (int i = 0; i < 16; i++) {
            Seat seat = new Seat(SeatType.CLASSIC);
            seat = seatService.saveSeat(seat); // Saving the seat
            seats.add(seat);
        }
        }
    }

    private List<CinemaRoom> createCinemaRooms(Cinema cinema) {
        List<CinemaRoom> cinemaRooms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CinemaRoom cinemaRoom = new CinemaRoom(64, cinema);
            cinemaRoom = cinemaRoomService.saveCinemaRoom(cinemaRoom); // Saving the room

            cinemaRoomService.saveCinemaRoom(cinemaRoom); // Updating the room with the associated seats

            cinemaRooms.add(cinemaRoom);
        }
        return cinemaRooms;
    }

}
