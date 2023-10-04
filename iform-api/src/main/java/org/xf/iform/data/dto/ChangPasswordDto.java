package org.xf.iform.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangPasswordDto {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String newPassword;

}
