package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class AttatchAndImageController {
	IBoardService service = new BoardServiceImpl();
	
	File saveFolder = new File("d:/saveFiles");
	
	@URIMapping("/board/download.do")
	public String download(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//		save네임을가지고현재미들티어에있는 진ㅉ나 네임을 찾아
//		그대상으로 개방 하고 뭐 카피를 떠서 ㅇ뭐요?!!?????!?!?!?!??!?!?!?!?!?!?!?!?!?!??!
		String what = req.getParameter("what");
		if(StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		Attatch2VO attatch = service.downloadAttatch(Integer.parseInt(what));
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
		return null;
	}
	
	@URIMapping(value="/board/imageUpload.do", method=HttpMethod.POST)
	public String upload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(req instanceof MultipartRequestWrapper) {
			//다운캐스팅가능
			PartWrapper uploadFile = ((MultipartRequestWrapper) req).getPartWrapper("upload");
			//실제업로드되었는지
			if(uploadFile!=null) {
				//해당폴더의위치를잡아서파일객체
				String saveFolderURL = "/boardImages";			// 그래서 이걸 받아옴
				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
				//절대경로필요 그래서 위에
				File saveFolder = new File(saveFolderPath);
				if(!saveFolder.exists()) saveFolder.mkdirs();		//uplodaFile saveFolder에 저장
				String savename = UUID.randomUUID().toString();
				try(
					InputStream is = uploadFile.getInputStream();	
				){
//저위치에다가 저 이름으로 만든다					FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
				}
				//카피를뜨고나면  이걸로 img태그에 src를 쓸테니까
				String saveURL = req.getContextPath() + saveFolderURL+"/"+savename;
				Map<String, Object> messageMap = new HashMap<>();
				messageMap.put("fileName", uploadFile.getFileName());
				messageMap.put("uploaded", 1);
				messageMap.put("url", saveURL);
				resp.setContentType("application/json;charset=UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				try(
					PrintWriter out = resp.getWriter();	
				){
					mapper.writeValue(out, messageMap);	
				}
			}
		}
		return null;
	}
}











