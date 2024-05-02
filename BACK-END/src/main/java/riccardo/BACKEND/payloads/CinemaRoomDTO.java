package riccardo.BACKEND.payloads;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CinemaRoomDTO(@NotNull(message = "Total seat is mandatory")
                            @Min(1)
                            @Max(64)
                            int totalSeat,
                            @NotNull(message = "Id cinema is mandatory")
                            @Min(1)
                            long idCinema,
                            @NotNull(message = "Id seat is mandatory")
                            @Min(1)
                            List<Long> idSeat) {
}
