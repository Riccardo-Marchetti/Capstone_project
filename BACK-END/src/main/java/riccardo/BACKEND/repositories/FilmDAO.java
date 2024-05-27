package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.enums.FilmState;
import riccardo.BACKEND.enums.FilmType;
import riccardo.BACKEND.enums.SeatType;

import java.util.List;

@Repository
public interface FilmDAO extends JpaRepository<Film, Long> {
    List<Film> findByType (FilmType type);
    List<Film> findByFilmState (FilmState filmState);
    List<Film> findByTitleContainingIgnoreCase(String query);
}
