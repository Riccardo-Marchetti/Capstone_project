package riccardo.BACKEND.payloads;


import java.time.LocalDate;
import java.util.List;

public record TicketDTO(String assignedSeats,

                        double price,

                        LocalDate bookingDate,

                        long idUser,

                        long idShow,

                        List<Long> idSeat) {
}
