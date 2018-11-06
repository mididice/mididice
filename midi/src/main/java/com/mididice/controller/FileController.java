package com.mididice.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mididice.service.FileService;

@ControllerAdvice
@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	private FileService fileService;
	
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@GetMapping("/download/{enc}")
	public ResponseEntity download(@PathVariable String enc
			) throws Exception{

        Resource resource = fileService.loadFileAsResource(enc);
        logger.info("someone has download");
        return fileService.attachFileDownload(resource);
    }
	
	//mp4
	@GetMapping("/res/download2.do")
	public ModelAndView download2(@RequestParam("m") String m, HttpServletRequest request
			) throws Exception{

		String realPath = request.getSession().getServletContext().getRealPath("/resources/mp4/");

		File down = new File(realPath+m+".mp4");

		if(!down.canRead()){
			throw new Exception("mp4 not found");
		}
		
		return new ModelAndView("download","downloadFile",down);
	}

}
