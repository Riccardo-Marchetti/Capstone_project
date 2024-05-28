package riccardo.BACKEND.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.BACKEND.entities.Comment;
import riccardo.BACKEND.entities.Film;

import java.util.List;
import java.util.Optional;

// This interface is a repository for Comment entities
@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {

    //  These methods search for comments on a movie
    Page<Comment> findByFilm(Film film, Pageable pageable);
    List<Comment> findByFilm(Film film);

    //  This method searches for a comment by id and by the movie id
    Optional<Comment> findByIdAndFilmId(long commentId, long filmId);
}
