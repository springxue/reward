package com.weixin.reward.controller;

import com.weixin.reward.bean.Setting;
import com.weixin.reward.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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
    public Map setSerial(@RequestParam String serial) {
        Map result=new HashMap();

        try{
            settingService.setSerial(serial);

        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("data",serial);
        result.put("msg","success");
        return result;
    }
    @RequestMapping("/setSetting")
    @ResponseBody
    public Map setReward(@RequestBody Setting rewardSetting) {
        Map result=new HashMap();
        try{
            System.out.println(rewardSetting);
            settingService.setSetting(rewardSetting);
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("data",rewardSetting);
        result.put("msg","success");
        return result;
    }

    @RequestMapping("/getSetting")
    @ResponseBody
    public Map getReward() {
        Map result=new HashMap();
        Setting setting=new Setting();
        try{
          setting=settingService.getSetting();
            System.out.println("+++++++++++");
            System.out.println(setting);
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","error");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            result.put("exception",str);
            return result;
        }
        result.put("data",setting);
        result.put("msg","success");
        return result;
    }
}
