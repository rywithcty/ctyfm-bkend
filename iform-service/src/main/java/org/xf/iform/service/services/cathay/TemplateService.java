package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.TemplateEntity;

import java.util.List;

public interface TemplateService {
    List<TemplateEntity> getTemplateList(Integer temId, String temTitle);
    TemplateEntity getTemplate(Integer temId);
    int addTemplate(TemplateEntity templateEntity);
    int editTemplate(TemplateEntity templateEntity);
    int deleteTemplate(Integer temId);
}
