package com.hj.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Created by zhongzy on 2017/5/10.
 */
public class ConfigPropertiesUtils {
    private Log log = LogFactory.getLog(ConfigPropertiesUtils.class);
    private static ConfigPropertiesUtils instance = null;
    private static Map<String, Properties> PROPS_MAP = new HashMap<String, Properties>();

    private ConfigPropertiesUtils() {
    }

    public Properties loadPropertyFile(String fileName) {
        if (StringUtils.isBlank(fileName))
            throw new IllegalArgumentException("配置文件路径不允许为空 : " + fileName);
        Properties properties = PROPS_MAP.get(fileName);
        if (properties == null) {
            properties = loadProperties(fileName);
            PROPS_MAP.put(fileName, properties);
        }
        return properties;
    }

    private Properties loadProperties(String fileName) {
        Properties properties = null;
        InputStream in = null;
        try {
            in = ConfigPropertiesUtils.class.getResourceAsStream(fileName);
            properties = new Properties();
            properties.load(in);
        }
        catch (FileNotFoundException e) {
            //throw new IllegalArgumentException("配置文件路径错误: "   + fileName);
            log.error("配置文件路径错误 " + fileName);
        }
        catch (IOException var11) {
            if(log.isErrorEnabled()) {
                log.error("加载配置文件 " + fileName + " 失败 " + var11.getMessage());
            }
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException var10) {
                    var10.printStackTrace();
                }
            }

        }
        return properties;
    }

    public static ConfigPropertiesUtils getInstance() {
        if(instance == null) {
            instance = new ConfigPropertiesUtils();
        }

        return instance;
    }
}
