package org.xf.iform.service.persistence.dao.cathay;

import org.xf.iform.core.entity.cathay.CategoryEntity;

import java.util.List;

public interface CategoryDao {
    List<CategoryEntity> findCategoryAll();

    List<CategoryEntity> findCategoryByFields(Integer catId, String catTitle);

    CategoryEntity findCategoryById(Integer catId);

    CategoryEntity insertCategory(CategoryEntity categoryEntity);

    int updateCategory(CategoryEntity categoryEntity);

    int deleteCategory(Integer catId);


}
