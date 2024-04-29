package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Show;

@Repository
public interface ShowDAO extends JpaRepository<Show, Long> {
}
