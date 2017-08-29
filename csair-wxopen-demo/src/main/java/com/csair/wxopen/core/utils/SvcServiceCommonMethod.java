package com.csair.wxopen.core.utils;

import com.csair.wxopen.core.mapper.JsonUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @ClassName: SvcServiceCommonMethod
 * @Description: Svc接口调用的公共方法
 * @author: Jason
 * @date: 2014-1-8
 * 
 */
@Component
public class SvcServiceCommonMethod {

	private static final Logger LOGGER = LoggerFactory.getLogger(SvcServiceCommonMethod.class);

	private final Integer REQUEST_TIMEOUT = Integer.valueOf(10000);
	
	/**
	 * get请求svc接口返回XML
	 * 
	 * @param url
	 * @return
	 */
	public String getRequestSvc(String url) {
		LOGGER.info("getRequestSvc()" + url);
		HttpClient httpClient = new HttpClient(); // 创建HTTP对象
		// 设置 Http 连接超时为6秒  
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(REQUEST_TIMEOUT);
		httpClient.getParams().setContentCharset("UTF-8");
		GetMethod getMethod = new GetMethod(url);// get方法
		// 设置请求超时为 5 秒  
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, REQUEST_TIMEOUT);
		try {
			httpClient.executeMethod(getMethod);
			if (HttpStatus.SC_OK == getMethod.getStatusCode()) // 是否请求成功
			{
				String xml = getMethod.getResponseBodyAsString();// 取得返回的xml数据
				LOGGER.info("getRequestSvc() xml " + xml);
				return xml;
			} else {
				LOGGER.info("getRequestSvc()" + url + "-:-"
						+ getMethod.getStatusCode());
			}
		} catch (HttpException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * get请求svc接口
	 * 
	 * @param url
	 * @return
	 */
	public <T> T getRequestSvc(String url,Class<?>... type) {
		LOGGER.info("getRequestSvc()" + url);
		HttpClient httpClient = new HttpClient(); // 创建HTTP对象
		// 设置 Http 连接超时为6秒  
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(REQUEST_TIMEOUT);
		httpClient.getParams().setContentCharset("UTF-8");
		GetMethod getMethod = new GetMethod(url);// get方法
		// 设置请求超时为 5 秒  
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, REQUEST_TIMEOUT);
		try {
			httpClient.executeMethod(getMethod);
			if (HttpStatus.SC_OK == getMethod.getStatusCode()) // 是否请求成功
			{
				String xml = getMethod.getResponseBodyAsString();// 取得返回的xml数据
				LOGGER.info("getRequestSvc() xml " + xml);
				T customer = (T) xml2Obj(xml, type);
				LOGGER.info("getRequestSvc() Result "
						+ JsonUtil.obj2json(customer));
				return customer;
			} else {
				LOGGER.info("getRequestSvc()" + url + "-:-"
						+ getMethod.getStatusCode());
			}
		} catch (HttpException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * POST请求svc接口
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public <T> T postRequestSvc(String url,
			Map<String, String> parameters,Class<?>... type) {
		LOGGER.info("postRequestSvc()" + url + ":parameters-" + parameters);
		HttpClient httpClient = new HttpClient(); // 创建HTTP对象
		// 设置 Http 连接超时为6秒  
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(REQUEST_TIMEOUT);
		//乱码问题，需要设置UTF-8
		httpClient.getParams().setContentCharset("UTF-8");
		PostMethod postMethod = new PostMethod(url);// post方法
		// 设置请求超时为 5 秒  
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, REQUEST_TIMEOUT);
		for(Entry<String, String> entry:parameters.entrySet()){
			postMethod.addParameter(new NameValuePair(entry.getKey(),entry.getValue()));
		}
		
		try {
			httpClient.executeMethod(postMethod);
			if (HttpStatus.SC_OK == postMethod.getStatusCode()) // 是否请求成功
			{
				String xml = postMethod.getResponseBodyAsString();// 取得返回的xml数据
				LOGGER.info("postRequestSvc() xml " + xml);
				T customer = (T) xml2Obj(xml, type);

				LOGGER.info("postRequestSvc() Result "
						+ JsonUtil.obj2json(customer));

				return customer;
			} else {
				LOGGER.info("postRequestSvc()" + url + "-:-"
						+ postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * xml转object对象
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 */
	public static Object xml2Obj(String xml, Class<?>... clazz) {
		JAXBContext jaxbContext;
		Object customer = null;
		try {
			jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			customer = (Object) jaxbUnmarshaller
					.unmarshal(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
		    LOGGER.error(e.getMessage(), e);
        } 
		return customer;
	}

	/**
	 * 对象转XML
	 * 
	 * @param obj
	 * @param clazz
	 * @return
	 */
	public String obj2xml(Object obj, Class<?> clazz) {
		StringWriter swriter;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xml头信息
			swriter = new StringWriter();

			marshaller.marshal(obj, swriter); // 将XML内容打印为字符串
			return swriter.getBuffer().toString();
		} catch (PropertyException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 利用XStream转换XML为对象
	 * @param xml
	 * @param aliasMap
	 * @return
	 */
	public Object xml2obj(String xml,Map<String,Class> aliasMap){
		LOGGER.info("xml2obj xstream:"+xml);
		XStream xStream = new XStream(new DomDriver());
	    Iterator<Entry<String, Class>> it = aliasMap.entrySet().iterator();
	    Entry<String ,Class> entry = null;
	    while(it.hasNext()){
	    	entry = it.next();
	    	xStream.alias(entry.getKey(), entry.getValue());
	    }
	    Object obj =  xStream.fromXML(xml);
	    return obj;
	}
}
