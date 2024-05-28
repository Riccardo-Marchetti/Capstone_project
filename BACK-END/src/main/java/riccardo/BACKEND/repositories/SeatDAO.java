package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Seat;

import java.util.List;

// This interface is a repository for Seat entities
@Repository
public interface SeatDAO extends JpaRepository<Seat, Long> {
}
