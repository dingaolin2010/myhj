/**
 * @desc 
 * @author tangan
 * @date 2016年9月29日 下午2:20:40
 * @return
 */
package com.csair.wxopen.core.utils;

import com.alibaba.druid.util.Base64;
import com.csair.wxopen.core.exception.ErrorCode;
import com.csair.wxopen.core.exception.ServiceException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * @desc 
 * @author tangan
 * @date 2016年9月29日 下午2:20:40
 * @email tangan@foreveross.com
 */
public class ConvertInfoUtil 
{
	private ConvertInfoUtil()
	{
		
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConvertInfoUtil.class);

	/**
	 * 将文本转换为二维码图标编码
	 * @param text   二维码内容
	 * @param format 二维码类型
	 */
	public static String generateQrCode(String text, BarcodeFormat format,int width,int height) {
		EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, format, width, height, hints);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "jpg", stream);
			return Base64.byteArrayToBase64(stream.toByteArray());
		} catch (WriterException | IOException e) {
			throw new ServiceException("获取二维码失败，请稍候重试", ErrorCode.QRCODE_ERROR, e);
		}
	}
}
