package com.tr.demoboot.biz.api;


import com.tr.demoboot.biz.request.LoginRequest;

public interface UserOperationService {
	
	public Object login(LoginRequest loginRequest) throws Exception;

}