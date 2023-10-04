package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.WorkEntity;

import java.util.List;

public interface WorkService {
    public List<WorkEntity> getWorkList(Integer worId, String worTitle);
    public WorkEntity getWork(Integer worId);
    public int addWork(WorkEntity workEntity);
    public int editWork(WorkEntity workEntity);
    public int deleteWork(Integer worId);
}
