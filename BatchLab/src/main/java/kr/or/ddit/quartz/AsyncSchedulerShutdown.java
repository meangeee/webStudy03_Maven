package kr.or.ddit.quartz;

import java.util.concurrent.Future;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class AsyncSchedulerShutdown {
	@Autowired
	Scheduler scheduler;
	
	@Async
	public Future<String> stopAtFuture() throws SchedulerException {
		System.out.println("어플리케이션의 자원에 대한 release 등의 여타 작업 수행");
		try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {}
		scheduler.shutdown();
		return new AsyncResult<String>("작업 종료");
	}
}
