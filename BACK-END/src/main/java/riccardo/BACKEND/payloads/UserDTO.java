package riccardo.BACKEND.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

// This class is a Data Transfer Object (DTO) for User
public record UserDTO(@NotEmpty(message = "Name is mandatory")
                      @Size(min = 3, message = "Name must be longer than 3 chars")
                      String name,
                      @NotEmpty(message = "Surname is mandatory")
                      @Size(min = 4, message = "Surname must be longer than 4 chars")
                      String surname,
                      @NotEmpty(message = "Username is mandatory")
                      @Size(min = 4, message = "Username must be longer than 4 chars")
                      String username,
                      @NotEmpty(message = "Email is mandatory")
                      @Email(message = "You must insert a valid email")
                      String email,
                      @NotEmpty(message = "Password is mandatory")
                      @Size(min = 4, message = "Password must be longer than 4 chars")
                      String password

                      ) {
}
