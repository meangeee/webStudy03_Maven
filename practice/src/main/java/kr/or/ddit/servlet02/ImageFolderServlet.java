package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.naming.ldap.Rdn;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

//@WebServlet("/imagesFolderView")
public class ImageFolderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String viewName = "/WEB-INF/views/imagesFolderView.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//선택한 image 가져오기
		ServletContext a = req.getServletContext();
		System.out.println(a);
		String s = a.getRealPath("/images/");
		String image = req.getParameter("filename");
//		선택한 image의 전체경로를 갖고 오고 싶다
		String targetPath = s+image;
		String seven = a.getRealPath("/07/");
		String put = seven + image;
		
		String first = req.getParameter("command");
		
		
		if(first.equals("COPY")) {
			try (
        			FileInputStream input = new FileInputStream(targetPath);
        			FileOutputStream output = new FileOutputStream(put);) {
        			IOUtils.copy(input, output);
        	}
			
		}else if(first.equals("MOVE")) {
			try (
				FileInputStream input = new FileInputStream(targetPath);
				FileOutputStream output = new FileOutputStream(put);) {
				IOUtils.copy(input, output);
			}
			File file = new File(targetPath);
			file.delete();

		} else if (first.equals("DELETE")) {
			File file = new File(targetPath);
			file.delete();
		}
		String location = req.getContextPath() + "/imagesFolderView";
		resp.sendRedirect(location);
	}
}
