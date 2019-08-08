package com.weixin.reward.controller;


import com.weixin.reward.bean.WxPrePayParam;
import com.weixin.reward.bean.WxPrePayRetuern;
import com.weixin.reward.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WxPayController {

    @Autowired
    WxPayService wxPayService;
    @RequestMapping("/getPrePay")
    @ResponseBody
    public Map getPrePay(@RequestBody WxPrePayParam wxPrePayParam, HttpServletRequest request){
        return  wxPayService.getPrePay(wxPrePayParam,request);
    }
    @RequestMapping(value = "/getPrePayNotify", headers = {"content-type=application/xml"})
//    @ResponseBody
    public String getPrePayNotify(@RequestBody WxPrePayRetuern wxPrePayRetuern,Model model){
        Map map= new HashMap();
        map.put("msg","success");
        map.put("data",wxPrePayRetuern);
        model.addAttribute("prePayResult",map);
        return "prePayResult";
    }

}