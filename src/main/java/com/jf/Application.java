package com.jf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * 类描述：配置类
 *
 * @author HJ
 *
 * @version 创建时间:2019年3月16日上午10:57:49
 */
@SpringBootApplication
@MapperScan("com.jf.dao")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("程序开跑");
	}
//	@Bean
//	public LoginService loginService () {
//		LoginService loginService = new LoginService();
//		return loginService;
//	}

}
