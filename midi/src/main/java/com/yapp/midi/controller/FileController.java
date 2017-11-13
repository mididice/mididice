package com.yapp.midi.controller;

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
import com.yapp.midi.util.RandomString;

/* 
	파일 다운로드 및 결과화면 컨트롤러
	Request : (/res?filename=파일명)  ==> (/res/randomstring) ==> mp3파일 다운, 재생 등
*/

@Controller
public class FileController {
	
	RandomString r = new RandomString();
	
	private static final int offset = 25;
	
	/*	
	 	midi파일 병합 후, mp3파일로 변환하여 파일명을 넘겨받아야 한다!!^^
		filename을 get방식으로 넘겨주어야 함 (예 : /res?filename=파일명)
	*/
	
	@RequestMapping(value = "/res", method=RequestMethod.GET)
	public String redirectResult(@RequestParam("filename")String filename, RedirectAttributes redi){
		String enc;
		
		if(filename.indexOf('.')==-1){
			//파일이름이 확장자가 없는경우
			enc = r.encrypt(filename, offset);
		}else{
			//파일이름이 확장자가 있는경우
			enc = r.encrypt(filename.substring(0, filename.length()-4), offset);
		}
		//System.out.println(enc);
		redi.addAttribute("name", "name");
		return "redirect:/res/"+enc;
	}
	
	//파일명 변환 url 생성 (예: /res/randomstring)
	@RequestMapping(value = "/res/{enc}", method=RequestMethod.GET)
	public String resultUrl(Model model, @PathVariable String enc){
		String filen = r.decrypt(enc, offset);
		model.addAttribute("enc", enc);
		model.addAttribute("filename",filen);
		String playPath = "../resources/save/";
		model.addAttribute("p",playPath);
		model.addAttribute("resImg","res_"+enc+".jpg");
		return "result"; //결과파일 ==> result file로 변경해야함
	}
	
	//파일 다운로드
	@RequestMapping(value="/res/download.do", method=RequestMethod.GET)
	public ModelAndView download(@RequestParam("fe") String enc, HttpServletRequest request
			) throws Exception{
		
		//최종 mp3파일의 경로
		String realPath = request.getSession().getServletContext().getRealPath("/resources/save/");

		//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3로 변경해야함
		File down = new File(realPath+r.decrypt(enc, offset)+".mp3");
		
		//System.out.println(realPath+r.decrypt(enc, offset));
		if(!down.canRead()){
			throw new Exception("파일을 찾을수 없습니다(^_^)");
			
		}
		return new ModelAndView("download","downloadFile",down);
	}
	
}
