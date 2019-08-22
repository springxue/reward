package com.weixin.reward.service;

import com.github.wxpay.sdk.*;


import com.weixin.reward.bean.Transfers;
import com.weixin.reward.bean.WxPrePayParam;
import com.weixin.reward.util.RewardWxPayUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class WxPayService {
    public Map doWxPrePay(WxPrePayParam wxPrePayParam) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(wxPrePayParam);
        Map<String, String> requestDataMap = new TreeMap<>();
        requestDataMap.put("appid", "wxa7650780ab7edbbf");
        requestDataMap.put("body", wxPrePayParam.getBody());
        requestDataMap.put("mch_id", "1547881691");
        String nonce_str = WXPayUtil.generateNonceStr().toUpperCase();
        requestDataMap.put("nonce_str", nonce_str);
        requestDataMap.put("notify_url", "http://localhost:8086/reward/getPrePayNotify");
        requestDataMap.put("openid", wxPrePayParam.getOpenid());
        String out_trade_no = RewardWxPayUtils.genOut_trade_no();
        requestDataMap.put("out_trade_no", out_trade_no);
        InetAddress localhost = null;
        String hostAddress = "";
        try {
            localhost = InetAddress.getLocalHost();
            hostAddress = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        requestDataMap.put("spbill_create_ip", hostAddress);
        requestDataMap.put("sign_type", "MD5");
        Date timeStart = new Date();
        String time_start = simpleDateFormat.format(timeStart);
        requestDataMap.put("time_start", time_start);
        requestDataMap.put("total_fee", wxPrePayParam.getTotal_fee());


        requestDataMap.put("trade_type", "JSAPI");
        String sign = "";
        try {
//            sign=  WXPayUtil.generateSignature(requestDataMap,"cjxcx20190808wwwcjxcxscn585657ds", WXPayConstants.SignType.MD5);
            System.out.println(requestDataMap);

            sign = WXPayUtil.generateSignature(requestDataMap, "19565cjhgkr526opy5879yrfgt002134", WXPayConstants.SignType.MD5);
//            sign=  WXPayUtil.generateSignature(requestDataMap,"xuezhenchunbaishihui123456789011", WXPayConstants.SignType.MD5);
        } catch (Exception e) {

            e.printStackTrace();
        }

        requestDataMap.put("sign", sign);
        String requestPayMxl = "";
        try {
            requestPayMxl = WXPayUtil.generateSignedXml(requestDataMap, "cjxcx12090810hgyrdgk54ohde8sxm85", WXPayConstants.SignType.MD5);
//          requestPayMxl=  WXPayUtil.mapToXml(requestDataMap);
            System.out.println(requestPayMxl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map responseMap = new HashMap();
        String responseDataXml = RewardWxPayUtils.doPostByXml("https://api.mch.weixin.qq.com/pay/unifiedorder", requestPayMxl);
        System.out.println(responseDataXml);
        try {
            responseMap = WXPayUtil.xmlToMap(responseDataXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //===========================二次签名=====================
//        responseMap.put("timeStamp",String.valueOf(timeStart.getTime()/1000));
//        Map secondSign=new TreeMap();
//        secondSign.put("appId",wxPrePayParam.getAppid());
//        secondSign.put("partnerid",wxPrePayParam.getMch_id());
//        secondSign.put("prepayid",responseMap.get("prepay_id"));
//        secondSign.put("noncestr",WXPayUtil.generateNonceStr());
//        secondSign.put("timeStamp",String.valueOf(new Date().getTime()/1000));
//        secondSign.put("package","Sign=WXPay");
//        String sign2="";
//        try {
//            sign2=WXPayUtil.generateSignature(secondSign,"19565cjhgkr526opy5879yrfgt002134", WXPayConstants.SignType.MD5);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        secondSign.put("sign",sign2);
//        return secondSign;
        return responseMap;
    }

    public Map payToPerson(Transfers transfers) {
        Map resultMap = new TreeMap();
        String result = "";
        try {
            Map<String, String> parm = new TreeMap<String, String>();
            parm.put("mch_appid", "wxa7650780ab7edbbf"); //公众账号appid
            parm.put("mchid", "1547881691"); //商户号
            parm.put("nonce_str", WXPayUtil.generateNonceStr().toUpperCase()); //随机字符串
            parm.put("partner_trade_no", RewardWxPayUtils.genOut_trade_no()); //商户订单号
            parm.put("openid", transfers.getOpenid()); //用户openid
            parm.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK
            //parm.put("re_user_name", "安迪"); //check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
            parm.put("amount", String.valueOf(transfers.getAmount())); //转账金额
            parm.put("desc", transfers.getDesc()); //企业付款描述信息
            parm.put("spbill_create_ip", RewardWxPayUtils.getLocalIp()); //服务器Ip地址
            System.out.println(parm);
            String sign=WXPayUtil.generateSignature(parm,"19565cjhgkr526opy5879yrfgt002134", WXPayConstants.SignType.MD5);
            System.out.println(sign);
            parm.put("sign",sign );

            String paramXml = WXPayUtil.mapToXml(parm);
            System.out.println(paramXml);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            System.out.println(keyStore.getProvider());

            keyStore.load(new FileInputStream(new File("C:\\project\\reward\\src\\main\\resources\\apiclient_cert.p12")), "1547881691".toCharArray());
            SSLContext sslcontext = SSLContexts.custom()
                    //忽略掉对服务器端证书的校验
                    .loadTrustMaterial(new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    })

                    //加载服务端提供的truststore(如果服务器提供truststore的话就不用忽略对服务器端证书的校验了)
//                    .loadTrustMaterial(new File("D:\\truststore.jks"), "123456".toCharArray(),
//                            new TrustSelfSignedStrategy())
//                    .loadKeyMaterial(keyStore, "1547881691".toCharArray())
                    .build();
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .build();

            try {

                HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers");
                System.out.println("Executing request " + httpPost.getRequestLine());
                httpPost.setEntity(new StringEntity(paramXml, "UTF-8"));
                //添加http头信息
                httpPost.setHeader("Content-Type", "text/xml");
                CloseableHttpResponse response = httpclient.execute(httpPost);

                try {
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity, "UTF-8");
                    System.out.println(response.getStatusLine());
//                  System.out.println(IOUtils.toString(entity.getContent()));
                    EntityUtils.consume(entity);
                    System.out.println("=======企业付款结果==========");
                    System.out.println(result);
                    try {
                        resultMap = WXPayUtil.xmlToMap(result);
                        System.out.println("=============resultMap=============");
                        System.out.println(resultMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
//            WXPayRequest wxPayRequest = new WXPayRequest(new RewardWxPayConfig());
//            wxPayRequest.requestWithCert()
//            restmap = XmlUtil.xmlParse(restxml);
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//        }
//
//        if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
//            LOG.info("转账成功：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
//            Map<String, String> transferMap = new HashMap<>();
//            transferMap.put("partner_trade_no", restmap.get("partner_trade_no"));//商户转账订单号
//            transferMap.put("payment_no", restmap.get("payment_no")); //微信订单号
//            transferMap.put("payment_time", restmap.get("payment_time")); //微信支付成功时间
//            WebUtil.response(response,
//                    WebUtil.packJsonp(callback,
//                            JSON.toJSONString(new JsonResult(1, "转账成功", new ResponseData(null, transferMap)),
//                                    SerializerFeatureUtil.FEATURES)));
//        }else {
//            if (CollectionUtil.isNotEmpty(restmap)) {
//                LOG.info("转账失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
//            }
//            WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//                    .toJSONString(new JsonResult(-1, "转账失败", new ResponseData()), SerializerFeatureUtil.FEATURES)));
//        }
//      return null;
//    }
//}
