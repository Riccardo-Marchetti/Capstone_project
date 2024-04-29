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
    @Setter (AccessLevel.NONE)
    private long id;
    private String description;
    private int rating;

    @ManyToOne
    @JoinColumn (name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Comment(String description, int rating, Film film, User user) {
        this.description = description;
        this.rating = rating;
        this.film = film;
        this.user = user;
    }
}
