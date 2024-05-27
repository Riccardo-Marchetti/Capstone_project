package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.entities.Comment;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.CommentDTO;
import riccardo.BACKEND.services.CinemaService;
import riccardo.BACKEND.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Page<Comment> getAllComments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam (defaultValue = "rating") String sortBy){
        return commentService.getAllComments(page, size, sortBy);
    }

    @GetMapping ("/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Comment getCommentById (@PathVariable long commentId){
        return this.commentService.getCommentById(commentId);
    }

    @GetMapping ("/comments/{filmId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public Page<Comment> getCommentsByFilm(@PathVariable long filmId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return this.commentService.getCommentsByFilm(filmId, page, size);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Comment saveComment (@RequestBody @Validated CommentDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.commentService.saveComment(payload);
    }

    @PostMapping ("/me/{filmId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    @ResponseStatus (HttpStatus.CREATED)
    public Comment postComment (@RequestBody @Validated CommentDTO payload, @AuthenticationPrincipal User currentUser,@PathVariable long filmId, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.commentService.postComment(payload, currentUser, filmId);
    }

    @PutMapping ("/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Comment updateComment (@PathVariable long commentId, @RequestBody @Validated CommentDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.commentService.updateComment(commentId, payload);
    }

    @DeleteMapping ("/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteComment (@PathVariable long commentId){
        this.commentService.deleteComment(commentId);
    }

    @DeleteMapping ("/me/{filmId}/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public void deleteMyComment (@PathVariable long filmId, @PathVariable long commentId, @AuthenticationPrincipal User currentUser){
        Comment comment = this.commentService.getCommentByIdAndMovieId(commentId, filmId);
        if (currentUser.getId() == (comment.getUser().getId())) {
            this.commentService.deleteComment(commentId);
        } else {
            throw new AccessDeniedException("You do not have permission to delete this comment");
        }
    }
}
