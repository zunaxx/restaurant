package peksoft.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlReadyExistsException extends RuntimeException{

    public AlReadyExistsException(String message) {
        super(message);
    }
}
