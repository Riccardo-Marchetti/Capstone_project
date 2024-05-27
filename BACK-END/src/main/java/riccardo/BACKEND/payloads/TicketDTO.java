package riccardo.BACKEND.payloads;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record TicketDTO(@NotEmpty(message = "Assigned seats is mandatory")
                        String[] assignedSeats,
                        @NotNull(message = "Price is mandatory")
                        @Min(0)
                        double price,
                        @NotEmpty(message = "Selected showtime is mandatory")
                        String selectedShowTime,
                        @NotNull(message = "Id show is mandatory")
                        @Min(1)
                        long idShow,
                        @NotNull(message = "Id seat is mandatory")
                        List<Long> idSeat) {
}
