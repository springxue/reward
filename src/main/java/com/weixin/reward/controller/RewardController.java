package com.weixin.reward.controller;

import com.weixin.reward.bean.Res;
import com.weixin.reward.bean.UserInfo;
import com.weixin.reward.bean.UserMessage;
import com.weixin.reward.service.RewardService;
import com.weixin.reward.service.SettingService;
import com.weixin.reward.service.UserInfoService;
import com.weixin.reward.util.RewardWxPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RewardController {
    @Autowired
    RewardService rewardService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    SettingService settingService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "启动成功";
    }


    @RequestMapping("/getOpenId")
    @ResponseBody
    //获取openid
    public Map getOpenid(@RequestParam String code,@RequestParam String encryptedData,@RequestParam String iv ) {
        System.out.println(code);
        Map openidAndSessionKey=new HashMap();
        Map result=new HashMap();
        try{
            openidAndSessionKey =rewardService.getOpenid(code);
            String session_key=String.valueOf(openidAndSessionKey.get("session_key"));
         String unionid=RewardWxPayUtils.getUserInfo(encryptedData,session_key,iv);
         openidAndSessionKey.put("unionid",unionid);
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
       result.put("data",openidAndSessionKey);
       result.put("msg","success");
        return result;
    }

    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public Map saveUserInfo(@RequestBody UserInfo userInfo) {
        Map result=new HashMap();
        try {
            userInfoService.saveUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("data",userInfoService.getUserInfoByOpenid(userInfo.getOpenid()));
        result.put("msg","success");
        return result;
    }

    @RequestMapping("/saveUserInfoAndUnionId")
    @ResponseBody
    public Map saveUserInfoAndUnionId(@RequestBody Res res) {
        Map result=new HashMap();
        try {
            userInfoService.saveUserInfoAndUnionId(res);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("data",userInfoService.getUserInfoByOpenid(res.getUserInfo().getOpenid()));
        result.put("msg","success");
        return result;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map getUserInfo(@RequestParam String openid) {
        Map result=new HashMap();
        UserInfo userInfo=new UserInfo();
        try {
            userInfo=userInfoService.getUserInfoByOpenid(openid);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("data",userInfo);
        result.put("msg","success");
        return result;
    }

    @RequestMapping("/addUserMessage")
    @ResponseBody
    public Map addUserMessage(@RequestBody UserMessage userMessage) {
        Map result=new HashMap();
        try {
            userInfoService.addUserMessage(userMessage);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("msg","success");
        result.put("data",userInfoService.getUserInfoByOpenid(userMessage.getOpenid()));
        return result;
    }

    @RequestMapping("/getUserMessageListByOpenid")
    @ResponseBody
    public Map getUserMessageListByOpenid(@RequestParam String openid) {
        List<UserMessage> userMessageList=new ArrayList<>();
        Map result=new HashMap();
        try{
            userMessageList=userInfoService.getUserMessageListByOpenid(openid);
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("data",userMessageList);
        result.put("msg","success");
        return result;
    }
    @RequestMapping("/getReward")
    @ResponseBody
    public Map getReward() {
        Map result=new HashMap();
        String reward=null;
        try {
            reward=settingService.getReward();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("msg","success");
        result.put("data",reward);
        return result;
    }
}
