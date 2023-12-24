package peksoft.services.impl;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.RestaurantRequest;
import peksoft.dto.response.RestaurantResponse;
import peksoft.entities.Restaurant;
import peksoft.exceptions.BadCredentialsException;
import peksoft.exceptions.NotFoundException;
import peksoft.repo.RestaurantRepo;
import peksoft.repo.dao.RestaurantJdbcTemplate;
import peksoft.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {


    private final RestaurantRepo restaurantRepo;
    private final RestaurantJdbcTemplate restaurantJdbcTemplate;


    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantJdbcTemplate.getAllRestaurants();
    }

    @Override
    public SimpleResponse saveRest(RestaurantRequest saveRest) {
        Restaurant restaurant=new Restaurant();
        restaurant.setName(saveRest.name());
        restaurant.setLocation(saveRest.location());
        restaurant.setRestType(saveRest.restType());
        restaurant.setNumberOfEmployees(saveRest.numberOfEmployees());
        restaurant.setService(saveRest.service());
        restaurantRepo.save(restaurant);
        log.info("Rest is S a v e d");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant successfully saved")
                .build();
    }

    @Override
    public RestaurantResponse getRestById(Long id) {
        return restaurantJdbcTemplate.getRestById(id).orElseThrow(()->
                new NotFoundException("Restaurant with id: %s not found".formatted(id)));
    }

    @Override
    public SimpleResponse deleteRestById(Long id) {
        if (!restaurantRepo.existsById(id)){
            throw new BadCredentialsException("Restaurant with id: %s not exists");
        }
        restaurantRepo.deleteById(id);
      return SimpleResponse.builder()
              .httpStatus(HttpStatus.OK)
              .message("Restaurant with id: %s is deleted")
              .build();
    }

    @Override
    public SimpleResponse updateRest(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Restaurant with id: %s not found".formatted(id)));
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setNumberOfEmployees(restaurantRequest.numberOfEmployees());
        restaurant.setService(restaurantRequest.service());
        restaurantRepo.save(restaurant);
        log.info("Restaurant is UPDATE Boss");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant with id: %s is updated".formatted(restaurant.getId()))
                .build();
    }
}
