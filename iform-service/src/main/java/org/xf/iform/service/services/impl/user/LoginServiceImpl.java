package org.xf.iform.service.services.impl.user;

import org.xf.iform.core.entity.cathay.PersonnelEntity;
import org.xf.iform.service.services.user.LoginService;
import org.xf.iform.service.persistence.dao.cathay.PersonnelDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    PersonnelDao wrEmployeeDao;
    @Override
    public Optional<PersonnelEntity> loginPassword(String userId) {
        if (StringUtils.isNumeric(userId)) {
            return wrEmployeeDao.findPersonnelById(userId);
        } else {
            return wrEmployeeDao.findPersonnelById(StringUtils.upperCase(userId));
        }
    }
}
