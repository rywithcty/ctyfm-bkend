package org.xf.iform.service.services.report.contract;

import org.xf.iform.core.dto.report.contract.RptContractItemDtoEntity;
import org.xf.iform.core.dto.report.contract.RptExesDto;
import org.xf.iform.core.dto.report.contract.RptItemExesDto;

import java.util.List;

public interface RptContractService {
    List<RptContractItemDtoEntity> getContractItemReport(Integer ctStatus);

    List<Integer> getExesYear();

    List<RptItemExesDto> getItemExes(Integer ctStatus);

    List<RptExesDto>getExes(Integer ctStatus);
}
