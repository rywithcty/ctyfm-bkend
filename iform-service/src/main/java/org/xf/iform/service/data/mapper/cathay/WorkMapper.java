package org.xf.iform.service.data.mapper.cathay;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.cathay.WorkDto;
import org.xf.iform.core.entity.cathay.WorkEntity;

@Mapper
public interface WorkMapper {
    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);

    WorkEntity toEntity(WorkDto workDto);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void memberUpdate(MemberDto memberDto, @MappingTarget MemberEntity memberEntity);
}
