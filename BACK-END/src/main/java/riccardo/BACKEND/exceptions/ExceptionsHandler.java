package riccardo.BACKEND.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import riccardo.BACKEND.payloads.ErrorsDTO;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest (BadRequestException ex){
        if (ex.getErrorList() != null){
            String message = ex.getErrorList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));
            return new ErrorsDTO(message, LocalDateTime.now());
        }
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFoundException (NotFoundException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleException (Exception ex){
        return new ErrorsDTO("Server related problem, we will resolve as soon as possible", LocalDateTime.now());
    }
    @ExceptionHandler
    @ResponseStatus (HttpStatus.UNAUTHORIZED)
    public ErrorsDTO handleException (UnauthorizedException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler
    @ResponseStatus (HttpStatus.FORBIDDEN)
    public ErrorsDTO handleException (AccessDeniedException ex){
        return new ErrorsDTO("You do not have access to this feature!", LocalDateTime.now());
    }
}
