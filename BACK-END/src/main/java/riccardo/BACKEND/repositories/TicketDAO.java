package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Ticket;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.payloads.UserDTO;

import java.util.List;

@Repository
public interface TicketDAO extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser (User user);
}
