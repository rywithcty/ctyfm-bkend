package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.SourceEntity;
import org.xf.iform.service.persistence.dao.cathay.SourceDao;
import org.xf.iform.service.services.cathay.SourceService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceDao sourceDao;

    /**
     */
    @Override
    public List<SourceEntity> getSourceList(Integer catId) {
        return sourceDao.findSourceByFields(catId);
    }

    /**
     */
    @Override
    public SourceEntity getSource(Integer souId) {
        return sourceDao.findSourceById(souId);
    }

    /**
     */
    @Override
    @Transactional
    public int addSource(SourceEntity personnelEntity) {
        return sourceDao.insertSource(personnelEntity).getSouId();
    }

    /**
     */
    @Override
    @Transactional
    public int editSource(SourceEntity sourceEntity) {
        return sourceDao.updateSource(sourceEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deleteSource(Integer souId) {
        return sourceDao.deleteSource(souId);
    }
}
