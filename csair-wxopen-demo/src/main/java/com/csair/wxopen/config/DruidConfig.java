package com.csair.wxopen.config;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Description TODO
 * @author kelvin
 * @date 2016年11月25日 下午3:07:55
 */
@Configuration
public class DruidConfig {
	private final static Logger logger = LoggerFactory.getLogger(DruidConfig.class);
	@Value("${spring.datasource.driver-class-name}")
	String driver;
	@Value("${spring.datasource.url}")
	String jdbcUrl;
	@Value("${spring.datasource.username}")
	String userName;
	@Value("${spring.datasource.password}")
	String password;
	@Value("${spring.druid.loginusername}")
	String druidUserName;
	@Value("${spring.druid.loginpassword}")
	String druidPassword;
	
	
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		// reg.addInitParameter("allow", "127.0.0.1,192.168.16.110");
		// reg.addInitParameter("deny","");
		reg.addInitParameter("loginUsername", druidUserName);
		reg.addInitParameter("loginPassword", druidPassword);
		// 禁用HTML页面上的“Reset All”功能
		reg.addInitParameter("resetEnable", "false");
		return reg;
	}
	@Bean
	public DruidDataSource druidDataSource(){
		DruidDataSource dataSource= new DruidDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setValidationQuery("select 'x'");
		dataSource.setTestWhileIdle(true);
//		dataSource.setTestOnBorrow(true);
//		dataSource.setTestOnReturn(true);
		try {
			dataSource.setFilters("stat, wall");
        } catch (SQLException e) {
        	logger.error(e.getMessage());
        }
		
		
		return dataSource;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
