package org.xf.iform.service.services.impl.cathay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.PersonnelEntity;
import org.xf.iform.core.exception.CustomException;
import org.xf.iform.service.persistence.dao.cathay.PersonnelDao;
import org.xf.iform.service.services.cathay.PersonnelService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelDao personnelDao;

    /**
     */
    @Override
    public List<PersonnelEntity> getPersonnelList(Integer comId, String perEmail, String perAccount,
                                                  String perNo, String perPosition) {
        return personnelDao.findPersonnelByFields(comId, perEmail, perAccount,
            perNo, perPosition);
    }

    /**
     */
    @Override
    public PersonnelEntity getPersonnel(Integer perId) {
        return personnelDao.findPersonnelById(String.valueOf(perId)).orElseThrow(() -> new CustomException(CustomException.ErrorCode.NODATA_EXISTS, "使用者不存在"));
    }

    /**
     */
    @Override
    @Transactional
    public int addPersonnel(PersonnelEntity personnelEntity) {
        return personnelDao.insertPersonnel(personnelEntity).getPerId();
    }

    /**
     */
    @Override
    @Transactional
    public int editPersonnel(PersonnelEntity personnelEntity) {
        return personnelDao.updatePersonnel(personnelEntity);
    }

    /**
     */
    @Override
    @Transactional
    public int deletePersonnel(Integer perId) {
        return personnelDao.deletePersonnel(perId);
    }
}
