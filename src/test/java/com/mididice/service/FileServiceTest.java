package com.mididice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

	@Autowired
	private FileService fileService;
	@Autowired
	private MidiService midiService;
	
	@Test
	public void fileDownloadTest() throws URISyntaxException, IOException {
		String names[] = new String[] {"11.mid","22.mid","33.mid","44.mid","55.mid","66.mid","12.mid","13.mid","14.mid"};
		String result = midiService.mergeMidi(names, "15", "9", "120");
		ResponseEntity<Resource> response = fileService.getImageAsResource(result);
		ResponseEntity r=new ResponseEntity(HttpStatus.OK);
		assertEquals(response.getHeaders(), r.getHeaders());	
		
	}
}
