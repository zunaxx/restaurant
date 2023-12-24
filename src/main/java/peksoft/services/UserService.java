package peksoft.services;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.AuthRequest;
import peksoft.dto.request.UserRequest;
import peksoft.dto.response.AuthResponse;

public interface UserService {

    void init();
    SimpleResponse signUp(UserRequest signUp, Long restId);

    AuthResponse signIn(AuthRequest signInRequest);
}
