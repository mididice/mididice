package com.yapp.midi.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.InvalidMidiDataException;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MidiController {
	
	@Autowired
	MidiSaveService midisaveservice;

	public static String OUTPUT_FILENAME = "yapp1456.mid";
	public static String FILE_PATH = "";
	/*
	 * 
	 * iputfile list(max 36), 타악기
	 * outfile name
	 * 
	 * */
	@RequestMapping(value="/append", method=RequestMethod.GET)
	public ModelAndView appendMidiTest(@RequestParam(value="names")String[] names,
			HttpServletRequest request ) throws IOException, InvalidMidiDataException{
		
		
		Pattern patternOne = null;
		Pattern resultMidi = new Pattern();
		String filename = null;
		
		String resultPath = request.getSession().getServletContext().getRealPath("/resources/yapp");
		String midiPath = request.getSession().getServletContext().getRealPath("/resources/midi");
		
		if(names!=null){
			for(int i = 0; i<names.length; i++){
				patternOne = MidiFileManager.loadPatternFromMidi(new File(midiPath+names[i]));
				resultMidi.add(patternOne.toString());
			}			
		}
		try {
			MidiFileManager.savePatternToMidi(resultMidi, new File(resultPath+OUTPUT_FILENAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		return mv;
	}
	

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

	@RequestMapping(value="/aaa", method=RequestMethod.GET)
	@ResponseBody
	public String pathTest(HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/resources/midi");
		return path;
	}

}
