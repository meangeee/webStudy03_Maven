package kr.or.ddit.servlet04;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.servlet04.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.servlet04.dao.IDataBasePropertyDAO;
import kr.or.ddit.servlet04.service.IDataBasePropertyService;
import kr.or.ddit.vo.DataBasePropertyVO;
@WebServlet("/jdbcDesc")
public class JdbcDescControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IDataBasePropertyService service = null;
		
		Map<String, Object> dataMap = new HashMap<>();
		
		service.readAndLoggingDataBaseProperty(dataMap);
		
		req.setAttribute("dataMap", dataMap);
		
		//서버사이드 경로?
		String view = "/WEB-INF/views/jdbcDesc.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
