package peksoft.services.impl;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.SubCategoryRequest;
import peksoft.dto.response.SubCategoryResponse;
import peksoft.entities.Category;
import peksoft.entities.Menu;
import peksoft.entities.SubCategory;
import peksoft.exceptions.NotFoundException;
import peksoft.repo.CategoryRepo;
import peksoft.repo.MenuRepo;
import peksoft.repo.SubCategoryRepo;
import peksoft.repo.dao.SubCategoryJdbcTemplate;
import peksoft.services.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;
    private final MenuRepo menuRepo;
    private final SubCategoryJdbcTemplate subCategoryJdbcTemplate;

    @Override
    public List<SubCategoryResponse> getAllSubCats() {
        return subCategoryJdbcTemplate.getAllSubCats();
    }

    @Override
    public SimpleResponse saveSubC(Long catId,Long menuId ,SubCategoryRequest saveSabC) {
        Category category = categoryRepo.findById(catId).orElseThrow(() ->
                new NotFoundException("Category with id: %s not found".formatted(catId)));
        Menu menu = menuRepo.findById(menuId).orElseThrow(() ->
                new NotFoundException("Menu with id: %s not found".formatted(menuId)));
        SubCategory subCategory=new SubCategory();
        subCategory.setName(saveSabC.name());
        subCategory.setCategory(category);



        return null;
    }
}
