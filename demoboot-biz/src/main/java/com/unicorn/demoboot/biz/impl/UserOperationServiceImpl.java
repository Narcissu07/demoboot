package com.unicorn.demoboot.biz.impl;

import com.alibaba.fastjson.JSON;
import com.unicorn.common.framework.api.CommonHttpClient;
import com.unicorn.common.framework.enums.CommonResultCodeEnum;
import com.unicorn.common.framework.exceptions.BusinessException;
import com.unicorn.common.framework.exceptions.RemoteDataException;
import com.unicorn.demoboot.biz.api.UserOperationService;
import com.unicorn.demoboot.biz.bo.LoginBO;
import com.unicorn.demoboot.biz.dto.MockDTO;
import com.unicorn.demoboot.biz.request.LoginRequest;
import com.unicorn.demoboot.common.config.BizConfig;
import com.unicorn.demoboot.common.enums.LoginEnum;
import com.unicorn.demoboot.dal.mybatis.mapper.UserPOMapper;
import com.unicorn.demoboot.dal.mybatis.model.UserPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserOperationServiceImpl implements UserOperationService {
	@Resource
	private UserPOMapper userPOMapper;
	
	@Resource
	CommonHttpClient commonHttpClient;

	@Resource
	BizConfig bizConfig;

	@Transactional
	public Object login(LoginRequest loginRequest) throws Exception {
		//1、流程规范，先业务校验和配置校验，再进行业务处理
		//1.1、业务校验样例
		String userName = loginRequest.getUserName();
		UserPO userPOParam = new UserPO();
		userPOParam.setUserName(userName);
		List<UserPO> userPOList = userPOMapper.select(userPOParam);
		//数据库唯一索引保证不会多条，这里只校验存在性
		if(userPOList.size()==0){
			throw new BusinessException(LoginEnum.USER_NOT_EXIST.getCode(), LoginEnum.USER_NOT_EXIST.getMessage());
		}


		//1.2、配置校验样例
		//由于目前使用springboot，通过@Value形式注入从配置文件读取，如果有误，则启动时则会报错，故运行时可不用考虑配置文件问题
		//因此这里主要考虑数据库配置表相关校验，如该业务无数据库相关配置表，则省略该步骤
		//注：这里省略相关查询配置表样例
//		if(true){
//			throw new ConfigException(CommonResultCodeEnum.CONFIG_ERROR.getCode(), "xxx配置有误");
//		}

		//2、业务处理，数据库查询样例
		//插入例子
//		UserPO userPO = new UserPO();
//		userPO.setUserId("6");
//		userPO.setUserName("user6");
//		userPO.setPassword("123456");
//		userPOMapper.insert(userPO);

		//主键查询例子
//		UserPO userPO = userPOMapper.selectByPrimaryKey("2");
//		System.out.println("--------userName:" + userPO.getUserName());

		//条件查询例子
//		UserPO userPO = new UserPO();
//		userPO.setUserName("yangjie");
//		List<UserPO> userPOList = userPOMapper.select(userPO);
//		System.out.println("--------size:" + userPOList.size());

		//分页例子
//		PageHelper.startPage(0, 2);
//		List<UserPO> userPOList = userPOMapper.selectAll();
//		System.out.println("--------size:" + userPOList.size());

		//联合查询例子
//		UserUO userUO = userPOMapper.getOne("1");
//		System.out.println("--------userName:" + userUO.getUserName());


		//3、涉及到外部接口调用业务处理样例
		//@Transactional去掉，涉及到外部接口，暂不开启事务处理
		//涉及到外部接口调用必须遵循规范，先insert保存任务，再执行相关外部调用，然后update结果，最后再封装结果返回

		//3.1 db insert，略
		//3.2 调用外部接口，以json格式返回举例
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userName", userName);
		String msg = commonHttpClient.get(bizConfig.mockUrl, paramMap);
		MockDTO mockDTO;
		//解析Json格式错误
		try {
			mockDTO = JSON.parseObject(msg, MockDTO.class);
		} catch (Exception e) {
			throw new RemoteDataException(CommonResultCodeEnum.REMOTE_DATA_ERROR.getCode(), CommonResultCodeEnum.REMOTE_DATA_ERROR.getMessage());
		}
		String code = mockDTO.getCode();
		//假定Mock状态里，1代表成功，2代表失败，其他编码代表其他异常
		//返回码有误
		if(StringUtils.isEmpty(code)|| (!code.equals("1") && !code.equals("2"))){
			throw new RemoteDataException(CommonResultCodeEnum.REMOTE_DATA_ERROR.getCode(), CommonResultCodeEnum.REMOTE_DATA_ERROR.getMessage());
		}
		//外部服务处理失败
		else if(code.equals("2")){
			throw new RemoteDataException(CommonResultCodeEnum.REMOTE_SERVICE_ERROR.getCode(), CommonResultCodeEnum.REMOTE_SERVICE_ERROR.getMessage());
		}
		//外部服务处理成功继续往下执行
		//3.3 db update，略
		//3.4 db 封装结果返回样例
		LoginBO loginBO = new LoginBO();
		UserPO userPO = userPOList.get(0);
		BeanUtils.copyProperties(userPO, loginBO);
		loginBO.setOtherData(mockDTO.getMockData());


		//4、模拟其他bug异常，例如空指针，数组越界等
//		String[] arr = new String[1];
//		arr[1] = "xxx";

		return loginBO;
	}
	
}
