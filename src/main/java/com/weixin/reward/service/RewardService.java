package com.weixin.reward.service;

import com.weixin.reward.util.RewardWxPayUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class RewardService {
    public  Map<String, Object> getOpenid(String code) {
        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append("wxa7650780ab7edbbf");
//        url.append("wx330525534812a0eb");

//        url.append(requestParam.getAppid());
        url.append("&secret=");//secret设置
        url.append("fcc9b852cb865e3fedd4cba028f69008");
//        url.append("a1f72d86aba9241e8e3b04235ce427e8");

//        url.append(requestParam.getSecret());
        url.append("&js_code=");//code设置
//        url.append("ca44474");
        url.append(code);
        url.append("&grant_type=authorization_code");
//        System.out.println(url.toString());
        Map<String, Object> map = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
//            System.out.println(content);//打印返回的信息
            JSONObject res = JSONObject.fromObject(content);//把信息封装为json
            System.out.println(res);
            //把信息封装到map
            map = RewardWxPayUtils.parseJSON2Map(res);//这个小工具的代码在下面
        } catch (Exception e) {
            e.printStackTrace();
        }
        String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa7650780ab7edbbf&secret=fcc9b852cb865e3fedd4cba028f69008";
        String accessToken=null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet accessTokenGet = new HttpGet(accessTokenUrl);    //构建一个GET请求
            HttpResponse response = client.execute(accessTokenGet);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
//            System.out.println(content);//打印返回的信息
            JSONObject res = JSONObject.fromObject(content);//把信息封装为json
            System.out.println("=========accessToken=======");
            System.out.println(res);
            //把信息封装到map
            Map accessTokenMap=new HashMap();

            accessTokenMap= RewardWxPayUtils.parseJSON2Map(res);//这个小工具的代码在下面
            accessToken=String.valueOf(accessTokenMap.get("access_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String unionIdUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+String.valueOf(map.get("openid"))+"&lang=zh_CN";
        System.out.println(unionIdUrl);
        String unionId=null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet unionIdGet = new HttpGet(unionIdUrl);    //构建一个GET请求
            HttpResponse response = client.execute(unionIdGet);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
//            System.out.println(content);//打印返回的信息
            JSONObject res = JSONObject.fromObject(content);//把信息封装为json
            System.out.println("=========unionid=======");
            System.out.println(res);
            //把信息封装到map
//            Map accessTokenMap=new HashMap();

//            accessTokenMap= parseJSON2Map(res);//这个小工具的代码在下面
//            unionId=String.valueOf(accessTokenMap.get("unionid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
