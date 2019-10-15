package kr.or.ddit.idol.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolSearchDAO_Oracle;
import kr.or.ddit.idol.service.IIdolSearchService;
import kr.or.ddit.idol.service.IdolSearchServiceImpl;

public class IdolSearchView {
	public static void main(String[] args) {
		//전략 주입자역할을 하는 애가 다 떠맡는다고요?
		//전략 주입 뭐 전략객체 뭔소리에요.
//		IIdolSearchDAO dao = new IdolSearchDAO_Oracle();
//		IIdolSearchService service = 
//				new IdolSearchServiceImpl(dao);
		ApplicationContext container = 
				new ClassPathXmlApplicationContext("kr/or/ddit/idol/conf/idol-context.xml");
		IIdolSearchService service = 
				//컨테이너안에는 여러개의 알맹이가 있음 = bean. 
//				찾아내기 위한 검색조건 설정해준것임
				container.getBean(IIdolSearchService.class);
		String code = "I001";
		String info = service.readIdol(code);
		System.out.println(info);
	}
}

