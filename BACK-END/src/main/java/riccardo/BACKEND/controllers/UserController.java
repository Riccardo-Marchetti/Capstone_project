package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.UserDTO;
import riccardo.BACKEND.services.FilmService;
import riccardo.BACKEND.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping ("/{userId}")
    public User getUserById (@PathVariable long userId){
        return this.userService.getUserById(userId);
    }

    @PutMapping ("/{userId}")
    public User updateUser (@PathVariable long userId, @RequestBody UserDTO payload ){
        return this.userService.updateUser(userId, payload);
    }

    @DeleteMapping ("/{userId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable long userId){
        this.userService.deleteUser(userId);
    }

}
