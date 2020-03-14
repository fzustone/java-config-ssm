package com.ssm.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.StandardCharsets;

/**
 * @author chenly
 * @create 2020-03-05 19:33
 */
@ComponentScan(basePackages = "com.ssm", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class, RestController.class }) })
@MapperScan(basePackages = "com.ssm.mapper")
@Configuration
public class AppConfig {
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		//如果设置为-1，表示Cache forever。一般生产环境下采用-1，开发环境为了方便调测采用某个正整数，规范地我们可通过profile来定义
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		//设置properties文件的basename，以便找到响应的资源文件
		messageSource.setBasenames("/lang/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}
}
