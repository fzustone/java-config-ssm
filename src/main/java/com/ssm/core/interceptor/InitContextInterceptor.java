package com.ssm.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenly
 * @create 2020-03-05 20:19
 */
public class InitContextInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER= LoggerFactory.getLogger(InitContextInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
			Exception {
		LOGGER.info("测试三生三世十里桃花");
		//wait to add MDC,ThreadLocal
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		LOGGER.info("测试测试结束输出");
	}
}
