package kr.or.ddit.simple;

import java.util.Timer;
import java.util.TimerTask;

public class T03_TimerTaskTest {
	private static class InterverJob extends TimerTask{

		int count = 0;
		@Override
		public void run() {
			if(++count==11) cancel();
			System.out.printf("data : %d, %s, %d\n", count, Thread.currentThread().getName(),
								Thread.activeCount());
		}
	}
	
	private static class CustomExecutor extends TimerTask{
	    Timer timer;
	     public CustomExecutor(Timer timer) {
	        super();
	        this.timer= timer;
	     }
	      @Override
	      public void run() {
	            new Thread(new InterverJob()).start(); //1초마다 1번식 돌아가야함
	      }
	   }
	
	
	
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new CustomExecutor(timer), 0, 2000);
	    System.err.printf("메인 쓰레드의 남은 작업을 실행 ,%s\n",Thread.currentThread().getName());
	      
	   }
}
