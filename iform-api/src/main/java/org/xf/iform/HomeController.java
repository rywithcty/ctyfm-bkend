package org.xf.iform;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.service.data.dto.TestEmployeeDto;
import org.xf.iform.service.services.TestService;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class HomeController {

    @Value("${spring.profiles.active:}")
    private String profileActive;

    @RequestMapping("/")
    public String authenticateUser() {
        return profileActive + " - AP is Running!";
    }

    @GetMapping("/home")
    public ResponseEntity<?> anyAccess() {
	BaseResponse<String> respDto = new BaseResponse<>();
	respDto.setData("springboot is running");
	return ResponseEntity.ok(respDto);
    }
}
