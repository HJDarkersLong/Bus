package com.jf.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jf.bean.UserInfo;
import com.jf.bean.UserLoginInfo;

@Mapper
public interface UserInfoDao {

	/**
	 * 
	 * 方法说明:添加
	 *
	 * @param userInfo
	 * 
	 * @author HJ 2019年3月16日
	 *
	 */
	@Insert("insert into user_info(id,username,password,role,nickname) values (UUID(),#{username},#{password},#{role},#{nickname})")
	Integer addUser(UserInfo userInfo);
	
	@Select("select * from user_info where username = #{username}")
	UserInfo findByUserName(@Param("username") String username);

	@Select("select * from user_info where username = #{username} and password=#{password}")
	UserInfo login(@Param("username") String username,@Param("password") String password);

}
