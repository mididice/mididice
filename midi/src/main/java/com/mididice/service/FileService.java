package com.mididice.service;

import java.net.URL;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.mididice.util.RandomString;

@Service
public class FileService {
	private static final int offset = 25;
	RandomString r = new RandomString();
	
	public Resource loadFileAsResource(String enc) {
		Resource resource = null;
		
		try {
			String fileName = r.decrypt(enc, offset);
			URL resultDir = ResourceUtils.getURL("classpath:static/save/");
			resource = new UrlResource(resultDir.toURI()+fileName+".mp3");

			if(resource.exists()) {
				return resource;
			}else {
				throw new Exception("파일이 존재 하지 않음");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resource;
		}
	}
	
	public String encrypt(String filename) {
		if(filename.indexOf('.')==-1){
			return r.encrypt(filename, offset);
		}else{
			return r.encrypt(filename.substring(0, filename.length()-4), offset);
		}
	}
	
	public String decrypt(String enc) {
		return r.decrypt(enc, offset);

	}
}
