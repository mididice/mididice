package com.mididice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
