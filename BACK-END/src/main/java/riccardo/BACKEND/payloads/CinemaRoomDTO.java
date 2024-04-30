package riccardo.BACKEND.payloads;


import java.util.List;

public record CinemaRoomDTO(int totalSeat,

                            long idCinema,

                            List<Long> idSeat) {
}
