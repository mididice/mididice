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
	?ŒŒ?¼ ?‹¤?š´ë¡œë“œ ë°? ê²°ê³¼?™”ë©? ì»¨íŠ¸ë¡¤ëŸ¬
	Request : (/res?filename=?ŒŒ?¼ëª?)  ==> (/res/randomstring) ==> mp3?ŒŒ?¼ ?‹¤?š´, ?¬?ƒ ?“±
*/

@Controller
public class FileController {
	
	RandomString r = new RandomString();
	
	private static final int offset = 25;
	
	/*	
	 	midi?ŒŒ?¼ ë³‘í•© ?›„, mp3?ŒŒ?¼ë¡? ë³??™˜?•˜?—¬ ?ŒŒ?¼ëª…ì„ ?„˜ê²¨ë°›?•„?•¼ ?•œ?‹¤!!^^
		filename?„ getë°©ì‹?œ¼ë¡? ?„˜ê²¨ì£¼?–´?•¼ ?•¨ (?˜ˆ : /res?filename=?ŒŒ?¼ëª?)
	*/
	
	@RequestMapping(value = "/res", method=RequestMethod.GET)
	public String redirectResult(@RequestParam("filename")String filename, RedirectAttributes redi){
		String enc;
		
		if(filename.indexOf('.')==-1){
			//?ŒŒ?¼?´ë¦„ì´ ?™•?¥?ê°? ?—†?Š”ê²½ìš°
			enc = r.encrypt(filename, offset);
		}else{
			//?ŒŒ?¼?´ë¦„ì´ ?™•?¥?ê°? ?ˆ?Š”ê²½ìš°
			enc = r.encrypt(filename.substring(0, filename.length()-4), offset);
		}
		//System.out.println(enc);
		redi.addAttribute("name", "name");
		return "redirect:/res/"+enc;
	}
	
	//?ŒŒ?¼ëª? ë³??™˜ url ?ƒ?„± (?˜ˆ: /res/randomstring)
	@RequestMapping(value = "/res/{enc}", method=RequestMethod.GET)
	public String resultUrl(Model model, @PathVariable String enc){
		String filen = r.decrypt(enc, offset);
		model.addAttribute("enc", enc);
		model.addAttribute("filename",filen);
		String playPath = "../resources/save/";
		model.addAttribute("p",playPath);
		model.addAttribute("resImg","res_"+enc+".jpg");
		return "result"; //ê²°ê³¼?ŒŒ?¼ ==> result fileë¡? ë³?ê²½í•´?•¼?•¨
	}
	
	//?ŒŒ?¼ ?‹¤?š´ë¡œë“œ
	@RequestMapping(value="/res/download.do", method=RequestMethod.GET)
	public ModelAndView download(@RequestParam("fe") String enc, HttpServletRequest request
			) throws Exception{
		
		//ìµœì¢… mp3?ŒŒ?¼?˜ ê²½ë¡œ
		String realPath = request.getSession().getServletContext().getRealPath("/resources/save/");

		//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3ë¡? ë³?ê²½í•´?•¼?•¨
		File down = new File(realPath+r.decrypt(enc, offset)+".mp3");
		
		//System.out.println(realPath+r.decrypt(enc, offset));
		if(!down.canRead()){
			throw new Exception("?ŒŒ?¼?„ ì°¾ì„?ˆ˜ ?—†?Šµ?‹ˆ?‹¤(^_^)");
			
		}
		return new ModelAndView("download","downloadFile",down);
	}
	
	//?ŒŒ?¼ ?‹¤?š´ë¡œë“œ
		@RequestMapping(value="/res/download2.do", method=RequestMethod.GET)
		public ModelAndView download2(@RequestParam("m") String m, HttpServletRequest request
				) throws Exception{
			
			//ìµœì¢… mp3?ŒŒ?¼?˜ ê²½ë¡œ
			String realPath = request.getSession().getServletContext().getRealPath("/resources/mp4/");

			//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3ë¡? ë³?ê²½í•´?•¼?•¨
			File down = new File(realPath+m+".mp4");
			
			//System.out.println(realPath+r.decrypt(enc, offset));
			if(!down.canRead()){
				throw new Exception("?ŒŒ?¼?„ ì°¾ì„?ˆ˜ ?—†?Šµ?‹ˆ?‹¤(^_^)");
				
			}
			return new ModelAndView("download","downloadFile",down);
		}
	
}
