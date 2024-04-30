package riccardo.BACKEND.entities;

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

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;
    private String name;
    private String city;
    private String address;

    @OneToMany (mappedBy = "cinema")
    private List<CinemaRoom> cinemaRoom;

    public Cinema(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;

    }
}
