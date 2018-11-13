package com.mididice.service;


import static org.junit.Assert.assertEquals;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
        List<File> list = new ArrayList<File>();
        list.addAll(Arrays.asList(listOfFiles));
        Collections.sort(list);
        assertEquals(list.get(0).getName(), "11.mid");
	}
	@Test
	public void getNewResource() {
		Resource resource = new ClassPathResource("/static/save/iu.mp3");
		assertEquals(resource.getFilename(), "iu.mp3");
	}
}
