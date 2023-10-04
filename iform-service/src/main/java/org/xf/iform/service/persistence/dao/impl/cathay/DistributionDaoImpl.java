package org.xf.iform.service.persistence.dao.impl.cathay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.DistributionEntity;
import org.xf.iform.service.persistence.dao.cathay.DistributionDao;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistributionDaoImpl extends BaseDaoImpl implements DistributionDao {

    /**
     * @return List<DistributionEntity>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<DistributionEntity> findDistributionAll() {
        return (List<DistributionEntity>) findAll(DistributionEntity.class);
    }

    /**
     * @return List<DistributionEntity>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DistributionEntity> findDistributionByFields(Integer disId, String disTitle) {
        Map<String, Object> paramMap = new HashMap<>();

        if (disId != null) paramMap.put("disId", disId);
        if (StringUtils.isNotBlank(disTitle)) paramMap.put("disTitle", disTitle);

        return (List<DistributionEntity>)findByField(DistributionEntity.class, paramMap);
    }

    /**
     * @return DistributionEntity
     */
    @Override
    public DistributionEntity findDistributionById(Integer disId) {
        return (DistributionEntity) findById(DistributionEntity.class, disId);
    }

    /**
     */
    @Override
    public DistributionEntity insertDistribution(DistributionEntity distributionEntity) {
        return (DistributionEntity) insert(distributionEntity);
    }

    /**
     */
    @Override
    public int updateDistribution(DistributionEntity distributionEntity) {
        return update(distributionEntity);
    }

    /**
     */
    @Override
    public int deleteDistribution(Integer disId) {
        return deleteById(DistributionEntity.class, disId);
    }
}
