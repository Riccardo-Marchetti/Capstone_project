package riccardo.BACKEND.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import riccardo.BACKEND.entities.User;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.UserDTO;
import riccardo.BACKEND.repositories.UserDAO;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private PasswordEncoder bcrypt;

    public Page<User> getAllUsers(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.userDAO.findAll(pageable);
    }

    public User getUserById (long id){
        return this.userDAO.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " has not been found"));

    }
    public User saveUser (UserDTO payload){
        this.userDAO.findByEmail(payload.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email: " + payload.email() + " is already in use");
                }
        );
        User users = new User( payload.name(), payload.surname(),payload.username(),  payload.email(), bcrypt.encode(payload.password()) );
        users.setAvatar("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
        return this.userDAO.save(users);
    }

    public User updateUser (long id, UserDTO payload){
        User user = this.userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        user.setName(payload.name());
        user.setSurname(payload.surname());
        user.setUsername(payload.username());
        user.setEmail(payload.email());
        user.setPassword(bcrypt.encode(payload.password()));
        return this.userDAO.save(user);
    }
    public void deleteUser (long id){
        User user = this.userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.userDAO.delete(user);
    }
    public User findByEmail(String email) {
        return this.userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " has not been found"));
    }

    public User uploadImage(MultipartFile image, long id) throws IOException {
        User user = this.userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        String avatar = (String) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        user.setAvatar(avatar);
        return this.userDAO.save(user);
    }
}
