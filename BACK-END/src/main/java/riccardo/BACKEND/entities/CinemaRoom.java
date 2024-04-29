package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CinemaRoom {

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @ManyToOne
    @JoinColumn (name = "cinema_id")
    private Cinema cinema;

    @OneToMany (mappedBy = "cinemaRoom")
    private List<Seat> seat;
}
