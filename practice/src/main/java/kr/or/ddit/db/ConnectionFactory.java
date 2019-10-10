package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Factory Object[Method] Pattern
 * 
 *
 */
public class ConnectionFactory {
	static String url;
	static String user;
	static String password;
	static BasicDataSource dataSource;

	static {
		//외부에있는 properties를 불러와서 하나하나 설정해줘야함
		//클래스패스 이후의 경로 퀄러파이드네임이 필요 퀄러파이드 네임은 확장자 필요x .구분
		ResourceBundle bundle =  ResourceBundle.getBundle("kr.or.ddit.db.dbInfo");
//		try {
//			Class.forName(driverClassName);//드라이버 클래스 로딩
//			
//		} catch (ClassNotFoundException e1) {
//			throw new RuntimeException(e1);
//		} 

		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		String driverClassName = bundle.getString("driverClassName");
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		long maxWait = Long.parseLong(bundle.getString("maxWait"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize); //?
		dataSource.setMaxWaitMillis(maxWait);//?
		dataSource.setMaxTotal(maxTotal);//?
		
	}
	

	public static Connection getConnection() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}
}
