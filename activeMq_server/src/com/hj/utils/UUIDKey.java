package com.hj.utils;

import java.util.Date;

/**
 * 18位生成方式，不支持多系统同时运行，单独系统可以生成
 */
public class UUIDKey {

    private final static String str = "1234567890";

    private final static int pixLen = str.length();

    private static volatile int pixOne = 0;

    private static volatile int pixTwo = 0;

    private static volatile int pixThree = 0;

    private static volatile int pixFour = 0;

    /**
     * 
     * 生成短时间内不会重复的长度为15位的字符串，主要用于数据库主键生成使用。<br/>
     * 
     * 生成策略为获取自1970年1月1日零时零分零秒至当前时间的毫秒数的16进制字符串值，该字符串值为11位<br/>
     * 
     * 并追加四位"0-9"的自增字符串.<br/>
     * 
     * 如果系统时间设置为大于<b>2304-6-27 7:00:26<b/>的时间，将会报错！<br/>
     * 
     * 由于系统返回的毫秒数与操作系统关系很大，所以本方法并不准确。本方法可以保证在系统返回的一个毫秒数内生成36的4次方个（1679616）ID不重复。<br/>
     * 
     */

    final public synchronized static String generate() {
        String dateString = DateFormatUtil.date2Str(new Date());
        pixFour++;
        if (pixFour == pixLen) {
            pixFour = 0;
            pixThree++;
            if (pixThree == pixLen) {
                pixThree = 0;
                pixTwo++;
                if (pixTwo == pixLen) {
                    pixTwo = 0;
                    pixOne++;
                    if (pixOne == pixLen) {
                        pixOne = 0;
                    }
                }
            }
        }
        String result = dateString + str.charAt(pixOne) + str.charAt(pixTwo) + str.charAt(pixThree) + str.charAt(pixFour);
        result = result.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
        R.debug("generate-name:{}", result);
        return result;
    }

}
