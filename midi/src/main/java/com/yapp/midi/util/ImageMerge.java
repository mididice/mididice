package com.yapp.midi.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class ImageMerge {
	
	//receive variable (bar count), (filename.midi array)
	public String returndeMergeImage( HttpServletRequest req, String bar, String imgNames[], String filename){
		
		String patternPath = req.getSession().getServletContext().getRealPath("/resources/images/patterns/");
		String resultImgPath = req.getSession().getServletContext().getRealPath("/resources/resimg/");
		
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
					System.out.println(f.getAbsolutePath());
					BufferedImage image = ImageIO.read(f); 
					bf.add(image);
				
					if(f.exists()){
						System.out.println("파일을 읽었어요");
					}else{
						System.out.println("no file");
					}
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

		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("이미지 합성 완료!");
		String resultImg = "res_"+filename+".jpg";
		return resultImg;
	}
}
