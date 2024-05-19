package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Ticket;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.payloads.UserDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TicketDAO extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser (User user);
    @Query("SELECT t FROM Ticket t WHERE :seatId MEMBER OF t.seat AND t.show.id = :showId")
    List<Ticket> findAllBySeatIdAndShowId(@Param("seatId") long seatId, @Param("showId") long showId);

    @Query("SELECT t FROM Ticket t WHERE t.show.id = :showId AND t.show.showDate = :showDate AND t.show.showTime = :showTime")
    List<Ticket> findAllByShowIdAndShowDateTime(@Param("showId") long showId, @Param("showDate") LocalDate showDate, @Param("showTime") List<LocalTime> showTime);
}
