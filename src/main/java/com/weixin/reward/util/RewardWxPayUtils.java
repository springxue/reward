package com.weixin.reward.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class RewardWxPayUtils {
    /**
     *对象转treeMap
     * @param obj
     * @return
     * @throws Exception
     */
    public static TreeMap<String, Object> objectTreeMap(Object obj)
            throws Exception {

        TreeMap<String, Object> map = new TreeMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());

            fields[i].setAccessible(accessFlag);
        }

        return map;
    }

    /**
     * 获取商户订单号
     * @return
     */
    public static String genOut_trade_no(){
        StringBuffer orderSNBuffer = new StringBuffer();
        orderSNBuffer.append(System.currentTimeMillis());
        orderSNBuffer.append(getRandomString(13));
        return orderSNBuffer.toString();
    }

    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取终端ip
     * @param request
     * @return
     */
    public static String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
