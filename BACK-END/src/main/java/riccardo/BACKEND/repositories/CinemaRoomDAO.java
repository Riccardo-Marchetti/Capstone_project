package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.CinemaRoom;

@Repository
public interface CinemaRoomDAO extends JpaRepository<CinemaRoom, Long> {
}
