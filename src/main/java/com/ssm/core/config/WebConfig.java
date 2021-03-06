package com.ssm.core.config;

import com.google.common.collect.Lists;
import com.ssm.controller.HelloWorldController;
import com.ssm.controller.TestSimpleUrlHandlerController;
import com.ssm.core.interceptor.InitContextInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Properties;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @author chenly
 * @create 2020-03-05 19:01
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.ssm.controller" })
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {

		//wait to change time format or other.
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(initContextInterceptor())
				.addPathPatterns("/**");
	}

	@Bean
	InitContextInterceptor initContextInterceptor() {
		return new InitContextInterceptor();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/")
				.setViewName("forward:/index");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Bean
	MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Lists.newArrayList(APPLICATION_JSON));
		return mappingJackson2HttpMessageConverter;
	}

	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setOrder(-100);

		Properties urlProperties = new Properties();
		urlProperties.put("/helloGuest", "helloGuestController");
		mapping.setMappings(urlProperties);
		return mapping;
	}

	@Bean
	public TestSimpleUrlHandlerController helloGuestController() {
		return new TestSimpleUrlHandlerController();
	}

	@Bean
	public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}

	@Bean("/welcome")
	public HelloWorldController helloWorldController() {
		return new HelloWorldController();
	}

}
