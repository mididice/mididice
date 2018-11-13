package com.mididice.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class FileStorageProperties {

	@Value("${file.save-dir}")
	private String saveDir;
	
	@Value("${file.image-dir}")
	private String resImg;

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getResImg() {
		return resImg;
	}

	public void setResImg(String resImg) {
		this.resImg = resImg;
	}
	
	
}
