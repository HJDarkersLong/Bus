package com.jf.service;

import com.jf.bean.UserLoginInfo;

public interface UserLoginInfoService {

	/**
	 * 
	 * 方法说明:新增用户
	 *
	 * @param userInfo
	 * @return
	 * 
	 * @author  HJ 2019年3月16日 
	 *
	 */
	boolean addUser(UserLoginInfo userInfo);
	
	/**
	 * 
	 * 方法说明:跟据id查找用户
	 *
	 * @param userId
	 * @return
	 * 
	 * @author  HJ 2019年3月16日 
	 *
	 */
	UserLoginInfo findById (int userId);
	
	/**
	 * 
	 * 方法说明:跟据id删除用户
	 *
	 * @param id
	 * @return
	 * 
	 * @author  HJ 2019年3月16日 
	 *
	 */
	boolean deleteUser (int id) ;
	
}
