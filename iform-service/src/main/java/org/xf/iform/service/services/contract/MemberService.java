package org.xf.iform.service.services.contract;

import org.xf.iform.core.dto.contract.MemberDto;
import org.xf.iform.core.entity.cathay.MemberEntity;

import java.util.List;

public interface MemberService {
    public List<MemberDto> getMemberByCtId(int ctId);
    public MemberDto getMember(Integer mbId);
    public MemberDto addMember(MemberEntity memberEntity);
    public MemberDto editMember(MemberDto member);
    public int deleteMember(Integer mbId);
}
