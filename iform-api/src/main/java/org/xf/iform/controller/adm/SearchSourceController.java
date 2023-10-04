package org.xf.iform.controller.adm;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.common.util.SeqUtils;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.entity.SearchSource;
import org.xf.iform.service.services.contract.SearchSourceService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comm")
public class SearchSourceController {

    @Autowired
    private SearchSourceService searchSourceService;

    @GetMapping("/getSearchSourceList")
    @Operation(summary = "取得下拉選單列表")
    public ResponseEntity<?> getSearchSourceList() {

        log.info("searchSourceList");
        BaseResponse<List<SearchSource>> respDto = new BaseResponse<>();
        respDto.setData(searchSourceService.getSearchSourceList());

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/searchSource/{ctpId}")
    @Operation(summary = "取得下拉選單")
    public ResponseEntity<?> getSearchSource(@PathVariable int ctpId) {

        log.info("searchSource:ctpId=" + ctpId);
        BaseResponse<SearchSource> respDto = new BaseResponse<>();
        respDto.setData(searchSourceService.getSearchSource(ctpId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/searchSource/add")
    @Operation(summary = "新增下拉選單")
    public ResponseEntity<?> addSearchSource(@RequestBody SearchSource searchSource) {

        log.info("searchSourceAdd:=" + searchSource.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(searchSourceService.addSearchSource(searchSource));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/searchSource/update")
    @Operation(summary = "更新下拉選單")
    public ResponseEntity<?> editSearchSource(@RequestBody SearchSource searchSource) {

        log.info("searchSourceEdit:=" + searchSource.toString());
        searchSourceService.editSearchSource(searchSource);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/searchSource/delete")
    @Operation(summary = "刪除下拉選單")
    public ResponseEntity<?> deleteSearchSource(@RequestParam("ssId") int ssId) {

        log.info("searchSourceDelete:=" + ssId);
        BaseResponse<String> respDto = new BaseResponse<>();
        searchSourceService.deleteSearchSource(ssId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
