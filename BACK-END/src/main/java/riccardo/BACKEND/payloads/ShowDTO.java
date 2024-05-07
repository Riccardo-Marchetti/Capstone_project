package riccardo.BACKEND.payloads;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ShowDTO(@FutureOrPresent(message = "The Show date must be today or in the future.")
                      @NotNull(message = "Show date is mandatory")
                      LocalDate showDate,
                      @NotNull(message = "Show time is mandatory")
                      List<LocalTime> showTime,
                      @NotNull(message = "Id film is mandatory")
                      @Min(1)
                      long idFilm,
                      @NotNull(message = "Id cinema room is mandatory")
                      @Min(1)
                      long idCinemaRoom,
                      @NotNull(message = "Id ticket is mandatory")
                      List<Long> idTicket) {
}
