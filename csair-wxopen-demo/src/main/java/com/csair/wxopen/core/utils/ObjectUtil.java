package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectUtil {
	
	private ObjectUtil()
	{
		
	}
	
	private final static Logger logger = LoggerFactory.getLogger(ObjectUtil.class);
	
	@SuppressWarnings("unchecked")
	/**
	 * 对象属性类型为String、Integer，将值为null转为""
	 * @param obj
	 * @return
	 */
	public static Object iterateObject(Object obj) {
		if(obj==null){
			return obj;
		}
		Class<?> currClass=obj.getClass();
		while(currClass!=Object.class){
			Field[] fields = currClass.getDeclaredFields();
			try {
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.getType() == List.class) {
						List<Object> childObjList = (List<Object>) field.get(obj);
						for (int i = 0; i < childObjList.size(); i++) {
							Object childObj = childObjList.get(i);
							iterateObject(childObj);
						}
					} else if (field.getType().toString().contains("com.csair")) {
						iterateObject(field.get(obj));
					} else if (field.getType() == String.class) {
						String value = (String) field.get(obj);
						if (value == null) {
							value = "";
						}
						field.set(obj, value);
					}else if (field.getType() == Integer.class) {
						Integer value = (Integer) field.get(obj);
						if (value == null) {
							value = 0;
						}
						field.set(obj, value);
					}
				}
			} catch (IllegalArgumentException e) {
				logger.error("对象重构异常", e);
			} catch (IllegalAccessException e) {
				logger.error("对象重构异常", e);
			}catch (Exception e) {
				logger.error("对象重构异常", e);
			}
			currClass=currClass.getSuperclass();
		}
		
		return obj;
	}
}
