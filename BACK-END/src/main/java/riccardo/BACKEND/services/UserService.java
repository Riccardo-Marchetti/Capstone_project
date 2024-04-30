package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.UserDTO;
import riccardo.BACKEND.repositories.UserDAO;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers(){
        return this.userDAO.findAll();
    }

    public User getUserById (long id){
        return this.userDAO.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " has not been found"));

    }

    public User updateUser (long id, UserDTO payload){
        User user = this.userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        user.setName(payload.name());
        user.setSurname(payload.surname());
        user.setUsername(payload.username());
        user.setEmail(payload.email());
        user.setPassword(payload.password());
        return this.userDAO.save(user);
    }
    public void deleteUser (long id){
        User user = this.userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.userDAO.delete(user);
    }
    public User findByEmail(String email) {
        return this.userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " has not been found"));
    }
}
