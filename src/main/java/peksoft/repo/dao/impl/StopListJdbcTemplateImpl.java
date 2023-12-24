package peksoft.repo.dao.impl;

import peksoft.dto.response.StopListResponse;
import peksoft.repo.dao.StopListJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StopListJdbcTemplateImpl implements StopListJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    private StopListResponse rowMapper(ResultSet rs,int rowName) throws SQLException{
        String menuName = rs.getString("name");
        return new StopListResponse(
                rs.getString("reason"),
                rs.getDate("date").toLocalDate(),
                menuName
        );
    }



    @Override
    public List<StopListResponse> getAllStopLists() {
        String sql= """
                select st.id,
                st.reason,
                st.date,
                m.name
                from stop_list st 
                inner join menus m on st.menu_id=m.id
                """;
        return jdbcTemplate.query(sql,this::rowMapper);
    }

    @Override
    public Optional<StopListResponse> getSlById(Long id) {
        String sql= """
                select st.id,
                st.reason,
                st.date,
                m.name
                from stop_list st 
                inner join menus m on st.menu_id=m.id
                where st.id=?
                """;
        return jdbcTemplate.query(sql,this::rowMapper,id).stream().findFirst();
    }
}
