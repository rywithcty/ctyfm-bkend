package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.entity.cathay.ItemEntity;

import java.util.List;

public interface ContractItemDao {
    public List<ItemEntity> findContractItemByContract(Integer ctId);

    public ItemEntity findContractItemById(Integer ctiId);

    public ItemEntity insertContractItem(ItemEntity itemEntity);

    public int updateContractItem(ItemEntity itemEntity);

    public int deleteContractItem(Integer ctiId);
    public int deleteContractItem(List<ItemEntity> itemEntityList);


}
