package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.entities.Comment;
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
    public Page<Comment> getAllComments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam (defaultValue = "film") String sortBy){
        return commentService.getAllComments(page, size, sortBy);
    }

    @GetMapping ("/{commentId}")
    public Comment getCommentById (@PathVariable long commentId){
        return this.commentService.getCommentById(commentId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Comment saveComment (@RequestBody @Validated CommentDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.commentService.saveComment(payload);
    }

    @PutMapping ("/{commentId}")
    public Comment updateComment (@PathVariable long commentId, @RequestBody @Validated CommentDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.commentService.updateComment(commentId, payload);
    }

    @DeleteMapping ("/{commentId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteComment (@PathVariable long commentId){
        this.commentService.deleteComment(commentId);
    }
}
