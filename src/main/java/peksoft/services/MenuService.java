package peksoft.services;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.MenuRequest;
import peksoft.dto.response.MenusResponse;

import java.util.List;

public interface MenuService {
    List<MenusResponse> getAllMenus();

    SimpleResponse saveMenu(MenuRequest saveMenu, Long restId);

    MenusResponse getMeById(Long id);

    SimpleResponse deleteMe(Long id);

    SimpleResponse updateMenu(Long id, Long menuId,MenuRequest menuRequest);
}
