package kr.or.ddit.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class InterQuartzJob extends QuartzJobBean{
	private int count = 0;
	private static int index = 0;
	private String name;
	public InterQuartzJob(){
		this.name = "InterQuartzJob-" + ++index;
	}
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.printf("data : %d ,%s, %s, 활성쓰레드수 : %d\n", ++count,
				name, Thread.currentThread().getName(),Thread.activeCount());
		
	}

}
