package com.csair.wxopen.core.mapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * java与json互转
 */
public class JsonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger( JsonUtil.class );
	
	
	private JsonUtil(){
	}
	/**
	 * jackson
	 */
	private static ObjectMapper jackson;
	private static ObjectMapper jacksonMore;
	
	static {
		ObjectMapper jacksonTmp = new ObjectMapper();        
		jacksonTmp.setSerializationInclusion( JsonInclude.Include.NON_NULL );
		// use ISO-8601
		jacksonTmp.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
		jacksonTmp.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
		jackson = jacksonTmp;
		
		jacksonMore = new ObjectMapper();   
		jacksonMore.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
		jacksonMore.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
		jacksonMore.setSerializationInclusion( JsonInclude.Include.ALWAYS );
		jacksonMore.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);  //运行匹配多余字段
	}
	/**
	 * java类型实例转换json字符串 (打印输出，不抛异常)
	 *   (空字符串去除)
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public static String printJson( Object obj ) {
		try {
			/* jackson */
			return jackson.writeValueAsString( obj );
		} catch ( Exception e ) { 
			logger.info( "jackson：printJson error. errorMsg:{}",e.getMessage()  );
		}
		return null; 
	}
	
	/**
	 * json字符串转换java类型实例
	 * @param jsonStr json字符串
	 * @param clazz java类型
	 * @return
	 * @throws Exception 
	 */
	public static <T> T json2Obj( String jsonStr, Class<T> clazz ) throws Exception {
		try {
			/* jackson */
			return jackson.readValue(jsonStr,clazz );
		} catch ( Exception e ) { 
			throw new Exception( "invoke json2Obj fail：" + clazz + "," + jsonStr, e );
		}
	}
	/**
	 * json字符串转换java类型实例
	 *     (匹配不相同的字段)
	 * @author jamefeng
	 * @date 2016年9月27日
	 * @param jsonStr json字符串
	 * @param clazz java类型
	 * @return
	 * @throws Exception 
	 */
	public static <T> T json2ObjMore( String jsonStr, Class<T> clazz ) throws Exception {
		try {
			/* jackson */
			return jacksonMore.readValue( jsonStr ,clazz);
		} catch ( Exception e ) { 
			throw new Exception( "invoke json2ObjMore fail：" + clazz + "," + jsonStr, e );
		} 
	}
	
	
	/**
	 * java类型实例转换json字符串
	 *   (空字符串去除)
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public static String obj2json( Object obj ) throws Exception {
		try {
			/* jackson */
			return jackson.writeValueAsString( obj );
		} catch ( Exception e ) { 
			throw new Exception( "invoke obj2json fail：" + obj, e );
		} 
	}
	

	
	/**
	 * java类型实例转换json字符串
	 *     包含空字符串的字段 ，内容变成 NULL
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public static String obj2jsonMore( Object obj ) throws Exception {
		try {
			/* jackson */
			return jacksonMore.writeValueAsString( obj );
		} catch ( Exception e ) { 
			throw new Exception( "invoke obj2jsonMore fail：" + obj, e );
		} 
	}
	
	/**
	 * 将json字符串转换成泛型List
	 *   (匹配不相同的字段)
	 * @author jamefeng
	 * @date 2016年9月27日
	 * @param jsonStr
	 * @param collectionClass
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	public static <T> List<T> json2List( String jsonStr,  Class<T> clazz) throws Exception {
		try {
			JavaType javaType = getCollectionType(List.class, clazz); 
			return jacksonMore.readValue( jsonStr,javaType );
		} catch ( Exception e ) { 
			throw new Exception( "invoke json2List fail：" + clazz + "," + jsonStr, e );
		} 
	}
	
	/**
	 * 获取泛型的Collection Type  
	 * @author jamefeng
	 * @date 2016年9月27日
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		  return jacksonMore.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	} 
	
	
	
	
	
	
}
