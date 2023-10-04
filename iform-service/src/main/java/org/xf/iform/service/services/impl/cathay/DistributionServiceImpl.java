package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.DistributionEntity;
import org.xf.iform.service.persistence.dao.cathay.DistributionDao;
import org.xf.iform.service.services.cathay.DistributionService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Autowired
    private DistributionDao distributionDao;

    /**
     */
    @Override
    public List<DistributionEntity> getDistributionList(Integer worId, Integer disId, String disTitle) {
        if (worId == null) {
            disId = null;
            disTitle = null;
        }
        return distributionDao.findDistributionByFields(disId, disTitle);
    }

    /**
     */
    @Override
    public DistributionEntity getDistribution(Integer disId) {
        return distributionDao.findDistributionById(disId);
    }

    /**
     */
    @Override
    @Transactional
    public int addDistribution(DistributionEntity personnelEntity) {
        return distributionDao.insertDistribution(personnelEntity).getDisId();
    }

    /**
     */
    @Override
    @Transactional
    public int editDistribution(DistributionEntity personnelEntity) {
        return distributionDao.updateDistribution(personnelEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deleteDistribution(Integer disId) {
        return distributionDao.deleteDistribution(disId);
    }
}
