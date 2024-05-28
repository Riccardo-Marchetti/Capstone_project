package riccardo.BACKEND.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import riccardo.BACKEND.enums.SeatType;

import java.time.LocalDate;

// This class is a Data Transfer Object (DTO) for Seat
public record SeatDTO(@NotNull(message = "Type is mandatory")
                      @Pattern(regexp = "CLASSIC|PREMIUM", message = "Invalid seat type use CLASSIC or PREMIUM")
                      SeatType type
                      ) {
}
