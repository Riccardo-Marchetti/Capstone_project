package riccardo.BACKEND.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{
    List<ObjectError> errorList;
    public BadRequestException (List<ObjectError> errorList){
        super("Validation Error");
        this.errorList = errorList;
    }
}
