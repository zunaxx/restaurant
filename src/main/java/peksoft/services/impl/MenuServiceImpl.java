package peksoft.services.impl;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.MenuRequest;
import peksoft.dto.response.MenusResponse;
import peksoft.entities.Menu;
import peksoft.entities.Restaurant;
import peksoft.exceptions.BadCredentialsException;
import peksoft.exceptions.NotFoundException;
import peksoft.repo.MenuRepo;
import peksoft.repo.RestaurantRepo;
import peksoft.repo.dao.MenuJdbcTemplate;
import peksoft.services.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService {

    private final MenuRepo menuRepo;
    private final RestaurantRepo restaurantRepo;
    private final MenuJdbcTemplate menuJdbcTemplate;



    @Override
    public List<MenusResponse> getAllMenus() {
        return menuJdbcTemplate.getAllMenus();
    }

    @Override
    public SimpleResponse saveMenu(MenuRequest saveMenu, Long restId) {
        Restaurant restaurant = restaurantRepo.findById(restId).orElseThrow(() ->
                new NotFoundException("Rest with id: %s not found".formatted(restId)));
        Menu menu=new Menu();
        menu.setName(saveMenu.name());
        menu.setImage(saveMenu.image());
        menu.setPrice(saveMenu.price());
        menu.setDescription(saveMenu.description());
        menu.setVegetarian(true);
        menu.setRestaurant(restaurant);
        menuRepo.save(menu);
        log.info("Menu is saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Menu is saved")
                .build();
    }

    @Override
    public MenusResponse getMeById(Long id) {
        return menuJdbcTemplate.getMeById(id).orElseThrow(()->
                new NotFoundException("Menu with id: %s not found boss".formatted(id)));
    }

    @Override
    public SimpleResponse deleteMe(Long id) {
        if (!menuRepo.existsById(id)){
            throw new BadCredentialsException("Menu with id: %s not exists".formatted(id));
        }
        menuRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Menu with id: %s is deleted".formatted(id))
                .build();
    }

    @Override
    public SimpleResponse updateMenu(Long id, Long menuId, MenuRequest menuRequest) {
        Menu menu = menuRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Menu with id: %s not found".formatted(id)));
        menu.setName(menuRequest.name());
        menu.setImage(menuRequest.image());
        menu.setPrice(menuRequest.price());
        menu.setDescription(menuRequest.description());
        menu.setVegetarian(menuRequest.isVegetarian());
        menuRepo.save(menu);
        log.info("Menu is updated");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Menu with id: %s is updated".formatted(menu.getId()))
                .build();
    }
}
