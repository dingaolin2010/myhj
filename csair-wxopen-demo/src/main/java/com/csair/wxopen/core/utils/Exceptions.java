/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.csair.wxopen.core.utils;

/**
 * 关于异常的工具类.
 * 
 * 参考了guava的Throwables。
 * 
 * @author calvin
 */
public class Exceptions {

	private Exceptions()
	{
		
	}
	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Throwable ex) {
		if (ex instanceof RuntimeException) {
			return (RuntimeException) ex;
		} else {
			return new RuntimeException(ex);
		}
	}

 

	/**
	 * 获取组合本异常信息与底层异常信息的异常描述, 适用于本异常为统一包装异常类，底层异常才是根本原因的情况。
	 */
	public static String getErrorMessageWithNestedException(Throwable ex) {
		Throwable nestedException = ex.getCause();
		return new StringBuilder().append(ex.getMessage()).append(" nested exception is ")
				.append(nestedException.getClass().getName()).append(":").append(nestedException.getMessage())
				.toString();
	}

	 
	/**
	 * 判断异常是否由某些底层的异常引起.
	 */
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex;
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}
}
