package com.weixin.reward.service;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.weixin.reward.bean.WxPrePayParam;
import com.weixin.reward.util.RewardWxPayUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class WxPayService {
    public Map getPrePay(WxPrePayParam wxPrePayParam, HttpServletRequest request){
        String appid= wxPrePayParam.getAppid();
        //商户号
        String mch_id= wxPrePayParam.getMch_id();
        //设备号
        String device_info=null;
        if(wxPrePayParam.getDevice_info()!=null&& wxPrePayParam.getDevice_info()!=""){
            device_info= wxPrePayParam.getDevice_info();
        }
        //生成随机字符串
        String nonce_Str=WXPayUtil.generateNonceStr();
        //设置随机字符串
        wxPrePayParam.setNonce_str(nonce_Str);
        String key="cjxcx20190808wwwcjxcxscn585657ds";
        String sign=null;
        //商品描述
        String body= wxPrePayParam.getBody();
        //构建用于生成签名的map
        Map data=new TreeMap();
        data.put("appid",appid);
        data.put("mch_id",mch_id);
        data.put("device_info",device_info);
        data.put("body",body);
        data.put("nonce_Str",nonce_Str);
        try {
            //生成签名
            sign=WXPayUtil.generateSignature(data,key, WXPayConstants.SignType.MD5);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成签名失败");
        }
        //设置签名
        wxPrePayParam.setSign(sign);
        //设置签名类型此处用md5
        wxPrePayParam.setSign_type("MD5");
        //生成用户订单号
        String out_trade_no=RewardWxPayUtils.genOut_trade_no();
        //设置商户订单号
        wxPrePayParam.setOut_trade_no(out_trade_no);
        //获取终端ip
        String spbill_create_ip=RewardWxPayUtils.getRemortIP(request);
        //设置终端ip
        wxPrePayParam.setSpbill_create_ip(spbill_create_ip);
        //设置接收与支付通知url
        wxPrePayParam.setNotify_url("http://39.97.247.11:8086/getPrePayNotify");
        //设置交易类型
        wxPrePayParam.setTrade_type("JSAPI");
        //新建一个treeMap用于存放WxPrePayParam，并用于后续转换为xml
        TreeMap prePayParamMap=new TreeMap();
        try {
            //将实体类转换为treeMap
             prePayParamMap= RewardWxPayUtils.objectTreeMap(wxPrePayParam);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("prePayParam转换TreeMap失败");
        }
        String wxPrePayParamXml="";
        try {
            wxPrePayParamXml = WXPayUtil.mapToXml(prePayParamMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClientBuilder.create().build();//构建一个Client
        HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");    //构建一个Post请求
        //设置参数
        StringEntity stringEntity=new StringEntity(wxPrePayParamXml,"utf-8");
        HttpResponse response = null;
        Map resultMap=new HashMap();
        try {
            response = client.execute(post);//提交Post请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);

            resultMap.put("msg","success");
            resultMap.put("data",content);
        } catch (Exception e) {
            resultMap.put("msg","error");
            e.printStackTrace();
            System.out.println("调用统一支付接口失败");
            return resultMap;
        }
        return resultMap;
    }

}
