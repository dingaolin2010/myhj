package com.csair.wxopen.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**   
* @Description 记录web请求日志
* @author kelvin
* @date 2016年9月29日 上午9:29:46 
*/
@Aspect
@Component
public class WebLogAspect {
	private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	@Pointcut("execution(public * com.csair.wxopen.*.web..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		startTime.set(System.currentTimeMillis());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("start---, path : " + request.getRequestURI() +", class_method : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName() + ", args : " + Arrays.toString(joinPoint.getArgs()) + ", http_method : " + request.getMethod() + ", from : "
				+ request.getRemoteAddr());
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
//		HttpServletResponse  response = attributes.getResponse();
		logger.info("end---, path : " + request.getRequestURI() +", spend time : " + (System.currentTimeMillis() - startTime.get()) + "ms, response : " + ret);
	}
}
