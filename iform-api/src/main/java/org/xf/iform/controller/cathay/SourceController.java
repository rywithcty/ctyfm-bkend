package org.xf.iform.controller.cathay;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.entity.cathay.SourceEntity;
import org.xf.iform.service.services.cathay.SourceService;
import org.xf.iform.service.services.cathay.SourceService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @GetMapping("/source")
    @Operation(summary = "取得來源")
    public ResponseEntity<?> getSourceList(@RequestParam(required = false) Integer catId) {

        log.info("sourceList");
        BaseResponse<List<SourceEntity>> respDto = new BaseResponse<>();
        respDto.setData(sourceService.getSourceList(catId));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/source/{souId}")
    @Operation(summary = "從ID取得來源")
    public ResponseEntity<?> getSource(@PathVariable int souId) {

        log.info("source:souId=" + souId);
        BaseResponse<SourceEntity> respDto = new BaseResponse<>();
        respDto.setData(sourceService.getSource(souId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/source")
    @Operation(summary = "新增來源")
    public ResponseEntity<?> addSource(@RequestBody SourceEntity sourceEntity) {

        log.info("sourceAdd:=" + sourceEntity.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(sourceService.addSource(sourceEntity));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/source")
    @Operation(summary = "更新來源")
    public ResponseEntity<?> editSource(@RequestBody SourceEntity sourceEntity) {

        log.info("sourceEdit:=" + sourceEntity.toString());
        sourceService.editSource(sourceEntity);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/source")
    @Operation(summary = "刪除來源")
    public ResponseEntity<?> deleteSource(@RequestParam("souId") int souId) {

        log.info("sourceDelete:=" + souId);
        BaseResponse<String> respDto = new BaseResponse<>();
        sourceService.deleteSource(souId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
