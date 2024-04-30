package riccardo.BACKEND.entities;

import jakarta.persistence.*;
import lombok.*;
import riccardo.BACKEND.enums.UserRole;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String avatar;
    @Enumerated (EnumType.STRING)
    private UserRole role;

    @OneToMany (mappedBy = "user")
    private List<Ticket> ticket;

    @OneToMany (mappedBy = "user")
    private List<Comment> comment;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = "https://ui-avatars.com/api/?name=" + this.name + "+" + this.surname;
        this.role = UserRole.USER;
    }
}
