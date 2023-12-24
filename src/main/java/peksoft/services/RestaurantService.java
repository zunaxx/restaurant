package peksoft.services;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.RestaurantRequest;
import peksoft.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getAllRestaurants();

    SimpleResponse saveRest(RestaurantRequest saveRest);

    RestaurantResponse getRestById(Long id);

    SimpleResponse deleteRestById(Long id);

    SimpleResponse updateRest(Long id, RestaurantRequest restaurantRequest);
}
