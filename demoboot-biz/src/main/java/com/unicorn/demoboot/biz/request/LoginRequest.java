package com.unicorn.demoboot.biz.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	@Length(max=64,min=1)
	private String userName;
	
	@NotBlank
	@Length(max=64,min=2)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
