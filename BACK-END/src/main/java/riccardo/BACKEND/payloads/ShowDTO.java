package riccardo.BACKEND.payloads;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ShowDTO(LocalDate showDate,

                      List<LocalTime> showTime,

                      long idFilm,

                      long idCinemaRoom,

                      List<Long> idTicket) {
}
