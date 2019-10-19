package kr.or.ddit.advice;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ** spring 은 메소드 호출 join point만 지원.
 * Weaving 시점에 따른 종류
 * 1. before advice
 * 2. after advice
 * 	 1) after-returning advice
 * 	 2) after-throwing advice
 * 3. aroun advice
 *
 */
@Aspect //advice와 pointcut이 들어있다 등록하기위한 7가지의 어노테이션에 등록되어있지않아서 빈에 등록되지않는다
public class LoggingAdvice {
	static Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut("execution(* kr.or.ddit.idol.service.*.*(..))")
	public void dummy() {}
	
	@Before("dummy()") //위에 가지고 있는 point를 쓰겠다 
	public void before(JoinPoint joinPoint){
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature =  joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		logger.info("{}.{} 호출, 파라미터 {}, 호출 시점 {}",
				clzName, methodName, Arrays.toString(args), new Date());
	}
	
	@After("dummy()")
	public void after(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature =  joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		
		logger.info("{}.{} 호출종료", clzName, methodName);
	}
	
	@AfterReturning(pointcut="dummy()",returning="retValue")
	public void after_returning(JoinPoint joinPoint, Object retValue) {
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature =  joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		//결과데이터를 받아오기 위한 설정이 필요함
		logger.info("{}.{} 호출종료, 결과값 : {} ", clzName, methodName, retValue);
	}
	
	@AfterThrowing(pointcut="dummy()",throwing="e")
	public void after_throwing(JoinPoint joinPoint, Throwable e) {
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature =  joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();

		logger.error("{}.{} 호출종료, 발생 예외 : {} ", clzName, methodName, e);
	}
	
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature =  joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		logger.info("{}.{} 호출, 파라미터 {}, 호출 시점 {}",
				clzName, methodName, Arrays.toString(args), new Date());
		Object retValue = null;
		try {
			long startTime = System.currentTimeMillis();
			retValue = joinPoint.proceed(args);
			long endTime = System.currentTimeMillis();
			logger.info("{}.{} 호출종료({}ms), 결과값 : {} ", clzName, methodName, (endTime-startTime),retValue);//after_returning
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("{}.{} 호출종료, 발생 예외 : {} ", clzName, methodName, e);
			throw e; //던지고나서 다시 컨슈머에게 예외를 돌려준다 ㄴ
		} 
		//이시점을 기준으로 상위에서 before  하위는 after
		//몇개인지 모르니까 배열로 넘긴다 
		
		logger.info("{}.{} 호출종료", clzName, methodName);
		
		return retValue;
		
	}
	
	
}

