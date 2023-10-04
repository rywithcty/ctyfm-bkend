package org.xf.iform.service.persistence.dao.user;

import org.xf.iform.service.data.po.user.ModulePo;

import java.util.List;

public interface UserInfoDao {
	/**
	 * 取得權限資訊
	 * @param employeeId
	 * @return
	 */
	public List<ModulePo> getUserAuthModule(String employeeId);

}
