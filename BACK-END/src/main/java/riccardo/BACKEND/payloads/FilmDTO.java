package riccardo.BACKEND.payloads;

import riccardo.BACKEND.enums.FilmType;

import java.time.LocalDate;

public record FilmDTO(String title,

                      String regista,

                      FilmType type,

                      int duration,

                      int rating,

                      String description,

                      LocalDate exitDate,

                      String trailer) {
}
