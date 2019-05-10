package com.jf.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.bean.UserLoginInfo;
import com.jf.dao.UserLoginInfoDao;
import com.jf.service.UserLoginInfoService;

@Service
@Transactional
public class UserLoginInfoServiceImpl implements UserLoginInfoService {

	@Autowired
	private UserLoginInfoDao userLoginInfoDao;
	
	@Override
	public boolean addUser(UserLoginInfo userInfo) {
		boolean flag = false;
		try {
			userLoginInfoDao.addUser(userInfo);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public UserLoginInfo findById(int userId) {
		return userLoginInfoDao.findById(userId);
	}

	@Override
	public boolean deleteUser(int id) {
		boolean flag = false;
		try {
			userLoginInfoDao.deleteUser(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	

}
