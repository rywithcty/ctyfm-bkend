package org.xf.iform.controller.adm;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.dto.contract.MemberDto;
import org.xf.iform.core.entity.cathay.MemberEntity;
import org.xf.iform.service.services.contract.MemberService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/adm")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/getCtMember/{ctId}")
    @Operation(summary = "取得文件成員")
    public ResponseEntity<?> getMemberList(@PathVariable int ctId) {

        log.info("getCtMember:ctId="+ctId);
        BaseResponse<List<MemberDto>> respDto = new BaseResponse<>();
        respDto.setData(memberService.getMemberByCtId(ctId));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/getMember/{mbId}")
    @Operation(summary = "取得文件成員")
    public ResponseEntity<?> getMember(@PathVariable int mbId) {

        log.info("member:mbId=" + mbId);
        BaseResponse<MemberDto> respDto = new BaseResponse<>();
        respDto.setData(memberService.getMember(mbId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/member/add")
    @Operation(summary = "新增文件成員")
    public ResponseEntity<?> addMember(@RequestBody MemberEntity memberEntity) {

        log.info("memberAdd:=" + memberEntity.toString());
        BaseResponse<MemberDto> respDto = new BaseResponse<>();
        respDto.setData(memberService.addMember(memberEntity));

        return ResponseEntity.ok(respDto);
    }

    @PutMapping("/member/update")
    @Operation(summary = "編輯文件成員")
    public ResponseEntity<?> editMember(@RequestBody @Valid MemberDto member) {

        log.info("memberEdit:=" + member.toString());
        BaseResponse<MemberDto> respDto = new BaseResponse<>();

        respDto.setData(memberService.editMember(member));

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/member/delete")
    @Operation(summary = "刪除文件成員")
    public ResponseEntity<?> deleteMember(@RequestParam("mbId") int mbId) {

        log.info("memberDelete:mbId=" + mbId);
        BaseResponse<String> respDto = new BaseResponse<>();
        memberService.deleteMember(mbId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
