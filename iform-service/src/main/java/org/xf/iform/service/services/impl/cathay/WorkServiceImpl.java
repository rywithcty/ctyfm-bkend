package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.WorkEntity;
import org.xf.iform.service.persistence.dao.cathay.WorkDao;
import org.xf.iform.service.services.cathay.WorkService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkDao workDao;

    /**
     */
    @Override
    public List<WorkEntity> getWorkList(Integer worId, String worTitle) {
        return workDao.findWorkByFields(worId, worTitle);
    }

    /**
     */
    @Override
    public WorkEntity getWork(Integer worId) {
        return workDao.findWorkById(worId);
    }

    /**
     */
    @Override
    @Transactional
    public int addWork(WorkEntity personnelEntity) {
        return workDao.insertWork(personnelEntity).getWorId();
    }

    /**
     */
    @Override
    @Transactional
    public int editWork(WorkEntity personnelEntity) {
        return workDao.updateWork(personnelEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deleteWork(Integer worId) {
        return workDao.deleteWork(worId);
    }
}
