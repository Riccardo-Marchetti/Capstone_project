package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.UserDTO;
import riccardo.BACKEND.payloads.UserLoginDTO;
import riccardo.BACKEND.payloads.UserLoginRespDTO;
import riccardo.BACKEND.services.AuthService;
import riccardo.BACKEND.services.UserService;

@RestController
@RequestMapping ("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping ("/login")
    public UserLoginRespDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginRespDTO(this.authService.authenticationUserAndGenerateToken(payload));
    }

    @PostMapping ("/register")
    @ResponseStatus (HttpStatus.CREATED)
    private User register (@RequestBody UserDTO payload, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.userService.saveUser(payload);
    }
}
