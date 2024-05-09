package riccardo.BACKEND;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.entities.CinemaRoom;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.enums.SeatType;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.CinemaRoomDTO;
import riccardo.BACKEND.payloads.SeatDTO;
import riccardo.BACKEND.services.CinemaRoomService;
import riccardo.BACKEND.services.CinemaService;
import riccardo.BACKEND.services.SeatService;
import riccardo.BACKEND.services.UserService;

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
    private CinemaRoomService cinemaRoomService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user = this.userService.existsByUsername(name, surname, username, email, password);

//     Creation of cinema
        if (cinemaService.findByNameAndCity("Smile Cinema", "Bergamo").isEmpty()){
        Cinema cinema1 = new Cinema("Smile Cinema", "Bergamo", "Via Arena");
        cinema1 = cinemaService.saveCinema(cinema1);
        }
        if (cinemaService.findByNameAndCity("Smile Cinema", "Milano").isEmpty()){
        Cinema cinema2 = new Cinema("Smile Cinema", "Milano", "Via Manzoni");
        cinema2 = cinemaService.saveCinema(cinema2);

        }
        if (cinemaService.findByNameAndCity("Smile Cinema", "Torino").isEmpty()){
        Cinema cinema3 = new Cinema("Smile Cinema", "Torino", "Via San Donato");
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
