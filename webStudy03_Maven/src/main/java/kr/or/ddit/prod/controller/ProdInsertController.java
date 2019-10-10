package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class ProdInsertController {
   IProdService service = new ProdServiceImpl();
   
   @URIMapping("/prod/prodInsert.do")
   public String insertForm(HttpServletRequest req,HttpServletResponse resp){
      return "prod/prodForm";
      
   }
   
   @URIMapping(value="/prod/prodInsert.do",method=HttpMethod.POST)
   public String insert(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
      ProdVO prod = new ProdVO();
      
      req.setAttribute("prod", prod);
      
      try {
         BeanUtils.populate(prod, req.getParameterMap());
      } catch (IllegalAccessException | InvocationTargetException e) {
         throw new RuntimeException(e);
      }
      if(req instanceof MultipartRequestWrapper) {
         PartWrapper partWrapper = ((MultipartRequestWrapper) req).getPartWrapper("prod_image");
         //partWrapper는 part의 성질을 가지고 있다 .
         //1.저장위치
         if(partWrapper!=null) {
        	 String saveFolderURL = "/prodImages";
        	 String saveFolerPath = req.getServletContext().getRealPath(saveFolderURL);
        	 File saveFolder = new File(saveFolerPath);
        	 if(!saveFolder.exists()) saveFolder.mkdirs();
        	 //2.저장명
        	 String saveName = UUID.randomUUID().toString();
        	 try(
        			 InputStream is = partWrapper.getInputStream();
        			 ){
        		 FileUtils.copyInputStreamToFile(is, new File(saveFolder,saveName));
        	 }
        	 prod.setProd_img(saveName);   
         }
      }
      
      //1.저장위치
      Map<String, String> errors = new HashMap<String, String>();
      
      req.setAttribute("errors", errors); //검증에 통과하지못했을경우마다 출력하기위해서
      
      boolean vaild = validate(prod,errors);
      String viewName = null;
      String message = null;
      if(vaild) {
         ServiceResult result = service.createProd(prod);
         switch (result) {
         case FAILED: //서버사이드
            message ="서버오류";
            viewName = "prod/prodForm";
            break;   

         default:
//            -OK : redirect -> welcome page //클라이언트사이드 
            message="추가성공";
            viewName="redirect:/prod/prodView.do?what="+prod.getProd_id();
            break;
         }
      }else {
         viewName = "prod/prodForm";
      }
      
      req.setAttribute("message", message);
      
      return viewName;
   }
   
   private boolean validate(ProdVO prod, Map<String, String> errors) {
      boolean valid = true;
      
      if (StringUtils.isBlank(prod.getProd_name())) {
         valid = false;
         errors.put("prod_name", "상품명 누락");
      }
      if (StringUtils.isBlank(prod.getProd_lgu())) {
         valid = false;
         errors.put("prod_lgu", "분류코드누락");
      }
      if (StringUtils.isBlank(prod.getProd_buyer())) {
         valid = false;
         errors.put("prod_buyer", "판매자 누락");
      }
      if (StringUtils.isBlank(Integer.toString(prod.getProd_cost()))) {
         valid = false;
         errors.put("prod_cost", "매입가 누락");
      }
      if (StringUtils.isBlank(Integer.toString(prod.getProd_price()))) {
         valid = false;
         errors.put("prod_price", "판매가 누락");
      }
      if (StringUtils.isBlank((prod.getProd_img()))) {
         valid = false;
         errors.put("prod_img", "이미지경로 누락");
      }


      return valid;
   }
   
   
}