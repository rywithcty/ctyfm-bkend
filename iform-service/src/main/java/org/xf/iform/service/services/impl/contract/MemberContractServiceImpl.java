package org.xf.iform.service.services.impl.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.dto.contract.MemberContractDto;
import org.xf.iform.core.dto.contract.MemberContractSearchDto;
import org.xf.iform.service.data.mapper.contract.MemberContractMapper;
import org.xf.iform.service.data.po.contract.MemberContractPo;
import org.xf.iform.service.persistence.dao.contract.MemberContractDao;
import org.xf.iform.service.services.contract.MemberContractService;

import java.util.List;

@Slf4j
@Service
public class MemberContractServiceImpl implements MemberContractService {

    @Autowired
    private MemberContractDao memberContractDao;

    /**
     * @return
     */
    @Override
    public List<MemberContractDto> getMemberContractList(MemberContractSearchDto searchDto) {
        log.info("MemberContractSearch:" + searchDto);
        List<MemberContractPo> memberContractPoList = memberContractDao.findMemberContract(searchDto);
        log.info(memberContractPoList.toString());
        List<MemberContractDto> memberContractDtoList = MemberContractMapper.INSTANCE.toDto(memberContractPoList);
        log.info(memberContractDtoList.toString());
        return memberContractDtoList;
    }
}
