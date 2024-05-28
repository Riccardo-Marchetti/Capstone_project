package riccardo.BACKEND;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import riccardo.BACKEND.entities.*;
import riccardo.BACKEND.enums.FilmState;
import riccardo.BACKEND.enums.FilmType;
import riccardo.BACKEND.enums.SeatType;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.CinemaRoomDTO;
import riccardo.BACKEND.payloads.SeatDTO;
import riccardo.BACKEND.services.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private ShowService showService;
    @Autowired
    private CinemaRoomService cinemaRoomService;

@Override
@Transactional
public void run(String... args) throws Exception {
    User user = this.userService.existsByUsername(name, surname, username, email, password);

    // Creation of cinema
    createCinemas();

    // Creation of the rooms for each cinema
    createRoomsForCinemas();

    // Creation of seats
    createSeats();

    // Creation of films
    createFilms();

    // Creation of shows
    generateShows();
}

    private void createCinemas() {
        if (cinemaService.findByNameAndCity("Dream Cinema", "Bergamo").isEmpty()) {
            Cinema cinema1 = new Cinema("Dream Cinema", "Bergamo", "Via Arena");
            cinema1 = cinemaService.saveCinema(cinema1);
        }
        if (cinemaService.findByNameAndCity("Dream Cinema", "Milano").isEmpty()) {
            Cinema cinema2 = new Cinema("Dream Cinema", "Milano", "Via Manzoni");
            cinema2 = cinemaService.saveCinema(cinema2);
        }
        if (cinemaService.findByNameAndCity("Dream Cinema", "Torino").isEmpty()) {
            Cinema cinema3 = new Cinema("Dream Cinema", "Torino", "Via San Donato");
            cinema3 = cinemaService.saveCinema(cinema3);
        }
    }

    private void createRoomsForCinemas() {
        List<Cinema> cinemas = cinemaService.findAll();
        for (Cinema cinema : cinemas) {
            if (cinema.getCinemaRoom() == null || cinema.getCinemaRoom().isEmpty()) {
                List<CinemaRoom> cinemaRooms = createCinemaRooms(cinema);
                cinema.setCinemaRoom(cinemaRooms);
                cinemaService.saveCinema(cinema); // Updating the cinema with the associated rooms
            }
        }
    }

    private void createSeats() {
        if (seatService.findAll().isEmpty()) {
            List<Seat> seats = new ArrayList<>();
            for (int i = 0; i < 32; i++) {
                Seat seat = new Seat(SeatType.CLASSIC, 10);
                seat = seatService.saveSeat(seat); // Saving the seat
                seats.add(seat);
            }
            for (int i = 0; i < 16; i++) {
                Seat seat = new Seat(SeatType.PREMIUM, 20);
                seat = seatService.saveSeat(seat); // Saving the seat
                seats.add(seat);
            }
            for (int i = 0; i < 16; i++) {
                Seat seat = new Seat(SeatType.CLASSIC, 10);
                seat = seatService.saveSeat(seat); // Saving the seat
                seats.add(seat);
            }
        }
    }

    private List<CinemaRoom> createCinemaRooms(Cinema cinema) {
        List<CinemaRoom> cinemaRooms = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            CinemaRoom cinemaRoom = new CinemaRoom(64, cinema);
            cinemaRoom = cinemaRoomService.saveCinemaRoom(cinemaRoom); // Saving the room

            cinemaRoomService.saveCinemaRoom(cinemaRoom); // Updating the room with the associated seats

            cinemaRooms.add(cinemaRoom);
        }
        return cinemaRooms;
    }
    private void createFilms() {
        List<Film> films = Arrays.asList(
                new Film("I soliti idioti 3 - Il ritorno", "Francesco Mandelli, Fabrizio Biggio, Ferruccio Martini", FilmType.COMEDY, "100", 1.5, "I Soliti Idioti tornano al cinema dieci anni dopo il loro ultimo film e molte cose sono cambiate nel mondo. Cosa è successo in questi dieci anni?", LocalDate.of(2024, 1, 25), "https://www.youtube.com/embed/Dwh0HM2TaKI?si=1C0XjLEkHNObEVuR", "https://pad.mymovies.it/filmclub/2023/09/254/locandinapg1.jpg", FilmState.INTHEATERS),
                new Film("Madame Web", "S. J. Clarkson", FilmType.SCIENCE_FICTION, "116", 0.0, "Cassandra Webb è una paramedica di New York che inizia a mostrare doti di chiaroveggenza, che le consentono di vedere il mondo dal punto di vista degli aracnidi. Presto, sitrova a dover proteggere tre giovani donne da un misterioso avversario.", LocalDate.of(2024, 2, 14), "https://www.youtube.com/embed/feMjjCWrQI4?si=hD486ec9vMt9glOR", "https://pad.mymovies.it/filmclub/2022/02/092/locandina.jpg", FilmState.INTHEATERS),
                new Film("Dune - Parte due", "Denis Villeneuve", FilmType.ACTION, "166", 3.3, "Paul Atreides si raduna dietro Chani e i Fremen mentre trama la sua vendetta contro coloro che hanno distrutto la sua famiglia. Deve fare di tutto per prevenire un terribile futuro che solo lui può prevedere.", LocalDate.of(2024, 2, 28), "https://www.youtube.com/embed/RD0-b7VO8F0?si=NPy7mYfNomxCz0_P", "https://pad.mymovies.it/filmclub/2021/10/212/locandinapg2.jpg", FilmState.INTHEATERS),
                new Film("Un altro ferragosto", "Paolo Virzì", FilmType.COMEDY, "115", 3.5, "Il seguito dell'acclamato Ferie d'Agosto del 1996, film cult che valse a Paolo Virzì il David di Donatello come Miglior Film.", LocalDate.of(2024, 3, 7), "https://www.youtube.com/embed/THXqUd-ndNQ?si=0YDwOYgSuj9-f24J", "https://pad.mymovies.it/filmclub/2023/04/231/locandina.jpg", FilmState.INTHEATERS),
                new Film("Imaginary", "Jeff Wadlow", FilmType.HORROR, "104", 2.5, "Una donna torna nella casa della sua infanzia per scoprire che l'amico immaginario che ha lasciato è molto reale ed è infelice per averlo abbandonato.", LocalDate.of(2024, 3, 14), "https://www.youtube.com/embed/CpSF-V7Cf-k?si=Gpa4RP5dkv1UGgI4", "https://pad.mymovies.it/filmclub/2023/11/210/locandina.jpg", FilmState.INTHEATERS),
                new Film("Un mondo a parte", "Riccardo Milani", FilmType.COMEDY, "113", 3.0, "Cercare in tutti i modi di salvare una scuola speciale. Antonio Albanese torna protagonista di una commedia diretta da Riccardo Milani.", LocalDate.of(2024, 3, 28), "https://www.youtube.com/embed/P5hgM2OcQh0?si=YiHN1Ijw3iJm8MOt", "https://pad.mymovies.it/filmclub/2023/04/249/locandina.jpg", FilmState.INTHEATERS),
                new Film("Godzilla e Kong - Il nuovo impero", "Adam Wingard", FilmType.ACTION, "113", 2.5, "Il trentesimo appuntamento con il mostro Godzilla, prodotto dalla Toho Company. L'avventura questa volta è nella Terra Cava, dove qualcosa si sta risvegliando.", LocalDate.of(2024, 3, 28), "https://www.youtube.com/embed/Ea2sVwOpO9Y?si=aRqGLalcjuRmyqa-", "https://pad.mymovies.it/filmclub/2022/11/017/locandina.jpg", FilmState.INTHEATERS),
                new Film("Omen - L'origine del presagio", "Arkasha Stevenson", FilmType.HORROR, "120", 3.0, "Una giovane donna americana parte per Roma per dare inizio a una nuova vita a servizio della Chiesa. La donna scopre però una terribile cospirazione interna, volta a far rinascere il male incarnato, che fa vacillare la sua fede.", LocalDate.of(2024, 4, 4), "https://www.youtube.com/embed/f8YTuMJgvKE?si=FwonoU-AEndl7UOY", "https://pad.mymovies.it/filmclub/2023/12/041/locandinapg3.jpg", FilmState.INTHEATERS),
                new Film("Ghostbusters - Minaccia glaciale", "Gil Kenan", FilmType.SCIENCE_FICTION, "115", 3.0, "La famiglia Spengler torna dove tutto ha avuto inizio, l'iconica caserma dei pompieri sede dei Ghostbusters a New York, per collaborare con gli acchiappafantasmi originali e salvare il mondo da una glaciazione.", LocalDate.of(2024, 4, 11), "https://www.youtube.com/embed/AKJEJCPD-3E?si=AaIkLRLL27m8tAmM", "https://www.ucicinemas.it/media/movie/o/2024/ghostbusters-minaccia-glaciale_kt7bSM8.png", FilmState.COMINGSOON),
                new Film("Gloria!", "Margherita Vicario", FilmType.DRAMATIC, "100", 3.5, "Il film racconta la storia di Teresa, una giovane dal talento visionario, inventa una musica ribelle, leggera e moderna.", LocalDate.of(2024, 4, 11), "https://www.youtube.com/embed/dCRFgY9haUA?si=RFSaKT-4Kvi1c31g", "https://pad.mymovies.it/filmclub/2023/06/225/locandina.jpg", FilmState.COMINGSOON),
                new Film("Challengers", "Luca Guadagnino", FilmType.DRAMATIC, "131", 3.5, "Luca Guadagnino dirige un film ambientato nel mondo del tennis ma incentrato sulla rivalità (anche amorosa) tra tre ragazzi.", LocalDate.of(2024, 4, 24), "https://www.youtube.com/embed/urW8aPwEBN0?si=d2IbW1cyK_pZUOkk", "https://www.warnerbros.it/wp-content/uploads/2023/06/Challengers_Poster-Italia-1.jpg", FilmState.COMINGSOON),
                new Film("The Fall Guy", "David Leitch", FilmType.ACTION, "126", 3.5, "La storia di uno stuntman che si ritrova a indagare sul caso di una persona scomparsa.", LocalDate.of(2024, 5, 1), "https://www.youtube.com/embed/vpyoBxGl5oc?si=Ed1EA5pUNqWVVSqY", "https://pad.mymovies.it/filmclub/2023/07/062/locandinapg1.jpg", FilmState.COMINGSOON),
                new Film("Il regno del pianeta delle scimmie", "Wes Ball", FilmType.ACTION, "145", 2.5, "Un nuovo capitolo della saga de Il pianeta delle scimmie, che riprenderà molti anni dopo la conclusione del film del 2017 The War - Il pianeta delle scimmie.", LocalDate.of(2024, 5, 8), "https://www.youtube.com/embed/YbjiQrLkRSw?si=oIoLe5DCXxTOY6Pi", "https://pad.mymovies.it/filmclub/2022/09/259/locandina.jpg", FilmState.COMINGSOON),
                new Film("Mothers' Instinct", "Benoît Delhomme", FilmType.DRAMATIC, "94", 3.5, "Jessica Chastain e Anne Hathaway al centro di un intenso thriller psicologico di influenza hitchcockiana.", LocalDate.of(2024, 5, 9), "https://www.youtube.com/embed/_8gFZ_uRFfQ?si=tjvzGpWoZfwvR9UZ", "https://www.multisalacorso.com/wp-content/uploads/2024/04/MOTHER-INSTINCT.jpg", FilmState.COMINGSOON),
                new Film("La profezia del male", "Spenser Cohen e Anna Halberg", FilmType.HORROR, "92", 2.5, "Quando un gruppo di amici viola incautamente la sacra regola della lettura dei Tarocchi, scatenano inconsapevolmente un male indicibile intrappolato nelle carte maledette.", LocalDate.of(2024, 5, 9), "https://www.youtube.com/embed/h3yfT8VSnsg?si=sJFN9Wi9nZXQubEA", "https://pad.mymovies.it/filmclub/2024/01/370/locandina.jpg", FilmState.COMINGSOON),
                new Film("Furiosa: A Mad Max Saga", "George Miller", FilmType.SCIENCE_FICTION, "148", 4.0, "Racconta l'infanzia e la giovinezza di una bambina nata in un'oasi di pace e di donne, rapita dal cattivo di turno e finita nel clan di un tiranno rock che ha l'età delle sue articolazioni.", LocalDate.of(2024, 5, 23), "https://www.youtube.com/embed/y3A4WfztOTA?si=R6Np8OG7vgwAoxEd", "https://i0.wp.com/www.cinemaaquila.it/images/2024/05/Furiosa-A-Mad-Max-Saga_Poster-Italia-7.jpg?fit=600%2C750&ssl=1", FilmState.COMINGSOON)
        );

        for (Film film : films) {
            if (filmService.findFilmByTitleFragment(film.getTitle()).isEmpty()) {
                filmService.saveFilm(film);
            }
        }
    }
    private void generateShows() {
        if (showService.getAllShows().isEmpty()) {
            //  Creation shows
            //  Recover the films and cinema rooms
            List<Film> films = filmService.findByFilmState(FilmState.INTHEATERS);
            Page<CinemaRoom> cinemaRoomsList = cinemaRoomService.getAllRoom(0, 20, "totalSeat");

            List<LocalTime> showTimes = new ArrayList<>();
            showTimes.add(LocalTime.of(13, 30));
            showTimes.add(LocalTime.of(15, 0));
            showTimes.add(LocalTime.of(17, 0));
            showTimes.add(LocalTime.of(21, 0));

            for (Film film : films) {
                for (int i = 0; i < 7; i++) {
                    LocalDate showDate = LocalDate.now().plusDays(i);

                    for (LocalTime showTime : showTimes) {
                        // Get a list of all cinema rooms not already showing this film at this time
                        List<CinemaRoom> availableRooms = cinemaRoomsList.stream()
                                .filter(room -> showService.findShowByDateTimeAndRoom(showDate, showTime, room) == null)
                                .collect(Collectors.toList());

                        if (!availableRooms.isEmpty()) {
                            // Shuffle the list of available rooms to ensure randomness
                            Collections.shuffle(availableRooms);
                            // Choose a room from the list of available rooms
                            CinemaRoom cinemaRoom = availableRooms.get(0);

                            // Create and save the new show
                            Show show = new Show(showDate, Arrays.asList(showTime), film, cinemaRoom);
                            showService.saveShow(show);
                        }
                    }
                }
            }
        }
    }
}
