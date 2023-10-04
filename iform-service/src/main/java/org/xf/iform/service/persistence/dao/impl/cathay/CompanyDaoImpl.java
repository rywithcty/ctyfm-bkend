package org.xf.iform.service.persistence.dao.impl.cathay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.CompanyEntity;
import org.xf.iform.service.persistence.dao.cathay.CompanyDao;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyDaoImpl extends BaseDaoImpl implements CompanyDao {

    /**
     * @return List<CompanyEntity>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CompanyEntity> findCompanyAll() {
        return (List<CompanyEntity>) findAll(CompanyEntity.class);
    }

    /**
     * @return List<CompanyEntity>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CompanyEntity> findCompanyByFields(Integer comId, String comTitle) {
        Map<String, Object> paramMap = new HashMap<>();

        if (comId != null) paramMap.put("comId", comId);
        if (StringUtils.isNotBlank(comTitle)) paramMap.put("comTitle", comTitle);

        return (List<CompanyEntity>)findByField(CompanyEntity.class, paramMap);
    }

    /**
     * @return CompanyEntity
     */
    @Override
    public CompanyEntity findCompanyById(Integer comId) {
        return (CompanyEntity) findById(CompanyEntity.class, comId);
    }

    /**
     */
    @Override
    public CompanyEntity insertCompany(CompanyEntity companyEntity) {
        return (CompanyEntity) insert(companyEntity);
    }

    /**
     */
    @Override
    public int updateCompany(CompanyEntity companyEntity) {
        return update(companyEntity);
    }

    /**
     */
    @Override
    public int deleteCompany(Integer comId) {
        return deleteById(CompanyEntity.class, comId);
    }
}
