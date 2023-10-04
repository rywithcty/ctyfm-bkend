package org.xf.iform.service.persistence.dao.cathay;

import org.xf.iform.core.entity.cathay.ContactEntity;

import java.util.List;

public interface ContactDao {
    List<ContactEntity> findContactAll();

    List<ContactEntity> findContactByFields(Integer comId, String comCode);

    ContactEntity findContactById(Integer cotId);

    ContactEntity insertContact(ContactEntity contactEntity);

    int updateContact(ContactEntity contactEntity);

    int deleteContact(Integer cotId);


}
