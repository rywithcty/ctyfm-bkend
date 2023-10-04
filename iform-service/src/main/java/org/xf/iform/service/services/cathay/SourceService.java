package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.SourceEntity;

import java.util.List;

public interface SourceService {
    List<SourceEntity> getSourceList(Integer catId);
    SourceEntity getSource(Integer souId);
    int addSource(SourceEntity sourceEntity);
    int editSource(SourceEntity sourceEntity);
    int deleteSource(Integer souId);
}
