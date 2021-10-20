package com.dkne.metabang.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
	private String account;
	private String email;
	
	@Builder
	public UserUpdateRequestDto(String account , String email) {
		this.account = account;
		this.email = email;
	}
}