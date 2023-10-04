package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.ContactEntity;

import java.util.List;

public interface ContactService {
    List<ContactEntity> getContactList(Integer cotId, Integer comId, String comCode);
    ContactEntity getContact(Integer cotId);
    int addContact(ContactEntity companyEntity);
    int editContact(ContactEntity companyEntity);
    int deleteContact(Integer cotId);
}
