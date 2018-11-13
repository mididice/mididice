package com.mididice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URL;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import com.mididice.util.GenerateFileName;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MidiServiceTest {

	@Autowired
	private MidiService midiService;
	
	@Test
	public void randomStringTest() {
		String fileName = GenerateFileName.createName();
		String anotherFileName = GenerateFileName.createName();
		assertThat(!fileName.equals(anotherFileName));
		
	}
	@Test
	public void mergeMidiTest() {
		String names[] = new String[] {"11.mid","22.mid","31.mid","43.mid"};
		String result = midiService.mergeMidi(names, "15", "9", "120");
		assertThat(result.contains("hk"));
//		Pattern patternOne = null;
//		Pattern resultMidi = new Pattern();
//		URL resultDir = ResourceUtils.getURL("classpath:static/save/");
//		URL midiDir = ResourceUtils.getURL("classpath:static/midi/");
//		String resultPath = resultDir.getPath();
//		String midiPath = midiDir.getPath();
//		
//		if(names!=null){
//			for(int i = 0; i<names.length; i++){
//				patternOne = MidiFileManager.loadPatternFromMidi(new File(midiPath+names[i]));
//				resultMidi.add(patternOne.toString());
//			}			
//		}
//		String fileName = GenerateFileName.createName();
//		fileName = fileName + "-"+seconds+"hk"+bar+"hk"+bpm;
//		String midiName = fileName +".mid";
//		MidiFileManager.savePatternToMidi(resultMidi, new File(resultPath+midiName));
	}
}
