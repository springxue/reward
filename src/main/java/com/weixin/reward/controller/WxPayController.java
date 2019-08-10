package com.weixin.reward.controller;


import com.github.wxpay.sdk.WXPayUtil;
import com.weixin.reward.bean.WxPrePayParam;
import com.weixin.reward.bean.WxPrePayRetuern;
//import com.weixin.reward.service.WxPayService;
import com.weixin.reward.service.WxPrePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WxPayController {

//    @Autowired
//    WxPayService wxPayService;
    @Autowired
    WxPrePayService wxPrePayService;
    @RequestMapping("/getPrePay")
    @ResponseBody
    public Map getPrePay(@RequestBody WxPrePayParam wxPrePayParam){

        Map result=new HashMap();
        Map returnData =new HashMap();
        try {
            String resultXml=wxPrePayService.doWxPrePay(wxPrePayParam);
           returnData= WXPayUtil.xmlToMap(resultXml);
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
//    @ResponseBody
    public String getPrePayNotify(@RequestBody WxPrePayRetuern wxPrePayRetuern,Model model){
        Map map= new HashMap();
        map.put("msg","success");
        map.put("data",wxPrePayRetuern);
        model.addAttribute("prePayResult",map);
        return "prePayResult";
    }

}