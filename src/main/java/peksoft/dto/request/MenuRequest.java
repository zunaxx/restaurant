package peksoft.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MenuRequest(
        String name,

        String image,

        BigDecimal price,

        String description,

        boolean isVegetarian
) {
}
