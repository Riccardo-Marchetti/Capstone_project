package riccardo.BACKEND.payloads;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// This class is a Data Transfer Object (DTO) for Comment
public record CommentDTO(@NotEmpty(message = "Description is mandatory")
                         String description,
                         @NotNull(message = "Rating is mandatory")
                         @Min(0)
                         @Max(5)
                         double rating,
                         @NotNull(message = "Id film is mandatory")
                         @Min(0)
                         long idFilm,
                         @NotNull(message = "Id user is mandatory")
                         @Min(0)
                         long idUser
                         ) {
}
