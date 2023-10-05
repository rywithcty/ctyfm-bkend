package org.xf.iform.controller.cathay;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.dto.cathay.TemplateDto;
import org.xf.iform.core.entity.cathay.TemplateEntity;
import org.xf.iform.service.data.mapper.cathay.TemplateMapper;
import org.xf.iform.service.services.cathay.TemplateService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/template")
    @Operation(summary = "取得樣板")
    public ResponseEntity<?> getTemplateList(@RequestParam(required = false) Integer temId,
                                              @RequestParam(required = false) String temTitle) {

        log.info("templateList: temId=" + temId + ", temTitle=" + temTitle);
        BaseResponse<List<TemplateEntity>> respDto = new BaseResponse<>();
        respDto.setData(templateService.getTemplateList(temId, temTitle));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/template/{temId}")
    @Operation(summary = "從ID取得樣板")
    public ResponseEntity<?> getTemplate(@PathVariable int temId) {

        log.info("template:temId=" + temId);
        BaseResponse<TemplateEntity> respDto = new BaseResponse<>();
        respDto.setData(templateService.getTemplate(temId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/template")
    @Operation(summary = "新增樣板")
    public ResponseEntity<?> addTemplate(@RequestBody TemplateDto templateDto) {
        TemplateEntity templateEntity = TemplateMapper.INSTANCE.toEntity(templateDto);
        log.info("templateAdd:=" + templateEntity.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(templateService.addTemplate(templateEntity));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/template")
    @Operation(summary = "更新樣板")
    public ResponseEntity<?> editTemplate(@RequestBody TemplateDto templateDto) {

        TemplateEntity templateEntity = TemplateMapper.INSTANCE.toEntity(templateDto);
        log.info("templateEdit:=" + templateEntity.toString());
        templateService.editTemplate(templateEntity);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/template")
    @Operation(summary = "刪除樣板")
    public ResponseEntity<?> deleteTemplate(@RequestParam int temId) {

        log.info("templateDelete:=" + temId);
        BaseResponse<String> respDto = new BaseResponse<>();
        templateService.deleteTemplate(temId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
