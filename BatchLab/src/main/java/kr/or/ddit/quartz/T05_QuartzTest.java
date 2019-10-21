package kr.or.ddit.quartz;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.quartz.SchedulerException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T05_QuartzTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException, SchedulerException {
		ConfigurableApplicationContext container =
				new ClassPathXmlApplicationContext("kr/or/ddit/quartz/quartz-context.xml");
	
//		AsyncSchedulerShutdown stopper = 
//				container.getBean(AsyncSchedulerShutdown.class);
//		Future<String> future = stopper.stopAtFuture();
//		while(true) {
//			if(future.isDone()) {
//				String message = future.get();
//				System.out.println(message);
//				container.close();
//				break;
//			}
//		}
		
	}
}
