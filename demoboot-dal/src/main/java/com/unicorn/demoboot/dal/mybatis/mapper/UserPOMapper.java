package com.unicorn.demoboot.dal.mybatis.mapper;

import com.unicorn.demoboot.dal.mybatis.model.UserPO;
import com.unicorn.demoboot.dal.mybatis.uo.UserUO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserPOMapper extends Mapper<UserPO> {
    @Select("SELECT u.user_name, d.address FROM user u, user_detail d WHERE u.user_id = d.user_id and u.user_id = #{userID}")
    @Results({
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "address", column = "address")
    })
    UserUO getOne(String userID);

}