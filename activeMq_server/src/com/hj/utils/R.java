package com.hj.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 字符串静态常量类
 * @author liaojw
 *
 */
public class R {

    public static final String DEFAULT_QL_DISTNO = "DNIqe2obckEinkzj";
    public static final String SUCCESS = "success";
    public static final String ERROR_404 = "error/404";
    public static final String REDIRECT = "redirect:/";
    public static final String FORWARD = "forward:/";
    public static final String DATA_STR = "{\"result\":1,  \"msg\": \"操作成功\", \"ext\":\"\",\"data\": \"\"}";
    public static final String FAIL_STR = "{\"result\":0,  \"msg\": \"操作失败\", \"ext\":\"\",\"data\": \"\"}";
    public static final String FORMAT_RETURN_STRING = "{\"result\":%d,  \"msg\": \"%s\",\"data\":\"%s\"}";
    public static final String OPER_FAIL_STR = "{\"result\":0,  \"msg\": \"%s\"}";
    public static final int OPER_CODE_SUCCESS = 1; //操作成功代码
    public static final int OPER_CODE_FAIL = 0;//操作失败代码
    public static final int OPER_CODE_ERROR = 2;//系统异常错误代码
    public static final String NULL_PARME_STRING = "参数不能为null！";
    public static final String NOT_DELE_STRING = "无法删除";
    public static final String FIX_STRING = "{\"result\":1, \"errno\":\"\", \"errmsg\": \"\", \"ext\":\"\",\"data\": \"\"}";
    public static final String NUMS = "0123456789";
    public static final String NUMS_CHARS = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    public static final String DEFAULT_NAME_STR = "#name#";
    public static final int REDIS_DB0 = 0;
    public static final int REDIS_DB1 = 1;
    public static final int REDIS_DB2 = 2;
    public static final int REDIS_DB3 = 3;
    public static final int REDIS_DB4 = 4;

    public static final int IMAGE_FIX_MID_SIZE = 900;
    public static final int IMAGE_FIX_SMALL_SIZE = 160;

    public static final int REDIS_COOKIES_MAX_AGE = 60 * 60 * 24 * 7; //默认一周时间
    public static final int REDIS_CART_MAX_SIZE = 15;

    /****支付变量****/
    public static final String ENDCODE_UTF8 = "UTF-8";
    public static final String ENDCODE_GBK = "GBK";

    public static final int SYS_ID = 18443;
    public static final String SYS_NAME = "系统自动发起";
    public static final String MSG_TITLE = "课程通知";
    public static final String MSG_TITLE1 = "提示通知";
    public static final String MSG_REMARK = "点击查看详情";
    public static final Integer PAY_SUCCESS = 2;

    private static Logger LOGGER = LoggerFactory.getLogger(R.class);

    public static void debug(String msg) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(msg);
        }
    }

    public static void debug(String format, Object arg) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(format, arg);
        }
    }

    public static void debug(String format, Object arg1, Object arg2) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(format, arg1, arg2);
        }
    }

    public static void debug(String format, Object... arguments) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(format, arguments);
        }
    }

    public static void info(String msg) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(msg);
        }
    }

    public static void info(String format, Object arg) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(format, arg);
        }
    }

    public static void info(String format, Object arg1, Object arg2) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(format, arg1, arg2);
        }
    }

    public static void info(String format, Object... arguments) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(format, arguments);
        }
    }

    public static void error(String msg) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(msg);
        }
    }

    public static void error(String format, Object arg) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(format, arg);
        }
    }

    public static void error(String format, Object arg1, Object arg2) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(format, arg1, arg2);
        }
    }

    public static void error(String format, Object... arguments) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(format, arguments);
        }
    }

    /**
     * 主要用于产生一个指定长度的随机值
     * @param len
     * @return
     */
    public static String random() {
        return RandomStringUtils.random(6, NUMS);
    }

    /*    public static String randomStr() {
            return RandomStringUtils.random(5, NUMS_CHARS);
        }
    */
    /**
     * 清除实体对象
     * @param objects
     */
    public static void clerarAllBean(Object... objects) {
        for (Object obj : objects) {
            obj = null;
        }
    }

    public static JSONObject findByParentId(JSONArray array, int parentId) {
        JSONObject result = null;
        for (int index = 0, size = array.size(); index < size; index++) {
            JSONObject ele = array.getJSONObject(index);
            if (ele.getIntValue("id") == parentId) {
                result = ele;
                break;
            }
        }
        return result;
    }
}
