package riccardo.BACKEND.payloads;

import java.time.LocalDateTime;

// This class is a Data Transfer Object (DTO) for Errors
public record ErrorsDTO(String message, LocalDateTime dateMessage) {
}
