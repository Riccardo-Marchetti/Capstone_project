package riccardo.BACKEND.payloads;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import riccardo.BACKEND.enums.FilmType;

import java.time.LocalDate;

public record FilmDTO(@NotEmpty(message = "Title is mandatory")
                      String title,
                      @NotEmpty(message = "Director is mandatory")
                      String director,
                      @NotNull(message = "Type is mandatory")
                      FilmType type,
                      @NotNull(message = "Duration is mandatory")
                      String duration,
                      @NotNull(message = "Id rating is mandatory")
                      @Min(1)
                      int rating,
                      @NotEmpty(message = "Description is mandatory")
                      String description,

                      @NotNull(message = "Exit date is mandatory")
                      LocalDate exitDate,
                      @NotNull(message = "Trailer is mandatory")
                      @URL(message = "Invalid URL")
                      String trailer,
                      @NotNull(message = "Cover is mandatory" )
                      @URL(message = "Invalid URL")
                      String cover
) {
}
