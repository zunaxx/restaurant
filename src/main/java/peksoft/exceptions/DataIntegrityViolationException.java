package peksoft.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
