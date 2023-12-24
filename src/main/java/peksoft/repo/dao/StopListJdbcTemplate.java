package peksoft.repo.dao;

import peksoft.dto.response.StopListResponse;

import java.util.List;
import java.util.Optional;

public interface StopListJdbcTemplate {

    List<StopListResponse> getAllStopLists();

    Optional<StopListResponse>getSlById(Long id);
}
