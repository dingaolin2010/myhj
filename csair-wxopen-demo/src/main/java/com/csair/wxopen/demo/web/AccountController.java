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
@RequestMapping(value = "/api/accounts/")
public class AccountController {
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private AccountService accountServcie;

	@RequestMapping(value = "login")
	public Map<String, String> login(@RequestParam("email") String email, @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or password empty", ErrorCode.BAD_REQUEST);
		}

		String token = accountServcie.login(email, password);

		return Collections.singletonMap("token", token);
	}

	@RequestMapping(value = "logout")
	public void logout(@RequestParam(value = "token", required = false) String token) {
		accountServcie.logout(token);
	}

	@RequestMapping(value = "register")
	public void register(@RequestParam("email") String email,
			@RequestParam(value = "name", required = false) String name, @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or name or password empty", ErrorCode.BAD_REQUEST);
		}

		accountServcie.register(email, name, password);
	}
}
