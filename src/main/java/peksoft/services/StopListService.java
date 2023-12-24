package peksoft.services;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.StopListRequest;
import peksoft.dto.response.StopListResponse;

import java.util.List;

public interface StopListService {
   List<StopListResponse> getAllStopLists();

    SimpleResponse saveSl(StopListRequest saveSl, Long menuId);

    StopListResponse getSlById(Long id);

    SimpleResponse deleteSl(Long id);

    SimpleResponse updateSl(Long id, StopListRequest stopListRequest);
}
