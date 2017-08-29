package com.csair.wxopen.core.exception;

public enum ErrorCode {

    /**session过期**/
    WX_OPENID_SESSION_EXPIRE("00000"),

    BAD_REQUEST("W400", 400), UNAUTHORIZED("W401", 401), FORBIDDEN("W403", 403), INTERNAL_SERVER_ERROR("W500", 500),

    OK("W200",200), BOOK_STATUS_WRONG("W1100", 400), BOOK_OWNERSHIP_WRONG("W1101", 403), NO_TOKEN("W1102", 401),
    UNIOIN2OPENID("W40001",200),
    /**
     * 通用的业务码
     */
    //找不到记录~
    NOT_FOUND_RECORD("99000"),
    //输入信息有误，请检查您的输入~
    INPUT_ERROR("99000"),
    //系统繁忙
    SYSTEM_BUSY("99001"),
    //网络异常
    NETWORD_BUSY("99002"),

    QRCODE_ERROR("99020");

    

	private String code;
	private int httpStatus;

	ErrorCode(String code, int httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}

    ErrorCode(String code){
        this.code = code;
        this.httpStatus = 200;

    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
    
    

}
