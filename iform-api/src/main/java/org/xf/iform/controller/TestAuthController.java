package org.xf.iform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.xf.iform.common.utils.JwtUtils;
import org.xf.iform.common.utils.UserDetailsImpl;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.data.dto.JwtResponseDto;
import org.xf.iform.data.dto.LoginDto;
import org.xf.iform.data.dto.MessageResponseDto;
import org.xf.iform.data.dto.SignupDto;
import org.xf.iform.service.services.user.UserInfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class TestAuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserInfoService userInfoService;
  
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword()));
    HttpSession session = request.getSession();
    session.setAttribute("userId", loginDto.getUserId());
    BaseResponse<String> respDto = new BaseResponse<>();
//    respDto.setData(new JwtResponseDto(jwt, userDetails.getUsername()));
    return ResponseEntity.ok(respDto);
  }

  @PostMapping("/enableUser")
  public ResponseEntity<?> enableUser (@RequestParam("empolyeeId") String empolyeeId) {
    BaseResponse<String> respDto = new BaseResponse<>();
    userInfoService.enableUser(empolyeeId);
    respDto.setMessage("使用者:" + empolyeeId + " 啟用成功");
    return ResponseEntity.ok(respDto);
  }
}
