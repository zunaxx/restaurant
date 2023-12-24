package peksoft.repo.dao;

import peksoft.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryJdbcTemplate {

    List<SubCategoryResponse>getAllSubCats();
}
