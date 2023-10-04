package org.xf.iform.data.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDto {
	@NotBlank(message = "請輸入使用者帳號")
	private String userId;

	@NotBlank(message = "請輸入密碼")
	private String password;

}
