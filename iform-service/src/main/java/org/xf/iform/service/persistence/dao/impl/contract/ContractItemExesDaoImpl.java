package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Repository;
import org.xf.iform.core.entity.ContractItemExes;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.ContractItemExesDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContractItemExesDaoImpl extends BaseDaoImpl implements ContractItemExesDao {


    /**
     * @param cieId
     * @return
     */
    @Override
    public ContractItemExes findContractItemById(Integer cieId) {
        return (ContractItemExes) findById(ContractItemExes.class, cieId);
    }

    /**
     * @param ctiId
     * @return
     */
    @Override
    public List<ContractItemExes> findContractItemByContractItem(Integer ctiId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ctiId", ctiId);
        List<ContractItemExes> contractItemExesList = (List<ContractItemExes>) findByField(ContractItemExes.class, paramMap);
        return contractItemExesList;
    }

    /**
     * @param contractItemExes
     * @return
     */
    @Override
    public ContractItemExes insertContractItemExes(ContractItemExes contractItemExes) {
        return (ContractItemExes) insert(contractItemExes);
    }

    /**
     * @param contractItemExes
     * @return
     */
    @Override
    public int updateContractItemExes(ContractItemExes contractItemExes) {
        return update(contractItemExes);
    }

    /**
     * @param cieId
     * @return
     */
    @Override
    public int deleteContractItemExes(Integer cieId) {
        return deleteById(ContractItemExes.class, cieId);
    }

    /**
     * @param contractItemExesList
     * @return
     */
    @Override
    public int deleteContractItemExes(List<ContractItemExes> contractItemExesList) {
        return deleteList(contractItemExesList);
    }
}
