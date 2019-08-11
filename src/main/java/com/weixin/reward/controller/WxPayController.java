package com.weixin.reward.controller;


import com.weixin.reward.bean.Transfers;
import com.weixin.reward.bean.WxPrePayParam;
//import com.weixin.reward.service.WxPayService;
import com.weixin.reward.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
 public String getPrePayNotify(HttpServletRequest request,HttpServletResponse response){
        System.out.println("========这是回调页面========");
        System.out.println(request);
        return null;
    }


    @RequestMapping("/payToPerson")
    @ResponseBody
    public Map payToPerson(@RequestBody Transfers transfers){
//        wxPayService.payToPerson(transfers);
        return null;
    }
}

