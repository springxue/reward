package com.weixin.reward.controller;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.ssl.SSLSocketFactoryBuilder;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.weixin.reward.bean.Transfers;
import com.weixin.reward.bean.WxPrePayParam;
//import com.weixin.reward.service.WxPayService;
import com.weixin.reward.service.WxPayService;
import com.weixin.reward.util.RewardWxPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class WxPayController {


    @Autowired
    WxPayService wxPayService;

    /**
     * 预支付接口
     * @param wxPrePayParam
     * @return
     */
    @RequestMapping("/getPrePay")
    @ResponseBody
    public Map getPrePay(@RequestBody WxPrePayParam wxPrePayParam){

        Map result=new HashMap();
        Map returnData =new HashMap();
        try {
            returnData=wxPayService.doWxPrePay(wxPrePayParam);
           returnData.replace("prepay_id","prepay_id="+returnData.get("prepay_id"));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
        }
        result.put("msg","success");
        result.put("data",returnData);
        return result;
    }
    @RequestMapping(value = "/getPrePayNotify")
 public String getPrePayNotify(HttpServletRequest request, HttpServletResponse response){
        System.out.println("========这是回调页面========");
        System.out.println(request);
        return null;
    }


    @RequestMapping("/payToPerson")
    @ResponseBody
    public Map payToPerson(@RequestBody Transfers transfers){
        Map result=new HashMap();
        Map returnData =new HashMap();

        try {
          returnData= wxPayService.payToPerson(transfers);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
        }
        result.put("msg","success");
        result.put("data",returnData);
        return returnData;
    }
    @RequestMapping("/transfer")
    @ResponseBody
    public Map transfer(@RequestBody Transfers transfers) throws Exception{
        Map resultMap = new TreeMap();
        String result = "";

            Map<String, String> parm = new TreeMap<String, String>();
            parm.put("mch_appid", "wxa7650780ab7edbbf"); //公众账号appid
            parm.put("mchid", "1547881691"); //商户号
            parm.put("nonce_str", WXPayUtil.generateNonceStr().toUpperCase()); //随机字符串
            parm.put("partner_trade_no", RewardWxPayUtils.genOut_trade_no()); //商户订单号
            parm.put("openid", transfers.getOpenid()); //用户openid
            parm.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK
            //parm.put("re_user_name", "安迪"); //check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
            parm.put("amount", String.valueOf(transfers.getAmount())); //转账金额
            parm.put("desc", transfers.getDesc()); //企业付款描述信息
            parm.put("spbill_create_ip", RewardWxPayUtils.getLocalIp()); //服务器Ip地址
            System.out.println(parm);
            String sign=WXPayUtil.generateSignature(parm,"19565cjhgkr526opy5879yrfgt002134", WXPayConstants.SignType.MD5);
            System.out.println(sign);
            parm.put("sign",sign );

            String paramXml = WXPayUtil.mapToXml(parm);
        //加载证书
        KeyStore clientStore = null;
        try {
            clientStore = KeyStore.getInstance("PKCS12");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        File file = new File("apiclient_cert.p12");
        InputStream certStream=null;
        try {
             certStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            clientStore.load(certStream, "1547881691".toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(clientStore, "1547881691".toCharArray());
        KeyManager[]keyManagers=kmf.getKeyManagers();
        //发送post请求

        String responseXml=  HttpRequest.post("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers")
                .setSSLSocketFactory(SSLSocketFactoryBuilder
                        .create()
                        .setProtocol(SSLSocketFactoryBuilder.TLSv1)
                        .setKeyManagers(keyManagers)
                        .setSecureRandom(new SecureRandom())
                        .build()
                )
                .body(paramXml)
                .execute()
                .body();
        return WXPayUtil.xmlToMap(responseXml);
    }
}

