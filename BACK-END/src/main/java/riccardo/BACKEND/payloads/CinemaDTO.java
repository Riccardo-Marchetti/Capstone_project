package riccardo.BACKEND.payloads;

import riccardo.BACKEND.entities.CinemaRoom;

import java.util.List;

public record CinemaDTO(String name,

                        String city,

                        String address,

                        List<Long> idCinemaRoom) {
}
