//package com.jf.controller;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//import javax.persistence.criteria.Order;
//import javax.websocket.server.PathParam;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.format.datetime.DateFormatter;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.alibaba.fastjson.JSONObject;
//import com.jf.bean.OrderInfo;
//import com.jf.bean.UserLoginInfo;
//import com.jf.service.UserLoginInfoService;
//
//@RestController
//@RequestMapping(value = "/recover/api/user")
//public class UserLoginInfoController {
//	
//	@Value("${appID}")
//	private String appID;
//	
////	@Value("${token}")
////	private String token;
////	
////	@Value("${userInfo_url}")
////	private String userInfo_url;
////	
////	@Value("${managerInfo_url}")
////	private String managerInfo_url;
////	
////	@Value("${addressInfo_url}")
////	private String addressInfo_url;
//	
//	@Autowired
//	private UserLoginInfoService userLoginInfoService;
//	
//	private static Logger log= LoggerFactory.getLogger(UserLoginInfoController.class);
//	
//	@RequestMapping(value = "/addUser",method = RequestMethod.GET)
//	public boolean addUser () {
//		System.out.println("新增");
//		UserLoginInfo userInfo = new UserLoginInfo();
//		userInfo.setId(UUID.randomUUID().toString());
//		userInfo.setChannelToken("channelToken");
//		userInfo.setCzyId("czyId");
//		userInfo.setOpenId("openId");
//		userInfo.setNickName("nickName");
//		userInfo.setSex("1");
//		return userLoginInfoService.addUser(userInfo);
//	}
//	
//	/**
//	 * 
//	 * 方法说明: 获取用户基础信息
//	 *
//	 * @return
//	 * @throws ClientProtocolException
//	 * @throws IOException
//	 * 
//	 * @author  HJ 2019年3月21日 
//	 *
//	 */
//	@RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
//	public String  toLogin (@PathParam("user_token") String user_token) throws ClientProtocolException, IOException {
//		log.info("-----------获取用户信息getUserInfo----------");
////		对user_token进行解密得到channel_token
////		byte[] decodeUserToken = Base64.getDecoder().decode(user_token.getBytes());
////		String channel_token = new String(decodeUserToken);
//		String channel_token = user_token;
//		String result = "";
//		long ts = new Date().getTime()/1000;
//		String sign = DigestUtils.md5Hex(appID + ts + token + "false");
//		
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet(userInfo_url + "?appID=" + appID + "&ts=" + ts + "&sign=" + sign + "&channel_token=" + channel_token);
//		CloseableHttpResponse response = httpClient.execute(httpGet);
//		 // 获取结果实体
//        // 判断网络连接状态码是否正常(0--200都数正常)
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            result = EntityUtils.toString(response.getEntity(),"UTF-8");
//            JSONObject parseObject = JSONObject.parseObject(result);
//            String message = (String)parseObject.get("message");
//            EntityUtils.consume(response.getEntity());
//            System.out.println(message);
//            Object objectString = parseObject.get("content");
//            if(!StringUtils.isEmpty(objectString)) {
//            	JSONObject object = (JSONObject)parseObject.get("content");
//            	UserLoginInfo userLoginInfo = new UserLoginInfo();
//            	userLoginInfo.setId(UUID.randomUUID().toString());
//            	userLoginInfo.setChannelToken(channel_token);
//            	userLoginInfo.setCzyId(object.getString("czy_id"));
//            	userLoginInfo.setOpenId(object.getString("openid"));
//            	userLoginInfo.setNickName(object.getString("nickname"));
//            	userLoginInfo.setSex(object.getString("sex"));
//            	userLoginInfo.setMobile(object.getString("mobile"));
//            	userLoginInfo.setHeadImgUrl(object.getString("head_img_url"));
//            	userLoginInfo.setLoginTime(new Timestamp(new Date().getTime()));
//            	userLoginInfo.setUpdateTime(new Timestamp(new Date().getTime()));
//            	boolean addResult = userLoginInfoService.addUser(userLoginInfo);
//            	System.out.println(object);
//            	if (!addResult) {
//            		log.error("--------------addUserLoginInfo error-------------");
//            	}
//            }
//            
//        }
//        // 释放链接
//        response.close();
//        return result;
//	}
//	
//	/**
//	 * 
//	 * 方法说明:	获取用户缴费地址绑定的客户经理信息
//	 *
//	 * @return
//	 * @throws ClientProtocolException
//	 * @throws IOException
//	 * 
//	 * @author  HJ 2019年3月21日 
//	 *
//	 */
//	@RequestMapping(value = "/getManagerInfo",method = RequestMethod.GET)
//	public String  getManagerInfo (@PathParam("user_token") String user_token) throws ClientProtocolException, IOException {
//		
//		String result = "";
//		long ts = new Date().getTime()/1000;
//		String sign = DigestUtils.md5Hex(appID + ts + token + "false");
//		
//		String channel_token = user_token;
//		
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet(managerInfo_url + "?appID=" + appID + "&ts=" + ts + "&sign=" + sign + "&channel_token=" + channel_token);
//		CloseableHttpResponse response = httpClient.execute(httpGet);
//		 // 获取结果实体
//        // 判断网络连接状态码是否正常(0--200都数正常)
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            result = EntityUtils.toString(response.getEntity(),"UTF-8");
//            JSONObject parseObject = JSONObject.parseObject(result);
//            String message = (String)parseObject.get("message");
//            EntityUtils.consume(response.getEntity());
//            System.out.println(message);
//            
//            Object object = parseObject.get("content");
//            System.out.println(result);
//        }
//        // 释放链接
//        response.close();
//        return result;
//	}
//	
//	/**
//	 * 
//	 * 方法说明: 获取用户缴费地址绑定的客户经理信息
//	 *
//	 * @return
//	 * @throws ClientProtocolException
//	 * @throws IOException
//	 * 
//	 * @author  HJ 2019年3月21日 
//	 *
//	 */
//	@RequestMapping(value = "/getAddressInfo",method = RequestMethod.GET)
//	public String  getAddressInfo (@PathParam("user_token") String user_token) throws ClientProtocolException, IOException {
//		
//		String result = "";
//		long ts = new Date().getTime()/1000;
//		String sign = DigestUtils.md5Hex(appID + ts + token + "false");
//		
//		String channel_token = user_token;
//		
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet(addressInfo_url + "?appID=" + appID + "&ts=" + ts + "&sign=" + sign + "&channel_token=" + channel_token);
//		CloseableHttpResponse response = httpClient.execute(httpGet);
//		 // 获取结果实体
//        // 判断网络连接状态码是否正常(0--200都数正常)
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            result = EntityUtils.toString(response.getEntity(),"UTF-8");
//            JSONObject parseObject = JSONObject.parseObject(result);
//            String message = (String)parseObject.get("message");
//            EntityUtils.consume(response.getEntity());
//            System.out.println(message);
//            
//            Object object = parseObject.get("content");
//            System.out.println(result);
//        }
//        // 释放链接
//        response.close();
//        return result;
//	}
//	
//	@RequestMapping(value = "/saveOrder")
//	public boolean  saveOrder (@RequestBody JSONObject orderJson) {
//		System.out.println(orderJson);
//		return true ;
//	}
//	
//	
//}
