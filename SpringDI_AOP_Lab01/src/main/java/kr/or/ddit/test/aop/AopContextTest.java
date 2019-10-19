package kr.or.ddit.test.aop;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.service.IIdolSearchService;

public class AopContextTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext container =
				new ClassPathXmlApplicationContext(
						"kr/or/ddit/conf/autoDI-context.xml"
						,"kr/or/ddit/conf/aop-context.xml"
						);
		IIdolSearchService service = container.getBean(IIdolSearchService.class);
		System.out.println(service.getClass());
		String info = service.readIdol("I001");
		System.out.println(info);
		service.readIdols();
	}
	

}
