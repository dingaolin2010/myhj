package com.csair.wxopen.config;

import com.csair.wxopen.core.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: caixh
 * Date: 17-4-19
 * Time: 下午9:19
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
