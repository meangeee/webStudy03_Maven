package kr.or.ddit.servlet04.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements IDataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList(Map<String, Object> dataMap) {

		//서블릿에서 작성해야 할 코드
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			List<DataBasePropertyVO> list = new ArrayList<>();
			
			//scope
			
			dataMap.put("list",list);
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			sql.append("select property_name, property_value, description");
			sql.append(" from database_properties");
			rs = stmt.executeQuery(sql.toString());
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCnt = rsmd.getColumnCount();
			String [] headers = new String[colCnt];
			dataMap.put("headers",headers);
			for(int idx=1; idx<=colCnt; idx++){
				headers[idx-1] = rsmd.getColumnName(idx);
			}
			while (rs.next()) {

				DataBasePropertyVO dvo = new DataBasePropertyVO();

				//call by reference구조라서 먼저 add해줘도 됨 value면 안됨
				list.add(dvo);
				dvo.setProperty_name(rs.getString(1));
				dvo.setProperty_value(rs.getString("PROPERTY_VALUE"));
				dvo.setDescription(rs.getString("DESCRIPTION"));
				//		list.add(dvo);
			}

			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
	}

}
