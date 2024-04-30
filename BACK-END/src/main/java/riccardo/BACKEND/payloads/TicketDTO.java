package riccardo.BACKEND.payloads;

import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.entities.Show;
import riccardo.BACKEND.entities.User;

import java.time.LocalDate;
import java.util.List;

public record TicketDTO(String assignedSeats,

                        double price,

                        LocalDate bookingDate,

                        User idUser,

                        Show idShow,

                        List<Seat> seat) {
}
