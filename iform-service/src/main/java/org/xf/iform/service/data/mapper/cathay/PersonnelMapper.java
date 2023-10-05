package org.xf.iform.service.data.mapper.cathay;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.cathay.PersonnelDto;
import org.xf.iform.core.entity.cathay.PersonnelEntity;

@Mapper
public interface PersonnelMapper {
    PersonnelMapper INSTANCE = Mappers.getMapper(PersonnelMapper.class);

    PersonnelEntity toEntity(PersonnelDto personnelDto);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void memberUpdate(MemberDto memberDto, @MappingTarget MemberEntity memberEntity);
}
