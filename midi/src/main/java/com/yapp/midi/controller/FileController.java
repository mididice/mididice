package com.yapp.midi.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yapp.midi.service.MidiSaveService;
import com.yapp.midi.service.MidiSaveServiceImpl;
import com.yapp.midi.util.Encrypt;

@Controller
public class FileController {
	
	

	@RequestMapping(value = "/res", method=RequestMethod.GET)
	public String test(){

		System.out.println();
		return "midi2";
	}
	
	//파일명 url 생성
	@RequestMapping(value = "/res/a", method=RequestMethod.GET)
	public String resultUrl(Model model, @RequestParam("filename") String filename){
		//SecretKey key = midisave.keyGenerator();
		System.out.println();
		//String encUrl = midisaveservice.createUrl("filename");
		//model.addAttribute("file",encUrl);
		//return "redirect:/res/"+filename;
		return "midi";
	}
	/*
	@RequestMapping(value = "/res/{filename}", method=RequestMethod.GET)
	public String resultUrl2(Model model, @PathVariable String filename){
		model.addAttribute("file",filename);
		return "midi";
	}*/
	/*
	@RequestMapping(value = "/res/{encpath}", method=RequestMethod.GET)
	public String resultUrl(Model model, @PathVariable String encpath){
		String dcryptfile = midisaveservice.decryptPath("pb8vlnlHaBB+TkbaEvmZkA==");
		model.addAttribute("file",dcryptfile);
		return "midi";
	}
	*/
	
	//파일 다운로드
	@RequestMapping(value="/res/download.do", method=RequestMethod.GET)
	public ModelAndView download(@RequestParam("filename") String filename, HttpServletRequest request
			) throws Exception{
		
		//최종 mp3파일의 경로
		String realPath = request.getSession().getServletContext().getRealPath("/resources/save/");
		
		File down = new File(realPath+filename);
		
		System.out.println(realPath+filename);
		if(!down.canRead()){
			throw new Exception("파일을 찾을수 없습니다(^_^)");
			
		}
		return new ModelAndView("download","downloadFile",down);
	}
	
}
