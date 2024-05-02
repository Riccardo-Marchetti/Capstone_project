package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.UserDTO;
import riccardo.BACKEND.services.FilmService;
import riccardo.BACKEND.services.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "20") int size, @RequestParam (defaultValue = "username") String sortBy){
        return this.userService.getAllUsers(page, size, sortBy);
    }

    @GetMapping ("/{userId}")
    public User getUserById (@PathVariable long userId){
        return this.userService.getUserById(userId);
    }

    @PutMapping ("/{userId}")
    public User updateUser (@PathVariable long userId, @RequestBody @Validated UserDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.userService.updateUser(userId, payload);
    }

    @DeleteMapping ("/{userId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable long userId){
        this.userService.deleteUser(userId);
    }

    @PostMapping ("/upload/{userId}")
    @ResponseStatus (HttpStatus.CREATED)
    public User uploadImage(@RequestParam ("avatar")MultipartFile image, @PathVariable long userId) throws IOException {
        return this.userService.uploadImage(image, userId);
    }
    @PostMapping ("/me/upload")
    @ResponseStatus (HttpStatus.CREATED)
    public User uploadAvatar(@RequestParam ("avatar")MultipartFile image, @PathVariable User currentUser) throws IOException {
        return this.userService.uploadImage(image, currentUser.getId());
    }
    @GetMapping("/me")
    public User findMe(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public User updateMe(@AuthenticationPrincipal User currentUser,
                         @RequestBody UserDTO payload,
                         BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.userService.updateUser(currentUser.getId(), payload);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMe(@AuthenticationPrincipal User currentUser) {
        this.userService.deleteUser(currentUser.getId());
    }
}
