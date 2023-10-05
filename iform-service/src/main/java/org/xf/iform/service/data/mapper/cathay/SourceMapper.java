package org.xf.iform.service.data.mapper.cathay;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.cathay.SourceDto;
import org.xf.iform.core.entity.cathay.SourceEntity;

@Mapper
public interface SourceMapper {
    SourceMapper INSTANCE = Mappers.getMapper(SourceMapper.class);

    SourceEntity toEntity(SourceDto sourceDto);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void memberUpdate(MemberDto memberDto, @MappingTarget MemberEntity memberEntity);
}
