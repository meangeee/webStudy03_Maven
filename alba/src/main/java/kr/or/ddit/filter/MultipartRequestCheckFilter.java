package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.wrapper.MultipartRequestWrapper;

public class MultipartRequestCheckFilter implements Filter{
	private static Logger logger = LoggerFactory.getLogger(MultipartRequestCheckFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터 생성", getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1. 파일 업로드 여부 확인
		HttpServletRequest req = (HttpServletRequest) request;
		String bodyMime = req.getContentType();
		if(bodyMime!=null && bodyMime.startsWith("multipart/")) {
			//2. 업로드된 파일데 대한 전처리 담당 Wrapper 생성
			MultipartRequestWrapper requestWrapper =
					new MultipartRequestWrapper(req);
			chain.doFilter(requestWrapper, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸", getClass().getSimpleName());
	}

}
