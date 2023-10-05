package org.xf.iform.controller.cathay;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.dto.cathay.WorkDto;
import org.xf.iform.core.entity.cathay.WorkEntity;
import org.xf.iform.service.data.mapper.cathay.WorkMapper;
import org.xf.iform.service.services.cathay.WorkService;
import org.xf.iform.service.services.cathay.WorkService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping("/work")
    @Operation(summary = "取得work")
    public ResponseEntity<?> getWorkList(@RequestParam(required = false) Integer worId,
                                              @RequestParam(required = false) String worTitle) {

        log.info("workList:worId=" + worId + "worTitle=" + worTitle);
        BaseResponse<List<WorkEntity>> respDto = new BaseResponse<>();
        respDto.setData(workService.getWorkList(worId, worTitle));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/work/{worId}")
    @Operation(summary = "從ID取得work")
    public ResponseEntity<?> getWork(@PathVariable int worId) {

        log.info("work:worId=" + worId);
        BaseResponse<WorkEntity> respDto = new BaseResponse<>();
        respDto.setData(workService.getWork(worId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/work")
    @Operation(summary = "新增work")
    public ResponseEntity<?> addWork(@RequestBody WorkDto workDto) {
        WorkEntity workEntity = WorkMapper.INSTANCE.toEntity(workDto);
        log.info("workAdd:=" + workEntity.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(workService.addWork(workEntity));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/work")
    @Operation(summary = "更新work")
    public ResponseEntity<?> editWork(@RequestBody WorkDto workDto) {
        WorkEntity workEntity = WorkMapper.INSTANCE.toEntity(workDto);

        log.info("workEdit:=" + workEntity.toString());
        workService.editWork(workEntity);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/work")
    @Operation(summary = "刪除work")
    public ResponseEntity<?> deleteWork(@RequestParam int worId) {

        log.info("workDelete:=" + worId);
        BaseResponse<String> respDto = new BaseResponse<>();
        workService.deleteWork(worId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
