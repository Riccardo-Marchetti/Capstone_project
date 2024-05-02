package riccardo.BACKEND.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CinemaDTO(@NotEmpty(message = "Name is mandatory")
                        String name,
                        @NotEmpty(message = "City is mandatory")
                        String city,
                        @NotEmpty(message = "Address is mandatory")
                        String address,
                        @NotNull(message = "Id cinema room is mandatory")
                        @Min(1)
                        List<Long> idCinemaRoom) {
}
