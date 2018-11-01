package com.mididice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;
import com.mididice.util.YoutubeAuth;

@Controller
public class ProcessYoutubeController {
	
	private static YouTube youtube;
	private static String VIDEO_FILE_FORMAT = "video/*";
	
	//파일 변환 로딩 화면
	@RequestMapping(value = "/res/convert", method = RequestMethod.POST)
	public String loading(@RequestParam("i") String img, @RequestParam("m") String mus, Model model){
		model.addAttribute("mus", mus);
		model.addAttribute("img", img);
		return "loading"; //로딩화면
	}
	
	//파일 변환(mp3 + img ==> mp4)
	@ResponseBody
	@RequestMapping(value = "/convert.do")
	public String mergeTwoFile(@RequestParam("i") String img, @RequestParam("m") String mus, 
			Model model, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		String videoPath = request.getSession().getServletContext().getRealPath("/resources/mp4/");
		String imagePath = request.getSession().getServletContext().getRealPath("/resources/resimg/"+img);
		String soundPath = request.getSession().getServletContext().getRealPath("/resources/save/"+mus+".mp3");
		
		Map<String, Object> code = new HashMap<String, Object>();
		
		try {
			Process process = 
					Runtime.getRuntime().exec("ffmpeg -loop 1 -i "+imagePath+" -i "+soundPath
							+" -c:v libx264 -c:a aac -strict experimental -b:a 192k -shortest "+videoPath+mus+".mp4"); 
			
			process.waitFor();
			
				if(process.exitValue() == 0){
					//Thread.sleep(1000);
					System.out.println("정상적으로 프로세스 실행 종료");
					code.put("code", 0); //파일 변환 완료
				}else{
					code.put("code", 1); //오류
					code.put("val", process.exitValue());
					code.put("a", process.getErrorStream());
					code.put("b", process.getInputStream());
				}
		
		} catch(Exception e) { 
			e.printStackTrace(); 
		}	
		
		String c = new ObjectMapper().writeValueAsString(code);
		return c;
	}	
	
	//Youtube upload API , 유투브로 mp4 파일 업로드 및 공유
	@ResponseBody
	@RequestMapping(value = "/upload.do" , method = {RequestMethod.GET, RequestMethod.POST})
	public String uploadVideo(@RequestParam("v") String video, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
		
		// Scope required to upload to YouTube.
	    List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");
	    String videoF = request.getSession().getServletContext().getRealPath("/resources/mp4/"+video+".mp4");
	    Map<String, Object> code = new HashMap<String, Object>();

	    try {
	      // Authorization.
	      Credential credential = YoutubeAuth.authorize(scopes);

	      // YouTube object used to make all API requests.
	      youtube = new YouTube.Builder(YoutubeAuth.HTTP_TRANSPORT, YoutubeAuth.JSON_FACTORY, credential).setApplicationName(
	          "youtube-cmdline-uploadvideo-sample").build();
	      File videoFile = new File(videoF);
	      System.out.println("You chose " + videoFile.getAbsolutePath() + " to upload.");
	      
	      // Add extra information to the video before uploading.
	      Video videoObjectDefiningMetadata = new Video();

	      VideoStatus status = new VideoStatus();
	      status.setPrivacyStatus("public");
	      videoObjectDefiningMetadata.setStatus(status);

	      // We set a majority of the metadata with the VideoSnippet object.
	      VideoSnippet snippet = new VideoSnippet();

	      Calendar cal = Calendar.getInstance();
	      //snippet.setTitle("Test Upload via Java on " + cal.getTime());
	      snippet.setTitle("MIDIdice");
	      snippet.setDescription(
	          "Video uploaded on " + cal.getTime());

	      // Set your keywords.
	      List<String> tags = new ArrayList<String>();
	      tags.add("midi");
	      tags.add("Mididice");
	      tags.add("Composition");
	      tags.add("Roll");
	      snippet.setTags(tags);

	      // Set completed snippet to the video object.
	      videoObjectDefiningMetadata.setSnippet(snippet);

	      InputStreamContent mediaContent = new InputStreamContent( VIDEO_FILE_FORMAT, new BufferedInputStream(new FileInputStream(videoFile)));
	      mediaContent.setLength(videoFile.length());

	      YouTube.Videos.Insert videoInsert = youtube.videos()
	          .insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

	      // Set the upload type and add event listener.
	      MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

	      uploader.setDirectUploadEnabled(false);

	      MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
	        public void progressChanged(MediaHttpUploader uploader) throws IOException {
	          switch (uploader.getUploadState()) {
	            case INITIATION_STARTED:
	              System.out.println("Initiation Started");
	              break;
	            case INITIATION_COMPLETE:
	              System.out.println("Initiation Completed");
	              break;
	            case MEDIA_IN_PROGRESS:
	              System.out.println("Upload in progress");
	              System.out.println("Upload percentage: " + uploader.getProgress());
	              break;
	            case MEDIA_COMPLETE:
	              System.out.println("Upload Completed!");
	              break;
	            case NOT_STARTED:
	              System.out.println("Upload Not Started!");
	              break;
	          }
	        }
	      };
	      uploader.setProgressListener(progressListener);

	      // Execute upload.
	      Video returnedVideo = videoInsert.execute();

	      // Print out returned results.
	      System.out.println("\n================== Returned Video ==================\n");
	      System.out.println("  - Id: " + returnedVideo.getId());
	      code.put("id", returnedVideo.getId());
	      System.out.println("  - Title: " + returnedVideo.getSnippet().getTitle());
	      System.out.println("  - Tags: " + returnedVideo.getSnippet().getTags());
	      System.out.println("  - Privacy Status: " + returnedVideo.getStatus().getPrivacyStatus());
	      //System.out.println("  - Video Count: " + returnedVideo.getStatistics().getViewCount());
	      code.put("code", 0);
	    } catch (GoogleJsonResponseException e) {
	      System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
	          + e.getDetails().getMessage());
	      e.printStackTrace();
	    } catch (IOException e) {
	      System.err.println("IOException: " + e.getMessage());
	      e.printStackTrace();
	    } catch (Throwable t) {
	      System.err.println("Throwable: " + t.getMessage());
	      t.printStackTrace();
	    }
	    String c = new ObjectMapper().writeValueAsString(code);
		return c;
	}
	
	//유투브 결과 화면
	@RequestMapping(value = "/res/youtbs" , method = RequestMethod.GET)
	public String SuccessYoutube(@RequestParam("id") String id, Model model){
		model.addAttribute("id", id);
		return "youtube";
	}
}
