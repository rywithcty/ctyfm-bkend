package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Repository;
import org.xf.iform.core.entity.cathay.ItemEntity;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.ContractItemDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContractItemDaoImpl extends BaseDaoImpl implements ContractItemDao {


    /**
     * @param ctId
     * @return
     */
    @Override
    public List<ItemEntity> findContractItemByContract(Integer ctId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ctId", ctId);
        List<ItemEntity> itemEntityList = (List<ItemEntity>) findByField(ItemEntity.class, paramMap);
        return itemEntityList;
    }

    /**
     * @param ctiId
     * @return
     */
    @Override
    public ItemEntity findContractItemById(Integer ctiId) {
        return (ItemEntity) findById(ItemEntity.class, ctiId);
    }

    /**
     * @param itemEntity
     * @return
     */
    @Override
    public ItemEntity insertContractItem(ItemEntity itemEntity) {
        return (ItemEntity) insert(itemEntity);
    }

    /**
     * @param itemEntity
     * @return
     */
    @Override
    public int updateContractItem(ItemEntity itemEntity) {
        return update(itemEntity);
    }

    /**
     * @param ctiId
     * @return
     */
    @Override
    public int deleteContractItem(Integer ctiId) {
        return deleteById(ItemEntity.class, ctiId);
    }

    /**
     * @param itemEntityList
     * @return
     */
    @Override
    public int deleteContractItem(List<ItemEntity> itemEntityList) {
        return deleteList(itemEntityList);
    }
}
