package kr.or.ddit.quartz;

public class InterPOJOJob{
	private int count = 0;
	private static int index = 0;
	private String name;
	public InterPOJOJob(){
		this.name = "InterQuartzJob-" + ++index;
	}
	
	public void minji()  {
		System.out.printf("data : %d ,%s, %s, 활성쓰레드수 : %d\n", ++count,
				name, Thread.currentThread().getName(),Thread.activeCount());
		
	}

}
