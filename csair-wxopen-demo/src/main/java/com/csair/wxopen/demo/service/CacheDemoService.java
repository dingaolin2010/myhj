package com.csair.wxopen.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csair.wxopen.core.service.CacheService;

/**
 * @Title:TODO
 * @Desription:TODO
 * @Author:Kelvin
 * @Date:2016年4月11日上午8:57:41
 * 
 */
@Service
public class CacheDemoService {
	private static Logger logger = LoggerFactory.getLogger(CacheDemoService.class);
	/*
	 * @Autowired CacheManager cacheManager;
	 */
	@Autowired
	CacheService cacheService;
	/*
	 * @Cacheable(value = "accountCache",keyGenerator = "wiselyKeyGenerator")
	 * public Account getAccount(String email, String name, String password) {
	 * logger.info("无缓存的时候调用这里---数据库查询"); Account account = new Account();
	 * account.email = email; account.name = name; account.hashPassword =
	 * password; //真实项目 应该查询数据库返回数据 return account; }
	 */

	public void testCache() {
		String cacheName = "test";
		String key = "key_openId";
		String value = "xxfjdasljdf";
		cacheService.put(key, value);

	}

}
