package peksoft.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String message) {
        super(message);
    }
}
