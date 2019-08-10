package com.weixin.reward.service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.weixin.reward.bean.WxPrePayParam;
import com.weixin.reward.util.RewardWxPayUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.TreeMap;

@Service
public class WxPrePayService {
    public String doWxPrePay(WxPrePayParam wxPrePayParam) {
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


        requestDataMap.put("total_fee", "1");


        requestDataMap.put("trade_type", "JSAPI");
        String sign = "";
        try {
//            sign=  WXPayUtil.generateSignature(requestDataMap,"cjxcx20190808wwwcjxcxscn585657ds", WXPayConstants.SignType.MD5);

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
        String responseDataXml = RewardWxPayUtils.doPostByXml("https://api.mch.weixin.qq.com/pay/unifiedorder", requestPayMxl);
        System.out.println(responseDataXml);

        return responseDataXml;
    }
}
