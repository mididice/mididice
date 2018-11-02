package com.mididice.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mididice.util.RandomString;

/* 
	??Ό ?€?΄λ‘λ λ°? κ²°κ³Ό?λ©? μ»¨νΈλ‘€λ¬
	Request : (/res?filename=??Όλͺ?)  ==> (/res/randomstring) ==> mp3??Ό ?€?΄, ?¬? ?±
*/

@Controller
public class FileController {
	
	RandomString r = new RandomString();
	
	private static final int offset = 25;
	
	/*	
	 	midi??Ό λ³ν© ?, mp3??Όλ‘? λ³????¬ ??Όλͺμ ?κ²¨λ°??Ό ??€!!^^
		filename? getλ°©μ?Όλ‘? ?κ²¨μ£Ό?΄?Ό ?¨ (? : /res?filename=??Όλͺ?)
	*/
	
	@RequestMapping(value = "/res", method=RequestMethod.GET)
	public String redirectResult(@RequestParam("filename")String filename, RedirectAttributes redi){
		String enc;
		
		if(filename.indexOf('.')==-1){
			//??Ό?΄λ¦μ΄ ??₯?κ°? ??κ²½μ°
			enc = r.encrypt(filename, offset);
		}else{
			//??Ό?΄λ¦μ΄ ??₯?κ°? ??κ²½μ°
			enc = r.encrypt(filename.substring(0, filename.length()-4), offset);
		}
		//System.out.println(enc);
		redi.addAttribute("name", "name");
		return "redirect:/res/"+enc;
	}
	
	//??Όλͺ? λ³?? url ??± (?: /res/randomstring)
	@RequestMapping(value = "/res/{enc}", method=RequestMethod.GET)
	public String resultUrl(Model model, @PathVariable String enc){
		String filen = r.decrypt(enc, offset);
		model.addAttribute("enc", enc);
		model.addAttribute("filename",filen);
		String playPath = "../resources/save/";
		model.addAttribute("p",playPath);
		model.addAttribute("resImg","res_"+enc+".jpg");
		return "result"; //κ²°κ³Ό??Ό ==> result fileλ‘? λ³?κ²½ν΄?Ό?¨
	}
	
	//??Ό ?€?΄λ‘λ
	@RequestMapping(value="/res/download.do", method=RequestMethod.GET)
	public ModelAndView download(@RequestParam("fe") String enc, HttpServletRequest request
			) throws Exception{
		
		//μ΅μ’ mp3??Ό? κ²½λ‘
		String realPath = request.getSession().getServletContext().getRealPath("/resources/save/");

		//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3λ‘? λ³?κ²½ν΄?Ό?¨
		File down = new File(realPath+r.decrypt(enc, offset)+".mp3");
		
		//System.out.println(realPath+r.decrypt(enc, offset));
		if(!down.canRead()){
			throw new Exception("??Ό? μ°Ύμ? ??΅??€(^_^)");
			
		}
		return new ModelAndView("download","downloadFile",down);
	}
	
	//??Ό ?€?΄λ‘λ
		@RequestMapping(value="/res/download2.do", method=RequestMethod.GET)
		public ModelAndView download2(@RequestParam("m") String m, HttpServletRequest request
				) throws Exception{
			
			//μ΅μ’ mp3??Ό? κ²½λ‘
			String realPath = request.getSession().getServletContext().getRealPath("/resources/mp4/");

			//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3λ‘? λ³?κ²½ν΄?Ό?¨
			File down = new File(realPath+m+".mp4");
			
			//System.out.println(realPath+r.decrypt(enc, offset));
			if(!down.canRead()){
				throw new Exception("??Ό? μ°Ύμ? ??΅??€(^_^)");
				
			}
			return new ModelAndView("download","downloadFile",down);
		}
	
}
