package com.yapp.midi.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class FileController {


	@RequestMapping(value = "/res", method=RequestMethod.GET)
	public String test(){
		
		return "midi2";
	}
	
	//파일명 url 생성
	@RequestMapping(value = "/res/a", method=RequestMethod.GET)
	public String resultUrl(Model model, @RequestParam("filename") String filename){
		//String encUrl = midisaveservice.createUrl("filename");
		//model.addAttribute("file",encUrl);
		return "redirect:/res/"+filename;
	}
	
	@RequestMapping(value = "/res/{filename}", method=RequestMethod.GET)
	public String resultUrl2(Model model, @PathVariable String filename){
		model.addAttribute("file",filename);
		return "midi";
	}
	/*
	@RequestMapping(value = "/res/{encpath}", method=RequestMethod.GET)
	public String resultUrl(Model model, @PathVariable String encpath){
		String dcryptfile = midisaveservice.decryptPath("pb8vlnlHaBB+TkbaEvmZkA==");
		model.addAttribute("file",dcryptfile);
		return "midi";
	}
	*/
	
}
