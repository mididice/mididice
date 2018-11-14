package com.mididice.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mididice.exception.FileStorageException;
import com.mididice.property.FileStorageProperties;
import com.mididice.util.RandomString;

@Service
public class FileService {
	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	private static final int offset = 25;
	RandomString r = new RandomString();
	
	private final Path imageStorageLocation;
	private final Path musicStorageLocation;
	public FileService(FileStorageProperties fileStorageProperties) {
		this.imageStorageLocation = Paths.get(fileStorageProperties.getResImg()).toAbsolutePath().normalize();
		this.musicStorageLocation = Paths.get(fileStorageProperties.getSaveDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.imageStorageLocation);
			Files.createDirectories(this.musicStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create dir", ex);
		}
	}
	
	public Resource loadFileAsResource(String enc) {
		Resource resource = null;
		
		try {
			Path targetLocation = musicStorageLocation.resolve(enc+".mp3");
			resource = new UrlResource(targetLocation.toUri());
//			URL resultDir = ResourceUtils.getURL("classpath:static/save/");
//			resource = new UrlResource(resultDir.toURI()+enc+".mp3");

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
	
	public ResponseEntity<Resource> getImageAsResource(String imageName) throws MalformedURLException {
	    HttpHeaders headers = new HttpHeaders();
		Path targetLocation = this.imageStorageLocation.resolve(imageName);
	    Resource resource = new UrlResource(targetLocation.toUri());
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	public ResponseEntity<Resource> getMusicAsResource(String mp3Name) throws MalformedURLException {
	    HttpHeaders headers = new HttpHeaders();
		Path targetLocation = this.musicStorageLocation.resolve(mp3Name);
		System.out.println(targetLocation.toAbsolutePath());
		System.out.println(targetLocation.toString());
	    Resource resource = new UrlResource(targetLocation.toUri());
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
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
