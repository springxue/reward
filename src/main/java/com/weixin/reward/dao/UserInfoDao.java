package com.weixin.reward.dao;

import com.weixin.reward.bean.UserInfo;
import com.weixin.reward.bean.UserMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserInfoDao {
   public void saveUserInfo(UserInfo userInfo);
   public UserInfo getUserInfoByOpenid(String openid);
   public void addUserMessage(UserMessage userMessage);
   public List<UserMessage> getUserMessageListByOpenid(@Param("openid") String openid);

   public void updateUserInfo(UserInfo userInfo);
}
