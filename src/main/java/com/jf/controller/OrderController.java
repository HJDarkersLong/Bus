package com.jf.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jf.bean.OrderInfo;
import com.jf.service.OrderService;

@RestController
@RequestMapping(value = "/bus/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/selectByUserId", method = RequestMethod.POST)
	@ResponseBody
	public void selectByUserId( @RequestBody  String username,HttpServletResponse response)
			throws ClientProtocolException, IOException {
		List<OrderInfo> selectByUserId = orderService.selectByUserId(username);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag","success");
		jsonObject.put("list",selectByUserId);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toJSONString());
		response.getWriter().close();
	}
	
	@RequestMapping(value ="/cancelOrder/{orderId}",method = RequestMethod.GET)
	public void cancelOrder(@PathVariable String orderId,HttpServletResponse response) throws IOException {
		String hintMessage;
		String flagMessage;
		JSONObject jsonObject = new JSONObject();
		Integer result = orderService.cancelOrder(orderId);
		if(result>0) {
			flagMessage = "success";
			hintMessage = "取消成功";
		}else {
			flagMessage = "fail";
			hintMessage = "取消失败";
		}
		jsonObject.put("flag",flagMessage);
		jsonObject.put("hintMessage",hintMessage);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toJSONString());
		response.getWriter().close();
	}
}
