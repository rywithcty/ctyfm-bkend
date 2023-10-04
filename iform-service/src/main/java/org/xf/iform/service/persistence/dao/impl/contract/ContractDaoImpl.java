package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.ContractEntity;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.ContractDao;

import java.util.List;
import java.util.Map;

@Service
public class ContractDaoImpl extends BaseDaoImpl implements ContractDao {

    /**
     * @return
     */
    @Override
    public List<ContractEntity> findContractAll() {
        return (List<ContractEntity>) findAll(ContractEntity.class);
    }

    /**
     * @return
     */
    @Override
    public List<ContractEntity> findContractByFields(Map<String, Object> paramMap) {
        return (List<ContractEntity>) findByField(ContractEntity.class, paramMap);
    }

    /**
     * @param ctId
     * @return
     */
    @Override
    public ContractEntity findContractById(Integer ctId) {
        return (ContractEntity) findById(ContractEntity.class, ctId);
    }

    /**
     * @param contractEntity
     */
    @Override
    public ContractEntity insertContract(ContractEntity contractEntity) {
        return (ContractEntity) insert(contractEntity);
    }

    /**
     * @param contractEntity
     */
    @Override
    public int updateContract(ContractEntity contractEntity) {
        return update(contractEntity);
    }

    /**
     * @param ctId
     */
    @Override
    public int deleteContract(Integer ctId) {
        return deleteById(ContractEntity.class, ctId);
    }

}
