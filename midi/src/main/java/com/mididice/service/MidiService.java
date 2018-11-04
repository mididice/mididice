package com.mididice.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.mididice.util.GenerateFileName;
import com.mididice.util.RandomString;

@Service
public class MidiService {
	private static final int offset = 25;

	public String mergeMidi(String[] names) {
		Pattern patternOne = null;
		Pattern resultMidi = new Pattern();
		
		String resultPath = null;
		String midiPath = null;
		
		try {
			URL resultDir = ResourceUtils.getURL("classpath:static/save/");
			URL midiDir = ResourceUtils.getURL("classpath:static/midi/");
			resultPath = resultDir.getPath();
			midiPath = midiDir.getPath();
			
			if(names!=null){
				for(int i = 0; i<names.length; i++){
					patternOne = MidiFileManager.loadPatternFromMidi(new File(midiPath+names[i]));
					resultMidi.add(patternOne.toString());
				}			
			}
			String fileName = GenerateFileName.createName();
			String midiName = fileName +".mid";
			MidiFileManager.savePatternToMidi(resultMidi, new File(resultPath+midiName));
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	public String[] getImageArr(String[] names) {

		String[] imgNames = new String[names.length];
		try {
			for(int i =0; i<names.length; i++){
				String name[] = names[i].split(".mid");
				imgNames[i] = name[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgNames;

	}
	
	public String getRandomFileName(String fileName) {
		RandomString r = new RandomString();
		String enc = null;
		
		if(fileName.indexOf('.')==-1){
			enc = r.encrypt(fileName, offset);
		}else{
			enc = r.encrypt(fileName.substring(0, fileName.length()-4), offset);
		}
		return enc;
	}
}
