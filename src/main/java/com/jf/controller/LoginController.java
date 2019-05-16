package com.jf.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jf.bean.UserInfo;
import com.jf.service.UserInfoService;

@Controller
@RequestMapping(value = "/bus/login")
public class LoginController {
	
//	@Value("${login_url}")
//	private String login_url;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private UserInfoService userInfoService;
	
	private static Logger log= LoggerFactory.getLogger(LoginController.class);
	

	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public String toIndex( Model model)
			throws ClientProtocolException, IOException {
		return "index";
	}
	
	@RequestMapping(value = "/toHome", method = RequestMethod.GET)
	public String toHome( Model model)
			throws ClientProtocolException, IOException {
		return "home";
	}
	
	@RequestMapping(value = "/toManager", method = RequestMethod.GET)
	public String toManager( Model model)
			throws ClientProtocolException, IOException {
		return "manager";
	}
	

	/**
	 * 
	 * 方法说明:注册页面
	 *
	 * @param user
	 * @param response
	 * @throws ClientProtocolException
	 * @throws IOException
	 * 
	 * @author  HJ 2019年5月13日
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register( @RequestBody UserInfo user,HttpServletResponse response)
			throws ClientProtocolException, IOException {
		String hintMessage;
		String flagMessage;
		int findByUserName = userInfoService.findByUserName(user.getUsername());
		if(findByUserName != 0) {
			flagMessage = "success";
			hintMessage = "该账号已注册，请直接登录";
		}else{
			boolean addUser = userInfoService.addUser(user);
			if(addUser) {
				flagMessage = "success";
				hintMessage = "注册成功，即将进入登录界面";
			}else{
				flagMessage = "fail";
				hintMessage = "注册失败";
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag",flagMessage);
		jsonObject.put("hintMessage",hintMessage);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toJSONString());
		response.getWriter().close();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login( @RequestBody UserInfo user,HttpServletResponse response) throws IOException {
		String hintMessage;
		String flagMessage;
		JSONObject jsonObject = new JSONObject();
		user = userInfoService.login(user.getUsername(),user.getPassword());
		if(!Objects.isNull(user)) {
			flagMessage = "success";
			hintMessage = "登录成功";
			jsonObject.put("role",user.getRole());
			jsonObject.put("nickname",user.getNickname());
			jsonObject.put("id",user.getId());
		}else {
			flagMessage = "fail";
			hintMessage = "用户名或密码错误";
		}
		
		jsonObject.put("flag",flagMessage);
		jsonObject.put("hintMessage",hintMessage);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toJSONString());
		response.getWriter().close();
	}
	
}
