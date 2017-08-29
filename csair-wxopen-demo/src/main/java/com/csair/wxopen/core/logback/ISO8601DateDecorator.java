package com.csair.wxopen.core.logback;

import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import net.logstash.logback.decorate.JsonFactoryDecorator;

import java.text.DateFormat;

public class ISO8601DateDecorator implements JsonFactoryDecorator {
	private ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

	@Override
	public MappingJsonFactory decorate(MappingJsonFactory factory) {
		ObjectMapper codec = factory.getCodec();
		DateFormat df = threadLocal.get();
		if (df == null) {
			df = new ISO8601DateFormat();
			threadLocal.set(df);
		}
		codec.setDateFormat(df);
		return factory;
	}

}
