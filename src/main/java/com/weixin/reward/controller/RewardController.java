package com.weixin.reward.controller;

import com.weixin.reward.service.RewardService;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RewardController {
    @Autowired
    RewardService rewardService;
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello";
    }

    @RequestMapping("/getOpenId")
    @ResponseBody
    //获取openid
    public  Map<String, Object> getWxUserOpenid() {
        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append("wx1847a9e0c7ea3036");
        url.append("&secret=");//secret设置
        url.append("8e547ebf6d6e741d46171ac77ca44474");
        url.append("&js_code=");//code设置
        url.append("ca44474");
        url.append("&grant_type=authorization_code");
        Map<String, Object> map = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            System.out.println(content);//打印返回的信息
            JSONObject res = JSONObject.fromObject(content);//把信息封装为json
            System.out.println(res);
            //把信息封装到map
            map = rewardService.parseJSON2Map(res);//这个小工具的代码在下面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
