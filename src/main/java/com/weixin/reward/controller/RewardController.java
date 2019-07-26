package com.weixin.reward.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RewardController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello";
    }
}
