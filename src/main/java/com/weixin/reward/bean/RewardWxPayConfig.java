//package com.weixin.reward.bean;
//
//import com.github.wxpay.sdk.IWXPayDomain;
//import com.github.wxpay.sdk.WXPayConfig;
//import com.github.wxpay.sdk.WXPayConstants;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//public class RewardWxPayConfig extends WXPayConfig {
//    private byte[] certData;
//
//    public RewardWxPayConfig() throws Exception {
//        String certPath = "classpath:cert/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
//    }
//
//
//    @Override
//    public String getAppID() {
//        return "wxa7650780ab7edbbf";
//    }
//
//    @Override
//    public String getMchID() {
//        return "1547881691";
//    }
//
//    @Override
//    public String getKey() {
//        return "19565cjhgkr526opy5879yrfgt002134";
//    }
//
//    @Override
//    public InputStream getCertStream() {
//        return new ByteArrayInputStream(this.certData);
//    }
//
//    @Override
//    public IWXPayDomain getWXPayDomain() {// 这个方法需要这样实现, 否则无法正常初始化WXPay
//        // 这个方法需要这样实现, 否则无法正常初始化WXPay
//        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
//
//            public void report(String domain, long elapsedTimeMillis, Exception ex) {
//
//            }
//
//            public DomainInfo getDomain(WXPayConfig config) {
//                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
//            }
//        };
//        return iwxPayDomain;
//
//    }
//
//}
