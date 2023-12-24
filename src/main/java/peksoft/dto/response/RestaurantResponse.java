package peksoft.dto.response;

import peksoft.enums.RestType;

public record RestaurantResponse(
        Long id,
        String name,
        String location,
        RestType restType,
        int numberOfEmployees,
        int service
) {
}
