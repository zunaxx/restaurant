package peksoft.services.impl;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.StopListRequest;
import peksoft.dto.response.StopListResponse;
import peksoft.entities.Menu;
import peksoft.entities.StopList;
import peksoft.exceptions.BadCredentialsException;
import peksoft.exceptions.NotFoundException;
import peksoft.repo.MenuRepo;
import peksoft.repo.StopListRepo;
import peksoft.repo.dao.StopListJdbcTemplate;
import peksoft.services.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StopListServiceImpl implements StopListService {

    private final StopListRepo stopListRepo;
    private final StopListJdbcTemplate stopListJdbcTemplate;
    private final MenuRepo menuRepo;

    @Override
    public List<StopListResponse> getAllStopLists() {
        return stopListJdbcTemplate.getAllStopLists();
    }

    @Override
    public SimpleResponse saveSl(StopListRequest saveSl, Long menuId) {
        Menu menu = menuRepo.findById(menuId).orElseThrow(() ->
                new NotFoundException("Menu with id: %s not found".formatted(menuId)));
        StopList stopList=new StopList();
        stopList.setReason(saveSl.reason());
        stopList.setDate(saveSl.date());
        stopList.setMenu(menu);
        stopListRepo.save(stopList);
        menu.setStopList(stopList);
        menuRepo.save(menu);
        log.info("Stop list is stop list");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Stop list  to menu id: %s is saved".formatted(menuId))
                .build();
    }

    @Override
    public StopListResponse getSlById(Long id) {
        return stopListJdbcTemplate.getSlById(id).orElseThrow(()->
                new NotFoundException("Stop List with id: %s not found".formatted(id)));
    }

    @Override
    public SimpleResponse deleteSl(Long id) {
        if(!stopListRepo.existsById(id)){
            throw new BadCredentialsException("Stop list with id: %s not exists".formatted(id));
        }
        stopListRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Stop list with id: %s is deleted".formatted(id))
                .build();
    }

    @Override
    public SimpleResponse updateSl(Long id, StopListRequest stopListRequest) {
        StopList stopList = stopListRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Stop List with id: %s not found".formatted(id)));
        stopList.setReason(stopListRequest.reason());
        stopList.setDate(stopListRequest.date());
        stopListRepo.save(stopList);
        log.info("Stop List is updated Boss");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Stop List with id: %s is updated".formatted(stopList.getId()))
                .build();
    }
}
