package com.jf.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.bean.BusInfo;
import org.apache.http.ParseException;
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

	/**
	 * 预约
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value ="/saveOrder",method = RequestMethod.POST)
	public void saveOrder(@RequestBody OrderInfo order,HttpServletResponse response) throws IOException {
		String hintMessage;
		String flagMessage;
		order.setCreateTime(new Timestamp(new Date().getTime()));
		order.setStatus("1");//预约成功1 取消预约 0
		JSONObject jsonObject = new JSONObject();
		int result = orderService.addOrder(order);
		if(result==1) {
			flagMessage = "success";
			hintMessage = "预约成功";
		}else {
			flagMessage = "fail";
			hintMessage = "预约失败";
		}
		jsonObject.put("code",result);
		jsonObject.put("flag",flagMessage);
		jsonObject.put("hintMessage",hintMessage);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toJSONString());
		response.getWriter().close();
	}


	/**
			* 获取bus
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value ="/getBusInfo",method = RequestMethod.POST)
	public void getBusInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<BusInfo>  list=orderService.selectBusInfo();
		List<BusInfo>  list_=orderService.selectBusInfo();
		List<BusInfo>  buslist=new ArrayList<>();
		for(BusInfo bus:list){
			bus.setOrderDate(dateFormat(new Date(),DATE_PATTERN));
			buslist.add(bus);
		}
		for(BusInfo bus:list_){
			bus.setOrderDate(dateFormat(dateAdd(new Date(),1,false),DATE_PATTERN));
			buslist.add(bus);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("buslist",buslist);
		jsonObject.put("code",1);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toJSONString());
		response.getWriter().close();
	}



		public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
		public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
		public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
		public static final String DATE_PATTERN = "yyyy-MM-dd";
		public static final String MONTH_PATTERN = "yyyy-MM";
		public static final String YEAR_PATTERN = "yyyy";
		public static final String MINUTE_ONLY_PATTERN = "mm";
		public static final String HOUR_ONLY_PATTERN = "HH";

		/**
		 * 日期相加减天数
		 *
		 * @param date        如果为Null，则为当前时间
		 * @param days        加减天数
		 * @param includeTime 是否包括时分秒,true表示包含
		 * @return
		 * @throws ParseException
		 */
		public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException {
			if (date == null) {
				date = new Date();
			}
			if (!includeTime) {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
				try {
					date = sdf.parse(sdf.format(date));
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, days);
			return cal.getTime();
		}

		/**
		 * 时间格式化成字符串
		 *
		 * @param date    Date
		 * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
		 * @return
		 * @throws ParseException
		 */
		public static String dateFormat(Date date, String pattern) throws ParseException {
			if (pattern==null) {
				pattern = DATE_PATTERN;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
}
