package riccardo.BACKEND.payloads;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record TicketDTO(
                        @NotNull(message = "Price is mandatory")
                        @Min(0)
                        double price,
                        @FutureOrPresent (message = "The booking date must be today or in the future.")
                        @NotNull(message = "Booking date is mandatory")
                        LocalDate bookingDate,
                        @NotNull(message = "Id user is mandatory")
                        @Min(1)
                        long idUser,
                        @NotNull(message = "Id show is mandatory")
                        @Min(1)
                        long idShow,
                        @NotNull(message = "Id seat is mandatory")
                        List<Long> idSeat) {
}
