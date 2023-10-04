package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.entity.cathay.ContractEntity;

import java.util.List;
import java.util.Map;

public interface ContractDao {
    public List<ContractEntity> findContractAll();

    public List<ContractEntity> findContractByFields(Map<String, Object> paramMap);
    public ContractEntity findContractById(Integer ctId);

    public ContractEntity insertContract(ContractEntity contractEntity);

    public int updateContract(ContractEntity contractEntity);

    public int deleteContract(Integer ctId);


}
