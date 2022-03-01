package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Controller
public class UserController {
	
	private static final Logger logger = LogManager.getLogger("com.example.demo");
	
	@RequestMapping("/MyFirstPage")
	public String greeting(@RequestParam(value="title", required=false, defaultValue="xiao") String title, Model model) {
		model.addAttribute("name", title);		
		logger.info(title);
		System.out.println("title:"+ title);
		return "index";
	}

}
