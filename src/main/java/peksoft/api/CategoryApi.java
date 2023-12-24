package peksoft.api;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.CategoryRequest;
import peksoft.dto.response.CategoryResponse;
import peksoft.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@Tag(name = "CategoryApi")
public class CategoryApi {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get All Categories")
    List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping
    @Operation(summary = "Save Category")
    SimpleResponse saveCategory(@RequestBody CategoryRequest saveCategory){
        return categoryService.saveCategory(saveCategory);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get Category By Id")
    CategoryResponse getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category")
    SimpleResponse updateCategory(@PathVariable Long id,
                                  @RequestBody CategoryRequest categoryRequest){
        return categoryService.updateCategory(id,categoryRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category")
    SimpleResponse deleteCategory(@PathVariable Long id){
           return categoryService.deleteCategory(id);
    }
}
