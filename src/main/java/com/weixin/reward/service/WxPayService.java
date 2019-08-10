//package com.weixin.reward.service;
//
//import com.github.wxpay.sdk.WXPay;
//import com.github.wxpay.sdk.WXPayConfig;
//import com.github.wxpay.sdk.WXPayConstants;
//import com.github.wxpay.sdk.WXPayUtil;
//import com.weixin.reward.bean.WxPrePayParam;
//import com.weixin.reward.util.IWxPayConfig;
//import com.weixin.reward.util.RewardWxPayUtils;
//import com.weixin.reward.util.WxpayParam;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.springframework.stereotype.Service;
//import sun.security.provider.MD5;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//@Service
//public class WxPayService {
//
//    public Map getPrePay(WxPrePayParam wxPrePayParam, HttpServletRequest request){
//        WXPay wxPay=null;
//        try {
//            IWxPayConfig iWxPayConfig=new IWxPayConfig();
//             wxPay = new WXPay(iWxPayConfig);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        String appid= wxPrePayParam.getAppid();
//        //商户号
//        String mch_id= wxPrePayParam.getMch_id();
//        //设备号
//        String device_info=null;
//        if(wxPrePayParam.getDevice_info()!=null&& wxPrePayParam.getDevice_info()!=""){
//            device_info= wxPrePayParam.getDevice_info();
//        }
//        //生成随机字符串
//        String nonce_Str=WXPayUtil.generateNonceStr().toUpperCase();
//        //设置随机字符串
//        wxPrePayParam.setNonce_str(nonce_Str);
//        String key="19565cjhgkr526opy5879yrfgt002134";
//        String sign=null;
//        //商品描述
//        String body= wxPrePayParam.getBody();
//        //构建用于生成签名的map
//        String stringSignTemp="appid=wxa7650780ab7edbbf&body="+body+"&device_info="+device_info+"&mch_id="+mch_id+"&nonce_str="+nonce_Str+"&key=19565cjhgkr526opy5879yrfgt002134";
//        System.out.println(stringSignTemp);
//        String    sign2=RewardWxPayUtils.converToMD5(stringSignTemp).toUpperCase();
//        Map data=new HashMap();
//        data.put("appId",appid);
//        data.put("mch_id",mch_id);
//        data.put("device_info",device_info);
//        data.put("body",body);
//        data.put("nonce_Str",nonce_Str);
////        data.put("")
//        try {
//            //生成签名
//            sign=WXPayUtil.generateSignature(RewardWxPayUtils.objectToMap(wxPrePayParam),key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("生成签名失败");
//        }
//        data.put("sign",sign);
//        String dataXml="";
//        try {
//          dataXml=WXPayUtil.mapToXml(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(dataXml);
//
//        System.out.println("sign:"+sign);
//        System.out.println("sign2:"+sign2);
//        //设置签名
//        wxPrePayParam.setSign(sign);
//        //设置签名类型此处用md5
//        wxPrePayParam.setSign_type("MD5");
//        //生成用户订单号
//        String out_trade_no=RewardWxPayUtils.genOut_trade_no();
//        //设置商户订单号
//        wxPrePayParam.setOut_trade_no(out_trade_no);
//        //获取终端ip
//        String spbill_create_ip=RewardWxPayUtils.getRemortIP(request);
//        //设置终端ip
//        wxPrePayParam.setSpbill_create_ip("39.97.247.11");
//        //设置接收与支付通知url
//        wxPrePayParam.setNotify_url("http://www.cjxcxs.cn:8086/getPrePayNotify");
//        //设置交易类型
//        wxPrePayParam.setTrade_type("JSAPI");
//        //新建一个treeMap用于存放WxPrePayParam，并用于后续转换为xml
//       Map prePayParamMap=new HashMap();
//        try {
//            //将实体类转换为treeMap
//            System.out.println("wxprepaypram========================");
//            wxPrePayParam.setOpenid("ooNi15B1TuvknFnIkwt1s5CgjXJw");
//            System.out.println(wxPrePayParam);
//
//             prePayParamMap= RewardWxPayUtils.objectToMap(wxPrePayParam);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("prePayParam转换TreeMap失败");
//        }
//        try {
//            sign=WXPayUtil.generateSignature(prePayParamMap,key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        prePayParamMap.put("sign",sign);
//        String wxPrePayParamXml="";
//        try {
//            wxPrePayParamXml = WXPayUtil.mapToXml(prePayParamMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(wxPrePayParamXml);
//        try {
//            IWxPayConfig iWxPayConfig=new IWxPayConfig();
//            wxPay=new WXPay(iWxPayConfig);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Map returnMap=new HashMap();
//        Map aaa=new TreeMap();
//        aaa.put("appid",appid);
//        aaa.put("mch_id",mch_id);
//        aaa.put("device_info",device_info);
//        aaa.put("body",body);
//        aaa.put("sign",sign);
//        aaa.put("total_fee",wxPrePayParam.getTotal_fee());
//        aaa.put("nonce_str",nonce_Str);
//        aaa.put("out_trade_no",out_trade_no);
//        aaa.put("spbill_create_ip",spbill_create_ip);
//        aaa.put("notify_url","http://39.97.247.11:8086/getPrePayNotify");
//        aaa.put("trade_type","JSAPI");
//        System.out.println("=======");
//        System.out.println(prePayParamMap);
//        try {
//          returnMap=  wxPay.unifiedOrder(prePayParamMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(returnMap);
////        HttpClient client = HttpClientBuilder.create().build();//构建一个Client
////        HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");    //构建一个Post请求
////        //设置参数
////        StringEntity stringEntity=new StringEntity(wxPrePayParamXml,"utf-8");
////        post.setEntity(stringEntity);
////        HttpResponse response = null;
////        Map resultMap=new HashMap();
////        try {
////            response = client.execute(post);//提交Post请求
////            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
////            String content = EntityUtils.toString(result);
////            System.out.println(content);
////            resultMap.put("msg","success");
////            resultMap.put("data",content);
////        } catch (Exception e) {
////            resultMap.put("msg","error");
////            e.printStackTrace();
////            System.out.println("调用统一支付接口失败");
////            return resultMap;
////        }
//        return returnMap;
//    }
//
//}
