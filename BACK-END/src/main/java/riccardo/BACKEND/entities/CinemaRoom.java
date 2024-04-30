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
    @Setter (AccessLevel.NONE)
    private long number;

    private int totalSeat;
    @ManyToOne
    @JoinColumn (name = "cinema_id")
    private Cinema cinema;

    @OneToMany (mappedBy = "cinemaRoom")
    private List<Seat> seat;

    public CinemaRoom(int totalSeat, Cinema cinema) {
        this.totalSeat = totalSeat;
        this.cinema = cinema;
    }

    public CinemaRoom(int totalSeat, Cinema cinema, List<Seat> seat) {
        this.totalSeat = totalSeat;
        this.cinema = cinema;
        this.seat = seat;
    }
}
