package kr.or.ddit.springbatch;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T06_BatchQuartzTest {
   public static void main(String[] args) {
      ConfigurableApplicationContext container 
         = new ClassPathXmlApplicationContext("kr/or/ddit/springbatch/*-context.xml");
   }
   
}