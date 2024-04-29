package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Ticket;

@Repository
public interface TicketDAO extends JpaRepository<Ticket, Long> {
}
