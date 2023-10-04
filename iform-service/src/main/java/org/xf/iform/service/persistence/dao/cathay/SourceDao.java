package org.xf.iform.service.persistence.dao.cathay;

import org.xf.iform.core.entity.cathay.SourceEntity;

import java.util.List;

public interface SourceDao {
    List<SourceEntity> findSourceAll();

    SourceEntity findSourceById(Integer souId);

    List<SourceEntity> findSourceByFields(Integer catId);

    SourceEntity insertSource(SourceEntity sourceEntity);

    int updateSource(SourceEntity sourceEntity);

    int deleteSource(Integer souId);


}
