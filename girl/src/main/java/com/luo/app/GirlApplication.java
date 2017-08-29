package com.luo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class GirlApplication {
	
	/**
	 * @Component注解 :
	 * @Controller ：处理http请求
	 * @RestController spring4之后新增加的注解，原来返回json需要@ResponseBody配合 @Controller
	 * @RequestMapping 配置url映射
	 * @PathVariable 获取url中的数据
	 * @RequestParam 获取参数中的值
	 * @Getmapping 组合注解
	 * GetMapping 组合注解
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(GirlApplication.class, args);
	}
}
