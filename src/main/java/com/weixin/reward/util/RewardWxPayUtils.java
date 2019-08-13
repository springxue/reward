package com.weixin.reward.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.ssl.SSLSocketFactoryBuilder;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class RewardWxPayUtils {
    /**
     *对象转treeMap
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, String> objectToMap(Object obj)
            throws Exception {

        Map<String, String> map = new HashMap<String, String>();
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




    public static String converToMD5(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String doPostByXml(String url,String requestDataXml){
        CloseableHttpClient closeableHttpClient=null;
        CloseableHttpResponse closeableHttpResponse=null;
//        创建HttpClient链接对象
        closeableHttpClient= HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        //创建链接请求参数对象，并设置连接参数
        RequestConfig requestConfig=RequestConfig.custom()
                .setConnectTimeout(15000)
                .setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000)
                .build();
        //为httpPost请求设置参数
        httpPost.setConfig(requestConfig);
        //将上传参数保存到entity属性中
        try {
            httpPost.setEntity(new StringEntity(requestDataXml,"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加http头信息
        httpPost.setHeader("Content-Type","text/xml");
        //发送请求
        String result="";
        try {
           closeableHttpResponse= closeableHttpClient.execute(httpPost);
           //从响应对象中获取返回内容
            HttpEntity httpEntity=closeableHttpResponse.getEntity();
            result= EntityUtils.toString(httpEntity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取本机ip
     * @return
     */
    public static String getLocalIp(){
        InetAddress localhost = null;
        String hostAddress = "";
        try {
            localhost = InetAddress.getLocalHost();
            hostAddress = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
       return hostAddress;
    }

//    public  String doPostSSL(String url, Map<String, String> params, InputStream certFile, String certPass)throws Exception {
//        String data=WXPayUtil.mapToXml(params);
//      String result=  HttpRequest.post(url)
//                .setSSLSocketFactory(SSLSocketFactoryBuilder
//                        .create()
//                        .setProtocol(SSLSocketFactoryBuilder.TLSv1)
//                        .setKeyManagers(getKeyManager(certPass, null, certFile))
//                        .setSecureRandom(new SecureRandom())
//                        .build()
//                )
//                .body(data)
//                .execute()
//                .body();
//
//        return HttpKit.getDelegate().post(url, WxPayKit.toXml(params), certFile, certPass);
//    }
//    public KeyManager[] getKeyManager(String certPass, String certPath, InputStream certFile) throws Exception {
//        KeyStore clientStore = KeyStore.getInstance("PKCS12");
//        if (certFile != null) {
//            clientStore.load(certFile, certPass.toCharArray());
//        } else {
//            clientStore.load(new FileInputStream(certPath), certPass.toCharArray());
//        }
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        kmf.init(clientStore, certPass.toCharArray());
//        return kmf.getKeyManagers();
//    }
}
