package peksoft.repo.dao.impl;

import peksoft.dto.response.CategoryResponse;
import peksoft.repo.dao.CategoryJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryJdbcTemplateImpl implements CategoryJdbcTemplate {


    private final JdbcTemplate jdbcTemplate;

    private CategoryResponse rowMapper(ResultSet rs, int rowName) throws SQLException{
        return new CategoryResponse(
                rs.getString("name")
        );
    }

    @Override
    public List<CategoryResponse> getAllCategories(){
        String sql= """
                select id,
                name as name
                from categories
                """;
        return jdbcTemplate.query(sql,this::rowMapper);
    }

    @Override
    public Optional<CategoryResponse> getCategoryById(Long id) {
        String sql= """
                select id,
                name as name
                from categories where id=?
                """;
        return jdbcTemplate.query(sql,this::rowMapper,id).stream().findFirst();
    }
}
