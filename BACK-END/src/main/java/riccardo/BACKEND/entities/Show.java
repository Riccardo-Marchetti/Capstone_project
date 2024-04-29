package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Show {

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate showDate;
    private List<LocalTime> showTime;

    @ManyToOne
    @JoinColumn (name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn (name = "cinemaRoom_id")
    private CinemaRoom cinemaRoom;

    @OneToMany (mappedBy = "show")
    private List<Ticket> ticket;
}
