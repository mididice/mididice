package com.mididice.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.InvalidMidiDataException;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mididice.service.MidiToMp3Service;
import com.mididice.util.*;

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
		MidiToMp3Service toMp3 = new MidiToMp3Service();
		
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
			mp3Name = toMp3.midiToMp3(resultPath+filename);
//			mp3Name = "test.mp3";
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
		

		RandomString r = new RandomString();
		String enc;
		
		if(midiName.indexOf('.')==-1){
			//?��?��?��름이 ?��?��?���? ?��?��경우
			enc = r.encrypt(midiName, offset);
		}else{
			//?��?��?��름이 ?��?��?���? ?��?��경우
			enc = r.encrypt(midiName.substring(0, midiName.length()-4), offset);
		}
		
		//append image 3x3, 4x4, 5x5 
		ImageMerge i = new ImageMerge();
		String resImg = i.returndeMergeImage(request, bar, imgNames, enc);
		
		String url = "redirect:/res/"+enc;
		
		rediAttr.addFlashAttribute("midiFile", mp3Name);
		rediAttr.addFlashAttribute("seconds", seconds);
		rediAttr.addFlashAttribute("bar", bar);
		rediAttr.addFlashAttribute("bpm", bpm);
		rediAttr.addFlashAttribute("resImg", resImg);
		
		return url;
		
	}
	
}
