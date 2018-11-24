package com.mididice.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/mididice/")
	public String home(Locale locale, Model model) {
		logger.info("The client locale is {}.", locale);
		
		return "home";
	}
	@GetMapping("/mididice")
	public String mididiceHome(Locale locale, Model model) {
		logger.info("The client locale is {}.", locale);
		
		return "redirect:/mididice/";
	}
	
}
