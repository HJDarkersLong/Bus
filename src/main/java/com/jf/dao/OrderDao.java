package com.jf.dao;

import java.util.List;

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
	@Insert("insert into order_info(id,bus_info_id,status,user_id,create_time,update_time) values (UUID(),#{busInfoId},#{status},#{userId},#{createTime},#{updateTime})")
	Integer addOrder(OrderInfo orderInfo);
	
	@Select("select * from order_info o  left join user_info u on (o.user_id = u.username) where u.username = #{userId} group by o.id limit 10 ")
	List<OrderInfo>  selectByUserId(@Param("userId") String userId);

	@Update("update order_info set status = 0 where id = #{orderId}")
	Integer cancelOrder(String orderId);
	
	

}
