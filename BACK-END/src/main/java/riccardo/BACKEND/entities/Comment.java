package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    // ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;
    private String description;
    private double rating;
    private LocalDate commentDay;
    private LocalDateTime commentTime;
    @ManyToOne
    @JoinColumn (name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    // CONSTRUCTOR
    public Comment(String description, double rating,  Film film, User user) {
        this.description = description;
        this.rating = rating;
        this.commentDay = LocalDate.now();
        this.commentTime = LocalDateTime.now();
        this.film = film;
        this.user = user;
    }
}
