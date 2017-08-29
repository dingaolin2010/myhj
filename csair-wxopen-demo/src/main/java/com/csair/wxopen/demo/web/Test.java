package com.csair.wxopen.demo.web;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
/** 
 * @Title:TODO  
 * @Desription:TODO
 * @Author:Kelvin
 * @Date:2016年3月31日下午5:32:52   
 *    
 */
@RequestMapping(value = "/test")
@RestController
public class Test {
	
	@RequestMapping(value = "/test")
	public String test(Map<String,Object> map) {
		map.put("hello", "9999999999999999999999");
		System.out.println("************************8");
		System.out.println("************************8");
		System.out.println(map.toString());
		return "test";
	}
}