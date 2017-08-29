package com.csair.wxopen.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csair.wxopen.core.service.CacheService;
import com.csair.wxopen.demo.entity.Account;
import com.csair.wxopen.demo.service.CacheDemoService;

/** 
 * @Title:TODO  
 * @Desription:TODO
 * @Author:Kelvin
 * @Date:2016年4月11日上午9:08:14   
 *    
 */
@RestController
@RequestMapping(value = "/abc/")
public class CacheDemoController {
	private static Logger logger = LoggerFactory.getLogger(CacheDemoController.class);
	@Autowired
	CacheService cacheService;
	
	@Autowired
	CacheDemoService cacheDemoService;
	
	@RequestMapping("/getAccount")  
    @ResponseBody  
    public Object getAccount(){   
		String s = "";
		cacheService.put("123", "fasd");

        String name = (String)cacheService.get("123", String.class);
		Account a = new Account();
		a.email = "12321";
        a.name = name;

		logger.info(name);
        return a;
    }  
	 
}
