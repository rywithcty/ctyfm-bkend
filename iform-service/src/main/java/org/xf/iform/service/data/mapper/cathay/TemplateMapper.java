package org.xf.iform.service.data.mapper.cathay;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.cathay.TemplateDto;
import org.xf.iform.core.entity.cathay.TemplateEntity;

@Mapper
public interface TemplateMapper {
    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    TemplateEntity toEntity(TemplateDto sourceDto);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void memberUpdate(MemberDto memberDto, @MappingTarget MemberEntity memberEntity);
}
