package com.mididice.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mididice.util.RandomString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {

	@Test
	public void validEncryptFileName() {
		RandomString r = new RandomString();
		String enc = r.encrypt("iu", 25);
		String result = r.decrypt(enc, 25);
		assertEquals("iu", result);
	}
}
