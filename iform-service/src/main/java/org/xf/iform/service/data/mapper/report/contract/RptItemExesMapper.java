package org.xf.iform.service.data.mapper.report.contract;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.report.contract.RptItemExesDto;
import org.xf.iform.service.data.po.report.contract.RptItemExesPo;

import java.util.List;

@Mapper
public interface RptItemExesMapper {
    RptItemExesMapper INSTANCE = Mappers.getMapper(RptItemExesMapper.class);

    RptItemExesDto toDto(RptItemExesPo rptItemExesPo);

    List<RptItemExesDto> toDto(List<RptItemExesPo> rptItemExesPoList);
}
