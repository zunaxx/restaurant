package peksoft.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MenusResponse(
         String name,

         String image,

         BigDecimal price,

         String description,

         boolean isVegetarian
) {
}
