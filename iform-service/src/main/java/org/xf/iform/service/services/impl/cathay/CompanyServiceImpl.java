package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.CompanyEntity;
import org.xf.iform.service.persistence.dao.cathay.CompanyDao;
import org.xf.iform.service.services.cathay.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    /**
     */
    @Override
    public List<CompanyEntity> getCompanyList(Integer comId, String comTitle) {
        return companyDao.findCompanyByFields(comId, comTitle);
    }

    /**
     */
    @Override
    public CompanyEntity getCompany(Integer comId) {
        return companyDao.findCompanyById(comId);
    }

    /**
     */
    @Override
    @Transactional
    public int addCompany(CompanyEntity personnelEntity) {
        return companyDao.insertCompany(personnelEntity).getComId();
    }

    /**
     */
    @Override
    @Transactional
    public int editCompany(CompanyEntity personnelEntity) {
        return companyDao.updateCompany(personnelEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deleteCompany(Integer comId) {
        return companyDao.deleteCompany(comId);
    }
}
