package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.entity.ContractItemExes;

import java.util.List;

public interface ContractItemExesDao {
    public ContractItemExes findContractItemById(Integer cieId);

    public List<ContractItemExes> findContractItemByContractItem(Integer ctiId);

    public ContractItemExes insertContractItemExes(ContractItemExes contractItemExes);

    public int updateContractItemExes(ContractItemExes contractItemExes);

    public int deleteContractItemExes(Integer cieId);

    public int deleteContractItemExes(List<ContractItemExes> contractItemExesList);


}
