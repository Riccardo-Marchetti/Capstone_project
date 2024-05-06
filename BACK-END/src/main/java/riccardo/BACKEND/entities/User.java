package riccardo.BACKEND.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import riccardo.BACKEND.enums.UserRole;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"password", "role", "authorities", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "enabled"})
public class User implements UserDetails {

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
    @JsonIgnore
    @OneToMany (mappedBy = "user")
    private List<Ticket> ticket;
    @JsonIgnore
    @OneToMany (mappedBy = "user")
    private List<Comment> comment;

    public User(String name,String surname, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = "https://ui-avatars.com/api/?name=" + this.name + "+" + this.surname;
        this.role = UserRole.USER;
    }

    public User(String name, String surname, String username, String email, String password, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = "https://ui-avatars.com/api/?name=" + this.name + "+" + this.surname;
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
