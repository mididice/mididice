package com.mididice.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


@Service
public class ImageService {
	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

	//receive variable (bar count), (filename.midi array)
	public String mergeImage( String bar, String imgNames[], String filename){

		String patternPath = null;
		String resultImgPath = null;
		try {
			URL patternDir = ResourceUtils.getURL("classpath:static/images/patterns/");
			URL resImgDir = ResourceUtils.getURL("classpath:static/resimg/");
			patternPath = patternDir.getPath();
			resultImgPath = resImgDir.getPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int s = (int)Math.sqrt(Integer.parseInt(bar));  //bar count
		String barimg[][] = new String[s][s];

		int cnt1 = 0;
		for(int a=0; a<s; a++){
			for(int b=0; b<s; b++){
				barimg[a][b] = imgNames[cnt1];
				cnt1++;
			}
		}
		//String barimg2[][] = {{"22","11","11"},{"22","11","11"},{"22","11","11"},{"22","11","11"}};
		ArrayList<BufferedImage> bf = new ArrayList<BufferedImage>();

		try {
			for(int i=0;i<s;i++){
				for(int j=0;j<s;j++){
					File f = new File(patternPath+barimg[i][j]+".png");
//					System.out.println(f.getAbsolutePath());
					BufferedImage image = ImageIO.read(f); 
					bf.add(image);

//					if(f.exists()){
//						logger.info("exists file");
//					}else{
//						logger.error("no file");
//					}
				}
			}
			int w = bf.get(0).getWidth();
			int h = bf.get(0).getHeight();

			BufferedImage merged = new BufferedImage(w*s ,h*s, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = (Graphics2D) merged.getGraphics();

			graphics.setBackground(Color.WHITE);
			int cnt = 0;
			for(int a=0; a<s ; a++){
				for(int b=0; b<s; b++){
					graphics.drawImage(bf.get(cnt), b*h, a*h, null);
					cnt++;
				}
			}

			/*			
				graphics.drawImage(bf.get(0), 0, 0, null);
				graphics.drawImage(bf.get(1), w, 0, null);
				graphics.drawImage(bf.get(2), 0, h, null);
				graphics.drawImage(bf.get(3), w, h, null);
			 */

			ImageIO.write(merged, "jpg", new File(resultImgPath+"res_"+filename+".jpg"));

			//ImageIO.write(merged, "png", new File(patternPath+"rerere.png")); //result image file
			// ImageIO.write(mergedImage, "jpg", new File("c:\\mergedImage.jpg"));
			// ImageIO.write(mergedImage, "png", new File("c:\\mergedImage.png"));
			String resultImg = "res_"+filename+".jpg";
			logger.info("generated image : ", resultImg);
			return resultImg;
		}catch (Exception e) {
			logger.error("exception generated image");
			return "";
		}
		
	}
}