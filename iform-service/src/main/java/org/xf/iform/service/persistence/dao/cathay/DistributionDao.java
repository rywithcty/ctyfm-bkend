package org.xf.iform.service.persistence.dao.cathay;

import org.xf.iform.core.entity.cathay.DistributionEntity;

import java.util.List;

public interface DistributionDao {
    List<DistributionEntity> findDistributionAll();

    List<DistributionEntity> findDistributionByFields(Integer disId, String disTitle);

    DistributionEntity findDistributionById(Integer disId);

    DistributionEntity insertDistribution(DistributionEntity distributionEntity);

    int updateDistribution(DistributionEntity distributionEntity);

    int deleteDistribution(Integer disId);


}
