package org.xf.iform.service.data.mapper.cathay;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.cathay.CategoryDto;
import org.xf.iform.core.entity.cathay.CategoryEntity;

@Mapper
public interface CategroyMapper {
    CategroyMapper INSTANCE = Mappers.getMapper(CategroyMapper.class);

    CategoryEntity toEntity(CategoryDto categoryDto);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void memberUpdate(MemberDto memberDto, @MappingTarget MemberEntity memberEntity);
}
