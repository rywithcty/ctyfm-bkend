package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.ContactEntity;
import org.xf.iform.service.persistence.dao.cathay.ContactDao;
import org.xf.iform.service.services.cathay.ContactService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    /**
     */
    @Override
    public List<ContactEntity> getContactList(Integer cotId, Integer comId, String comCode) {
        if (cotId == null) return contactDao.findContactByFields(comId, comCode);
        List<ContactEntity> contactEntityList = new ArrayList<>();
        ContactEntity contactEntity = getContact(cotId);
        if (contactEntity != null) contactEntityList.add(getContact(cotId));
        return contactEntityList;
    }

    /**
     */
    @Override
    public ContactEntity getContact(Integer cotId) {
        return contactDao.findContactById(cotId);
    }

    /**
     */
    @Override
    @Transactional
    public int addContact(ContactEntity personnelEntity) {
        return contactDao.insertContact(personnelEntity).getCotId();
    }

    /**
     */
    @Override
    @Transactional
    public int editContact(ContactEntity personnelEntity) {
        return contactDao.updateContact(personnelEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deleteContact(Integer catId) {
        return contactDao.deleteContact(catId);
    }
}
