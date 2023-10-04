package org.xf.iform.service.persistence.dao.impl.cathay;

import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.TemplateEntity;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.cathay.TemplateDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TemplateDaoImpl extends BaseDaoImpl implements TemplateDao {

    /**
     * @return
     */
    @Override
    public List<TemplateEntity> findTemplateByFields(Integer temId, String temTitle) {

        Map<String, Object> paramMap = new HashMap<>();

        if (temId != null) paramMap.put("temId", temId);
        if (temTitle != null) paramMap.put("temTitle", temTitle);

        return (List<TemplateEntity>)findByField(TemplateEntity.class, paramMap);
    }

    /**
     * @param ctpId
     * @return
     */
    @Override
    public TemplateEntity findTemplateById(Integer ctpId) {
        return (TemplateEntity) findById(TemplateEntity.class, ctpId);
    }

    /**
     * @param templateEntity
     */
    @Override
    public TemplateEntity insertTemplate(TemplateEntity templateEntity) {
        return (TemplateEntity) insert(templateEntity);
    }

    /**
     * @param templateEntity
     */
    @Override
    public int updateTemplate(TemplateEntity templateEntity) {
        return update(templateEntity);
    }

    /**
     * @param ctpId
     */
    @Override
    public int deleteTemplate(Integer ctpId) {
        return deleteById(TemplateEntity.class, ctpId);
    }
}
