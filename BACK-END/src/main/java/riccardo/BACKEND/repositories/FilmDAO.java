package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.enums.FilmState;
import riccardo.BACKEND.enums.FilmType;
import riccardo.BACKEND.enums.SeatType;

import java.util.List;

// This interface is a repository for Film entities
@Repository
public interface FilmDAO extends JpaRepository<Film, Long> {

    //  This method searches for movies by type
    List<Film> findByType (FilmType type);

    //  This method searches for movies by state
    List<Film> findByFilmState (FilmState filmState);

    //  This method searches for movies by title
    List<Film> findByTitleContainingIgnoreCase(String query);
}
