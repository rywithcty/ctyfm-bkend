package org.xf.iform.controller;


import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.common.utils.UserDetailsImpl;
import org.xf.iform.service.data.dto.user.UserBasicInfoDto;
import org.xf.iform.service.services.user.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/getCurUserBasicInfo")
    @Operation(summary = "取得使用者基本資訊")
    public ResponseEntity<?> getUserBasicInfo(/*String employeeId*/HttpServletRequest request) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();

        log.info("getUserBasicInfo:userId=" + userId);
        BaseResponse<UserBasicInfoDto> respDto = new BaseResponse<>();
        respDto.setData(userInfoService.getUserBasicInfo(userId));

        return ResponseEntity.ok(respDto);
    }

    @PutMapping("/enableUser")
    @Operation(summary = "啟用使用者")
    public ResponseEntity<?> enableUser (@RequestParam("empolyeeId") String empolyeeId) {
        BaseResponse<String> respDto = new BaseResponse<>();
        userInfoService.enableUser(empolyeeId);
        respDto.setMessage("使用者:" + empolyeeId + " 啟用成功");
        return ResponseEntity.ok(respDto);
    }

    @PutMapping("/resetPassword")
    @Operation(summary = "重置使用者密碼")
    public ResponseEntity<?> resetPassword (@RequestParam("empolyeeId") String empolyeeId) {
        BaseResponse<String> respDto = new BaseResponse<>();
        userInfoService.resetPassword(empolyeeId);
        respDto.setMessage("使用者:" + empolyeeId + " 密碼重設成功");

        return ResponseEntity.ok(respDto);
    }
}
