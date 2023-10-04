package org.xf.iform.service.persistence.dao.impl.cathay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.CategoryEntity;
import org.xf.iform.service.persistence.dao.cathay.CategoryDao;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

    /**
     * @return List<CategoryEntity>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CategoryEntity> findCategoryAll() {
        return (List<CategoryEntity>) findAll(CategoryEntity.class);
    }

    /**
     * @return List<CategoryEntity>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CategoryEntity> findCategoryByFields(Integer catId, String catTitle) {
        Map<String, Object> paramMap = new HashMap<>();

        if (catId != null) paramMap.put("catId", catId);
        if (StringUtils.isNotBlank(catTitle)) paramMap.put("catTitle", catTitle);
        return (List<CategoryEntity>)findByField(CategoryEntity.class, paramMap);
    }

    /**
     * @return CategoryEntity
     */
    @Override
    public CategoryEntity findCategoryById(Integer catId) {
        return (CategoryEntity) findById(CategoryEntity.class, catId);
    }

    /**
     */
    @Override
    public CategoryEntity insertCategory(CategoryEntity categoryEntity) {
        return (CategoryEntity) insert(categoryEntity);
    }

    /**
     */
    @Override
    public int updateCategory(CategoryEntity categoryEntity) {
        return update(categoryEntity);
    }

    /**
     */
    @Override
    public int deleteCategory(Integer catId) {
        return deleteById(CategoryEntity.class, catId);
    }
}
