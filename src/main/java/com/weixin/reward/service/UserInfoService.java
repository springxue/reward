package com.weixin.reward.service;

import com.weixin.reward.bean.UserInfo;
import com.weixin.reward.bean.UserMessage;
import com.weixin.reward.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;
    public void saveUserInfo(UserInfo userInfo){
        System.out.println(userInfo);
        if(userInfoDao.getUserInfoByOpenid(userInfo.getOpenid())==null){
            userInfoDao.saveUserInfo(userInfo);
        }else {
            userInfoDao.updateUserInfo(userInfo);
        }
    }
    public UserInfo getUserInfoByOpenid(String openid){
     return userInfoDao.getUserInfoByOpenid(openid);
    }
    public void addUserMessage(UserMessage userMessage){
        userMessage.setCreateTime(new Date());
        userInfoDao.addUserMessage(userMessage);
    }
    public List<UserMessage> getUserMessageListByOpenid(String openid){
        return userInfoDao.getUserMessageListByOpenid(openid);
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoDao.updateUserInfo(userInfo);
    }
}
