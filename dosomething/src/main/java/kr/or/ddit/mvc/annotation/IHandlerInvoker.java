package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerInvoker {
	public String invokerHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) throws Exception; //왜 String이라고요?
	
}
