package kr.or.ddit.buyer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.db.ConnectionFactory;

public class BuyerDAOImpl implements IBuyerDAO {

	// 자신
	private static BuyerDAOImpl instance;

	// 기본생성자
	public BuyerDAOImpl() {

	}

	// Singleton
	public static BuyerDAOImpl getInstance() {
		if (instance == null)
			instance = new BuyerDAOImpl();
		return instance;
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO BUYER (BUYER_ID,                ");
		sql.append("		BUYER_NAME, BUYER_LGU, BUYER_BANKNAME, ");
		sql.append("		BUYER_ZIP, BUYER_ADD1 , BUYER_MAIL ) ");
		sql.append(" VALUES (?, ?, ?, ?,  ");
		sql.append("		?, ?, ? ) ");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정
			int idx = 1;
			pstmt.setString(idx++, buyer.getBuyer_id());
			pstmt.setString(idx++, buyer.getBuyer_name());
			pstmt.setString(idx++, buyer.getBuyer_lgu());
			pstmt.setString(idx++, buyer.getBuyer_bankname());
			pstmt.setString(idx++, buyer.getBuyer_zip());
			pstmt.setString(idx++, buyer.getBuyer_add1());
			pstmt.setString(idx++, buyer.getBuyer_mail());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		List<BuyerVO> list = new ArrayList<BuyerVO>();
		StringBuffer sql = new StringBuffer();
		// 쿼리문 넣기
		sql.append("SELECT ");
		sql.append("BUYER_ID, ");
		sql.append("BUYER_NAME ");
		sql.append("FROM BUYER ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				BuyerVO savedBuyer = new BuyerVO();
				// list에 buyer이름을 모두 담는다
				list.add(savedBuyer);
				savedBuyer.setBuyer_id(rs.getString("BUYER_ID"));
				savedBuyer.setBuyer_name(rs.getString("BUYER_NAME"));
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public BuyerVO selectBuyer(BuyerVO buyer) {
		BuyerVO savedBuyer = null;
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		sql.append(" BUYER_ID, BUYER_NAME, BUYER_LGU, BUYER_BANK,        ");
		sql.append(" BUYER_BANKNAME, BUYER_ZIP,            ");
		sql.append(" BUYER_ADD1, BUYER_ADD2,               ");
		sql.append(" BUYER_MAIL ");
		sql.append(" FROM BUYER    ");
		sql.append(" WHERE BUYER_ID = ? ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, buyer.getBuyer_id());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				savedBuyer = new BuyerVO();
				savedBuyer.setBuyer_id(rs.getString("BUYER_ID"));
				savedBuyer.setBuyer_name(rs.getString("BUYER_NAME"));
				savedBuyer.setBuyer_lgu(rs.getString("BUYER_LGU"));
				savedBuyer.setBuyer_bank(rs.getString("BUYER_BANK"));
				savedBuyer.setBuyer_bankname(rs.getString("BUYER_BANKNAME"));
				savedBuyer.setBuyer_zip(rs.getString("BUYER_ZIP"));
				savedBuyer.setBuyer_add1(rs.getString("BUYER_ADD1"));
				savedBuyer.setBuyer_add2(rs.getString("BUYER_ADD2"));
				savedBuyer.setBuyer_mail(rs.getString("BUYER_MAIL"));
			}
			return savedBuyer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE BUYER SET ");
		sql.append(" BUYER_NAME         = ?, ");
		sql.append(" BUYER_LGU          = ?, ");
		sql.append(" BUYER_BANKNAME     = ?, ");
		sql.append(" BUYER_ZIP          = ?, ");
		sql.append(" BUYER_ADD1         = ?, ");
		sql.append(" BUYER_MAIL         = ? ");
		sql.append(" WHERE BUYER_ID = ? ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정
			int idx = 1;
			pstmt.setString(idx++, buyer.getBuyer_name());
			pstmt.setString(idx++, buyer.getBuyer_lgu());
			pstmt.setString(idx++, buyer.getBuyer_bankname());
			pstmt.setString(idx++, buyer.getBuyer_zip());
			pstmt.setString(idx++, buyer.getBuyer_add1());
			pstmt.setString(idx++, buyer.getBuyer_mail());
			pstmt.setString(idx++, buyer.getBuyer_id());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteBuyer(BuyerVO buyer) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM BUYER    ");
		sql.append("WHERE BUYER_ID = ? ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정
			pstmt.setString(1, buyer.getBuyer_id());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
