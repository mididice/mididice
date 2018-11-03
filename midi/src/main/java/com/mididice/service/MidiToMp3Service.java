package com.mididice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/*
 */
@Service
public class MidiToMp3Service {
	//private final static String dirPath = "../resources/save";
	private static final Logger logger = LoggerFactory.getLogger(MidiToMp3Service.class);

	public String midiToMp3(String midiPath) {
		// TODO Auto-generated method stub
		try {
			//command midi
			//String command = "timidity -Ow -o - "+midiPath+".mid | lame - "+midiPath+".mp3";
			String[] command = {
					"/bin/sh",
					"-c",
					"timidity -Ow -o - "+midiPath+".mid | lame - "+midiPath+".mp3"
					};
			logger.info("converted command is {}", (Object)command);
			
			Runtime rt = Runtime.getRuntime();
			
			Process p = rt.exec(command);
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "error");
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "output");
			
			errorGobbler.start();
			outputGobbler.start();
			
			int ev = p.waitFor();
			logger.info("exitValue: {}", ev);
		} catch (Exception e) {
			logger.error("midi to mp3");
			return "test.mp3";
		}
		return midiPath+".mp3";
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
