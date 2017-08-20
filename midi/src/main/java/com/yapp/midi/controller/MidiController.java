package com.yapp.midi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yapp.midi.service.MidiSaveService;

@Controller
public class MidiController {

	@RequestMapping(value="/append", method=RequestMethod.GET)
	public ModelAndView appendMidiTest(){
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
}
