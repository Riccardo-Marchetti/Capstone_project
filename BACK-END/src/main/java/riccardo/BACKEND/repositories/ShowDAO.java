package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.CinemaRoom;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Show;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowDAO extends JpaRepository<Show, Long> {
    @Query("SELECT s FROM Show s WHERE s.showDate = :showDate  AND s.cinemaRoom = :cinemaRoom")
   List<Show> findShowByDateTimeAndRoom(@Param("showDate") LocalDate showDate,  @Param("cinemaRoom") CinemaRoom cinemaRoom);
   List<Show> findAllByFilm(Film film);
}

