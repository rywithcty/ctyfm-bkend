package org.xf.iform.service.services.impl.user;

import org.xf.iform.core.entity.cathay.PersonnelEntity;
import org.xf.iform.core.exception.CustomException;
import org.xf.iform.service.services.user.UserInfoService;
import org.xf.iform.service.data.dto.user.UserBasicInfoDto;
import org.xf.iform.service.persistence.dao.user.UserInfoDao;
import org.xf.iform.service.persistence.dao.cathay.PersonnelDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    PersonnelDao personnelDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    PasswordEncoder encoder;

    @Override
    @Transactional
    public void enableUser(String empolyeeId) {
//        PersonnelEntity personnel = wrEmployeeDao.findPersonnelById(empolyeeId).orElseThrow(() -> new CustomException(CustomException.ErrorCode.NODATA_EXISTS, "使用者不存在"));
//        if (StringUtils.equals("1",  personnel.getUseYn()))
//            throw new CustomException("使用者已啟用");
//        personnel.setPassword(encoder.encode(empolyeeId));
//        personnel.setPwdModtime(new Date());
//        personnel.setUseYn("1");
//        wrEmployeeDao.updateEmployee(personnel);

    }

    @Override
    public void resetPassword(String empolyeeId) {

        PersonnelEntity personnelEntity = personnelDao.findPersonnelById(empolyeeId).orElseThrow(() -> new CustomException(CustomException.ErrorCode.NODATA_EXISTS, "使用者不存在"));

//        if (StringUtils.equals("1",  personnelEntity.getUseYn()))
//            throw new CustomException("使用者未啟用，無法重設密碼");
//        personnelEntity.setPassword(encoder.encode(empolyeeId));
//        personnelEntity.setPwdModtime(new Date());
//        wrEmployeeDao.updateEmployee(personnelEntity);
    }

    @Transactional
    @Override
    public void changePassword(String userId, String password, String newPassword) {
//        PersonnelEntity personnel;
//        if (StringUtils.isNumeric(userId)) {
//            personnel = wrEmployeeDao.findPersonnelById(userId).orElseThrow(() -> new CustomException(CustomException.ErrorCode.NODATA_EXISTS, "使用者不存在"));
//        } else {
//            personnel = wrEmployeeDao.getUserByAd(userId).orElseThrow(() -> new CustomException(CustomException.ErrorCode.NODATA_EXISTS, "使用者不存在"));
//        }
//
//        if (!encoder.matches(password, personnel.getPassword())) {
//            throw new CustomException("密碼錯誤");
//        }
//        personnel.setPassword(encoder.encode(newPassword));
//        personnel.setPwdModtime(new Date());
//        wrEmployeeDao.updateEmployee(personnel);
    }
    @Override
    public UserBasicInfoDto getUserBasicInfo(String empolyeeId) {
        PersonnelEntity personnelEntity = personnelDao.findPersonnelById(empolyeeId).orElseThrow(() -> new CustomException("使用者不存在"));

        return UserBasicInfoDto.builder()
            .employeeId(empolyeeId)
            .cnName(personnelEntity.getPerNick())
            .adAccount(personnelEntity.getPerAccount())
            .modules(userInfoDao.getUserAuthModule(empolyeeId))
            .build();
    }

    @Transactional
    @Override
    public void userLogin(String userId, String password) {
        PersonnelEntity personnelEntity;
        personnelEntity = personnelDao.findPersonnelById(userId).orElseThrow(() -> new CustomException(CustomException.ErrorCode.NODATA_EXISTS, "使用者不存在"));

        //密碼先設同用戶名
        if (!encoder.matches(password, encoder.encode(personnelEntity.getPerNo()))) {
            throw new CustomException("密碼錯誤");
        }
    }
}
