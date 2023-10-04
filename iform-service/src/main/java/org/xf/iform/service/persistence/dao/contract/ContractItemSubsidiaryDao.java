package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.dto.contract.ContractItemSubDto;
import org.xf.iform.core.entity.cathay.RatioEntity;

import java.util.List;
import java.util.Map;

public interface ContractItemSubsidiaryDao {
    public List<RatioEntity> findContractItemSubsidiaryByContractItem(Integer ctiId);

    public RatioEntity insertContractItemSubsidiary(RatioEntity ratioEntity);

    public int updateContractItemSubsidiary(RatioEntity ratioEntity);

    public int deleteContractItemSubsidiary(Integer ctisId);

    public int deleteContractItemSubsidiary(List<RatioEntity> ratioEntityList);

    public List<ContractItemSubDto> getContractItemSubList(Map<String, Object> paramMap);
}
