package com.unicorn.demoboot.biz.api;


import com.unicorn.demoboot.biz.request.LoginRequest;

public interface UserOperationService {
	
	public Object login(LoginRequest loginRequest) throws Exception;

}