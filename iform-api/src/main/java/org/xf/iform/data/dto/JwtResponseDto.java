package org.xf.iform.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {
	private String token;
	private String type = "Bearer";
	@JsonIgnore
	private String username;

	public JwtResponseDto(String accessToken, String username) {
		this.token = accessToken;
		this.username = username;
	}

}
