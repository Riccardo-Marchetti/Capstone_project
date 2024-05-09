package riccardo.BACKEND.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Setter (AccessLevel.NONE)
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
    @JsonIgnore
    private List<Ticket> ticket;

    public Show(LocalDate showDate, List<LocalTime> showTime, Film film, CinemaRoom cinemaRoom, List<Ticket> ticket) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.film = film;
        this.cinemaRoom = cinemaRoom;
        this.ticket = ticket;
    }
    public Show(LocalDate showDate, List<LocalTime> showTime, Film film, CinemaRoom cinemaRoom) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.film = film;
        this.cinemaRoom = cinemaRoom;
    }
}
