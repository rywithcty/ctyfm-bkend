package org.xf.iform.service.services.contract;

import org.xf.iform.core.dto.contract.MemberContractDto;
import org.xf.iform.core.dto.contract.MemberContractSearchDto;

import java.util.List;

public interface MemberContractService {
    public List<MemberContractDto> getMemberContractList(MemberContractSearchDto searchDto);
}
