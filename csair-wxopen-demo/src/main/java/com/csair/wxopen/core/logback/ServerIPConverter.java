package com.csair.wxopen.core.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class ServerIPConverter extends ClassicConverter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerIPConverter.class);
	private static String ip;
	static {
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			LOGGER.info(e.getMessage(),e);
			ip = "unKnownHost";
		}
	}

	@Override
	public String convert(ILoggingEvent event) {
		return ip;
	}

}
