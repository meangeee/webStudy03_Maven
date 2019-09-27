package kr.or.ddit.prod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	// 자기자신
	private static ProdDAOImpl instance;

	// 생성자
	public ProdDAOImpl() {

	}

	// singleton
	public static ProdDAOImpl getInstance() {
		if (instance == null)
			instance = new ProdDAOImpl();
		return instance;
	}

	@Override
	public int insertProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProdVO> selectProdList() {
		List<ProdVO> list = new ArrayList<ProdVO>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT                            ");
		sql.append("A.PROD_ID,                        ");
		sql.append("A.PROD_NAME,                        ");
		sql.append("B.LPROD_NM,                       ");
		sql.append("C.BUYER_NAME,                     ");
		sql.append("A.PROD_COST,                      ");
		sql.append("A.PROD_PRICE,                     ");
		sql.append("A.PROD_MILEAGE                     ");
		sql.append("FROM PROD A, LPROD B, BUYER C     ");
		sql.append("WHERE A.PROD_LGU = B.LPROD_GU AND ");
		sql.append("      A.PROD_BUYER = C.BUYER_ID  ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProdVO savedProd = new ProdVO();
				savedProd.setProd_id(rs.getString("PROD_ID"));
				savedProd.setProd_name(rs.getString("PROD_NAME"));
				savedProd.setLprod_nm(rs.getString("LPROD_NM"));
				savedProd.setBuyer_name(rs.getString("BUYER_NAME"));
				savedProd.setProd_cost(rs.getInt("PROD_COST"));
				savedProd.setProd_price(rs.getInt("PROD_PRICE"));
				savedProd.setProd_mileage(rs.getInt("PROD_MILEAGE"));
				list.add(savedProd);

			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		return null;

	}

	@Override
	public int updateProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

}
