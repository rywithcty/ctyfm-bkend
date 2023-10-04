package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getCategoryList(Integer catId, String catTitle);
    CategoryEntity getCategory(Integer catId);
    int addCategory(CategoryEntity companyEntity);
    int editCategory(CategoryEntity companyEntity);
    int deleteCategory(Integer catId);
}
