package riccardo.BACKEND.payloads;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CommentDTO(@NotEmpty(message = "Description is mandatory")
                         String description,
                         @NotNull(message = "Rating is mandatory")
                         @Min(0)
                         @Max(5)
                         int rating,
                         @NotNull(message = "Id film is mandatory")
                         @Min(1)
                         long idFilm,
                         @NotNull(message = "Id user is mandatory")
                         @Min(1)
                         long idUser
                         ) {
}
