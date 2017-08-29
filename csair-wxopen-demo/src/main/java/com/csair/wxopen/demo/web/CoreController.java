package com.csair.wxopen.demo.web;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csair.wxopen.core.exception.ErrorCode;
import com.csair.wxopen.core.exception.ServiceException;
import com.csair.wxopen.demo.service.AccountService;

/** 
 * @Title:TODO  
 * @Desription:TODO
 * @Author:Kelvin
 * @Date:2016年3月31日下午5:32:52   
 *    
 */
@RestController
@RequestMapping(value = "socketapp/")
public class CoreController {
	private static Logger logger = LoggerFactory.getLogger(CoreController.class);
	 

	@RequestMapping(value = "test")
	public String login(@RequestParam("echostr") String str) {

		return str;
	}

 
}
