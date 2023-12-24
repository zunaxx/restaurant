package peksoft.dto.request;

import lombok.Builder;

@Builder
public record CategoryRequest(
        String name
) {
}
