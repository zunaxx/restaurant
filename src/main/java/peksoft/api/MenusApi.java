package peksoft.api;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.MenuRequest;
import peksoft.dto.response.MenusResponse;
import peksoft.services.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
@Tag(name = "MenusApi")
public class MenusApi {

    private final MenuService menuService;

    @GetMapping
    @Operation(summary = "Get All")
    List<MenusResponse> getAllMenus() {
        return menuService.getAllMenus();
    }

    @PostMapping("/{restId}")
    @Operation(summary = "Save Menu")
    SimpleResponse saveMenu(@RequestBody MenuRequest saveMenu, @PathVariable Long restId) {
        return menuService.saveMenu(saveMenu, restId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Menu By Id")
    MenusResponse getMeById(@PathVariable Long id) {
        return menuService.getMeById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update menu")
    SimpleResponse updateMenu(@PathVariable Long id,
                              @RequestParam Long menuId,
                              @RequestBody MenuRequest menuRequest){
        return menuService.updateMenu(id, menuId, menuRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete With Id")
    SimpleResponse deleteMe(@PathVariable Long id) {
        return menuService.deleteMe(id);
    }
}
