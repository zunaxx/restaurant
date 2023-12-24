package peksoft.api;


import peksoft.dto.SimpleResponse;
import peksoft.dto.request.RestaurantRequest;
import peksoft.dto.response.RestaurantResponse;
import peksoft.services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant")
@Tag(name = "RestaurantApi")
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @GetMapping
    @Operation(summary = "Get All Restaurants")
    public List<RestaurantResponse> getAllRestaurants(){
       return restaurantService.getAllRestaurants();
    }

    @PermitAll
    @PostMapping
    @Operation(summary = "Save Restaurant",description = "To save, fill in all fields")
    SimpleResponse saveRest(@RequestBody RestaurantRequest saveRest){
        return restaurantService.saveRest(saveRest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By ID")
    RestaurantResponse getRestById(@PathVariable Long id){
        return restaurantService.getRestById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Restaurant")
    SimpleResponse updateRest(@PathVariable Long id,
                              @RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.updateRest(id,restaurantRequest);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete with Id")
    SimpleResponse deleteRest(@PathVariable Long id){
        return restaurantService.deleteRestById(id);
    }

}
