package org.xf.iform.service.persistence.dao.cathay;

import org.xf.iform.core.entity.cathay.WorkEntity;

import java.util.List;

public interface WorkDao {
    List<WorkEntity> findWorkAll();

    List<WorkEntity> findWorkByFields(Integer worId, String worTitle);

    WorkEntity findWorkById(Integer worId);

    WorkEntity insertWork(WorkEntity workEntity);

    int updateWork(WorkEntity workEntity);

    int deleteWork(Integer worId);


}
