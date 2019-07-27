package com.weixin.reward.bean;

public class RequestParam {
    private String appid;
    private String secret;
    private String code;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
