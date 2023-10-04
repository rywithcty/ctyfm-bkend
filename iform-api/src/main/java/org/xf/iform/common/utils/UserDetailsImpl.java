package org.xf.iform.common.utils;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.xf.iform.core.entity.cathay.PersonnelEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String username;

	@JsonIgnore
	private String password;

	private Date pwdModtime;
	
	public UserDetailsImpl(String username, String password, Date pwdModtime) {
		this.username = username;
		this.password = password;
		this.pwdModtime = pwdModtime;
	}

	public static UserDetailsImpl build(PersonnelEntity personnelEntity) {

//		return new UserDetailsImpl(personnelEntity.getEmployeeId(), personnelEntity.getPassword(), personnelEntity.getPwdModtime());
		return new UserDetailsImpl(personnelEntity.getPerNo(), personnelEntity.getPerNo(), new Date());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Date getPwdModtime() {
		return pwdModtime;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
