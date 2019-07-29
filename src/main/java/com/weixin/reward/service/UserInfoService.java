package com.weixin.reward.service;

import com.weixin.reward.bean.UserInfo;
import com.weixin.reward.bean.UserMessage;
import com.weixin.reward.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;
    public void saveUserInfo(UserInfo userInfo){
        userInfoDao.saveUserInfo(userInfo);
    }
    public UserInfo getUserInfoByOpenid(String openid){
     return userInfoDao.getUserInfoByOpenid(openid);
    }
    public void addUserMessage(UserMessage userMessage){
        userInfoDao.addUserMessage(userMessage);
    }
    public List<UserMessage> getUserMessageListByOpenid(String openid){
        return userInfoDao.getUserMessageListByOpenid(openid);
    }


}
