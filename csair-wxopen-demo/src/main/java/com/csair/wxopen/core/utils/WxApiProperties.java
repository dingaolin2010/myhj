package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


/**
 * wxapi资源文件工具类
 * 
 * @ClassName: WxApiProperties
 * @Description: wxapi资源文件工具类
 * @author: lyk
 * @date: 2013-10-23
 * 
 */
public final class WxApiProperties {
	
	private static Logger log = LoggerFactory.getLogger(WxApiProperties.class);

	private static Properties properties = null;

	private WxApiProperties() {
		getProperties();
	}

	private static void getProperties() {
		properties = new Properties();
		try {
			properties.load(WxApiProperties.class.getClassLoader()
					.getResourceAsStream("wxapi.properties"));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

	/**
	 * 读取配置文件中的属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		String property = null;
		if (null == properties) {
			getProperties();
		}
		property = properties.getProperty(key);
		if (property == null) {
			property = "";
			log.error("key:" + key + "获取到的property为null");
		}

		return property;
	}

}
