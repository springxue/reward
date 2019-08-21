package com.weixin.reward.util;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
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
public static Map<String, Object> parseJSON2Map(JSONObject json) {
    Map<String, Object> map = new HashMap<String, Object>();
    // 最外层解析
    for (Object k : json.keySet()) {
        Object v = json.get(k);
        // 如果内层还是数组的话，继续解析
        if (v instanceof JSONArray) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            @SuppressWarnings("unchecked")
            Iterator<JSONObject> it = ((JSONArray) v).iterator();
            while (it.hasNext()) {
                JSONObject json2 = it.next();
                list.add(parseJSON2Map(json2));
            }
            map.put(k.toString(), list);
        } else {
            map.put(k.toString(), v);
        }
    }
    return map;
}
    /**
     * 获取信息
     */
    public  static String getUserInfo(String encryptedData,String sessionkey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
//                return JSONObject.parseObject(result);
                System.out.println("解密结果");
                System.out.println(result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
