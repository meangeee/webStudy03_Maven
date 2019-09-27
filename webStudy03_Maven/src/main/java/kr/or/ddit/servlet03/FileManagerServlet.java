package kr.or.ddit.servlet03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.CommandType;
import kr.or.ddit.utils.MarshallingUtils;

@WebServlet("/serverFileManager")
public class FileManagerServlet extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftSrc = req.getParameter("leftSrc");
		String rightTarget = req.getParameter("rightTarget");
		if(leftSrc==null) leftSrc = "";
		if(rightTarget==null) rightTarget = "";
		String realPathLeft = application.getRealPath(leftSrc);
		String realPathRight = application.getRealPath(rightTarget);
		File folderLeft = new File(realPathLeft);
		File folderRight = new File(realPathRight);
		
		int status = 200;
		String message = null;
		Map<String, FileWrapper[]> jsonMap = new HashMap<>();
		try {
			List<FileWrapper> leftFiles = traversing(folderLeft);
			List<FileWrapper> rightFiles = traversing(folderRight);
			// marshalling 준비
			FileWrapper[] leftArray = new FileWrapper[leftFiles.size()];
			FileWrapper[] rightArray = new FileWrapper[rightFiles.size()];
			jsonMap.put("leftFiles", leftFiles.toArray(leftArray));
			jsonMap.put("rightFiles", rightFiles.toArray(rightArray));
			// ---
			req.setAttribute("leftFiles", leftFiles);
			req.setAttribute("rightFiles", rightFiles);
			
		}catch (FileNotFoundException | IllegalArgumentException e) {
			if(e instanceof FileNotFoundException) {
				status = HttpServletResponse.SC_NOT_FOUND;
			}else {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
			message = e.getMessage();
		}
		if(status==200) {
			String accept = req.getHeader("Accept");
			if(accept.contains("json")) {
				//json
				resp.setContentType("application/json");
				String json = new MarshallingUtils().marshalling(jsonMap);
				try(
					PrintWriter out = resp.getWriter();
				){
					out.print(json);
				}
			}else {
				String viewName = "/WEB-INF/views/serverFileManager.jsp";
				req.getRequestDispatcher(viewName).include(req,resp);
			}
		}else {
			resp.sendError(status, message);	
		}
	}


	private List<FileWrapper> traversing(File folder) throws FileNotFoundException, MalformedURLException {
		if(!folder.exists()) {
			throw new FileNotFoundException(folder.getName()+"폴더가 존재하지 않슴다.");
		}
		if(folder.isFile()) {
			throw new IllegalArgumentException(folder.getName()+"는 디렉토리가 아님.");
		}
		File[] files = folder.listFiles();
		List<FileWrapper> result = new ArrayList<>();
		
		URL root = application.getResource("");
		URL current = folder.toURL();
		if(!root.equals(current)) {
			File parent = folder.getParentFile();
			result.add(new FileWrapper(parent, application, true));
		}
		for(File file : files) {
			result.add(new FileWrapper(file, application));
		}
		return result;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String srcFileParam = req.getParameter("srcFile");
		String command = req.getParameter("command");
		String leftSrc = req.getParameter("leftSrc");
		String rightTarget = req.getParameter("rightTarget");
		int status = 200;
		if(StringUtils.isBlank(srcFileParam) || 
				StringUtils.isBlank(command)) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else {
			File srcFile = new File(application.getRealPath(srcFileParam)); 
			if(!srcFile.exists()) {
				status = HttpServletResponse.SC_NOT_FOUND;
			}else {
				try {
					CommandType commandType =  CommandType.valueOf(command);
					if(status==200) {
						File targetFolder = new File(application.getRealPath(rightTarget));
						commandType.commandProcess(srcFile, targetFolder);
					}
				}catch (IllegalArgumentException e) {
					status = 400;
				}
			}
			
			if(status==200) {
				String viewPtrn = "/serverFileManager?leftSrc=%s&rightTarget=%s&srcFile=%s";
				resp.sendRedirect(req.getContextPath() + 
								String.format(viewPtrn, leftSrc, rightTarget, srcFileParam));
			}else {
				resp.sendError(status);
			}
		}
	}
}











