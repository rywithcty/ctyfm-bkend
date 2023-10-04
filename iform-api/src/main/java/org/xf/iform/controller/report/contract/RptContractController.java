package org.xf.iform.controller.report.contract;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.dto.report.contract.RptContractItemDtoEntity;
import org.xf.iform.core.dto.report.contract.RptExesDto;
import org.xf.iform.core.dto.report.contract.RptItemExesDto;
import org.xf.iform.service.services.report.contract.RptContractService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/report")
public class RptContractController {

    @Autowired
    private RptContractService rptContractService;

    @GetMapping("/getContractItemReport")
    @Operation(summary = "取得文件項目報表")
    public ResponseEntity<?> getContractItemReport(@RequestParam(required = false) Integer ctStatus) {

        log.info("getContractItemReport:ctStatus=" + ctStatus);
        BaseResponse<List<RptContractItemDtoEntity>> respDto = new BaseResponse<>();
        respDto.setData(rptContractService.getContractItemReport(ctStatus));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/getContractExesYear")
    @Operation(summary = "取得分攤使用年份")
    public ResponseEntity<?> getContractExesYear() {

        log.info("getContractExesYear");
        BaseResponse<List<Integer>> respDto = new BaseResponse<>();
        respDto.setData(rptContractService.getExesYear());

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/getContractItemExes")
    @Operation(summary = "取得文件項目金額報表")
    public ResponseEntity<?> getContractItemExes(@RequestParam(required = false) Integer ctStatus) {

        log.info("getContractItemExes:ctStatus=" + ctStatus);
        BaseResponse<List<RptItemExesDto>> respDto = new BaseResponse<>();
        respDto.setData(rptContractService.getItemExes(ctStatus));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/getContractExes")
    @Operation(summary = "取得文件項目實際金額-一公司與年份區分")
    public ResponseEntity<?> getContractExes(@RequestParam(required = false) Integer ctStatus) {

        log.info("getContractExes:ctStatus=" + ctStatus);
        BaseResponse<List<RptExesDto>> respDto = new BaseResponse<>();
        respDto.setData(rptContractService.getExes(ctStatus));

        return ResponseEntity.ok(respDto);
    }

}
