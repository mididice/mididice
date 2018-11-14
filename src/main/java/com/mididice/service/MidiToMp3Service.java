package com.mididice.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mididice.exception.FileStorageException;
import com.mididice.property.FileStorageProperties;


/*
 */
@Service
public class MidiToMp3Service {
	//private final static String dirPath = "../resources/save";
	private static final Logger logger = LoggerFactory.getLogger(MidiToMp3Service.class);
	
	private final Path fileStorageLocation;
	public MidiToMp3Service(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getSaveDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create dir", ex);
		}
	}
	@Async
	public String midiToMp3(String fileName) {
		
		try {
			Path filePathName = fileStorageLocation.resolve(fileName+".mid");
			String pathFileName = filePathName.toString();
			List<String> commandList = new ArrayList<String>();
			String[] command = null;
			if(isWindows()) {
				return "";
				
			}else {
				//String command = "timidity -Ow -o - "+midiPath+".mid | lame - "+midiPath+".mp3";
				commandList.add("/bin/sh");
				commandList.add("-c");
				commandList.add("timidity -Ow -o -");
				commandList.add(pathFileName);
				commandList.add(" | ");
				commandList.add("lame - ");
				commandList.add(fileStorageLocation.toAbsolutePath()+File.separator+fileName+".mp3");
	
			}
			
			Runtime rt = Runtime.getRuntime();
			command = commandList.toArray(new String[0]);
			logger.info("converted command is {}", (Object)command);
			
			Process p = rt.exec(command);
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "error");
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "output");
			
			errorGobbler.start();
			outputGobbler.start();
			
			int ev = p.waitFor();
			logger.info("exitValue: {}", ev);
			return fileName+".mp3";
		} catch (Exception e) {
			logger.error("midi to mp3 not working");
			e.printStackTrace();
			return "";
		}
		
	}
	
	private boolean isWindows() {
		String os = System.getProperty("os.name");
		if(os.contains("Window")) {
			return true; 
		}
		return false;
	}
}
class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    
    StreamGobbler(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
    }
    
    public void run()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
                System.out.println(type + ">" + line);    
            } catch (IOException ioe)
              {
                ioe.printStackTrace();  
              }
    }
}
