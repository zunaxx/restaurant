package peksoft.repo.dao;

import peksoft.dto.response.MenusResponse;

import java.util.List;
import java.util.Optional;

public interface MenuJdbcTemplate {

    List<MenusResponse> getAllMenus();

    Optional<MenusResponse>getMeById(Long id);
}
