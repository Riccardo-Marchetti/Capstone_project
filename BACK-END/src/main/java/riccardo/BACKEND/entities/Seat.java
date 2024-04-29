package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;
import riccardo.BACKEND.enums.SeatType;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seat {

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated (EnumType.STRING)
    private SeatType type;
    private boolean booked;
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn (name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn (name = "cinemaRoom_id")
    private CinemaRoom cinemaRoom;
}
