package riccardo.BACKEND.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cinema {

    // ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;
    private String name;
    private String city;
    private String address;

    @OneToMany (mappedBy = "cinema")
    @JsonIgnore
    private List<CinemaRoom> cinemaRoom;

    // CONSTRUCTOR
    public Cinema(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

}
