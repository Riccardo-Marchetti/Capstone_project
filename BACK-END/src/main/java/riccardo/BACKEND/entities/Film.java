package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;
import riccardo.BACKEND.enums.FilmType;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Film {

    // ATTRIBUTI
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;

    private String title;
    private String director;

    @Enumerated (EnumType.STRING)
    private FilmType type;

    private int duration;
    private int rating;
    private String description;
    private LocalDate exitDate;
    private String trailer;

    @OneToMany (mappedBy = "film")
    private List<Comment> comment;

    @OneToMany (mappedBy = "film")
    private List<Show> shows;

    public Film(String title, String director, FilmType type, int duration, int rating, String description, LocalDate exitDate, String trailer) {
        this.title = title;
        this.director = director;
        this.type = type;
        this.duration = duration;
        this.rating = rating;
        this.description = description;
        this.exitDate = exitDate;
        this.trailer = trailer;
    }
}
