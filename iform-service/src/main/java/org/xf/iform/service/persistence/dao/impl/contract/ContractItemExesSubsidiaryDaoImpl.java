package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Repository;
import org.xf.iform.core.entity.ContractItemExesSubsidiary;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.ContractItemExesSubsidiaryDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContractItemExesSubsidiaryDaoImpl extends BaseDaoImpl implements ContractItemExesSubsidiaryDao {


    /**
     * @param cieId
     * @return
     */
    @Override
    public List<ContractItemExesSubsidiary> findContractItemExesSubByContractExesItem(Integer cieId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cieId", cieId);
        List<ContractItemExesSubsidiary> contractItemExesSubsidiaryList = (List<ContractItemExesSubsidiary>) findByField(ContractItemExesSubsidiary.class, paramMap);
        return contractItemExesSubsidiaryList;
    }

    /**
     * @param contractItemExesSubsidiary
     * @return
     */
    @Override
    public ContractItemExesSubsidiary insertContractItemExesSubsidiary(ContractItemExesSubsidiary contractItemExesSubsidiary) {
        return (ContractItemExesSubsidiary) insert(contractItemExesSubsidiary);
    }

    /**
     * @param contractItemExesSubsidiary
     * @return
     */
    @Override
    public int updateContractItemExesSubsidiary(ContractItemExesSubsidiary contractItemExesSubsidiary) {
        return update(contractItemExesSubsidiary);
    }

    /**
     * @param ciesId
     * @return
     */
    @Override
    public int deleteContractItemExesSubsidiary(Integer ciesId) {
        return deleteById(ContractItemExesSubsidiary.class, ciesId);
    }

    /**
     * @param contractItemExesSubsidiaryList
     * @return
     */
    @Override
    public int deleteContractItemExesSubsidiary(List<ContractItemExesSubsidiary> contractItemExesSubsidiaryList) {
        return deleteList(contractItemExesSubsidiaryList);
    }
}
