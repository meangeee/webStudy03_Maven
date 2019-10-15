package kr.or.ddit.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.service.IIdolSearchService;

public class SimpleContextTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext container =
				new ClassPathXmlApplicationContext("kr/or/ddit/conf/simple-context.xml");
//		IIdolSearchService service = container.getBean("service3",IIdolSearchService.class);
//		System.out.println(service.readIdol("I002"));
//		IIdolSearchDAO dao1 = container.getBean(IIdolSearchDAO.class);
//		IIdolSearchDAO dao2 = container.getBean(IIdolSearchDAO.class);
//		System.out.println(dao1);
//		System.out.println(dao2);
//		System.out.println(dao1==dao2);
//		container.close();
	}
}
