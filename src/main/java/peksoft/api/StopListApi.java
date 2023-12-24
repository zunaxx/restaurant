package peksoft.api;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.StopListRequest;
import peksoft.dto.response.StopListResponse;
import peksoft.services.StopListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stop_list")
@Tag(name = "StopListApi")
public class StopListApi {

    private final StopListService stopListService;

    @GetMapping
    @Operation(summary = "Get ALL")
    List<StopListResponse> getAllStopLists(){
        return stopListService.getAllStopLists();
    }

    @PostMapping("/{menuId}")
    @Operation(summary = "Save Stop List")
    SimpleResponse saveSl(@RequestBody StopListRequest saveSl,
                          @PathVariable Long menuId){
        return stopListService.saveSl(saveSl,menuId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Stop List get By Id")
    StopListResponse getSlById(@PathVariable Long id){
         return stopListService.getSlById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Stop List")
    SimpleResponse updateStopList(@PathVariable Long id,
                                  @RequestBody StopListRequest stopListRequest){
        return stopListService.updateSl(id,stopListRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Stop List")
    SimpleResponse deleteSl(@PathVariable Long id){
        return stopListService.deleteSl(id);
    }

}
