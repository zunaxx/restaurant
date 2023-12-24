package peksoft.repo.dao.impl;

import peksoft.dto.response.MenusResponse;
import peksoft.repo.dao.MenuJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuJdbcTemplateImpl implements MenuJdbcTemplate{

    private final JdbcTemplate jdbcTemplate;

    public MenusResponse rowMapper(ResultSet rs, int rowName) throws SQLException{
        return new MenusResponse(
                rs.getString("name"),
                rs.getString("image"),
                rs.getBigDecimal("price"),
                rs.getString("description"),
                rs.getBoolean("isVegetarian"));
    }


    @Override
    public List<MenusResponse> getAllMenus() {
        String sql= """
                select id,
                name as name,
                image as image,
                price as price,
                description as description,
                is_vegetarian as isVegetarian
                from menus
                """;
        return jdbcTemplate.query(sql,this::rowMapper);
    }

    @Override
    public Optional<MenusResponse> getMeById(Long id) {
        String sql= """
                select id,
                name as name,
                image as image,
                price as price,
                description as description,
                is_vegetarian as isVegetarian
                from menus where id=?
                """;
        return jdbcTemplate.query(sql,this::rowMapper,id).stream().findFirst();
    }
}
