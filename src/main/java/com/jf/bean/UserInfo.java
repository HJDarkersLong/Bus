package com.jf.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userInfo")
public class UserInfo {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(length = 128)
	private String id;
	
	/**
	 * 昵称
	 */
	@Column(length = 128)
	private String nickname;
	
	/**
	 * 用户名
	 */
	@Column(length = 128)
	private String username;
	
	/**
	 * 密码
	 */
	@Column(length = 128)
	private String password;
	
	/**
	 * 密码
	 */
	@Column(length = 10)
	private String role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickName) {
		this.nickname = nickName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
