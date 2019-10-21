package kr.or.ddit.quartz;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.listeners.SchedulerListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomSchedulerListener extends SchedulerListenerSupport{
	
	@Autowired
	ConfigurableApplicationContext container;
	
	@Autowired
	AsyncSchedulerShutdown asyncShutdown;
	
	@Override
	public void triggerFinalized(Trigger trigger) {
		System.err.printf("%s 종료(작업 종료 상태 확인)",  trigger);
		try {
			Future<String> future = asyncShutdown.stopAtFuture();
			while(true) {
				if(future.isDone()) {
					String message = future.get();
					System.out.println(message);
					container.close();
					break;
				}
			}
		} catch (SchedulerException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
