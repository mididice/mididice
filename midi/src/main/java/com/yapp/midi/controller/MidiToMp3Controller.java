package com.yapp.midi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 	midi를 mp3로 변환하는 컨트롤러	
 	save에 저장되어 있는 midi파일을 mp3로 변환한다.
 */
public class MidiToMp3Controller {
	//midi파일들이 있는 경
	//private final static String dirPath = "../resources/save";

	@RequestMapping(value = "/midiToMp3", method=RequestMethod.GET)
	public String midiToMp3(@RequestParam(value="midi")String midiPath) {
		// TODO Auto-generated method stub
		try {
			//변수 command는 midi를 mp3로 변환하는 리눅스 명령어이 
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
