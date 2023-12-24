package peksoft.services;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.CategoryRequest;
import peksoft.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();

    SimpleResponse saveCategory(CategoryRequest saveCategory);

    CategoryResponse getCategoryById(Long id);

    SimpleResponse updateCategory(Long id, CategoryRequest categoryRequest);

    SimpleResponse deleteCategory(Long id);
}
