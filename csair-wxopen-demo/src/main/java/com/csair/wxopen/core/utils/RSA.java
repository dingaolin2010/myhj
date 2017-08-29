package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;


public class RSA {

	private static final Logger LOGGER = LoggerFactory.getLogger(RSA.class);
	private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/** * 加密,key可以是公钥，也可以是私钥  RSA加密明文最长117位，需要分段加密* * 
	 * @param message 
	 * @return 
	 * @throws Exception */
	public byte[] encrypt(String message, Key key) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] me = message.getBytes();
			byte[] doFinal = null, doFinal2;
			for (int i = 0; i < me.length; i += 117) {
				if (i+117>me.length)
					doFinal2 = cipher.doFinal(me,i,me.length-i);
				else
					doFinal2 = cipher.doFinal(me,i,117);
				if (doFinal==null)
					doFinal = doFinal2;
				else
					doFinal = join(doFinal,doFinal2);
			}
			return doFinal;
		} catch (Exception e) {
			LOGGER.error("不可能抛出的异常encrypt:{}", e);
			
		}
		return new byte[0];
	}
	
	/** 
	　　* 连接两个byte数组，之后返回一个新的连接好的byte数组 
	　　* @param a1 
	　　* @param a2 
	　　* @return 一个新的连接好的byte数组 
	　　*/ 
	static public byte[] join(byte[] a1, byte[] a2) { 
		byte[] result = new byte[a1.length + a2.length]; 
		System.arraycopy(a1, 0, result, 0, a1.length); 
		System.arraycopy(a2, 0, result, a1.length, a2.length); 
		return result;
	} 



	/** *
	 *  从文件读取object
	 * */
	public Object readFromFile(String fileName)  {
		InputStream is ;
		try {
			is = new FileInputStream(fileName);
			return readFromFile(is);
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(),e);
			return null;
		}

	}
	
	public Object readFromFile(InputStream is)  {
		ObjectInputStream input;
		try {
			input = new ObjectInputStream(is);
			Object obj = input.readObject();
			input.close();
			return obj;
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error(e.getMessage(),e);
			return null;
		}
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (byte aB : b) {
			sb.append(HEXCHAR[(aB & 0xf0) >>> 4]);
			sb.append(HEXCHAR[aB & 0x0f]);
		}
		return sb.toString();
	}

	public static byte[] toBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
					16);
		}
		return bytes;
	}


}
