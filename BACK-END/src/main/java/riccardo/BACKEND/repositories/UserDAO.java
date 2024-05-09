package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByEmail (String email);

    boolean existsByUsername (String username);
    boolean existsByEmail (String email);

    User findByUsername (String username);

    boolean existsByUsernameAndEmail(String username, String email);
}
