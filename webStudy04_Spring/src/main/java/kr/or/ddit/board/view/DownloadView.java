package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.Attatch2VO;

public class DownloadView extends AbstractView {
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	File saveFolder;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		saveFolder = new File("d:/saveFiles");
	}
	
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		Attatch2VO attatch = (Attatch2VO) model.get("attatch");
		File downloadFile = new File(saveFolder, attatch.getAtt_savename());
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Length", attatch.getAtt_filesize()+"");
		// inline, attatchment
		String filename = attatch.getAtt_filename();
//		filename = URLEncoder.encode(filename, "UTF-8");
		filename = new String(filename.getBytes(), "ISO-8859-1");
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		try(
			OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(downloadFile, os);
		}
	}

}
