package com.weixin.reward.util;

import com.weixin.reward.bean.WeiXinPrePay;

public class Result {
    private WeiXinPrePay data;
    private String stateCode;
    private String desc;

    public WeiXinPrePay getData() {
        return data;
    }

    public void setData(WeiXinPrePay data) {
        this.data = data;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", stateCode='" + stateCode + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
