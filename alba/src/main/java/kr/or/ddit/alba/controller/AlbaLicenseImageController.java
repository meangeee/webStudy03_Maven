package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.LicenseAlbaVO;

@CommandHandler
public class AlbaLicenseImageController {
	IAlbaService service = new AlbaServiceImpl();
	@URIMapping(value="/alba/licenseImage.do" , method=HttpMethod.POST)
	public String openImage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		LicenseAlbaVO licAlba = service.selecImg(setLicAlba(req));
		
        Map<String, Object> result = new HashMap<String, Object>();
        if(licAlba.getLic_image() == null) {
           result.put("valid", new Boolean(true));
        } else {
           result.put("valid", new Boolean(false));
           result.put("base64", licAlba.getLic_base64());
        }
        
        resp.setContentType("application/json;charset=UTF-8");
        String json = new MarshallingUtils().marshalling(result); //마샬링
        
        try(
           PrintWriter out = resp.getWriter();
        ){
           out.println(json);
        }
        
        return null;
  }
  
  private LicenseAlbaVO setLicAlba(HttpServletRequest request) {
     String licCode = request.getParameter("lic_code");
     String al_id    = request.getParameter("al_id");
     
     LicenseAlbaVO lvo = new LicenseAlbaVO();
     lvo.setLic_code(licCode);
     lvo.setAl_id(al_id);
     
     return lvo;
  }
}
