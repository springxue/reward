//package com.weixin.reward.util;
//
//import com.github.wxpay.sdk.IWXPayDomain;
//import com.github.wxpay.sdk.WXPayConfig;
//import com.github.wxpay.sdk.WXPayConstants;
//
//import org.apache.commons.io.IOUtils;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//@Service
//public class IWxPayConfig extends WXPayConfig { // 继承sdk WXPayConfig 实现sdk中部分抽象方法
//
//    private byte[] certData;
//
////    @Value("${vendor.wx.config.app_id}")
//    private String app_id="wxa7650780ab7edbbf";
//
////    @Value("${vendor.wx.pay.key}")
//    private String wx_pay_key="fcc9b852cb865e3fedd4cba028f69008";
//
////    @Value("${vendor.wx.pay.mch_id}")
//    private String wx_pay_mch_id="1547881691";
//
//    public IWxPayConfig() throws Exception { // 构造方法读取证书, 通过getCertStream 可以使sdk获取到证书
//        InputStream certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("cert/apiclient_cert.p12");
//        this.certData = IOUtils.toByteArray(certStream);
//        certStream.close();
//    }
//
//    @Override
//    public String getAppID() {
//        return app_id;
//    }
//
//    @Override
//    public String getMchID() {
//        return wx_pay_mch_id;
//    }
//
//    @Override
//    public String getKey() {
//        return wx_pay_key;
//    }
//
//    @Override
//    public InputStream getCertStream() {
//        return new ByteArrayInputStream(this.certData);
//    }
//
//    @Override
//    public IWXPayDomain getWXPayDomain() { // 这个方法需要这样实现, 否则无法正常初始化WXPay
//        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
//            @Override
//            public void report(String domain, long elapsedTimeMillis, Exception ex) {
//
//            }
//            @Override
//            public IWXPayDomain.DomainInfo getDomain(WXPayConfig config) {
//                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
//            }
//        };
//        return iwxPayDomain;
//    }
//}
