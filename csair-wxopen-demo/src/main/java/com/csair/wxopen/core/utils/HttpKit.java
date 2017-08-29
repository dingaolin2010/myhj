/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.csair.wxopen.core.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Map.Entry;


/**
 * https 请求 微信为https的请求
 * @author L.cm
 * @date 2013-10-9 下午2:40:19
 */
public class HttpKit {
    protected static final Logger log = LoggerFactory.getLogger(HttpKit.class);
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String GET = "GET"; // GET
    private static final String POST = "POST";// POST
    
    /**
     * 初始化http请求参数
     * @param url
     * @param method
     * @param headers
     */
    private static HttpURLConnection initHttp (String url, String method, Map<String, String> headers) throws IOException {
        URL _url = new URL(url);
        HttpURLConnection http = (HttpURLConnection) _url.openConnection();
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(20000);
        http.setRequestMethod(method);
        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }
    
    /**
     * 初始化http请求参数
     * @param url
     * @param method
     * @return
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.KeyManagementException
     */
    private static HttpsURLConnection initHttps (String url, String method, Map<String, String> headers) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {

        URL _url = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
        // 设置域名校验
        http.setHostnameVerifier(new HttpKit().new TrustAnyHostnameVerifier());
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod(method);
        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * 初始化http请求参数
     * @param url
     * @param method
     */
    private static HttpsURLConnection initProxyHttps (String url, String method, Map<String, String> headers) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
    	System.getProperties().put("proxySet", "true");
    	System.getProperties().put("proxyHost", "10.101.1.6");
    	System.getProperties().put("proxyPort", "80");
    	Authenticator.setDefault(new BasicAuthenticator("770513", "DING123!"));
    	URL _url = new URL(url);
    	HttpsURLConnection https = (HttpsURLConnection) _url.openConnection();
    	// 设置域名校验
    	https.setHostnameVerifier(new HttpKit().new TrustAnyHostnameVerifier());
    	// 连接超时
    	https.setConnectTimeout(25000);
    	// 读取超时 --服务器响应比较慢，增大时间
    	https.setReadTimeout(25000);
    	https.setRequestMethod(method);
    	https.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
    	https.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
    	if (null != headers && !headers.isEmpty()) {
    		for (Entry<String, String> entry : headers.entrySet()) {
    			https.setRequestProperty(entry.getKey(), entry.getValue());
    		}
    	}

    	https.setDoOutput(true);
    	https.setDoInput(true);
    	https.connect();
    	return https;
    }

    /**
     *
     * @description
     * 功能描述: get 请求
     * @return       返回类型:
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    private static String get(String url, Map<String, String> headers) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException {
		long start = System.currentTimeMillis();
        HttpURLConnection http = null;
        if (isHttps(url)) {
            http = initHttps(url, GET, headers);
        } else {
            http = initHttp(url, GET, headers);
        }
        InputStream in = null;
        StringBuilder bufferRes = new StringBuilder();
        try {
            in = http.getInputStream();
            String valueString = null;

        	BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            while ((valueString = read.readLine()) != null){
                bufferRes.append(valueString);
            }
		} finally {
            if(in != null){
                in.close();
            }
            http.disconnect();// 关闭连接
            long end = System.currentTimeMillis();
            log.info("GET url: " + url + ",耗时:" + (end-start) + "ms");
		}
        return bufferRes.toString();
    }

    /**
     *
     * @description
     * 功能描述: get 请求
     * @return       返回类型:
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException {
        return get(url, null);
    }

    /**
     * http get请求访问
     * @param url 请求地址
     * @param params  key-value 参数
     * @param headers 请求头部
     */
    public static String get(String url, Map<String, String> params,Map<String,String> headers) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException {
        return get(initParams(url, params), headers);
    }

    /**
     * @description
     * 功能描述: POST 请求
     * @return       返回类型:
     * @throws java.io.IOException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public static String post(String url, String params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {

        long start = System.currentTimeMillis();
        HttpURLConnection http = null;
        if (isHttps(url)) {
            http = initHttps(url, POST, null);
        } else {
            http = initHttp(url, POST, null);
        }
        OutputStream out = http.getOutputStream();
        out.write(params.getBytes(DEFAULT_CHARSET));
        out.flush();
        out.close();

        InputStream in = http.getInputStream();
        String valueString = null;
        StringBuffer bufferRes = new StringBuffer();
        try {
        	BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            while ((valueString = read.readLine()) != null){
                bufferRes.append(valueString);
            }
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} finally{
			in.close();
	        if (http != null) {
	            http.disconnect();// 关闭连接
	        }
            long end = System.currentTimeMillis();
            log.info("POST URL:{},耗时:{}ms",url, end-start);
		}


        return bufferRes.toString();
    }

    public static byte[] post(String url, byte[] params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        long start = System.currentTimeMillis();
        HttpURLConnection http ;
        if (isHttps(url)) {
            http = initHttps(url, POST, null);
        } else {
            http = initHttp(url, POST, null);
        }
        OutputStream out = http.getOutputStream();
        out.write(params);
        out.flush();
        out.close();

        InputStream in = http.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int count;
        byte[] strByte = null;
        try {
        	while ((count = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, count);
            }
            strByte = bos.toByteArray();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} finally{
			bos.close();
	        bis.close();
	        in.close();
	        http.disconnect();// 关闭连接
            long end = System.currentTimeMillis();
            log.info("POST URL:{},耗时:{}ms",url, end-start);
		}

        return strByte;
    }

    public static byte[] post(String url, byte[] params, Map<String,String> headers) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        long start = System.currentTimeMillis();
        HttpURLConnection http ;
        if (isHttps(url)) {
            http = initHttps(url, POST, headers);
        } else {
            http = initHttp(url, POST, headers);
        }
        OutputStream out = http.getOutputStream();
        out.write(params);
        out.flush();
        out.close();

        InputStream in = http.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int count;
        byte[] strByte = null;
        try {
            while ((count = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, count);
            }
            strByte = bos.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally{
            bos.close();
            bis.close();
            in.close();
            http.disconnect();// 关闭连接
            long end = System.currentTimeMillis();
            log.info("POST URL:{},耗时:{}ms",url, end-start);
        }
        return strByte;
    }

    /**
     * 上传媒体文件
     * @param url
     * @param file
     * @return
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.KeyManagementException
     */
    public static String upload(String url,File file) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
        StringBuffer bufferRes = null;
        URL urlGet = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlGet.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        OutputStream out = new DataOutputStream(conn.getOutputStream());
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filename=\""+ file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] data = sb.toString().getBytes();
        out.write(data);
        DataInputStream fs = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = fs.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
        fs.close();
        out.write(end_data);
        out.flush();
        out.close();

        // 定义BufferedReader输入流来读取URL的响应
        InputStream in = conn.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
        String valueString = null;
        bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null){
            bufferRes.append(valueString);
        }
        in.close();
        if (conn != null) {
            // 关闭连接
            conn.disconnect();
        }
        return bufferRes.toString();
    }



    /**
     * 功能描述: 构造请求参数
     * @return       返回类型:
     * @throws java.io.UnsupportedEncodingException
     */
    public static String initParams(String url, Map<String, String> params) throws UnsupportedEncodingException {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(map2Url(params));
        return sb.toString();
    }

    /**
     * map构造url
     * @return       返回类型:
     * @throws java.io.UnsupportedEncodingException
     */
    public static String map2Url(Map<String, String> paramToMap) throws UnsupportedEncodingException {
        if (null == paramToMap || paramToMap.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
        boolean isfist = true;
        for (Entry<String, String> entry : paramToMap.entrySet()) {
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if (StringUtils.isNotEmpty(value)) {
                    url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
            }
        }
        return url.toString();
    }
    
    /**
     * 检测是否https
     * @param url
     */
    private static boolean isHttps (String url) {
        return url.startsWith("https");
    }
    
    /**
     * https 域名校验
     * @return
     */
    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;// 直接返回true
        }
    }

}
