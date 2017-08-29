package com.csair.wxopen.core.exception;

import org.springframework.util.StringUtils;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8634700792767837033L;

	public ErrorCode errorCode;

    private String code;

    /**
     * 内部系统异常错误编码
     * @param message 提示语
     * @param errorCode W40001
     */
	public ServiceException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;//WX40001
	}

	public ServiceException(String message,ErrorCode errorCode, Exception exception){
		super(message,exception);
		this.errorCode = errorCode;
	}

    /**
     * 外部系统异常错误编码
     * @param message 提示语
     * @param code InterfaceCode(系统编码）+ 错误码
    * @param exception 上层异常堆栈
     */
    public ServiceException(String message, String code, Exception exception){
        super(message,exception);
        this.code = code;
        this.errorCode = ErrorCode.OK;
    }

    /**
     * 外部系统异常错误编码
     * @param message 提示语
     * @param code 错误码
     */
    public ServiceException(String message, String code){
        super(message);
        this.code = code;
        this.errorCode = ErrorCode.OK;
    }


    public String getCode(){
        if(StringUtils.isEmpty(code)){
            return errorCode.getCode();
        }
        return this.code;
    }

}
