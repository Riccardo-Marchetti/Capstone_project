package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.UnauthorizedException;
import riccardo.BACKEND.payloads.UserLoginDTO;
import riccardo.BACKEND.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticationUserAndGenerateToken (UserLoginDTO payload){
        // 1 controllo le credenziali
        // 1.1 cerco nel db tramite l’email l’utente
        User user = userService.findByEmail(payload.email());

        // 1.2 verifico se la password combacia con quella ricevuta nel payload
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            // 2 se tutto è ok, genero un token e lo torno
           return jwtTools.createToken(user);
        } else {
            // 3 altrimenti lancio l'eccezione
            throw new UnauthorizedException("Incorrect credentials! Log in again");
        }
    }
}
