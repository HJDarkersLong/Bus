package com.jf.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/recover/login")
public class LoginController {
	
//	@Value("${login_url}")
//	private String login_url;
//	
//	@Value("${appID}")
//	private String appID;
//	
//	@Value("${token}")
//	private String token;
//	
//	@Value("${h5_url}")
//	private String h5_url;
	
	@Autowired
	ApplicationContext context;
	
	private static Logger log= LoggerFactory.getLogger(LoginController.class);
	
//	@RequestMapping(value = "/toRecover", method = RequestMethod.GET)
//	public ModelAndView toRecover(@PathParam("channel_token") String channel_token,@PathParam("source") String source) throws ClientProtocolException, IOException {
//		
//		long ts = new Date().getTime()/1000;
//		String sign = DigestUtils.md5Hex(appID + ts + token + "false");
//		String url = h5_url + "?user_token=" + channel_token;
//	
////		如果传递参数则需要进行encode
//		String encodeUrl = URLEncoder.encode(url, "UTF-8");
//		log.info("-------------------"+url+"--------------------");
//		log.info(login_url + "?appID=" + appID + "&ts=" + ts + "&sign=" + sign + "&redirect_link=" + url);
////		TODO:跳转到页面地址并传递参数
////		return new ModelAndView(login_url + "?appID=" + appID + "&ts=" + ts + "&sign=" + sign + "&redirect_link=" + encodeUrl);
//		if(source != null) {
//			if(source.equals("mini_program")) {
//				return new ModelAndView("redirect:" + url);
//			}
//		}
//		return new ModelAndView("redirect:" + login_url + "?appID=" + appID + "&ts=" + ts + "&sign=" + sign + "&redirect_link=" + encodeUrl);
//	}
//	
//	@RequestMapping(value = "/toRecoverMini", method = RequestMethod.GET)
//	public ModelAndView toRecoverMini(@PathParam("channel_token") String channel_token,@PathParam("source") String source) throws ClientProtocolException, IOException {
//		String url = h5_url + "?user_token=" + channel_token;
//		return new ModelAndView("redirect:" + url);
//	}

	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public String toIndex(@PathParam("channel_token") String channel_token,@PathParam("source") String source, Model model)
			throws ClientProtocolException, IOException {
		model.addAttribute("channel_token",channel_token);
		if(source != null) {
			model.addAttribute("source",source);
		}
		return "index";
	}
	
}
