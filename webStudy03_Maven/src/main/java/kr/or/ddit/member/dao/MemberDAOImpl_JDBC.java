package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class MemberDAOImpl_JDBC implements IMemberDAO {
	private static MemberDAOImpl_JDBC instance;

	private MemberDAOImpl_JDBC() {
	}

	public static MemberDAOImpl_JDBC getInstance() {
		if (instance == null)
			instance = new MemberDAOImpl_JDBC();
		return instance;
	}

	@Override
	public MemberVO selectMember(MemberVO member) {
		MemberVO savedMember = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                                       ");
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,                        ");
		sql.append("     MEM_REGNO1,    MEM_REGNO2,                               ");
		sql.append("     TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,                  ");
		sql.append("     MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                       ");
		sql.append("     MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                   ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,                       ");
		sql.append("     MEM_MEMORIAL,                                            ");
		sql.append("     TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,  ");
		sql.append("     MEM_MILEAGE,                                             ");
		sql.append("     MEM_DELETE                                               ");
		sql.append(" FROM  MEMBER                                                 ");
		sql.append(" WHERE MEM_ID = ?                                             ");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, member.getMem_id());
//			pstmt.setString(2, member.getMem_pass());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				savedMember = new MemberVO();
				savedMember.setMem_id(rs.getString("MEM_ID"));
				savedMember.setMem_pass(rs.getString("MEM_PASS"));
				savedMember.setMem_name(rs.getString("MEM_NAME"));
				savedMember.setMem_regno1(rs.getString("MEM_REGNO1"));
				savedMember.setMem_regno2(rs.getString("MEM_REGNO2"));
				savedMember.setMem_bir(rs.getString("MEM_BIR"));
				savedMember.setMem_zip(rs.getString("MEM_ZIP"));
				savedMember.setMem_add1(rs.getString("MEM_ADD1"));
				savedMember.setMem_add2(rs.getString("MEM_ADD2"));
				savedMember.setMem_hometel(rs.getString("MEM_HOMETEL"));
				savedMember.setMem_comtel(rs.getString("MEM_COMTEL"));
				savedMember.setMem_hp(rs.getString("MEM_HP"));
				savedMember.setMem_mail(rs.getString("MEM_MAIL"));
				savedMember.setMem_job(rs.getString("MEM_JOB"));
				savedMember.setMem_like(rs.getString("MEM_LIKE"));
				savedMember.setMem_memorial(rs.getString("MEM_MEMORIAL"));
				savedMember.setMem_memorialday(rs.getString("MEM_MEMORIALDAY"));
				savedMember.setMem_mileage(rs.getInt("MEM_MILEAGE"));
				savedMember.setMem_delete(rs.getString("MEM_DELETE"));
				// reflection 이후 작성.
			}
			return savedMember;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
//		MemberVO newMember = new MemberVO();
//		StringBuffer sql = new StringBuffer();
//		
//		sql.append("INSERT INTO MEMBER ");
//		sql.append(" MEM_ID    , ");
//		sql.append(" MEM_PASS  , ");
//		sql.append(" MEM_NAME  , ");
//		sql.append(" MEM_ZIP   , ");
//		sql.append(" MEM_ADD1  , ");
//		sql.append(" MEM_ADD2  , ");
//		sql.append(" MEM_MAIL  ");
//		sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ? ");
//		
//		
//		try (Connection conn = ConnectionFactory.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
//			// 쿼리 파라미터 설정
//			int idx = 0;
//			pstmt.setString(idx++, member.getMem_id());
//			pstmt.setString(idx++, member.getMem_pass());
//			pstmt.setString(idx++, member.getMem_name());
//			pstmt.setString(idx++, member.getMem_zip());
//			pstmt.setString(idx++, member.getMem_add1());
//			pstmt.setString(idx++, member.getMem_add2());
//			pstmt.setString(idx++, member.getMem_mail());
//			return pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
		
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO MEMBER (  ");
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,    MEM_REGNO1,      ");
		sql.append("     MEM_REGNO2,    MEM_BIR,    MEM_ZIP,    MEM_ADD1,      ");
		sql.append("     MEM_ADD2,    MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,   ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,    MEM_MEMORIAL,   ");
		sql.append("     MEM_MEMORIALDAY,    MEM_MILEAGE       ");
		sql.append(" ) VALUES (                                                ");
		sql.append(" 	?,    ?,    ?,    ?,                                   ");
		sql.append("     ?,    ?,    ?,    ?,                                  ");
		sql.append("     ?,    ?,    ?,    ?,                                  ");
		sql.append("     ?,    ?,    ?,    ?,                                  ");
		sql.append("     ?,    2000		                                   ");
		sql.append(" )                                                         ");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정
			int idx = 1;
			pstmt.setString(idx++, member.getMem_id());
			pstmt.setString(idx++, member.getMem_pass());
			pstmt.setString(idx++, member.getMem_name());
			pstmt.setString(idx++, member.getMem_regno1());
			pstmt.setString(idx++, member.getMem_regno2());
			pstmt.setString(idx++, member.getMem_bir());
			pstmt.setString(idx++, member.getMem_zip());
			pstmt.setString(idx++, member.getMem_add1());
			pstmt.setString(idx++, member.getMem_add2());
			pstmt.setString(idx++, member.getMem_hometel());
			pstmt.setString(idx++, member.getMem_comtel());
			pstmt.setString(idx++, member.getMem_hp());
			pstmt.setString(idx++, member.getMem_mail());
			pstmt.setString(idx++, member.getMem_job());
			pstmt.setString(idx++, member.getMem_like());
			pstmt.setString(idx++, member.getMem_memorial());
			pstmt.setString(idx++, member.getMem_memorialday());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<MemberVO> selectMemberList(PagingInfoVO pagingVO) {
		List<MemberVO> list = new ArrayList<MemberVO>();

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" MEM_ID,    MEM_NAME,    MEM_HP, ");
		sql.append(" MEM_MAIL,    MEM_ADD1, MEM_MILEAGE ");
		sql.append(" FROM  MEMBER ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			
		
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				MemberVO savedMember = new MemberVO();
				list.add(savedMember);
				savedMember.setMem_id(rs.getString("MEM_ID"));
				savedMember.setMem_name(rs.getString("MEM_NAME"));
				savedMember.setMem_hp(rs.getString("MEM_HP"));
				savedMember.setMem_mail(rs.getString("MEM_MAIL"));
				savedMember.setMem_add1(rs.getString("MEM_ADD1"));
				savedMember.setMem_mileage(rs.getInt("MEM_MILEAGE"));
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();

		sql.append("UPDATE  MEMBER SET");
		sql.append("MEM_ZIP        = ? ");
		sql.append("MEM_ADD1       = ? ");
		sql.append("MEM_ADD2       = ? ");
		sql.append("MEM_HOMETEL    = ? ");
		sql.append("MEM_COMTEL     = ? ");
		sql.append("MEM_HP         = ? ");
		sql.append("MEM_MAIL       = ? ");
		sql.append("MEM_JOB        = ? ");
		sql.append("MEM_LIKE       = ? ");
		sql.append("MEM_MEMORIAL   = ? ");
		sql.append("MEM_MEMORIALDAY= ? ");
		sql.append("WHERE MEM_ID = ?");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정
			int idx = 1;
			pstmt.setString(idx++, member.getMem_zip());
			pstmt.setString(idx++, member.getMem_add1());
			pstmt.setString(idx++, member.getMem_add2());
			pstmt.setString(idx++, member.getMem_hometel());
			pstmt.setString(idx++, member.getMem_comtel());
			pstmt.setString(idx++, member.getMem_hp());
			pstmt.setString(idx++, member.getMem_mail());
			pstmt.setString(idx++, member.getMem_job());
			pstmt.setString(idx++, member.getMem_like());
			pstmt.setString(idx++, member.getMem_memorial());
			pstmt.setString(idx++, member.getMem_memorialday());
			pstmt.setString(idx++, member.getMem_id());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE MEMBER SET, ");
		sql.append(" MEM_DELETE = 'Y' ");
		sql.append(" WHERE MEM_ID = ?");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pass());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}