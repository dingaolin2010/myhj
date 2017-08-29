package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class GZipUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(GZipUtil.class);

    private GZipUtil(){}

	public static byte[] gzip(byte[] data) throws IOException {    
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipEntry ze = new ZipEntry("servletservice");
		ZipOutputStream zos = new ZipOutputStream(baos);
		zos.putNextEntry(ze);
		zos.write(data, 0, data.length);
		zos.close();
        return baos.toByteArray();
    } 
    
    public static byte[] unzip(byte[] zipBytes) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(zipBytes);
		ZipInputStream zis = new ZipInputStream(bais);
		zis.getNextEntry();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final int buffSize = 4096;
		byte inBuf[] = new byte[buffSize];
		int n;
        try{
        	while ((n = zis.read(inBuf, 0, buffSize)) != -1) {
        		baos.write(inBuf, 0, n);
        	}
        } catch(Exception ex){
            LOGGER.error(ex.getMessage(),ex);
        }

		byte[] data = baos.toByteArray();
		zis.close();
		return data;
	}
    
     
} 
