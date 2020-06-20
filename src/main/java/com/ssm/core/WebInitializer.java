package com.ssm.core;

import com.ssm.core.config.AppConfig;
import com.ssm.core.config.WebConfig;
import com.ssm.core.datasouce.DataBaseConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;

/**
 * 替代web.xml
 *
 * @author chenly
 * @create 2020-03-05 19:01
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		//防止spring内存溢出监听器，比如quartz
		servletContext.addListener(IntrospectorCleanupListener.class);
		servletContext.getServletRegistration("jsp").addMapping("*.html");

		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter",
				CharacterEncodingFilter.class);
		encodingFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, false, "/*");


		//session相关，用于存储到redis
		/*servletContext.addFilter("springSessionRepositoryFilter", DelegatingFilterProxy.class)
				.addMappingForUrlPatterns(null, false, "/*");*/


	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class, DataBaseConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
}
