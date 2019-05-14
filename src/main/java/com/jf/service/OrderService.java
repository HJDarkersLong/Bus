package com.jf.service;

import java.util.List;

import com.jf.bean.OrderInfo;

public interface OrderService {
	
	/**
	 * 
	 * 方法说明:添加订单
	 *
	 * @param userInfo
	 * @return
	 * 
	 * @author  HJ 2019年5月13日
	 */
	boolean addOrder(OrderInfo orderInfo);
	
	List<OrderInfo> selectByUserId(String userId);

	Integer cancelOrder(String orderId);

}
