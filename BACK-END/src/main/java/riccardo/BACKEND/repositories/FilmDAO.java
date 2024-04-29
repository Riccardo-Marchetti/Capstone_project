package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Film;

@Repository
public interface FilmDAO extends JpaRepository<Film, Long> {
}
