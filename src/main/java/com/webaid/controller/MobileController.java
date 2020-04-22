package com.webaid.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/m/*")
public class MobileController {
	
	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);
	
	
	@RequestMapping(value = "/menu01_01", method = RequestMethod.GET)
	public String menu01_01(Model model) {
		logger.info("menu01_01");
		
		return "m/sub/menu01_01";
	}
	
	@RequestMapping(value = "/menu01_03", method = RequestMethod.GET)
	public String menu01_03(Model model) {
		logger.info("menu01_03");
		
		return "m/sub/menu01_03";
	}
	
	@RequestMapping(value = "/menu02_01", method = RequestMethod.GET)
	public String menu02_01(Model model) {
		logger.info("menu02_01");
		
		return "m/sub/menu02_01";
	}
	
	@RequestMapping(value = "/menu02_02", method = RequestMethod.GET)
	public String menu02_02(Model model) {
		logger.info("menu02_02");
		
		return "m/sub/menu02_02";
	}
	
	@RequestMapping(value = "/menu02_03", method = RequestMethod.GET)
	public String menu02_03(Model model) {
		logger.info("menu02_03");
		
		return "m/sub/menu02_03";
	}
	
	@RequestMapping(value = "/menu02_04", method = RequestMethod.GET)
	public String menu02_04(HttpServletRequest req, Model model) {
		logger.info("menu02_04");
		
		return "m/sub/menu02_04";
	}
	
}
