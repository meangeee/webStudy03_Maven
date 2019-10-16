package kr.or.ddit.common.rest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApartmentController {
   @Inject
   RestTemplate template;
   
   @RequestMapping(value="/apart",produces=MediaType.TEXT_HTML_VALUE)
   public String view() {
      return "apart/view";
   }
   
   @RequestMapping(value="/apart",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ResponseBody
   public Map proxy(
		   @RequestParam(required=false)String LAWD_CD, 
		   @RequestParam(required=false)String DEAL_YMD
		   ) {
      String serviceUrl = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      StringBuffer url = new StringBuffer(serviceUrl);
      url.append("?LAWD_CD={LAWD_CD}");
      url.append("&DEAL_YMD={DEAL_YMD}");
      url.append("&serviceKey={serviceKey}");
      Map<String, Object> uriVariables = new HashMap<>();
      uriVariables.put("LAWD_CD", "11110");
      uriVariables.put("DEAL_YMD", "201909");
      uriVariables.put("serviceKey", "B5DJogvvjCt5ApdZzZbFc+RH1R7PZWHu8bMcAQ/Wdv8Dw6IknxHv5SK3kthDzOPWhWWsniozKpnaIOzP1fa+xg==");
//      ResponseEntity<String> resp = template.getForEntity(url.toString(), String.class, uriVariables);
//      return resp.getBody(); //xml데이터가 된다 
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
      HttpEntity entity = new HttpEntity<>(headers);
      ResponseEntity<Map> resp = template.exchange(url.toString(), HttpMethod.GET,entity, Map.class, uriVariables);

      Map<String, Object> result = resp.getBody();
      
      return result;
   }
}