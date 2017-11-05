package com.yapp.midi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProcessYoutubeController {
	
	//파일 변환 로딩 화면
	@RequestMapping(value = "/res/convert", method = RequestMethod.POST)
	public String loading(@RequestParam("i") String img, @RequestParam("m") String mus, Model model){
		model.addAttribute("mus", mus);
		model.addAttribute("img", img);
		return "loading"; //로딩화면
	}
	
	//파일 변환(mp3 + img ==> mp4)
	@ResponseBody
	@RequestMapping(value = "/convert.do")
	public String mergeTwoFile(@RequestParam("i") String img, @RequestParam("m") String mus, 
			Model model, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		String videoPath = request.getSession().getServletContext().getRealPath("/resources/mp4/");
		String imagePath = request.getSession().getServletContext().getRealPath("/resources/resimg/"+img);
		String soundPath = request.getSession().getServletContext().getRealPath("/resources/save/"+mus+".mp3");
		
		Map<String, Object> code = new HashMap<String, Object>();
		
		try {
			Process process = 
					Runtime.getRuntime().exec("ffmpeg -loop 1 -i "+imagePath+" -i "+soundPath
							+" -c:v libx264 -c:a aac -strict experimental -b:a 192k -shortest "+videoPath+mus+".mp4"); 
			
			process.waitFor();

				if(process.exitValue() == 0){
					Thread.sleep(1000);
					System.out.println("정상적으로 프로세스 실행 종료");
					code.put("code", 0); //파일 변환 완료
				}else{
					code.put("code", 1); //오류
				}
		
		} catch(Exception e) { 
			e.printStackTrace(); 
		}	
		
		String c = new ObjectMapper().writeValueAsString(code);
		return c;
	}	
}
