package kr.or.ddit.alba.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicAlbaVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/alba")
public class AlbaRetrieveController{
	@Inject
	IAlbaService service;
	
	@RequestMapping(value="licenseImage.do" , produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String licenseImage(
			@RequestParam(required=true) String al_id,
			@RequestParam(required=true) String lic_code
			)  {
		LicAlbaVO licAlba = new LicAlbaVO();
		licAlba.setAl_id(al_id);
		licAlba.setLic_code(lic_code);
		LicAlbaVO license = service.retrieveLicense(licAlba);
		resp.setContentType("application/octet-stream");
		byte[] imageData = license.getLic_image();
		if(imageData==null){
			try(
				FileInputStream fis = new FileInputStream(req.getServletContext().getRealPath("/images/noImage.png"));
			){		
				int size = fis.available();
				imageData = new byte[size];
				IOUtils.read(fis, imageData);
			}
		}
		try(
			OutputStream os = resp.getOutputStream();
			ByteArrayInputStream is = new ByteArrayInputStream(imageData);
		){
			IOUtils.copy(is, os);
		}
		return null;
	}
	
	@RequestMapping("albaList.do")
	public String list(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1")
			int currentPage
			, Model model
			)  {
//		String pageParam = req.getParameter("page");
//		String searchType = req.getParameter("searchType");
//		String searchWord = req.getParameter("searchWord");
//		int currentPage = 1;
//		if(StringUtils.isNotBlank(pageParam) && StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}
		
		PagingInfoVO<AlbaVO> pagingVO = new PagingInfoVO<AlbaVO>(7, 3);
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		pagingVO.setSearchMap(searchMap);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.retrieveAlbaCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<AlbaVO> albaList = service.retrieveAlbaList(pagingVO);
		pagingVO.setDataList(albaList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		String view = "alba/albaList";
		return view;
	}
	
	@RequestMapping("albaView.do")
	public String view(
			@RequestParam(required=true) String who,
			Model model,
			HttpServletResponse resp
			) throws IOException {
//		String who = req.getParameter("who");
		int sc = 0;
//		if(StringUtils.isBlank(who)) {
//			sc = HttpServletResponse.SC_BAD_REQUEST;
//		}else {
			AlbaVO alba = service.retrieveAlba(who);
			model.addAttribute("alba", alba);
			if(alba==null) {
				sc = HttpServletResponse.SC_NOT_FOUND;
//			}
			}
		String view = null;
		if(sc!=0) {
			resp.sendError(sc);
		}else {
			view = "alba/albaView";
		}
		return view;
	}
}
