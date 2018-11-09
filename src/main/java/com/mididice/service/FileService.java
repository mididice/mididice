package com.mididice.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.mididice.util.RandomString;

@Service
public class FileService {
	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	private static final int offset = 25;
	RandomString r = new RandomString();
	
	public Resource loadFileAsResource(String enc) {
		Resource resource = null;
		
		try {
			URL resultDir = ResourceUtils.getURL("classpath:static/save/");
			resource = new UrlResource(resultDir.toURI()+enc+".mp3");

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
	
	public ResponseEntity attachFileDownload(Resource resource) {
		 String contentType = null;
	        try {
	        	Path path = resource.getFile().toPath();
	            contentType = Files.probeContentType(path);
	            if(contentType == null) {
		            contentType = "application/octet-stream";
		        }
	            return ResponseEntity.created(resource.getURI())
	    	        	.contentType(MediaType.parseMediaType(contentType))
	    	        	.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	    	        	.body(resource);
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	            return ResponseEntity
	                    .status(HttpStatus.FORBIDDEN)
	                    .body("Error Message");
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
