package com.weixin.reward.controller;

import com.weixin.reward.bean.Setting;
import com.weixin.reward.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SettingController {
    @Autowired
    SettingService settingService;
    @RequestMapping("/getSerial")
    @ResponseBody
    public String getSerial() {
      return settingService.getSerial();
    }

    @RequestMapping("/setSerial")
    @ResponseBody
    public String setSerial(@RequestParam String serial) {
        try{
            settingService.setSerial(serial);

        }catch (Exception e){
            return "error";
        }
        return "success";
    }
    @RequestMapping("/setSetting")
    @ResponseBody
    public String setReward(@RequestBody Setting rewardSetting) {
        try{
            System.out.println(rewardSetting);
//            settingService.setSetting(rewardSetting);
        }catch (Exception e){
            return "error";
        }
        return "success";
    }

    @RequestMapping("/getSetting")
    @ResponseBody
    public Setting getReward() {
        return settingService.getSetting();

    }
}
