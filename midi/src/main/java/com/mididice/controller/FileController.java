package com.mididice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mididice.service.FileService;
import com.mididice.util.RandomString;

/* 
	?��?�� ?��?��로드 �? 결과?���? 컨트롤러
	Request : (/res?filename=?��?���?)  ==> (/res/randomstring) ==> mp3?��?�� ?��?��, ?��?�� ?��
*/

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	private FileService fileService;
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@GetMapping("/download/{enc}")
	public ResponseEntity<Resource> download(@PathVariable String enc, HttpServletRequest request
			) throws Exception{

        Resource resource = fileService.loadFileAsResource(enc);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	//?��?�� ?��?��로드
	@GetMapping("/res/download2.do")
	public ModelAndView download2(@RequestParam("m") String m, HttpServletRequest request
			) throws Exception{

		//최종 mp3?��?��?�� 경로
		String realPath = request.getSession().getServletContext().getRealPath("/resources/mp4/");

		//File down = new File(realPath+r.decrypt(enc, offset)+".mid"); // ==> mp3�? �?경해?��?��
		File down = new File(realPath+m+".mp4");

		//System.out.println(realPath+r.decrypt(enc, offset));
		if(!down.canRead()){
			throw new Exception("?��?��?�� 찾을?�� ?��?��?��?��(^_^)");

		}
		return new ModelAndView("download","downloadFile",down);
	}

}
