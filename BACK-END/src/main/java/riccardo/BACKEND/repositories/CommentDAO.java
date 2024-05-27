package riccardo.BACKEND.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Comment;
import riccardo.BACKEND.entities.Film;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {
    Page<Comment> findByFilm(Film film, Pageable pageable);
    List<Comment> findByFilm(Film film);
    Optional<Comment> findByIdAndFilmId(long commentId, long filmId);
}
