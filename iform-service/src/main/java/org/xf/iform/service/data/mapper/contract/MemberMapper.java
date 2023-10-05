package org.xf.iform.service.data.mapper.contract;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.contract.ContractMemberDto;
import org.xf.iform.core.dto.contract.MemberAddDto;
import org.xf.iform.core.dto.contract.MemberDto;
import org.xf.iform.core.entity.cathay.MemberEntity;

import java.util.List;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDto toDto(MemberEntity memberEntity);

    List<MemberDto> toDto(List<MemberEntity> memberEntityList);

    ContractMemberDto toContractMemberDto(MemberEntity memberEntity);
    List<ContractMemberDto> toContractMemberDto(List<MemberEntity> memberEntityList);

    MemberEntity addToEntity(MemberAddDto memberAddDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void memberUpdate(MemberDto memberDto, @MappingTarget MemberEntity memberEntity);
}
