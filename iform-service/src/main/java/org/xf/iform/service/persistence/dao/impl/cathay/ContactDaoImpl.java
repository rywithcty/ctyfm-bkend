package org.xf.iform.service.persistence.dao.impl.cathay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.ContactEntity;
import org.xf.iform.service.persistence.dao.cathay.ContactDao;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactDaoImpl extends BaseDaoImpl implements ContactDao {

    /**
     * @return List<ContactEntity>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ContactEntity> findContactAll() {
        return (List<ContactEntity>) findAll(ContactEntity.class);
    }

    /**
     * @return List<ContactEntity>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ContactEntity> findContactByFields(Integer comId, String comCode) {
        Map<String, Object> paramMap = new HashMap<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CT.* FROM `contact` CT");
        sql.append("  LEFT JOIN `company` C ON C.`comId` = CT.`comId`");
        sql.append(" WHERE 1 = 1 ");
        if (comId != null) {
            sql.append("  AND C.`comId` = :comId");
            paramMap.put("comId", comId);
        }
        if (comId != null) {
            sql.append("  AND C.`comCode` = :comCode");
            paramMap.put("comCode", comCode);
        }
        return (List<ContactEntity>) findBySqlParam(sql.toString(), paramMap, ContactEntity.class);
    }

    /**
     * @return ContactEntity
     */
    @Override
    public ContactEntity findContactById(Integer cotId) {
        return (ContactEntity) findById(ContactEntity.class, cotId);
    }

    /**
     */
    @Override
    public ContactEntity insertContact(ContactEntity contactEntity) {
        return (ContactEntity) insert(contactEntity);
    }

    /**
     */
    @Override
    public int updateContact(ContactEntity contactEntity) {
        return update(contactEntity);
    }

    /**
     */
    @Override
    public int deleteContact(Integer cotId) {
        return deleteById(ContactEntity.class, cotId);
    }
}
