package kr.or.ddit.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolSearchDAO_Mysql;
import kr.or.ddit.idol.service.IIdolSearchService;
import kr.or.ddit.idol.service.IdolSearchServiceImpl;

@Configuration
@Lazy
//무슨의미세요
@ComponentScan(basePackages="kr.or.ddit")
@ImportResource("classpath:kr/or/ddit/conf/props-context.xml")
public class JavaConfigContext {
	//dao, service
//	@Bean
//	public IIdolSearchDAO mySqlDAO() {
//		return new IdolSearchDAO_Mysql();
//	}
//	
//	@Bean
//	@Scope("prototype")
//	public IIdolSearchService idolService() {
//		return new IdolSearchServiceImpl(mySqlDAO());
//	}
	
	
}
