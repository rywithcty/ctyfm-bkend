package org.xf.iform.service.persistence.dao.impl.cathay;

import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.SourceEntity;
import org.xf.iform.service.persistence.dao.cathay.SourceDao;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SourceDaoImpl extends BaseDaoImpl implements SourceDao {

    /**
     * @return List<SourceEntity>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SourceEntity> findSourceAll() {
        return (List<SourceEntity>) findAll(SourceEntity.class);
    }

    /**
     * @return List<SourceEntity>
     */
    @SuppressWarnings("unchecked")
    public List<SourceEntity> findSourceByFields(Integer catId) {
        Map<String, Object> paramMap = new HashMap<>();

        if (catId != null) paramMap.put("catId", catId);

        return (List<SourceEntity>)findByField(SourceEntity.class, paramMap);
    }

    /**
     * @return SourceEntity
     */
    @Override
    public SourceEntity findSourceById(Integer souId) {
        return (SourceEntity) findById(SourceEntity.class, souId);
    }

    /**
     */
    @Override
    public SourceEntity insertSource(SourceEntity categoryEntity) {
        return (SourceEntity) insert(categoryEntity);
    }

    /**
     */
    @Override
    public int updateSource(SourceEntity categoryEntity) {
        return update(categoryEntity);
    }

    /**
     */
    @Override
    public int deleteSource(Integer souId) {
        return deleteById(SourceEntity.class, souId);
    }
}
