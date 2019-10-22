package kr.or.ddit.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogStep{
   //단순한 POJO를 만들고 싶음
   private static Logger logger = LoggerFactory.getLogger(SimpleLogStep.class);
   public void previous() {
      logger.info("작업시작");
   }

   
      
}