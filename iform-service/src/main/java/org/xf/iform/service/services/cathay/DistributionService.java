package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.DistributionEntity;

import java.util.List;

public interface DistributionService {
    List<DistributionEntity> getDistributionList(Integer worId, Integer disId, String disTitle);
    DistributionEntity getDistribution(Integer disId);
    int addDistribution(DistributionEntity distributionEntity);
    int editDistribution(DistributionEntity distributionEntity);
    int deleteDistribution(Integer disId);
}
