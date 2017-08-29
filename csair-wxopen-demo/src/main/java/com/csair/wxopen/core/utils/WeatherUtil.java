package com.csair.wxopen.core.utils;

import java.util.HashMap;
import java.util.Map;

public final class WeatherUtil {
    private WeatherUtil() {
    }

    private static Map<String, String> cnWeather = new HashMap<String, String>();
    private static Map<String, String> enWeather = new HashMap<String, String>();

    static {
        cnWeather.put("w45.png", "无");
        cnWeather.put("w45_2.png", "阴天");
        cnWeather.put("w45_3.png", "夜晚多云");
        cnWeather.put("w45_4.png", "白天大雨");
        cnWeather.put("w45_5.png", "夜晚大雨");
        cnWeather.put("w45_6.png", "大雨");
        cnWeather.put("w45_7.png", "雨加雪");
        cnWeather.put("w45_8.png", "大雪");
        cnWeather.put("w45_1.png", "晴");
        cnWeather.put("w45_9.png", "夜晚大雪");
        cnWeather.put("w45_10.png", "小雪");
        cnWeather.put("w45_11.png", "风");
        cnWeather.put("w45_12.png", "白天小雪");
        cnWeather.put("w45_13.png", "霾");

        enWeather.put("w45.png", "Nothing");
        enWeather.put("w45_1.png", "Clear Day");
        enWeather.put("w45_2.png", "Cloudy");
        enWeather.put("w45_3.png", "Cloudy at night");
        enWeather.put("w45_4.png", "Heavy rain at daytime");
        enWeather.put("w45_5.png", "Heavy rain at night");
        enWeather.put("w45_6.png", "Heavy rain");
        enWeather.put("w45_7.png", "Freezing rain");
        enWeather.put("w45_8.png", "Heavy snow");
        enWeather.put("w45_9.png", "Heavy snow at night");
        enWeather.put("w45_10.png", "Snow");
        enWeather.put("w45_11.png", "Windy");
        enWeather.put("w45_12.png", "Snow at daytime");
        enWeather.put("w45_13.png", "Haze");
    }


    // 解析天气描述查找对应图片路径
    public static String findImagePath(String s) {
        String imagePath = "";
        if (s.contains("haze") || s.contains("smog") || s.contains("霾")) {
            imagePath = "w45_13.png";
        } else if (s.contains("rain") || s.contains("雨") || s.contains("shower")) {  //
            if (s.contains("阴") || s.contains("云") || s.contains("cloudy")) {
                imagePath = "w45_5.png";
            } else if (s.contains("晴") || s.contains("sunny") || s.contains("clear")) {
                imagePath = "w45_4.png";
            } else if (s.contains("雪") || s.contains("snow")) {
                imagePath = "w45_7.png";
            } else {
                imagePath = "w45_6.png";
            }
        } else if (s.contains("雪") || s.contains("snow")) {
            if (s.contains("阴") || s.contains("云") || s.contains("cloudy")) {
                imagePath = "w45_9.png";
            } else if (s.contains("晴") || s.contains("sunny") || s.contains("clear")) {
                imagePath = "w45_12.png";
            } else {
                imagePath = "w45_8.png";
            }
        } else if (s.contains("晴") || s.contains("sunny") || s.contains("clear")) {
            if (s.contains("阴") || s.contains("云") || s.contains("cloudy")) {
                imagePath = "w45_2.png";
            } else {
                imagePath = "w45_1.png";
            }
        } else if (s.contains("阴") || s.contains("云") || s.contains("cloudy")) {
            imagePath = "w45_3.png";
        } else if (s.contains("沙") || s.contains("sand")) {
            imagePath = "w45_11.png";
        } else if (s.contains("冰雹") || s.contains("hail")) {
            imagePath = "w45_10.png";
        } else {
            imagePath = "w45.png";
        }
        return imagePath;
    }

    public static String getWeatherType(String imgPath, String lang) {
        if ("zh".equals(lang)) {
            return cnWeather.get(imgPath);
        }
        return enWeather.get(imgPath);
    }

    public static String getWindZh(String windEn) {
        String windZh;
        if (windEn.contains("slight")) {
            windZh = "微风";
        } else if (windEn.contains("S.wind")) {
            windZh = "大风";
        } else if (windEn.contains("C.wind")) {
            windZh = "寒风";
        } else {
            windZh = "小风";
        }
        return windZh;
    }
}
