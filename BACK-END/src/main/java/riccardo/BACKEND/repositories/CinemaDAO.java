package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Cinema;

import java.util.List;

// This interface is a repository for Cinema entities
@Repository
public interface CinemaDAO extends JpaRepository<Cinema, Long> {
   // This method finds cinemas by their name and city
   List<Cinema> findByNameAndCity (String name, String city);
}
