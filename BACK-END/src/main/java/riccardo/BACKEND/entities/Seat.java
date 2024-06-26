package riccardo.BACKEND.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import riccardo.BACKEND.enums.SeatType;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seat {

    // ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;
    @Enumerated (EnumType.STRING)
    private SeatType type;
    private double price;

    @ManyToMany(mappedBy = "seat")
    @JsonIgnore
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn (name = "cinemaRoom_id")
    private CinemaRoom cinemaRoom;

    // CONSTRUCTOR
    public Seat(SeatType type,CinemaRoom cinemaRoom) {
        this.type = type;

        this.cinemaRoom = cinemaRoom;
    }

    public Seat(SeatType type, double price) {
        this.type = type;
        this.price = price;
    }

    public Seat(SeatType type) {
        this.type = type;

    }

}
