package org.xf.iform.service.services.user;

import org.xf.iform.core.entity.cathay.PersonnelEntity;

import java.util.Optional;

public interface LoginService {

	/**
	 * 使用帳號密碼登入使用員編orAD帳號
	 * @param userId
	 * @return
	 */
	public Optional<PersonnelEntity> loginPassword(String userId);

}
