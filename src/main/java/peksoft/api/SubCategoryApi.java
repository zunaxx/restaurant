package peksoft.api;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.SubCategoryRequest;
import peksoft.dto.response.SubCategoryResponse;
import peksoft.services.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sub_category")
@Tag(name = "SubCategoryApi")
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;

    @GetMapping
    @Operation(summary = "Get All Sub Categories")
    List<SubCategoryResponse>getAllSubCats(){
        return subCategoryService.getAllSubCats();
    }

    @PostMapping("/{catId}/{menuId}")
    SimpleResponse savSubC(@PathVariable Long catId,
                           @PathVariable Long menuId,
                           @RequestBody SubCategoryRequest saveSabC){
        return subCategoryService.saveSubC(catId,menuId,saveSabC);
    }
}
