package peksoft.dto.request;

import peksoft.enums.RestType;

public record RestaurantRequest(
        String name,
        String location,
        RestType restType,
        int numberOfEmployees,
        int service
) {

}
