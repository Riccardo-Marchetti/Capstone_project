package riccardo.BACKEND.payloads;

import riccardo.BACKEND.enums.SeatType;

import java.time.LocalDate;

public record SeatDTO(SeatType type,

                      boolean booked,

                      LocalDate bookingDate

                      ) {
}
