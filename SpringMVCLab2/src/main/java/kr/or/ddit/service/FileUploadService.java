package kr.or.ddit.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.controller.FileVO;

@Service
public class FileUploadService {
	//이부분 모르겠네요.
	@Inject
	WebApplicationContext container;
	ServletContext application;
	//xml에서 쓰는걸 자바에서
	@Value("#{appInfo.imagesUrl}")
	private String saveFolderUrl;
	
	//알듯말듯. 복습을 합시다.
	@Value("#{appInfo['imagesClientUrl']}")
	private String clientUrl;

	File saveFolder;
	//생성자보다 나중에 실행
	@PostConstruct	//객체를 생성한 다음에 나중에 실행되는 callback
	public void init(){
		//몰라요 이짓을 왜 하는거죠 자꾸
		application = container.getServletContext();
		//어디에다가 어떤이름으로 할지 설정해줘야함
		String folderPath = application.getRealPath(saveFolderUrl);
		saveFolder = new File(folderPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();

	}
	
	public void upload(FileVO fileVO) throws IllegalStateException, IOException {
		// binary 미들티어에
		fileVO.transfer(saveFolder);
		// meta 다오를 이용해 db에 저장?
		System.out.println("메타데이터 저장");
	}
}





