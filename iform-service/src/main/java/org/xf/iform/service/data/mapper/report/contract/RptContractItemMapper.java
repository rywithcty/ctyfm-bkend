package org.xf.iform.service.data.mapper.report.contract;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.xf.iform.core.dto.report.contract.RptContractItemDtoEntity;
import org.xf.iform.service.data.po.report.contract.RptContractItemPoEntity;

import java.util.List;

@Mapper
public interface RptContractItemMapper {
    RptContractItemMapper INSTANCE = Mappers.getMapper(RptContractItemMapper.class);

    RptContractItemDtoEntity toDto(RptContractItemPoEntity rptContractItemPo);

    List<RptContractItemDtoEntity> toDto(List<RptContractItemPoEntity> rptContractItemPoList);
}
