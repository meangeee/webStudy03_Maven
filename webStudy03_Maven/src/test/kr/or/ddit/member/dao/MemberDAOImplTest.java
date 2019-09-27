package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	IMemberDAO dao = MemberDAOImpl.getInstance();
	
	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember(new MemberVO("b001", null));
//		assertNotNull(member);
		assertNull(member);
	}

	@Test
	public void testInsertMember() {
	}

}
