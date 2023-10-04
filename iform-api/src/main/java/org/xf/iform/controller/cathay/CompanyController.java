package org.xf.iform.controller.cathay;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.entity.cathay.CompanyEntity;
import org.xf.iform.service.services.cathay.CompanyService;
import org.xf.iform.service.services.cathay.WorkService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/company")
    @Operation(summary = "取得公司")
    public ResponseEntity<?> getCompanyList(@RequestParam(required = false) Integer comId,
                                              @RequestParam(required = false) String comTitle) {

        log.info("companyList: comId=" + comId + ", comTitle=" + comTitle);
        BaseResponse<List<CompanyEntity>> respDto = new BaseResponse<>();
        respDto.setData(companyService.getCompanyList(comId, comTitle));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/company/{comId}")
    @Operation(summary = "從ID取得公司")
    public ResponseEntity<?> getCompany(@PathVariable int comId) {

        log.info("company:comId=" + comId);
        BaseResponse<CompanyEntity> respDto = new BaseResponse<>();
        respDto.setData(companyService.getCompany(comId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/company")
    @Operation(summary = "新增公司")
    public ResponseEntity<?> addCompany(@RequestBody CompanyEntity companyEntity) {

        log.info("addCompany:=" + companyEntity.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(companyService.addCompany(companyEntity));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/company")
    @Operation(summary = "更新公司")
    public ResponseEntity<?> editCompany(@RequestBody CompanyEntity companyEntity) {

        log.info("editCompany:=" + companyEntity.toString());
        companyService.editCompany(companyEntity);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/company")
    @Operation(summary = "刪除公司")
    public ResponseEntity<?> deleteCompany(@RequestParam int comId) {

        log.info("deleteCompany:=" + comId);
        BaseResponse<String> respDto = new BaseResponse<>();
        companyService.deleteCompany(comId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
