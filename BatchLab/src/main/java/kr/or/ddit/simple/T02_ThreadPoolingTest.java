package kr.or.ddit.simple;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T02_ThreadPoolingTest {
   private static class IntervalJob implements Runnable {
      private String name;
      private static int index; //모든 객체에 대한 상태유지 됨 
      IntervalJob(){ //생성자..
         this.name = "IntervalJob-"+ ++index; //숫자상태값 유지 property 필요
      }
      public String getName() {
         return name;
      }
      @Override
      public void run() {
         int count = 0;
         while (++count <= 10) {
            System.out.printf("data : %d , %s, 활성 쓰레드 수 :%d\n", count, Thread.currentThread().getName(),
                  Thread.activeCount()); 
            // 대기상태로 바꾸기
            try {
               Thread.sleep(500);
            } catch (InterruptedException e) {
               continue;
            }
         }
      }

   }

   private static class CustomExecutor implements Runnable {
      ThreadPoolExecutor poolExecutor; //주입하기
      
      
      public CustomExecutor(ThreadPoolExecutor poolExecutor) {
         super();
         this.poolExecutor = poolExecutor;
      }


      @Override
      public void run() {
         // 매 2초마다 실행하기
         while (true) {
        	poolExecutor.execute(new IntervalJob());
            try {
               Thread.sleep(2000);
            } catch (InterruptedException e) {
               continue;
            }
         }
      }
   }

   public static void main(String[] args) {
      BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(5); // 잡괄라 7+5 7ㄱㅐ는 활성, 5개는 대기
      // 13번째는 없어.. 3초기다려..
      RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {
         @Override // 거부당항 13번재 job
         public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (r instanceof IntervalJob) {
               // 내가 정의한 작업이 거부당햇다- 확인하기 
               System.err.printf("%s 작업거부 ㅠ",((IntervalJob) r).getName());
            }

         }
      };
      
      //thread 생성함
      ThreadPoolExecutor poolExecutor
         = new ThreadPoolExecutor(5, 7, 3, 
               TimeUnit.SECONDS, workQueue, rejectedHandler); // , ,3: 기다려라
      poolExecutor.execute(new CustomExecutor(poolExecutor)); //만들어진 쓰레드 재활용 ! 
      
      // 자원할당하기
//      new Thread(new CustomExecutor()).start();
      System.err.printf("메인 쓰레드의 남은 작업을 실행 ,%s\n", Thread.currentThread().getName());
      // 쓰레드 만들고 버리고 반복함....
      // 어ㅓㄸㅎ게 해결할까??????????????????
      // 쓰레드 무한제 생성은 문제됨 -> 일정갯수 미만으로 만듥 기, 만들고 버리지 말고 계속 쓰자
   }

}