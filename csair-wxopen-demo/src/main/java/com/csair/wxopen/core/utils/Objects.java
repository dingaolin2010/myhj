package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * add by ayu_784900 160623 sonar
 * 
 * */
public class Objects {

	private static final Logger LOGGER = LoggerFactory.getLogger(Objects.class);
	public static Double getNumber(Object in)
	{
		return Double.parseDouble(in.toString());
	}
	public static boolean isNotEmpty(Object inObj)
	{
		return !isEmpty(inObj);
	}
	public static boolean isNotNull(Object ori)
	{
		return !isNull(ori);
	}
	public static boolean isNull(Object ori)
	{
		return ori==null;
	}
	public static boolean isOrTrue(Boolean[] in){//或关系
		boolean retn=false;
		if(in==null || in.length<=0){
			return retn;
		}
		for(Boolean o:in){
			if(o){
				retn= true;
				break;
			}
		}
		return retn;
	}
	public static boolean isTrue(Boolean[] in){//与关系
		boolean retn=true;
		if(in==null || in.length<=0){
			return !retn;
		}
		for(Boolean o:in){
			if(!o){
				retn= false;
				break;
			}
		}
		return retn;
	}
	public static boolean isBatchAndEmpty(Object... in){
		boolean retn=false;
		if(in==null || in.length<=0){
			return !retn;
		}
		for(Object o:in){
			if(isEmpty(o)){
				retn= true;
				break;
			}
		}
		return retn;
	}
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object inObj)
	{
		if(inObj==null)
		{
			return true;
		}
		if(inObj instanceof Collection)
		{
			return ((Collection)inObj).isEmpty();
		}
		else if(inObj instanceof Map)
		{
			return ((Map)inObj).isEmpty();
		}
		else if(inObj instanceof List)
		{
			return ((List)inObj).isEmpty();
		}
		else if(inObj instanceof String)
		{
			return "".equals((String)inObj);
		}
		else if(inObj.getClass().isArray())
		{
			return Array.getLength(inObj)==0;
		}
		return false;
	}
	 
	public static int compareTo(Double ori,Double dest)
	{
		BigDecimal dori = new BigDecimal(ori); 
		BigDecimal ddest = new BigDecimal(dest); 
		return dori.compareTo(ddest);
	}
	 
	 
	 
	 
	private Objects(){
		
	}
	
	
	 
		
}
