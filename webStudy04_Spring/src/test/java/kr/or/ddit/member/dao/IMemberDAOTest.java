package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class IMemberDAOTest {
	
	@Inject
	IMemberDAO dao;
	Map<String, Object> paramMap;
	
	@Before
	public void setUp() throws Exception {
		paramMap = new HashMap<>();
	}

	@Test
	public void testRealDelete() {
		dao.realDelete(paramMap);
		System.out.println(paramMap.get("rowcnt"));
	}

}
