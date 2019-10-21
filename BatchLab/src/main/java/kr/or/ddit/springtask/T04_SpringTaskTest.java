package kr.or.ddit.springtask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T04_SpringTaskTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ConfigurableApplicationContext container =
				new ClassPathXmlApplicationContext("kr/or/ddit/springtask/task-context.xml");
		
		AsyncStopper asyncStopper = container.getBean(AsyncStopper.class);
		Future<String> future = asyncStopper.stopAtFuture();
		System.err.println("이게 언제 실행될까???");
		while(true) {
			if(future.isDone()) {
				String message = future.get();
				System.err.println(message);
				container.close();
				break;
			}
		}
	}
}
