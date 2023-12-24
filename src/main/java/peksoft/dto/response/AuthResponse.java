package peksoft.dto.response;

import peksoft.enums.Role;
import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        String email,
        Role role

) {


}
