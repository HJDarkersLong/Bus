package com.jf.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jf.bean.BusInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jf.bean.OrderInfo;

public interface OrderDao {
	
	/**
	 * 
	 * 方法说明:添加
	 *
	 * @param userInfo
	 * 
	 * @author HJ 2019年3月16日
	 *
	 */
	@Insert("insert into order_info(id,bus_info_id,bus_info_name,status,user_id,order_date,create_time,open_time) values (UUID(),#{bus_info_id},#{bus_info_name},#{status},#{userId},#{order_date},#{createTime},#{open_time})")
	Integer addOrder(OrderInfo orderInfo);
	
	@Select("select * from order_info o  left join user_info u on (o.user_id = u.id) where u.username = #{userId} group by o.id order by o.create_time desc limit 10 ")
	List<OrderInfo>  selectByUserId(@Param("userId") String userId);

	@Update("update order_info set status = 0 where id = #{orderId}")
	Integer cancelOrder(String orderId);

	@Select("select count(0) from order_info where status=1 and bus_info_id=#{busid} and  order_date=#{orderDate} order by createtime asc ")
	int  selectByBusId(@Param("busid") String busid,@Param("orderDate") String orderDate);

	@Select("select * from order_info where status=1 and bus_info_id=#{busid} and  order_date=#{orderDate} and user_id=#{userId}")
	OrderInfo  selectByBusIdForDay(@Param("busid") String busid,@Param("orderDate") String orderDate,@Param("userId") String userId);


	@Select("select * from bus_info where status=1 ")
	List<BusInfo> selectBusInfo();

	@Select("select * from user_info u  left join order_info o on (o.user_id = u.id) where o.order_date = #{orderDate} and o.bus_info_id =#{bus_info_id} and status =1 ")
	List<OrderInfo>  getCurrOrderInfo(@Param("orderDate") String orderDate,@Param("bus_info_id") String bus_info_id);
}
