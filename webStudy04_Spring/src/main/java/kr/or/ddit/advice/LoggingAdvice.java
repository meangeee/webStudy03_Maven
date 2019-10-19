package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAdvice {
	private static Logger logger = 
			LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Around("execution(* kr.or.ddit..service.*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		String clzName = joinPoint.getTarget().getClass().getSimpleName();
		String mtdName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		logger.info("{}.{} 호출, 전달 파라미터 : {} ",
					clzName, mtdName, Arrays.toString(args)
				);
		long start = System.currentTimeMillis();
		Object retValue = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		logger.info("{}.{}, 소요시간 : ({}ms) 반환값 : {}", 
					clzName, mtdName, (end-start), retValue
				);
		return retValue;
	}
}










