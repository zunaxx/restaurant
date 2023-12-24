package peksoft.repo.dao.impl;

import peksoft.dto.response.RestaurantResponse;
import peksoft.enums.RestType;
import peksoft.repo.dao.RestaurantJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RestaurantJdbcTemplateImpl implements RestaurantJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;


    private RestaurantResponse rowMapper(ResultSet rs, int rowName) throws SQLException {
        String restTypeStr=rs.getString("restType");
        RestType restType = RestType.valueOf(restTypeStr);
     return new RestaurantResponse(
             rs.getLong("id"),
             rs.getString("name"),
             rs.getString("location"),
             restType,
             rs.getInt("numberOfEmployees"),
             rs.getInt("service")

     );

    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
    String sql= """
            select id,
            name as name,
            location as location,
            rest_type as restType,
            number_of_employees as numberOfEmployees,
            service as service
            from restaurants
            """;
        return jdbcTemplate.query(sql,this::rowMapper);
    }

    @Override
    public Optional<RestaurantResponse> getRestById(Long id) {
       String sql= """
               select id,
               name as name,
               location as location,
               rest_type as restType,
               number_of_employees as numberOfEmployees,
               service as service
               from restaurants where id=?
               """;
        return jdbcTemplate.query(sql,this::rowMapper,id).stream().findFirst();
    }


}
