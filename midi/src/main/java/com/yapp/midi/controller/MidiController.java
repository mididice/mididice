package com.yapp.midi.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.InvalidMidiDataException;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.yapp.midi.util.*;

@Controller
public class MidiController {

	public static String OUTPUT_FILENAME = "yapp1456.mid";
	public static String FILE_PATH = "";
	private static final int offset = 25;
	/*
	 * 
	 * iputfile list(max 36)
	 * outfile name
	 * 
	 * */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String appendMidiTest(
			@RequestParam(value="midis")String[] names, //11.midi
			@RequestParam(value="seconds")String seconds,
			@RequestParam(value="bar")String bar,
			@RequestParam(value="bpm")String bpm,
			HttpServletRequest request,
			RedirectAttributes rediAttr) throws IOException, InvalidMidiDataException{
		
		GenerateFileName gfn = new GenerateFileName();
		MidiToMp3Controller toMp3 = new MidiToMp3Controller();
		
		Pattern patternOne = null;
		Pattern resultMidi = new Pattern();
		String filename = null;
		String midiName = null;
		String mp3Name =  null;
		
		String resultPath = request.getSession().getServletContext().getRealPath("/resources/save/");
		String midiPath = request.getSession().getServletContext().getRealPath("/resources/midi/");
		
		if(names!=null){
			for(int i = 0; i<names.length; i++){
				patternOne = MidiFileManager.loadPatternFromMidi(new File(midiPath+names[i]));
				resultMidi.add(patternOne.toString());
			}			
		}
		try {
			filename = gfn.createName();
			midiName = filename +".mid";
			MidiFileManager.savePatternToMidi(resultMidi, new File(resultPath+midiName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
//			mp3Name = toMp3.midiToMp3(resultPath+filename);
			mp3Name = "test.mp3";
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] imgNames = new String[names.length];
		try {
			for(int i =0; i<names.length; i++){
				String name[] = names[i].split(".mid");
				imgNames[i] = name[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i =0; i<imgNames.length;i++){
			System.out.println(imgNames[i]);
		}
		
		ImageMerge i = new ImageMerge();
		String resImg = i.returndeMergeImage(request, bar, imgNames, filename);
		System.out.println(resImg);
		
		ModelAndView mv = new ModelAndView();

		RandomString r = new RandomString();
		RedirectView rediView = new RedirectView();
		
		
		String enc;
		
		if(midiName.indexOf('.')==-1){
			//파일이름이 확장자가 없는경우
			enc = r.encrypt(midiName, offset);
		}else{
			//파일이름이 확장자가 있는경우
			enc = r.encrypt(midiName.substring(0, midiName.length()-4), offset);
		}
		//System.out.println(enc);
		
//		mv.addObject("midiFile", mp3Name);
//		mv.addObject("seconds", seconds);
//		mv.addObject("bar", bar);
//		mv.addObject("bpm", bpm);
		
		
		String url = "redirect:/res/"+enc;
		//rediView.setUrl(url);
		//rediView.setExposeModelAttributes(false);
//		rediView.addStaticAttribute(, value);
		//mv.setView(rediView);
		
		rediAttr.addFlashAttribute("midiFile", mp3Name);
		rediAttr.addFlashAttribute("seconds", seconds);
		rediAttr.addFlashAttribute("bar", bar);
		rediAttr.addFlashAttribute("bpm", bpm);
		rediAttr.addFlashAttribute("resImg", resImg);
		
		return url;
		
	}
	
}
