package kr.or.ddit.alba.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseAlbaVO;
import kr.or.ddit.vo.LicenseVO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class AlbaInsertController {
	IAlbaService service = new AlbaServiceImpl();

	@URIMapping("/alba/albaInsert.do")
	public String loadAlbaForm(HttpServletRequest req, HttpServletResponse resp) {
		List<LicenseVO> licenseName = service.licenseList();
		List<GradeVO> gradeName = service.gradeList();
		req.setAttribute("licenseName", licenseName);
		req.setAttribute("gradeName", gradeName);
		return "/alba/albaForm";
	}

	@URIMapping(value = "/alba/albaInsert.do", method = HttpMethod.POST)
	public String insertAlba(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		AlbaVO alba = new AlbaVO();
		req.setAttribute("alba", alba);

		String[] licCodes = req.getParameterValues("lic_code");

		try {
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		int cnt = service.createAlba(alba);
		String viewName = null;
		if (cnt > 0) {
			if (licCodes.length > 0) {
				// 파트데이터 꺼내기 => 자격증 등록----------------------------------------------
				if (req instanceof MultipartRequestWrapper) {
					MultipartRequestWrapper requestWrapper = (MultipartRequestWrapper) req;
					PartWrapper[] partWrapper = requestWrapper.getPartWrappers("lic_image");
					if (partWrapper.length > 0) {

						for (int i = 0; i < partWrapper.length; i++) {
							LicenseAlbaVO licAlba = new LicenseAlbaVO();
							licAlba.setAl_id(alba.getAl_id());
							licAlba.setLic_code(licCodes[i]);
							licAlba.setLic_image(partWrapper[i].getBytes());

							service.insertImg(licAlba);
						}
					}
				}
			}
			viewName = "redirect:/alba/albaList.do";
		} else {
			viewName = "alba/albaView";
		}

		// 사진
//		if(req instanceof MultipartRequestWrapper) {
//			PartWrapper partWrapper = ((MultipartRequestWrapper) req).getPartWrapper("alba_image");
//			//저장위치
//			if(partWrapper!=null) {
//				String saveFolderURL = "/albaImages";
//				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
//				File saveFolder = new File(saveFolderPath);
//				if(!saveFolder.exists()) saveFolder.mkdirs();
//				//저장명
//				String saveName = UUID.randomUUID().toString();
//				try(
//	        			 InputStream is = partWrapper.getInputStream();
//	        			 ){
//	        		 FileUtils.copyInputStreamToFile(is, new File(saveFolder,saveName));
//	        	 }
//	        	 alba.setal_i(saveName);
//			}
//		}

		return viewName;
	}
}
