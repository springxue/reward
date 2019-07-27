package com.weixin.reward.controller;

import com.weixin.reward.bean.UserInfo;
import com.weixin.reward.bean.UserMessage;
import com.weixin.reward.service.RewardService;
import com.weixin.reward.service.UserInfoService;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class RewardController {
    @Autowired
    RewardService rewardService;
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/getOpenId")
    @ResponseBody
    //获取openid
    public Map getOpenid(@RequestParam String code){
        return rewardService.getOpenid(code);
    }

    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public String saveUserInfo(@RequestBody UserInfo userInfo){
        try {
            userInfoService.saveUserInfo(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam String openid){
        return userInfoService.getUserInfoByOpenid(openid);
    }

    @RequestMapping("/addUserMessage")
    @ResponseBody
    public String addUserMessage(@RequestBody UserMessage userMessage){
        try{
            userInfoService.addUserMessage(userMessage);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/getUserMessageListByOpenid")
    @ResponseBody
    public List<UserMessage> getUserMessageListByOpenid(@RequestParam String openid){
       return userInfoService.getUserMessageListByOpenid(openid);
    }
}
