package org.xf.iform.controller.cathay;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.entity.cathay.PersonnelEntity;
import org.xf.iform.service.services.cathay.PersonnelService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    @GetMapping("/personnel")
    @Operation(summary = "取得人員")
    public ResponseEntity<?> getPersonnelList(@RequestParam(required = false) Integer comId,
                                              @RequestParam(required = false) String perEmail,
                                              @RequestParam(required = false) String perAccount,
                                              @RequestParam(required = false) String perNo,
                                              @RequestParam(required = false) String perPosition) {

        log.info("personnelList");
        BaseResponse<List<PersonnelEntity>> respDto = new BaseResponse<>();
        respDto.setData(personnelService.getPersonnelList(comId, perEmail, perAccount,
            perNo, perPosition));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/personnel/{perId}")
    @Operation(summary = "從ID取得人員")
    public ResponseEntity<?> getPersonnel(@PathVariable int perId) {

        log.info("personnel:perId=" + perId);
        BaseResponse<PersonnelEntity> respDto = new BaseResponse<>();
        respDto.setData(personnelService.getPersonnel(perId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/personnel")
    @Operation(summary = "新增人員")
    public ResponseEntity<?> addPersonnel(@RequestBody PersonnelEntity personnelEntity) {

        log.info("personnelAdd:=" + personnelEntity.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(personnelService.addPersonnel(personnelEntity));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/personnel")
    @Operation(summary = "更新人員")
    public ResponseEntity<?> editPersonnel(@RequestBody PersonnelEntity personnelEntity) {

        log.info("personnelEdit:=" + personnelEntity.toString());
        personnelService.editPersonnel(personnelEntity);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/personnel")
    @Operation(summary = "刪除人員")
    public ResponseEntity<?> deletePersonnel(@RequestParam("perId") int perId) {

        log.info("personnelDelete:=" + perId);
        BaseResponse<String> respDto = new BaseResponse<>();
        personnelService.deletePersonnel(perId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
