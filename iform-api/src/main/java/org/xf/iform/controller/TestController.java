package org.xf.iform.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.xf.iform.common.util.SeqUtils;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.service.data.dto.TestEmployeeDto;
import org.xf.iform.service.services.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rileytest")
public class TestController {
    
    @Autowired
    TestService testService;
    
    @GetMapping("/accessCheck")
    public ResponseEntity<?> anyAccess() {
	BaseResponse<String> respDto = new BaseResponse<>();
	respDto.setData("Board.");
	return ResponseEntity.ok(respDto);
    }
    
    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathParam("employeeId") String employeeId) throws Exception {
	log.info("getEmployee:" + employeeId );
	BaseResponse<TestEmployeeDto> respDto = new BaseResponse<>();
	respDto.setData(testService.getEmployee(employeeId));
	return ResponseEntity.ok(respDto);
    }
    
    @GetMapping("/getEmployeePage")
    public ResponseEntity<?> getEmployeePage(@RequestParam("page") int page,@RequestParam("size") int size) throws Exception {
	BaseResponse<List<TestEmployeeDto>> respDto = new BaseResponse<>();
	respDto.setData(testService.getEmployeePage(page, size));
	return ResponseEntity.ok(respDto);
    }

    @GetMapping("/getContractSeq")
    @Operation(summary = "取得文件編號")
    public ResponseEntity<?> getContractSeq() {

        log.info("getContractSeq");
        BaseResponse<Integer> respDto = new BaseResponse<>();

        respDto.setData(SeqUtils.nextVal(SeqUtils.SeqType.CONTRACT_SEQ));

        return ResponseEntity.ok(respDto);
    }
}
