package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Comment;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.CommentDTO;
import riccardo.BACKEND.repositories.CommentDAO;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private FilmService filmService;
    @Autowired
    private UserService userService;

    // Returns a paginated list of all comments
    public Page<Comment> getAllComments(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.commentDAO.findAll(pageable);
    }

    // Returns a comment by its id
    public Comment getCommentById (long id){
        return this.commentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    // Saves a new comment to the database
    public Comment saveComment (CommentDTO payload){
        Comment comment = new Comment(payload.description(), payload.rating(), filmService.getFilmById(payload.idFilm()), userService.getUserById(payload.idUser()));
        return this.commentDAO.save(comment);
    }

    // Save a comment, calculates average rating for the film, and updates the film's rating
    public Comment postComment (CommentDTO payload, User currentUser, long selectedFilm){
        Comment comment = new Comment(payload.description(), payload.rating(), filmService.getFilmById(selectedFilm), currentUser);
        Comment savedComment = this.commentDAO.save(comment);

        // Find all comments for that movie
        List<Comment> comments = this.commentDAO.findByFilm(savedComment.getFilm());

        // Calculate the average of the ratings
        double totalRating = 0;
        for (Comment c : comments) {
            totalRating += c.getRating();
        }
        double averageRating = totalRating / comments.size();

        //Update movie rating
        Film film = savedComment.getFilm();
        film.setRating(averageRating);
        filmService.updateFilm(film.getId(), film);

        return savedComment;
    }

    // Updates a comment in the database
    public Comment updateComment (long id, CommentDTO payload){
        Comment comment = this.commentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        comment.setDescription(payload.description());
        comment.setRating(payload.rating());
        comment.setFilm(filmService.getFilmById(payload.idFilm()));
        comment.setUser(userService.getUserById(payload.idUser()));
        return this.commentDAO.save(comment);
    }

    // Deletes a comment from the database
    public void deleteComment (long id){
        Comment comment = this.commentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.commentDAO.delete(comment);
    }

    // Returns a page of comments for a specific film
    public Page<Comment> getCommentsByFilm (long id, int page, int size) {
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by("rating").descending());
        Film film = this.filmService.getFilmById(id);
        this.commentDAO.findAll(pageable);
        return this.commentDAO.findByFilm(film, pageable);

    }

    // Returns a comment based on its ID and the film's id
    public Comment getCommentByIdAndMovieId(long commentId, long filmId) {
        return this.commentDAO.findByIdAndFilmId(commentId, filmId)
                .orElseThrow(() -> new NotFoundException(commentId));
    }
}
