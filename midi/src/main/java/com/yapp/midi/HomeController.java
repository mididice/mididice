package com.yapp.midi;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
//	@RequestMapping(value = "/result", method = RequestMethod.GET)
//	public String result(Locale locale, Model model) {
//		
//		return "result";
//	}
	@RequestMapping(value = "/m", method = RequestMethod.GET)
	public String music()   {
		return "test";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request) 
	{
//		Set pathSet = request.getSession().getServletContext().getResourcePaths("/");
//		System.out.println(pathSet);	

		String pdfPath = request.getSession().getServletContext().getRealPath("/resource/pamphlet.pdf");
		System.out.println(pdfPath);

		return null;
	}
}
