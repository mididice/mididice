package com.mididice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 	midië¥? mp3ë¡? ë³??™˜?•˜?Š” ì»¨íŠ¸ë¡¤ëŸ¬	
 	save?— ???ž¥?˜?–´ ?žˆ?Š” midi?ŒŒ?¼?„ mp3ë¡? ë³??™˜?•œ?‹¤.
 */
public class MidiToMp3Controller {
	//midi?ŒŒ?¼?“¤?´ ?žˆ?Š” ê²?
	//private final static String dirPath = "../resources/save";

	@RequestMapping(value = "/midiToMp3", method=RequestMethod.GET)
	public String midiToMp3(@RequestParam(value="midi")String midiPath) {
		// TODO Auto-generated method stub
		try {
			//ë³??ˆ˜ command?Š” midië¥? mp3ë¡? ë³??™˜?•˜?Š” ë¦¬ëˆ…?Š¤ ëª…ë ¹?–´?´ 
			//String command = "timidity -Ow -o - "+midiPath+".mid | lame - "+midiPath+".mp3";
			String[] command = {
					"/bin/sh",
					"-c",
					"timidity -Ow -o - "+midiPath+".mid | lame - "+midiPath+".mp3"
					};
			System.out.println(command);
			Runtime rt = Runtime.getRuntime();
			
			Process p = rt.exec(command);
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "error");
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "output");
			
			errorGobbler.start();
			outputGobbler.start();
			
			int ev = p.waitFor();
			System.out.println("exitValue:"+ev);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
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
