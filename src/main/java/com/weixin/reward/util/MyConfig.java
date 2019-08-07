package com.weixin.reward.util;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {
private byte[] certData;
public MyConfig() throws Exception {
        String certPath = "classpath:apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
        }

@Override
public String getAppID() {
        return "wxa7650780ab7edbbf";
        }

@Override
public String getMchID() {
        return "fcc9b852cb865e3fedd4cba028f69008";
        }

@Override
public String getKey() {
        return "1547881691";
        }

@Override
public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
        }

@Override
public int getHttpConnectTimeoutMs() {
        return 8000;
        }

@Override
public int getHttpReadTimeoutMs() {
        return 10000;
        }

@Override
public IWXPayDomain getWXPayDomain() {
            return new IWXPayDomain() {
@Override
public void report(String domain, long elapsedTimeMillis, Exception ex) {
        }

@Override
public DomainInfo getDomain(WXPayConfig config) {
        return new DomainInfo("api.mch.weixin.qq.com", false);
        }
        };
}
}
