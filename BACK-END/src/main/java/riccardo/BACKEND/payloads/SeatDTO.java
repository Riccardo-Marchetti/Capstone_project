package riccardo.BACKEND.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import riccardo.BACKEND.enums.SeatType;

import java.time.LocalDate;

public record SeatDTO(@NotNull(message = "Type is mandatory")
                      SeatType type
//                      @NotNull(message = "Booked is mandatory")
//                      boolean booked,
//                      @FutureOrPresent(message = "The booking date must be today or in the future.")
//                      @NotNull(message = "Booking date is mandatory")
//                      LocalDate bookingDate
                      ) {
}
