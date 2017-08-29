package com.csair.wxopen.core.exception;

import com.csair.wxopen.core.mapper.JsonMapper;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice(annotations = { RestController.class })
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	private JsonMapper jsonMapper = new JsonMapper();

	@ExceptionHandler(value = { ServiceException.class })
	public final ResponseEntity<ErrorResult> handleServiceException(ServiceException ex, HttpServletRequest request) {
		// 注入servletRequest，用于出错时打印请求URL与来源地址
		logError(ex, request);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		ErrorResult result = new ErrorResult(ex.getCode(), ex.getMessage());
		return new ResponseEntity<ErrorResult>(result, headers, HttpStatus.valueOf(ex.errorCode.getHttpStatus()));
	}

	@ExceptionHandler(value = { Exception.class })
	public final ResponseEntity<ErrorResult> handleGeneralException(Exception ex, HttpServletRequest request) {
		logError(ex, request);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		ErrorResult result = new ErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return new ResponseEntity<ErrorResult>(result, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logError(ex);
		return handleExceptionInternal(ex, null, headers, status, request);
	}
 
	public void logError(Exception ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		if(ex instanceof ServiceException){
			ServiceException e = (ServiceException)ex;
			map.put("code",e.getCode());
		}
		logger.error(jsonMapper.toJson(map), ex);
	}

	public void logError(Exception ex, HttpServletRequest request) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		map.put("from", request.getRemoteAddr());
		String queryString = request.getQueryString();
		map.put("path", queryString != null ? (request.getRequestURI() + "?" + queryString) : request.getRequestURI());
        if(ex instanceof ServiceException){
            ServiceException e = (ServiceException)ex;
            map.put("code",e.getCode());
        }
		logger.error(jsonMapper.toJson(map), ex);
	}
}
