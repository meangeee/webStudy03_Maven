package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	
	private SqlSessionFactory SqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public int insertMember(MemberVO member) {
		// insert/update/delete 의 경우 autoCommit 설정이 없을때
		// commit필요
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt = mapper.insertMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberList(); //아래코드보다 속도가 느리긴 함. 하지만 안정성이 높음
//			return sqlSession.selectList("kr.or.ddit.member.dao.IMemberDAO.selectMemberList");
		}
	}

	@Override
	public MemberVO selectMember(MemberVO member) {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			mapper.selectMember(member);
			sqlSession.commit();
			return mapper.selectMember(member);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();
				){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt = mapper.updateMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteMember(MemberVO member) {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();
				){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt = mapper.deleteMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

}
