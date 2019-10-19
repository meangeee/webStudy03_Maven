package kr.or.ddit.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.conf.JavaConfigContext;
import kr.or.ddit.idol.service.IIdolSearchService;
import kr.or.ddit.vo.DbInfoVO;

public class JavaConfigContextTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = 
				new AnnotationConfigApplicationContext(JavaConfigContext.class);
//		IIdolSearchService service1 = container.getBean(IIdolSearchService.class);
//		IIdolSearchService service2 = container.getBean(IIdolSearchService.class);
//		System.out.println(service1.readIdol("I002"));
//		System.out.println(service1==service2);
		DbInfoVO vo = container.getBean("dbInfoVO", DbInfoVO.class);
		System.out.println(vo);
	}
}
