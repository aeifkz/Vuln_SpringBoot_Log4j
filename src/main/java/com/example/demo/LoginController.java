package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger("com.example.demo");
	
	@RequestMapping(method = RequestMethod.POST,value="/UserLogin")
	public String greeting(@RequestBody String login_info) {
		String login = java.net.URLDecoder.decode(login_info);
		logger.info(login);
		System.out.println("login:"+ login);
		return "Your account is logged by log4j.";
	}

}
