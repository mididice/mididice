package com.yapp.midi.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 	midi를 mp3로 변환하는 컨트롤러	
 	save에 저장되어 있는 midi파일을 mp3로 변환한다.
 */
public class MidiToMp3Controller {
	//midi파일들이 있는 경
	private final static String dirPath = "../resources/save";

	@RequestMapping(value = "/midiToMp3", method=RequestMethod.GET)
	public String midiToMp3(@RequestParam(value="midi")String midiPath) {
		// TODO Auto-generated method stub
		try {
			//변수 command는 midi를 mp3로 변환하는 리눅스 명령어이 
			String command = "timidity -Ow -o -"+dirPath+midiPath+".mid | lame - "+dirPath+midiPath+".mp3";
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dirPath+midiPath+".mp3";
	}
}
