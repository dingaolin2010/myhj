package com.csair.wxopen.util;

import org.junit.Test;

import com.csair.wxopen.core.utils.HttpKit;
import com.csair.wxopen.core.utils.HttpUtil2;

public class HttpUtil2Test {

//	@Test
//	public void testHttpUtil(){
//		try {
//			String result = HttpUtil.postByJson("https://wxtest.csair.com/lzf_wxopen/flightdelay/queryFlightDelayInfoes"
//					, "{\"flightDate\":\"20160922\",\"flightNo\":\"CZ0366\"\"queryNo\":\"7841006753097\",\"queryType\":\"TN\",\"channel\":\"1\"}"
//					, null, "UTF-8", 100000, 100000);
//			System.out.println("result:"+result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	@Test
	public void testHttpUtil2(){
		try {
//			System.setProperty("https.proxyHost","");
//	        System.setProperty("https.proxyPort","");
			String url ="http://10.108.68.97:8080/extra/rest/flightdelay/queryFlightDeplayInfoes";
			
			String param1 ="{\"flightDate\":\"20160922\",\"flightNo\":\"CZ0366\",\"queryNo\":\"7841006753097\",\"queryType\":\"TN\",\"channel\":\"1\"}";
			System.out.println(param1);
			String result = HttpUtil2.postByJson(url, param1, null, "UTF-8", 100000, 100000);
			System.out.println("result:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testHttpUtil3(){
		try {
			String url ="http://10.108.68.97:8080/extra/rest/flightdelay/queryFlightDeplayInfoes";
			String params ="{\"flightDate\":\"20160922\",\"flightNo\":\"CZ0366\"\"queryNo\":\"7841006753097\",\"queryType\":\"TN\",\"channel\":\"1\"}";
			String result = HttpKit.post(url, params);
			System.out.println("result:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testHttpUtil4(){
		try {
			String url ="http://10.108.68.97:8080/extra/rest/flightdelay/queryFlightDeplayInfoes";
			
			String param ="{\"flightDate\":\"20160922\",\"flightNo\":\"CZ0366\",\"queryNo\":\"7841006753097\",\"queryType\":\"TN\",\"channel\":\"1\"}";
			System.out.println("params:"+param);
//			String result = HttpKit.postByJson(url, param);
//			System.out.println("result:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
