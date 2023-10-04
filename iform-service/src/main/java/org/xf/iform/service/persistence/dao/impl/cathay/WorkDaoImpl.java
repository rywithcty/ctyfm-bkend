package org.xf.iform.service.persistence.dao.impl.cathay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.WorkEntity;
import org.xf.iform.service.persistence.dao.cathay.WorkDao;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkDaoImpl extends BaseDaoImpl implements WorkDao {

    /**
     * @return List<WorkEntity>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<WorkEntity> findWorkAll() {
        return (List<WorkEntity>) findAll(WorkEntity.class);
    }

    /**
     * @return List<WorkEntity>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<WorkEntity> findWorkByFields(Integer worId, String worTitle) {
        Map<String, Object> paramMap = new HashMap<>();

        if (worId != null) paramMap.put("worId", worId);
        if (StringUtils.isNotBlank(worTitle)) paramMap.put("worTitle", worTitle);

        return (List<WorkEntity>)findByField(WorkEntity.class, paramMap);
    }

    /**
     * @return WorkEntity
     */
    @Override
    public WorkEntity findWorkById(Integer worId) {
        return (WorkEntity) findById(WorkEntity.class, worId);
    }

    /**
     */
    @Override
    public WorkEntity insertWork(WorkEntity workEntity) {
        return (WorkEntity) insert(workEntity);
    }

    /**
     */
    @Override
    public int updateWork(WorkEntity workEntity) {
        return update(workEntity);
    }

    /**
     */
    @Override
    public int deleteWork(Integer worId) {
        return deleteById(WorkEntity.class, worId);
    }
}
