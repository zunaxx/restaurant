package peksoft.repo.dao;

import peksoft.dto.response.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryJdbcTemplate {
    List<CategoryResponse>getAllCategories();

    Optional<CategoryResponse>getCategoryById(Long id);
}
