package kr.or.ddit.member;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.member.dao.IMemberDAO;

@Component
public class MemberDeleteQuartzJob {
   private static Logger logger = 
		   LoggerFactory.getLogger(MemberDeleteQuartzJob.class);
   
   @Inject
   IMemberDAO dao;
   
   
   @Transactional
   public void delete() {
      Map<String, Object> paramMap = new HashMap<>();
      dao.realDelete(paramMap);
      logger.info("{} 명 탈퇴 처리 완료.",paramMap.get("rowcnt"));
   }
}