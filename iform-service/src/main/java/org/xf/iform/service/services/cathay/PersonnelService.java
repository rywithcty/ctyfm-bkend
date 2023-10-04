package org.xf.iform.service.services.cathay;

import org.xf.iform.core.entity.cathay.PersonnelEntity;

import java.util.List;

public interface PersonnelService {
    List<PersonnelEntity> getPersonnelList(Integer comId, String perEmail, String perAccount,
                                                  String perNo, String perPosition);
    PersonnelEntity getPersonnel(Integer perId);
    int addPersonnel(PersonnelEntity personnelEntity);
    int editPersonnel(PersonnelEntity personnelEntity);
    int deletePersonnel(Integer perId);
}
