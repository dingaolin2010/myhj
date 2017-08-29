package com.csair.wxopen.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/** 
 * @Title:ValidatorConfig
 * @Desription:配置信息
 * @Author:Kelvin
 * @Date:2016年4月28日上午10:04:30   
 *    
 */
@Configuration 
public class ValidatorConfig {
	  @Bean
	  public LocalValidatorFactoryBean getLocalValidatorFactoryBean(@Qualifier("myMessageSource") MessageSource messageSource){  
		  LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		  localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
		  localValidatorFactoryBean.setValidationMessageSource(messageSource);
		  return localValidatorFactoryBean;
	  }
	  
	  @Bean
	  public MessageSource myMessageSource(){
		  ResourceBundleMessageSource resourceBundleMessageSource= new ResourceBundleMessageSource();
		  resourceBundleMessageSource.setBasename("bundle/messages");
		  return resourceBundleMessageSource;
	  }
}
