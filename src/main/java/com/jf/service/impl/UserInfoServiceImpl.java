package com.jf.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.bean.UserInfo;
import com.jf.dao.UserInfoDao;
import com.jf.service.UserInfoService;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public boolean addUser(UserInfo userInfo) {
		UserInfo findByUserName = userInfoDao.findByUserName(userInfo.getUsername());
		if(Objects.isNull(findByUserName)) {
			
		}
		Integer result = userInfoDao.addUser(userInfo);
		if (result>0) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int findByUserName(String username) {
		UserInfo findByUserName = userInfoDao.findByUserName(username);
		if(!Objects.isNull(findByUserName)) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public UserInfo login(String username, String password) {
		return userInfoDao.login(username,password);
	}

}
