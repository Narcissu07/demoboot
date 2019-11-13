package com.unicorn.demoboot.api.controller;

import com.unicorn.common.framework.enums.CommonResultCodeEnum;
import com.unicorn.common.framework.results.CommonResult;
import com.unicorn.demoboot.biz.api.UserOperationService;
import com.unicorn.demoboot.biz.request.LoginRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/usercenter")
public class UserOperationController{
	@Resource
	private UserOperationService userOperationService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult login(LoginRequest loginRequest) throws Exception {
		Object data = userOperationService.login(loginRequest);

		CommonResult commonResult = new CommonResult();
		commonResult.setCode(CommonResultCodeEnum.SUCCESS.getCode());
		commonResult.setMessage(CommonResultCodeEnum.SUCCESS.getMessage());
		commonResult.setData(data);

		return commonResult;
	}
}