package org.xf.iform.service.persistence.dao.cathay;

import org.xf.iform.core.entity.cathay.CompanyEntity;

import java.util.List;

public interface CompanyDao {
    List<CompanyEntity> findCompanyAll();

    List<CompanyEntity> findCompanyByFields(Integer comId, String comTitle);

    CompanyEntity findCompanyById(Integer comId);

    CompanyEntity insertCompany(CompanyEntity companyEntity);

    int updateCompany(CompanyEntity companyEntity);

    int deleteCompany(Integer comId);


}
