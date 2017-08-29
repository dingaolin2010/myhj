package com.csair.wxopen.core.utils;

import java.util.ResourceBundle;

public class HintWordUtil {
	
	private static ResourceBundle bundleCN=ResourceBundle.getBundle("hintword");
	
	private HintWordUtil() {
		
	}
	
	/**
	 * 
	 * @param lang
	 * @return
	 */
	public static ResourceBundle getResourceBundle(){
		 return bundleCN;
	}
	
	/**
	 * 
	 * @param key
	 * @param lang
	 * @return
	 */
	public static String getString(String key){
		return getResourceBundle().getString(key);
	}
}
