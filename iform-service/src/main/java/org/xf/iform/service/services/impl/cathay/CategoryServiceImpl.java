package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.CategoryEntity;
import org.xf.iform.service.persistence.dao.cathay.CategoryDao;
import org.xf.iform.service.services.cathay.CategoryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     */
    @Override
    public List<CategoryEntity> getCategoryList(Integer catId, String catTitle) {
        return categoryDao.findCategoryByFields(catId, catTitle);
    }

    /**
     */
    @Override
    public CategoryEntity getCategory(Integer catId) {
        return categoryDao.findCategoryById(catId);
    }

    /**
     */
    @Override
    @Transactional
    public int addCategory(CategoryEntity personnelEntity) {
        return categoryDao.insertCategory(personnelEntity).getCatId();
    }

    /**
     */
    @Override
    @Transactional
    public int editCategory(CategoryEntity personnelEntity) {
        return categoryDao.updateCategory(personnelEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deleteCategory(Integer catId) {
        return categoryDao.deleteCategory(catId);
    }
}
