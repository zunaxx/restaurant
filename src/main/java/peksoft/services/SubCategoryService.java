package peksoft.services;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.SubCategoryRequest;
import peksoft.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {
    List<SubCategoryResponse> getAllSubCats();

    SimpleResponse saveSubC(Long catId,Long menuId,SubCategoryRequest saveSabC);
}
