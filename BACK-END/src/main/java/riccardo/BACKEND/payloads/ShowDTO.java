package riccardo.BACKEND.payloads;

import riccardo.BACKEND.entities.CinemaRoom;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ShowDTO(LocalDate showDate,

                      List<LocalTime> showTime,

                      Film idFilm,

                      CinemaRoom idCinemaRoom,

                      List<Ticket> idTicket) {
}
