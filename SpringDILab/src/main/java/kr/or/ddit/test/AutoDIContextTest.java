package kr.or.ddit.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.service.IIdolSearchService;

public class AutoDIContextTest {
	public static void main(String[] args) {
		//container 생성부터임 구조 잘 기억해두기
		ConfigurableApplicationContext container =
				new ClassPathXmlApplicationContext("kr/or/ddit/conf/autoDI-context.xml");
		
		//bean등록 몇개나 되어있는지
		int count = container.getBeanDefinitionCount();
		System.out.println(count);
		//어떤애들이 등록되어있는지 확인
		String[] names = container.getBeanDefinitionNames();
		for(String name : names) {
			System.err.println(name);
		}
		//주입
		IIdolSearchService service = container.getBean(IIdolSearchService.class);
		String info = service.readIdol("I001");
		System.out.println(info);
	}
}
