package com.hj.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MyJSON extends JSONObject {

    private static final long serialVersionUID = 1L;

    private static final String EMPTY = "";

    public MyJSON() {
        super();
    }

    public MyJSON(boolean ordered) {
        super(ordered);
    }

    public MyJSON(String text) {
        super();
        this.putAll(JSONObject.parseObject(text));
    }

    public MyJSON(int initialCapacity, boolean ordered) {
        super(initialCapacity, ordered);
    }

    public MyJSON(int initialCapacity) {
        super(initialCapacity);
    }

    public MyJSON(Map<String, Object> map) {
        super(map);
    }

    /**
     * 转成单个JSON对象
     * @param key
     * @return
     */
    public MyJSON getNewJSON(String key) {
        return new MyJSON(this.getString(key));
    }

    public JSONArray getNewArray(String key) {
        return JSONArray.parseArray(this.getString(key));
    }

    public void show() {
        System.out.println(format());
    }

    public String format() {
        return JSON.toJSONString(this, true);
    }

    public MyJSON add(String key, Object value, Object... entries) {
        if (StringUtils.isNotEmpty(key)) {
            this.put(key, (null == value ? EMPTY : value));
        }
        int index = 0, size = entries.length;
        if (size > 0 && size % 2 == 0) {
            for (; index < size; index = index + 2) {
                this.put((String) entries[index], (null == entries[index + 1] ? EMPTY : entries[index + 1]));
            }
        }
        return this;
    }

    public static MyJSON addObj(String key, Object value, Object... entries) {
        int index = 0, size = entries.length;
        MyJSON myJSONObject = new MyJSON(size + 1);
        if (StringUtils.isNotEmpty(key)) {
            myJSONObject.put(key, (null == value ? EMPTY : value));
        }
        if (size > 0 && size % 2 == 0) {
            for (; index < size; index = index + 2) {
                myJSONObject.put((String) entries[index], (null == entries[index + 1] ? EMPTY : entries[index + 1]));
            }
        }
        return myJSONObject;
    }

    public static void showObject2Str(MyJSON myJsonObject) {
        System.out.println(JSON.toJSONString(myJsonObject, true));
    }

    public static void showObject2Str(JSONObject jsonObject) {
        System.out.println(JSON.toJSONString(jsonObject, true));
    }

    public static void showObject2Str(JSONArray jsonArray) {
        System.out.println(JSON.toJSONString(jsonArray, true));
    }

    public static String jsonFormat(MyJSON myJsonObject) {
        return JSON.toJSONString(myJsonObject, true);
    }

    public static String jsonFormat(JSONObject jsonObject) {
        return JSON.toJSONString(jsonObject, true);
    }

    public static String jsonFormat(JSONArray jsonArray) {
        return JSON.toJSONString(jsonArray, true);
    }

    public static String textFormat(String text) {
        return JSON.toJSONString(new MyJSON(text), true);
    }

    public static String textFormats(String text) {
        return JSON.toJSONString(JSONArray.parseArray(text), true);
    }
}
