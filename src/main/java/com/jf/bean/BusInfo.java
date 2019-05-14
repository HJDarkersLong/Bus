package com.jf.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="busInfo")
public class BusInfo{
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(length = 128)
	private String id;

	@Column(length = 128)
	private String name;

	@Column(length = 128)
	private String open_time;
	
	/**
	 * 状态：预约成功，取消成功
	 */
	@Column(length = 10)
	private String status;
	
	/**
	 * 更新时间
	 */
	@Column(length = 128)
	private Timestamp updatetime;
	
	/**
	 * 创建时间
	 */
	@Column(length = 128)
	private Timestamp createtime;

	private String orderDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

}
