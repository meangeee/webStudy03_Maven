package kr.or.ddit.servlet02.service;

import java.io.File;

public class ImageListService {
	public String[] getImageList(){
		
//		3. 요청 정보를 생성 --> layered architecture
//		1) raw data 확보
//		2) data가공해서 information으로 생성(logic) raw data 가공 = information
	 
		File imaFolder = new File("d:/contents");
		String[] images = imaFolder.list((dir,name)->{return name.endsWith(".jpg") || name.endsWith(".gif");}); 
		return images;
	}
}
