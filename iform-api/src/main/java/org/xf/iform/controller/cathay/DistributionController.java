package org.xf.iform.controller.cathay;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.entity.cathay.DistributionEntity;
import org.xf.iform.service.services.cathay.DistributionService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class DistributionController {

    @Autowired
    private DistributionService distributionService;

    @GetMapping("/distribution")
    @Operation(summary = "取得分類原則")
    public ResponseEntity<?> getDistributionList(@RequestParam(required = false) Integer worId,
                                                 @RequestParam(required = false) Integer disId,
                                                 @RequestParam(required = false) String disTitle) {

        log.info("distributionList: disId=" + disId + ", disTitle=" + disTitle);
        BaseResponse<List<DistributionEntity>> respDto = new BaseResponse<>();
        respDto.setData(distributionService.getDistributionList(worId, disId, disTitle));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/distribution/{disId}")
    @Operation(summary = "從ID取得分類原則")
    public ResponseEntity<?> getDistribution(@PathVariable int disId) {

        log.info("distribution:disId=" + disId);
        BaseResponse<DistributionEntity> respDto = new BaseResponse<>();
        respDto.setData(distributionService.getDistribution(disId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/distribution")
    @Operation(summary = "新增分類原則")
    public ResponseEntity<?> addDistribution(@RequestBody DistributionEntity distributionEntity) {

        log.info("distributionAdd:=" + distributionEntity.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(distributionService.addDistribution(distributionEntity));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/distribution")
    @Operation(summary = "更新分類原則")
    public ResponseEntity<?> editDistribution(@RequestBody DistributionEntity distributionEntity) {

        log.info("distributionEdit:=" + distributionEntity.toString());
        distributionService.editDistribution(distributionEntity);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/distribution")
    @Operation(summary = "刪除分類原則")
    public ResponseEntity<?> deleteDistribution(@RequestParam int disId) {

        log.info("distributionDelete:=" + disId);
        BaseResponse<String> respDto = new BaseResponse<>();
        distributionService.deleteDistribution(disId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
