package org.xf.iform.common.security.service;

import org.xf.iform.common.utils.UserDetailsImpl;
import org.xf.iform.core.entity.cathay.PersonnelEntity;
import org.xf.iform.service.services.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.xf.iform.core.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	LoginService loginService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("getUser=" + username);
//		Optional<WrEmployee> optWrEmployee = wrUserService.getUser(username);
//		Optional<WrEmployee> optWrEmployee = loginService.loginPassword(username);
//		log.info("optWrEmployee.isPresent=" + optWrEmployee.isPresent());
//		WrEmployee wrEmployee = wrUserService.getUser(username)
		PersonnelEntity personnelEntity = loginService.loginPassword(username)
				.orElseThrow(() -> new /*UsernameNotFoundException*/CustomException("使用者帳號或密碼錯誤: " + username));
		log.info(personnelEntity.toString());
		return UserDetailsImpl.build(personnelEntity);
	}

}
