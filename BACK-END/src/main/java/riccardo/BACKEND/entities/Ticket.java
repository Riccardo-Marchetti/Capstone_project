package riccardo.BACKEND.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {

    // ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;
    private double price;
    private boolean booked;
    private String selectedShowTime;
    private LocalDate bookingDate;
    private String[] assignedSeats;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "show_id")
    private Show show;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "ticket_seat",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seat;

    // CONSTRUCTOR
    public Ticket( double price, User user, Show show, List<Seat> seat) {
        this.price = price;
        this.bookingDate = LocalDate.now();
        this.user = user;
        this.show = show;
        this.seat = seat;
    }

    public Ticket(double price, String selectedShowTime, String[] assignedSeats, User user, Show show, List<Seat> seat) {
        this.price = price;
        this.selectedShowTime = selectedShowTime;
        this.booked = true;
        this.bookingDate = LocalDate.now();
        this.assignedSeats = assignedSeats;
        this.user = user;
        this.show = show;
        this.seat = seat;
    }
}
