package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.Comment;
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

    public Page<Comment> getAllComments(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.commentDAO.findAll(pageable);
    }

    public Comment getCommentById (long id){
        return this.commentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public Comment saveComment (CommentDTO payload){
        Comment comment = new Comment(payload.description(), payload.rating(), filmService.getFilmById(payload.idFilm()), userService.getUserById(payload.idUser()));
        return this.commentDAO.save(comment);
    }
    public Comment updateComment (long id, CommentDTO payload){
        Comment comment = this.commentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        comment.setDescription(payload.description());
        comment.setRating(payload.rating());
        comment.setFilm(filmService.getFilmById(payload.idFilm()));
        comment.setUser(userService.getUserById(payload.idUser()));
        return this.commentDAO.save(comment);
    }
    public void deleteComment (long id){
        Comment comment = this.commentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.commentDAO.delete(comment);
    }
}
