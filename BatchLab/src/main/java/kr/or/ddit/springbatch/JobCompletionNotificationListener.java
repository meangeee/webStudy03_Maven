package kr.or.ddit.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	//로거의 역할은 단순하게 메시지 전달이기 때문에 여러번 객체생성 할 이유 x 그래서 private static
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	
	//super code는 afterJob에선 의미가 없음
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(!BatchStatus.COMPLETED.equals(jobExecution.getStatus())) return;
		//성공
		//db에서 꺼내서 확인을 해봐야 할 것 아닙니까 쿼리문을 통해 데이터를 조회해봐야지
		String sql = "SELECT PERSON_ID, FIRST_NAME, LAST_NAME FROM PEOPLE";
		//result s어쩌고 타입 이랑 int 
		jdbcTemplate.query(sql, (rs, index)->{
			Person person = Person.builder()
					.personId(rs.getLong("PERSON_ID"))
					.firstName(rs.getString("FIRST_NAME"))
					.lastName(rs.getString("LAST_NAME"))
					.build();
			return person;
		});

		
		
	}
}
