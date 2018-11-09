package com.mididice.service;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

	@Test
	public void resourceFileTest() throws URISyntaxException, IOException {

		URL resultDir = ResourceUtils.getURL("classpath:static/save/");
		
		Resource resource = new UrlResource(resultDir.toURI()+"iu.mp3");
		
		if(resource.exists()) {
			assertTrue(true);	
		}else {
			assertTrue(false);
		}
		
	}
}
