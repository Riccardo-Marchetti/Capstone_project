package riccardo.BACKEND.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {
}
