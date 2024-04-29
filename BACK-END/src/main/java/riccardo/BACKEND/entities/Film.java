package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Type;
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
    private long id;

    private String title;
    private String regista;

    @Enumerated (EnumType.STRING)
    private Type type;

    private int duration;
    private int rating;
    private String description;
    private LocalDate exitDate;
    private String trailer;

    @OneToMany (mappedBy = "film")
    private List<Comment> comment;

    @OneToMany (mappedBy = "film")
    private List<Show> shows;
}
