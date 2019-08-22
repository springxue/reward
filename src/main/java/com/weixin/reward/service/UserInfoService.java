package com.weixin.reward.service;

import com.weixin.reward.bean.Res;
import com.weixin.reward.bean.UserInfo;
import com.weixin.reward.bean.UserMessage;
import com.weixin.reward.dao.UserInfoDao;
import com.weixin.reward.util.RewardWxPayUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void saveUserInfoAndUnionId(Res res) {
        System.out.println("======res======");
        System.out.println(res);
       String result= RewardWxPayUtils.getUserInfo(res.getEncryptedData(),res.getUserInfo().getSession_key(),res.getIv());
        System.out.println(result);
        JSONObject resultJson = JSONObject.fromObject(result);//把信息封装为json
       Map resultMap= RewardWxPayUtils.parseJSON2Map(resultJson);
       String unionid=String.valueOf(resultMap.get("unionId"));
      UserInfo userInfo= res.getUserInfo();
      userInfo.setOpenid(unionid);
      userInfoDao.saveUserInfo(userInfo);

//        String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa7650780ab7edbbf&secret=fcc9b852cb865e3fedd4cba028f69008";
//        String accessToken=null;
//        try {
//            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
//            HttpGet accessTokenGet = new HttpGet(accessTokenUrl);    //构建一个GET请求
//            HttpResponse response = client.execute(accessTokenGet);//提交GET请求
//            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
//            String content = EntityUtils.toString(result);
////            System.out.println(content);//打印返回的信息
//            JSONObject resultJson = JSONObject.fromObject(content);//把信息封装为json
//            System.out.println("=========accessToken=======");
//            System.out.println(resultJson);
//            //把信息封装到map
//            Map accessTokenMap=new HashMap();
//
//            accessTokenMap= RewardWxPayUtils.parseJSON2Map(resultJson);//这个小工具的代码在下面
//            accessToken=String.valueOf(accessTokenMap.get("access_token"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String unionIdUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+String.valueOf(res.getUserInfo().getOpenid())+"&lang=zh_CN";
//        System.out.println(unionIdUrl);
//        String unionId=null;
//        try {
//            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
//            HttpGet unionIdGet = new HttpGet(unionIdUrl);    //构建一个GET请求
//            HttpResponse response = client.execute(unionIdGet);//提交GET请求
//            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
//            String content = EntityUtils.toString(result);
////            System.out.println(content);//打印返回的信息
//            JSONObject resultJson = JSONObject.fromObject(content);//把信息封装为json
//            System.out.println("=========unionid=======");
//            System.out.println(resultJson);
            //把信息封装到map
//            Map accessTokenMap=new HashMap();

//            accessTokenMap= parseJSON2Map(res);//这个小工具的代码在下面
//            unionId=String.valueOf(accessTokenMap.get("unionid"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
