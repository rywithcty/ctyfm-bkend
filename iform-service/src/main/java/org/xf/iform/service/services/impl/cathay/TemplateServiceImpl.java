package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.TemplateEntity;
import org.xf.iform.service.persistence.dao.cathay.TemplateDao;
import org.xf.iform.service.services.cathay.TemplateService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDao templateDao;

    /**
     */
    @Override
    public List<TemplateEntity> getTemplateList(Integer temId, String temTitle) {
        return templateDao.findTemplateByFields(temId, temTitle);
    }

    /**
     */
    @Override
    public TemplateEntity getTemplate(Integer temId) {
        return templateDao.findTemplateById(temId);
    }

    /**
     */
    @Override
    @Transactional
    public int addTemplate(TemplateEntity personnelEntity) {
        return templateDao.insertTemplate(personnelEntity).getTemId();
    }

    /**
     */
    @Override
    @Transactional
    public int editTemplate(TemplateEntity personnelEntity) {
        return templateDao.updateTemplate(personnelEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deleteTemplate(Integer temId) {
        return templateDao.deleteTemplate(temId);
    }
}
