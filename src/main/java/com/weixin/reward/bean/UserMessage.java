package com.weixin.reward.bean;

public class UserMessage {
    private String openid;
    private String nickName;
    private String content;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "openid='" + openid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
