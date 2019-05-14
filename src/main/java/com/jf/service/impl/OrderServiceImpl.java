package com.jf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.bean.OrderInfo;
import com.jf.dao.OrderDao;
import com.jf.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public boolean addOrder(OrderInfo orderInfo) {
		Integer result = orderDao.addOrder(orderInfo);
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<OrderInfo> selectByUserId(String userId) {
		return orderDao.selectByUserId(userId);
	}

	@Override
	public Integer cancelOrder(String orderId) {
		return orderDao.cancelOrder(orderId);
	}

}
