package org.xf.iform.service.data.mapper.contract;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.contract.MemberContractDto;
import org.xf.iform.service.data.po.contract.MemberContractPo;

import java.util.List;

@Mapper
public interface MemberContractMapper {
    MemberContractMapper INSTANCE = Mappers.getMapper(MemberContractMapper.class);

    MemberContractDto toDto(MemberContractPo memberContractPo);

    List<MemberContractDto> toDto(List<MemberContractPo> memberContractPoList);
}
