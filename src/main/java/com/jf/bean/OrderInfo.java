package com.jf.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderInfo")
public class OrderInfo {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(length = 128)
	private String id;
	
	/**
	 * 车次id
	 */
	@Column(length = 128)
	private String bus_info_id;
	
	@Column(length = 128)
	private String bus_info_name;
	
	/**
	 * 用户id
	 */
	@Column(length = 128)
	private String userId;
	
	private String username;
	
	private String nickname;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBus_info_id() {
		return bus_info_id;
	}

	public void setBus_info_id(String bus_info_id) {
		this.bus_info_id = bus_info_id;
	}

	public String getBus_info_name() {
		return bus_info_name;
	}

	public void setBus_info_name(String bus_info_name) {
		this.bus_info_name = bus_info_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return updatetime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updatetime = updateTime;
	}

	public Timestamp getCreateTime() {
		return createtime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createtime = createTime;
	}
}
