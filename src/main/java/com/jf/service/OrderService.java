package com.jf.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jf.bean.BusInfo;
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
	int   addOrder(OrderInfo orderInfo);

	List<OrderInfo> selectByUserId(String userId);
	
	List<OrderInfo> getCurrOrderInfo(String orderDate,String bus_info_id);

	Integer cancelOrder(String orderId);

	List<BusInfo> selectBusInfo();


}
