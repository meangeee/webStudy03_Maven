package kr.or.ddit.springtask;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;

public class IntervalJobTask {
	
	private int count = 0;
	
	@Scheduled(cron="*/3 * * * * *")
	public void tsetCron() {
		Calendar cal = Calendar.getInstance();
		System.out.printf("%tc 에 실행된 작업\n ", cal);
	}
	
	@Scheduled(initialDelay=0, fixedRate=1000)
	public void breakTime() {
		System.out.printf("data : %d ,%s, 활성쓰레드수 : %d\n", ++count,
				Thread.currentThread().getName(),Thread.activeCount());
	}
}