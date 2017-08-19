package com.yapp.midi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MidiController {

	@RequestMapping(value="/append", method=RequestMethod.GET)
	public ModelAndView appendMidiTest(){
		ModelAndView mv = new ModelAndView();
		return mv;
	}
}
