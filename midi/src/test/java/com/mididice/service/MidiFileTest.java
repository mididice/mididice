package com.mididice.service;


import static org.junit.Assert.assertEquals;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MidiFileTest {

	@Test
	public void getFile() throws IOException, URISyntaxException {
		URL dir = ResourceUtils.getURL("classpath:static/midi/");
        File folder = new File(dir.toURI());
        File[] listOfFiles = folder.listFiles();
        assertEquals(listOfFiles[0].getName(), "11.mid");
	}
}
