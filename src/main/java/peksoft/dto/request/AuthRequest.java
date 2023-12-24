package peksoft.dto.request;

import peksoft.enums.Role;

public record AuthRequest(
        String email,
        String password,
        Role role
) {
}
