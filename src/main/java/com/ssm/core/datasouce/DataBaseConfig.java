package com.ssm.core.datasouce;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 数据库配置
 *
 * @author chenly
 * @create 2020-03-05 19:33
 */
@Configuration
@PropertySource(value = { "classpath:database.properties" })
public class DataBaseConfig {

	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${initial.size}")
	private int initialSize;
	@Value("${min.idle}")
	private int minIdle;
	@Value("${max.active}")
	private int maxActive;
	@Value("${max.wait}")
	private long maxWait;

	@Bean
	public DruidDataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driver);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setMaxWait(maxWait);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
		druidDataSource.setMinEvictableIdleTimeMillis(300000);

		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setPoolPreparedStatements(true);
		druidDataSource.setMaxOpenPreparedStatements(20);
		return druidDataSource;
	}

	@Bean
	public MybatisSqlSessionFactoryBean sqlSessionFactory() throws IOException {
		MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(druidDataSource());
		sqlSessionFactoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setUseGeneratedKeys(true);
		// 配置驼峰功能
		configuration.setMapUnderscoreToCamelCase(true);
		// 本地调试设置日志
		configuration.setLogImpl(StdOutImpl.class);
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.ssm.model");

		sqlSessionFactoryBean.setPlugins(pageInterceptor());
		return sqlSessionFactoryBean;
	}

	@Bean
	public PageInterceptor pageInterceptor() {
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("pageSizeZero", "true");
		properties.setProperty("reasonable", "true");
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}

}
