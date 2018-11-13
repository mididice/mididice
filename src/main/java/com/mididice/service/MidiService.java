package com.mididice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.mididice.exception.FileStorageException;
import com.mididice.property.FileStorageProperties;
import com.mididice.util.GenerateFileName;
import com.mididice.util.RandomString;

@Service
public class MidiService {
	private static final int offset = 25;
	private final Path fileStorageLocation;
	public MidiService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getSaveDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create dir", ex);
		}
	}
	
	public String mergeMidi(String[] names, String seconds, String bar, String bpm) {
		Pattern patternOne = null;
		Pattern resultMidi = new Pattern();
		
		String resultPath = null;
		String midiPath = null;
		Resource midiResource = null;
		try {
			
//			URL resultDir = ResourceUtils.getURL("classpath:static/save/");
//			URL midiDir = ResourceUtils.getURL("classpath:static/midi/");
//			resultPath = resultDir.getPath();
//			midiPath = midiDir.getPath();
//			
			if(names!=null){
				for(int i = 0; i<names.length; i++){					
					midiResource = new ClassPathResource("/static/midi/"+names[i]);
//					patternOne = MidiFileManager.loadPatternFromMidi(new File(midiPath+names[i]));
					patternOne = MidiFileManager.loadPatternFromMidi(midiResource.getInputStream());
					resultMidi.add(patternOne.toString());
				}			
			}
			String fileName = GenerateFileName.createName();
			fileName = fileName + "-"+seconds+"hk"+bar+"hk"+bpm;
			String midiName = fileName +".mid";
			Path targetLocation = this.fileStorageLocation.resolve(midiName);
			MidiFileManager.savePatternToMidi(resultMidi, targetLocation.toFile());
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
