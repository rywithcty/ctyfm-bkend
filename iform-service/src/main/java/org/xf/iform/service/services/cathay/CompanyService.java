package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.CompanyEntity;

import java.util.List;

public interface CompanyService {
    List<CompanyEntity> getCompanyList(Integer comId, String comTitle);
    CompanyEntity getCompany(Integer comId);
    int addCompany(CompanyEntity companyEntity);
    int editCompany(CompanyEntity companyEntity);
    int deleteCompany(Integer comId);
}
