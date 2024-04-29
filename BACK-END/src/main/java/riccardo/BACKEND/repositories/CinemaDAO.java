package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Cinema;

@Repository
public interface CinemaDAO extends JpaRepository<Cinema, Long> {

}
