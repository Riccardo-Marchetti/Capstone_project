package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;
    private String assignedSeats;
    private double price;
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "show_id")
    private Show show;

    @OneToMany (mappedBy = "ticket")
    private List<Seat> seat;

    public Ticket(String assignedSeats, double price, User user, Show show, List<Seat> seat) {
        this.assignedSeats = assignedSeats;
        this.price = price;
        this.bookingDate = LocalDate.now();
        this.user = user;
        this.show = show;
        this.seat = seat;
    }
}
