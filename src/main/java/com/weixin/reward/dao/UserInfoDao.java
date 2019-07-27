package com.weixin.reward.dao;

import com.weixin.reward.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
public interface UserInfoDao {
   public void saveUserInfo(UserInfo userInfo);
   public UserInfo getUserInfoByOpenid(String openid);
}
