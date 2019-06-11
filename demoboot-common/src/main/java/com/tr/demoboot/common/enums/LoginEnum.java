package com.tr.demoboot.common.enums;

public enum LoginEnum {

	//编码都以21开头
	USER_NOT_EXIST("2101","用户名或者密码有误"),

	OTHER_CODE("2102","其他校验错误");
	//返回码
	private final String	code;
	//返回码描述
	private final String	message;
	
	/**
	 * 构造一个<code>CommonCodeEnum</code>枚举对象
	 * @param code
	 * @param message
	 */
	private LoginEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	

	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	/**
	 * 通过枚举<code>code</code>获得枚举
	 * @param code
	 * @return CommonCodeEnum
	 */
	public static LoginEnum getByCode(String code) {
		for (LoginEnum _enum : values()) {
			if (_enum.getCode().equals(code)) {
				return _enum;
			}
		}
		return null;
	}
	
}

