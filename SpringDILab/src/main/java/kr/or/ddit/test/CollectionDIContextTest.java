package kr.or.ddit.test;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.vo.CollectionDIVO;

public class CollectionDIContextTest {
	public static void main(String[] args) {
		try (	
				
				// child container 에서 공통 사용될 공통 빈 등록.
				ConfigurableApplicationContext root =
				new ClassPathXmlApplicationContext("kr/or/ddit/conf/variousDI-context.xml");
				
				
				ConfigurableApplicationContext container =
				// classpath가 붙어 있기 때문에 classpath 안써줘도 됨
						//import 2번째 방법
//						classpath:kr/or/ddit/conf/variousDI-context.xml
				
				new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/conf/collectionDI-context.xml"},root);) {
			//container2는 못했어요 뭔가요
			
			// try 했는데 error가 없는 이유 클로즈 해줘야 한다는 의미
			
			//CollectionDIVO.class 이캐 그냥 돌리면 왜 어ㅗ류?
			CollectionDIVO vo1 = container.getBean("vo2",CollectionDIVO.class);
			System.out.println(Arrays.toString(vo1.getArray()));
			System.out.println(vo1.getList());
			System.out.println(vo1.getSet());
			System.out.println(vo1.getMap());
			System.out.println(vo1.getProps());

		}
	}
}
