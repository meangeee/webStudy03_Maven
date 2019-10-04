package kr.or.ddit.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠키생성과 쿠키 확보를 지원하는 유틸리티
 *
 */
public class CookieUtil {
   private Map<String, Cookie> cookieMap;
   
   private HttpServletRequest req; //쿠키에게 필수 전략 생성자를 통해 호출 할 수 있어야 함
   //DependencyInjection 의존성주입의 구조 : setter, constructor 필수전략 요수에 따라 다르게 사용하면 됨
   public CookieUtil(HttpServletRequest req) { //기본생성자 사라짐
	   super();
	   this.req = req;
	   cookieMap = new HashMap<String, Cookie>();
	   Cookie[] cookies = req.getCookies();
	   if(cookies!=null) {
		   for(Cookie tmp : cookies) {
			   cookieMap.put(tmp.getName(), tmp);
		   }
	   }//if end
}

private static String charset="UTF-8";
   public static void setCharset(String charset) {
      CookieUtil.charset = charset;
   }
   
   //cookieMap을 사용 할 수 있는 get
   public Map<String, Cookie> getCookieMap(){
	   return cookieMap;
   }
   
   public Cookie getCookie(String name) {
	   return cookieMap.get(name);
   }
   
   public String getCookieValue(String name) throws UnsupportedEncodingException{
	   Cookie cookie = getCookie(name);
	   if(cookie==null) return null;
	   String value = cookie.getValue();
	   return URLDecoder.decode(value, charset);
   }
   /**
    * 쿠키생성
    * @param name
    * @param value 쿠키값, encoding UTF-8
    * @return
    * @throws UnsupportedEncodingException
    */
   public static Cookie createCookie(String name, String value) throws UnsupportedEncodingException{
      // value 값이 특수문자가 입력되어 있다면
      value = URLEncoder.encode(value, charset); // charset 이 없는 인코딩 일 때 UnsupportedEncodingException 발생
      Cookie cookie = new Cookie(name, value);
      return cookie;
   }
   
   public static Cookie createCookie(String name, String value, int maxAge) throws UnsupportedEncodingException{
	   Cookie cookie = createCookie(name, value);
       cookie.setMaxAge(maxAge);
       return cookie;      
   }
   
   public static enum TextType{
      PATH, DOMAIN
   }
   
   public static Cookie createCookie(String name, String value, String text, TextType pathOrDomain) throws UnsupportedEncodingException{
	   Cookie cookie = createCookie(name, value);
	   if(TextType.PATH.equals(pathOrDomain)) { //객체이기때문에 equals쓰세요
		   cookie.setPath(text);
	   }else if(pathOrDomain == TextType.DOMAIN){//여기도 마찬가지입니다
		   cookie.setPath(text);
	   }
	   
	   return cookie;
   }
   
   public static Cookie createCookie(String name, String value, String text, TextType pathOrDomain, int maxAge) throws UnsupportedEncodingException{
      Cookie cookie = createCookie(name, value, text, pathOrDomain);
      cookie.setMaxAge(maxAge);
	   return cookie;
   }
   
   public static Cookie createCookie(String name, String value, String path, String domain) throws UnsupportedEncodingException{
      Cookie cookie = createCookie(name, value);
      cookie.setPath(path);
      cookie.setDomain(domain);
	   return cookie;
   }
   
   public static Cookie createCookie(String name, String value, String path, String domain, int maxAge) throws UnsupportedEncodingException{
      Cookie cookie = createCookie(name, value, path, domain);
      cookie.setMaxAge(maxAge);
	   return cookie;
   }
}