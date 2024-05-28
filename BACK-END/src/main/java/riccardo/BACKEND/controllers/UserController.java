package riccardo.BACKEND.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import riccardo.BACKEND.entities.Ticket;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.UserDTO;
import riccardo.BACKEND.services.TicketService;
import riccardo.BACKEND.services.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    // This method is used to get all users
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Page<User> getAllUsers(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "20") int size, @RequestParam (defaultValue = "username") String sortBy){
        return this.userService.getAllUsers(page, size, sortBy);
    }

    // This method is used to get a user by its ID
    @GetMapping ("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public User getUserById (@PathVariable long userId){
        return this.userService.getUserById(userId);
    }

    // This method is used to update an existing user
    @PutMapping ("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public User updateUser (@PathVariable long userId, @RequestBody @Validated UserDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.userService.updateUser(userId, payload);
    }

    // This method is used to delete a user
    @DeleteMapping ("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable long userId){
        this.userService.deleteUser(userId);
    }

    // This method is used to upload an image for a user
    @PostMapping ("/upload/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    @ResponseStatus (HttpStatus.CREATED)
    public User uploadImage(@RequestParam ("avatar")MultipartFile image, @PathVariable long userId) throws IOException {
        return this.userService.uploadImage(image, userId);
    }

    // This method is used to upload an avatar for the current user
    @PostMapping ("/me/upload")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    @ResponseStatus (HttpStatus.CREATED)
    public User uploadAvatar(@RequestParam ("avatar")MultipartFile image, @AuthenticationPrincipal User currentUser) throws IOException {
        return this.userService.uploadImage(image, currentUser.getId());
    }

    // This method is used to find the current user
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public User findMe(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    // This method is used to get tickets of the current user
    @GetMapping ("/me/tickets")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public List<Ticket> getUserTickets(@AuthenticationPrincipal User currentUser){
        return this.ticketService.findTicketsByUser(currentUser);
    }

    // This method is used to update the current user
    @PutMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    public User updateMe(@AuthenticationPrincipal User currentUser,
                         @RequestBody UserDTO payload,
                         BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.userService.updateUser(currentUser.getId(), payload);
    }

    // This method is used to delete the current user
    @DeleteMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMe(@AuthenticationPrincipal User currentUser) {
        this.userService.deleteUser(currentUser.getId());
    }

    // This method is used to delete a comment of the current user
    @DeleteMapping("/me/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR') || hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyComment(@AuthenticationPrincipal User currentUser, @PathVariable long commentId) {
        this.userService.deleteUser(currentUser.getId());
    }
}
