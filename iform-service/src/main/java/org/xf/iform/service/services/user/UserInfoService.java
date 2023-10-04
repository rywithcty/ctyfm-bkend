package org.xf.iform.service.services.user;

import org.xf.iform.service.data.dto.user.UserBasicInfoDto;

public interface UserInfoService {
    /**
     * 啟用使用者
     * @param empolyeeId
     */
    public void enableUser(String empolyeeId);

    /**
     * 重設使用者密碼
     * @param empolyeeId
     */
    public void resetPassword(String empolyeeId);

    /**
     * 變更使用者密碼
     * @param userId
     * @param password
     * @param newPassword
     */
    public void changePassword(String userId, String password, String newPassword);
    /**
     * 取得使用者基本資訊
     *
     * @param empolyeeId
     * @return
     */
    public UserBasicInfoDto getUserBasicInfo(String empolyeeId);

    /**
     * 使用者登入
     * @param userId
     * @param password
     */
    public void userLogin(String userId, String password);
}
