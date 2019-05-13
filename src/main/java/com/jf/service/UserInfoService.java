package com.jf.service;

import com.jf.bean.UserInfo;
import com.jf.bean.UserLoginInfo;

public interface UserInfoService {
	/**
	 * 
	 * 方法说明:添加用户
	 *
	 * @param userInfo
	 * @return
	 * 
	 * @author  HJ 2019年5月13日
	 */
	boolean addUser(UserInfo userInfo);
	
	/**
	 * 
	 * 方法说明：
	 *
	 * @param userInfo
	 * @return
	 * 
	 * @author  HJ 2019年5月13日
	 */
	int findByUserName(String username);

	UserInfo login(String username, String password);
}
