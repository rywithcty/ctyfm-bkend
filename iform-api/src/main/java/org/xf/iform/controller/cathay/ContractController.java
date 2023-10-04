package org.xf.iform.controller.cathay;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.dto.contract.*;
import org.xf.iform.core.entity.cathay.ContractEntity;
import org.xf.iform.core.entity.cathay.ItemEntity;
import org.xf.iform.core.entity.ContractItemExes;
import org.xf.iform.service.services.cathay.ContractService;
import org.xf.iform.service.services.contract.MemberContractService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class ContractController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private MemberContractService memberContractService;

    @GetMapping("/contract")
    @Operation(summary = "取得文件列表")
    public ResponseEntity<?> getContractList(
        @RequestParam(required = false) Integer ctId, @RequestParam(required = false) Integer ctStatus,
        @RequestParam(required = false) Integer ctpId) {

        log.info("contractList");
        BaseResponse<List<ContractEntity>> respDto = new BaseResponse<>();
        respDto.setData(contractService.getContractList(ctId, ctStatus, ctpId));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/contract/{ctId}")
    @Operation(summary = "取得文件")
    public ResponseEntity<?> getContract(@PathVariable int ctId) {

        log.info("contract:ctId=" + ctId);
        BaseResponse<ContractEntity> respDto = new BaseResponse<>();
        respDto.setData(contractService.getContract(ctId));

        return ResponseEntity.ok(respDto);
    }

//    @PutMapping("/contract")
//    @Operation(summary = "編輯文件")
//    public ResponseEntity<?> editContract(@RequestBody @Valid ContractEditDto contractEditDto) {
//
//        log.info("contractEdit:=" + contractEditDto.toString());
//        contractService.editContract(contractEditDto);
//        BaseResponse<String> respDto = new BaseResponse<>();
//        respDto.setData("success");
//
//        return ResponseEntity.ok(respDto);
//    }

    @PostMapping("/contract")
    @Operation(summary = "新增文件")
    public ResponseEntity<?> addContractNew(@RequestBody @Valid ContractDto contractDto) {

        log.info("contractAdd:=" + contractDto.toString());
        BaseResponse<ContractDto> respDto = new BaseResponse<>();
        respDto.setData(contractService.addContractNew(contractDto));

        return ResponseEntity.ok(respDto);
    }

    @PutMapping("/contract")
    @Operation(summary = "編輯文件")
    public ResponseEntity<?> editContractNew(@RequestBody @Valid ContractDto contractDto) {

        log.info("contractAdd:=" + contractDto.toString());
        BaseResponse<ContractDto> respDto = new BaseResponse<>();
        respDto.setData(contractService.editContractNew(contractDto));

        return ResponseEntity.ok(respDto);
    }

    @PutMapping("/contract/updateStatus")
    @Operation(summary = "更新文件狀態")
    public ResponseEntity<?> editContractStatus(@RequestBody @Valid ContractEditStatusDto contractEditStatusDto) {

        log.info("contractEditStatus:=" + contractEditStatusDto.toString());
        contractService.editContractStatus(contractEditStatusDto);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @PutMapping("/contract/updateCtDate")
    @Operation(summary = "編輯文件")
    public ResponseEntity<?> editContractCtDate(@RequestBody @Valid ContractEditConDateDto contractEditConDateDto) {

        log.info("contractEditStatus:=" + contractEditConDateDto.toString());
        contractService.editContractCtDate(contractEditConDateDto);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/contract")
    @Operation(summary = "刪除文件")
    public ResponseEntity<?> deleteContract(@RequestParam int conId) {

        log.info("contractDelete:=" + conId);
        BaseResponse<String> respDto = new BaseResponse<>();
        contractService.deleteContract(conId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/getMemberContract")
    @Operation(summary = "取得人員文件列表")
    public ResponseEntity<?> getMemberContract(
        @RequestParam(required = false) Integer ctId , @RequestParam(required = false) Integer mbType,
        @RequestParam(required = false) Integer ssId , @RequestParam(required = false) String  mbLv0 ,
        @RequestParam(required = false) String  mbLv1, @RequestParam(required = false) String  mbLv2 ,
        @RequestParam(required = false) Integer mbStatus, @RequestParam(required = false) String  mbNow,
        @RequestParam(required = false) String  ctSerial, @RequestParam(required = false) String  plAccount,
        @RequestParam(required = false) String  ctTitle, @RequestParam(required = false) Integer ctType,
        @RequestParam(required = false) Integer ctStatus, @RequestParam(required = false) Integer ctpId) {

        MemberContractSearchDto searchDto = MemberContractSearchDto.builder()
            .ctId(ctId)
            .mbType(mbType)
            .ssId(ssId)
            .mbLv0(mbLv0)
            .mbLv1(mbLv1)
            .mbLv2(mbLv2)
            .mbStatus(mbStatus)
            .mbNow(mbNow)
            .ctSerial(ctSerial)
            .plAccount(plAccount)
            .ctTitle(ctTitle)
            .ctType(ctType)
            .ctStatus(ctStatus)
            .ctpId(ctpId)
            .build();
        log.info("getMemberContract");
        BaseResponse<List<MemberContractDto>> respDto = new BaseResponse<>();
        respDto.setData(memberContractService.getMemberContractList(searchDto));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/contract/getMemberContract")
    @Operation(summary = "取得人員文件列表")
    public ResponseEntity<?> getMemberContract(@RequestBody MemberContractSearchDto searchDto) {
        log.info("getMemberContract");
        BaseResponse<List<MemberContractDto>> respDto = new BaseResponse<>();
        respDto.setData(memberContractService.getMemberContractList(searchDto));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/contract/getContractItemSub")
    @Operation(summary = "取得項目預計分攤")
    public ResponseEntity<?> getContractItemSub(
        @RequestParam(required = false) Integer ctId, @RequestParam(required = false) Integer ctiId) {

        BaseResponse<List<ContractItemSubDto>> respDto = new BaseResponse<>();
        respDto.setData(contractService.getContractItemSub(ctId, ctiId));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/contract/getContractItem")
    @Operation(summary = "取得作業項目")
    public ResponseEntity<?> getContractItem(@RequestParam Integer ctId) {

        BaseResponse<List<ItemEntity>> respDto = new BaseResponse<>();
        respDto.setData(contractService.getContractItem(ctId));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/contract/getContractItemExec")
    @Operation(summary = "取得項目費用明細")
    public ResponseEntity<?> getContractItemExes(@RequestParam(required = false) Integer ctId,
                                                 @RequestParam(required = false) Integer ctiId,
                                                 @RequestParam(required = false) Integer cieId) {

        BaseResponse<List<ContractItemExes>> respDto = new BaseResponse<>();
        respDto.setData(contractService.getContractItemExes(ctId, ctiId, cieId));

        return ResponseEntity.ok(respDto);
    }
}
