package org.xf.iform.service.data.mapper.report.contract;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.report.contract.RptExesDto;
import org.xf.iform.service.data.po.report.contract.RptExesPo;

import java.util.List;

@Mapper
public interface RptExesMapper {
    RptExesMapper INSTANCE = Mappers.getMapper(RptExesMapper.class);

    RptExesDto toDto(RptExesPo rptExesPo);

    List<RptExesDto> toDto(List<RptExesPo> rptExesPoList);
}
