package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByEmail (String email);
}