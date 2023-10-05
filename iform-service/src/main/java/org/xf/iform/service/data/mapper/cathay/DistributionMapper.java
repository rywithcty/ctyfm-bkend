package org.xf.iform.service.data.mapper.cathay;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.cathay.DistributionDto;
import org.xf.iform.core.entity.cathay.DistributionEntity;

@Mapper
public interface DistributionMapper {
    DistributionMapper INSTANCE = Mappers.getMapper(DistributionMapper.class);

    DistributionEntity toEntity(DistributionDto distributionDto);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void memberUpdate(MemberDto memberDto, @MappingTarget MemberEntity memberEntity);
}
