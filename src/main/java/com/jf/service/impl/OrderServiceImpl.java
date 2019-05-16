package com.jf.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jf.bean.BusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.bean.OrderInfo;
import com.jf.dao.OrderDao;
import com.jf.service.OrderService;

/**
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Value("${orderCount}")
	private String orderCount;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public synchronized int addOrder(OrderInfo orderInfo) {
		//先判断用户是否已经预约过该趟车次
		OrderInfo order=orderDao.selectByBusIdForDay(orderInfo.getBus_info_id(),orderInfo.getOrder_date(),orderInfo.getUserId());
		if(order!=null)
			return -2;//已经预约了
		//再判断该趟车次是否已经预约满员了（45人）
		int ordercount=orderDao.selectByBusId(orderInfo.getBus_info_id(),orderInfo.getOrder_date());
		if(ordercount >= Integer.parseInt(orderCount))
			return -1;//预约人数满了
		Integer result = orderDao.addOrder(orderInfo);
		if(result == 1) {
			return result;
		}else {
			return -3;//预约失败
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

	@Override
	public List<BusInfo> selectBusInfo() {
		return orderDao.selectBusInfo();
	}

	@Override
	public List<OrderInfo> getCurrOrderInfo(String orderDate, String bus_info_id) {
		return orderDao.getCurrOrderInfo(orderDate, bus_info_id);
	}


}
