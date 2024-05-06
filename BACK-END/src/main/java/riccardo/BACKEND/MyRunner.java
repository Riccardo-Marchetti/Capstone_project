package riccardo.BACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.services.UserService;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Value("${admin.name}")
    private String name;

    @Value("${admin.surname}")
    private String surname;
    @Value("${admin.username}")
    private String username;
    @Value("${admin.email}")
    private String email;
    @Value("${admin.password}")
    private String password;
    @Override
    public void run(String... args) throws Exception {
        User user = this.userService.existsByUsername(name, surname, username, email, password);


    }
}
