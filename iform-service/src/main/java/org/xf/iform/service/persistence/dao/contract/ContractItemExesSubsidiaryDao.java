package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.entity.ContractItemExesSubsidiary;

import java.util.List;

public interface ContractItemExesSubsidiaryDao {
    public List<ContractItemExesSubsidiary> findContractItemExesSubByContractExesItem(Integer cieId);

    public ContractItemExesSubsidiary insertContractItemExesSubsidiary(ContractItemExesSubsidiary contractItemExesSubsidiary);

    public int updateContractItemExesSubsidiary(ContractItemExesSubsidiary contractItemExesSubsidiary);

    public int deleteContractItemExesSubsidiary(Integer ciesId);

    public int deleteContractItemExesSubsidiary(List<ContractItemExesSubsidiary> contractItemExesSubsidiaryList);


}
