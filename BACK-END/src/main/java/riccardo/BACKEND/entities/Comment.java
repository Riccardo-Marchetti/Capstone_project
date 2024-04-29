package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private int rating;

    @ManyToOne
    @JoinColumn (name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
}
