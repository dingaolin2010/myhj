/**
 * @(#)ConfigUtil.java
 * 
 * Copyright csair.All rights reserved.
 * This software is the VOS system. 
 *
 * @Version: 1.0
 * @JDK: jdk jdk1.6.0_10
 * @Module: csmbp-global
 */ 
 /*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-3-18       wenbin      Created
 **********************************************
 */

package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置文件信息.
 *
 * @author wenbin
 * @since 2015-3-18
 */
public final class ConfigUtil {
	private ConfigUtil()
	{
		
	}
	
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigUtil.class);
    private static InputStream input = null;
    private static Properties props = null; 
    
    public static Properties getProperties() {       
        if (props == null) {
            initProperties();
        }     
        
        return props;
    }
    
    public static String getProperty(String key) {
        if (props == null) {
            initProperties();             
        }        
        
        return props.getProperty(key);
    }       
    
    /**
     *   读取应用程序的默认配置文件，并取得相关配置值
     */
    private static synchronized void initProperties() {
        props = new Properties();
        
        try {   
            if (props.size() == 0) {            
                input = ConfigUtil.class.getClassLoader().getResourceAsStream("application.properties");
                 props.load(input);  
                String value=props.getProperty("spring.profiles.active");
                input = ConfigUtil.class.getClassLoader().getResourceAsStream("application-"+value+".properties");
                props.load(input);  
            }
        } catch(Exception ex) {
            props = null; 
            LOGGER.error("获取应用配置时出现异常，异常原因:" + ex.getMessage(),ex);
        } finally {                               
            if(input != null){
                try {
                    input.close();
                    input = null;
                } catch(Exception ex) {
                    LOGGER.error("获取应用配置时出现异常，异常原因:" + ex.getMessage(),ex);
                }
            }   
        }    
    }

}
