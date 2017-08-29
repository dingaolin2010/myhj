package com.hj.utils;

import java.util.UUID;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DealStr {

	/**
	 * 字符串小写转换
	 * 
	 * @param str
	 * @return
	 */
	public static String dealLowcase(String str) {
		String temp = null;
		try {
			if (str != null || str != "") {
				temp = str.toLowerCase();
			}
		} catch (Exception e) {
			return null;
		}
		return temp.trim();

	}

	/**
	 * 生成32 位的唯一的uuid
	 * 
	 * @return
	 */
	public static String getUUId() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}

	/**
	 * BASE解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}


	/**
	 * 加密
	 */
	public static String encode(String str) {

		try {
			byte[] temp = str.getBytes("UTF-8");

			int t = 0, tmp = 0;
			for (int i = 0, c = temp.length; i < c; i++) {
				t = i / 32 + 2;
				tmp = (byte) ((temp[i]) ^ ((i + 1) % 32) ^ t);
				if (tmp != 0 && tmp != 255 && tmp != 128) {
					temp[i] = (byte) tmp;
				}
			}
			String result = (new BASE64Encoder()).encode(temp);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
 
	
/*	public static void main(String[] args) throws IOException {
			String str = "test1234";
			String reuslt = DealStr.encode(str);
			System.out.println(reuslt);
			
	}*/
}
