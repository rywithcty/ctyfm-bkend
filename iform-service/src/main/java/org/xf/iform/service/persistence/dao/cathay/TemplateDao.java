package org.xf.iform.service.persistence.dao.cathay;

import org.xf.iform.core.entity.cathay.TemplateEntity;

import java.util.List;

public interface TemplateDao {
    public List<TemplateEntity> findTemplateByFields(Integer temId, String temTitle);

    public TemplateEntity findTemplateById(Integer ctpId);

    public TemplateEntity insertTemplate(TemplateEntity templateEntity);

    public int updateTemplate(TemplateEntity templateEntity);

    public int deleteTemplate(Integer ctpId);


}
