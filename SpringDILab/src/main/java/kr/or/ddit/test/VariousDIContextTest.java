package kr.or.ddit.test;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import kr.or.ddit.vo.VariousDIVO;

public class VariousDIContextTest {
	public static void main(String[] args) throws IOException {
//		Entry 포인트
		ConfigurableApplicationContext container =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/conf/variousDI-context.xml");
		
		VariousDIVO vo1 = container.getBean("variousDIVO1",VariousDIVO.class);
		VariousDIVO vo2 = container.getBean("variousDIVO2",VariousDIVO.class);
		System.out.println(vo1 == vo2); //singleton bean을 운영하고 있다 = 둘은 같은 객체
		System.out.println(vo1.toString());
		System.out.println(vo1.getFile().exists()); //파일 객체가 잘 만들어졌다
		System.out.println(vo1.getFile().getAbsolutePath()); //경로가 잘 잡혔는지. 현재 classpath를 동적으로 잡음
//		System.out.println(vo1.getFile().exists());
		
//		모든 컨테이너 구현체는 그 자체로 ResourceLoader임.
//		smart resource loader 를 사용시,
//		classpath:, file:, http: (URI scheme)의 접두어 형태로
//		리소스의 종류를 식별하여 로딩할 수 있음.
		Resource res1 = container.getResource("file://d:/contents/Desert.jpg"); //절대경로
		System.out.println(res1.getClass().getSimpleName());
		Resource res2 = container.getResource("classpath:kr/or/ddit/images/Jellyfish.jpg"); //classpath아래에서 찾아라
		System.out.println(res2.getClass().getSimpleName());
		Resource res3 = container.getResource("file://d:/contents/Desert.jpg"); //file 접두어 // 루트
		System.out.println(res3.getClass().getSimpleName()); //UrlResource
		Resource res4 = container.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		System.out.println(res4.getClass().getSimpleName());
		System.out.println(res4.contentLength());
		System.out.println(res4.exists());
		System.out.println(res4.getFilename());
		System.out.println(res3.getFile());
		System.out.println(res2.getFile());
		System.out.println(res1.getFile());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
