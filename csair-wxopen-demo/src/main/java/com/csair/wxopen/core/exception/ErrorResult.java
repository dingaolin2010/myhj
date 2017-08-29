package com.csair.wxopen.core.exception;

public class ErrorResult {

	private  String code;
	private  String message;

	public ErrorResult() {
	}

	public ErrorResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorResult(int code, String message) {
		this.code = String.valueOf(code);
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}