package kr.or.ddit.simple;

public class T01_SimpleThreadTest {
	private static class InterverJob implements Runnable{

		@Override
		public void run() {
			int count = 0;
			while(++count <= 100) {
				
				System.out.printf("data : %d, %s, %d\n", count, Thread.currentThread().getName(),
						Thread.activeCount());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}
	}
	
	private static class CustomExecutor implements Runnable{
		@Override
		public void run() {
			while(true) {
				new Thread(new InterverJob()).start();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
	      //자원할당하기
//		new Thread(new InterverJob()).start();
		new Thread(new CustomExecutor()).start();
	      System.err.printf("메인 쓰레드의 남은 작업을 실행 ,%s\n",Thread.currentThread().getName());
	      
	   }
}
