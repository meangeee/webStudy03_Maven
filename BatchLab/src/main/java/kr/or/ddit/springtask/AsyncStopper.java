package kr.or.ddit.springtask;

import java.util.concurrent.Future;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class AsyncStopper {
	
	@Async
	public Future<String> stopAtFuture() {
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {}
		
		return new AsyncResult<String>("작업 종료");
	}
}

