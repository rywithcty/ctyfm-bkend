package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.dto.contract.MemberContractSearchDto;
import org.xf.iform.service.data.po.contract.MemberContractPo;

import java.util.List;

public interface MemberContractDao {
    public List<MemberContractPo> findMemberContract(MemberContractSearchDto searchDto);

}
