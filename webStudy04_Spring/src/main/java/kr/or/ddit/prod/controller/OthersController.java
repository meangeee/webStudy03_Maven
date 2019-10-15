package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.vo.BuyerVO;

@RestController
@RequestMapping("/prod/")
public class OthersController {
	@Inject
	IOthersDAO othersDAO;   
   
   @RequestMapping(value="getLprodList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
   public List<Map<String, Object>> getLprodListForAjax()  {
      List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
//      resp.setContentType("application/json;charset=UTF-8");
//      String json = new MarshallingUtils().marshalling(lprodList);
//      try(
//         PrintWriter out = resp.getWriter();
//      ){
//         out.println(json);
//      }
      return lprodList;
   }
   
   @RequestMapping(value="getBuyerList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
   public List<BuyerVO> getBuyerListForAjax(
		   @RequestParam(name="prod_lgu", required=false)String lgu
		   )  {
//	  String lgu = req.getParameter("prod_lgu");
      List<BuyerVO> buyerList = othersDAO.selectBuyerList(lgu);
//      resp.setContentType("application/json;charset=UTF-8");
//      String json = new MarshallingUtils().marshalling(buyerList);
//      try(
//         PrintWriter out = resp.getWriter();
//      ){
//         out.println(json);
//      }
      return buyerList;
   }
}











