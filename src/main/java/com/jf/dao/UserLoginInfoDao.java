package com.jf.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jf.bean.UserLoginInfo;

@Mapper
public interface UserLoginInfoDao {
	
	/**
	 * 
	 * 方法说明:添加
	 *
	 * @param userInfo
	 * 
	 * @author  HJ 2019年3月16日 
	 *
	 */
	@Insert("insert into userlogininfo(id,channel_token,open_id,czy_id,mobile,nick_name,sex,head_img_url,login_time,update_time)"
		  + "values (#{id},#{channelToken},#{openId},#{czyId},#{mobile},#{nickName},#{sex},#{headImgUrl},#{loginTime},#{updateTime})")
	Integer addUser(UserLoginInfo userInfo);
	
	/**
	 * 
	 * 方法说明:跟据id查询
	 *
	 * @param id
	 * @return
	 * 
	 * @author  HJ 2019年3月16日 
	 *
	 */
	@Select("select * from userinfo where id = #{id}")
	UserLoginInfo findById(@Param("id") int id);
	
	/**
	 * 
	 * 方法说明:跟据id删除
	 *
	 * @param id
	 * @return
	 * 
	 * @author  HJ 2019年3月16日 
	 *
	 */
	@Delete("delete from userinfo where id = #{id}")
	int deleteUser (int id ); 
}
