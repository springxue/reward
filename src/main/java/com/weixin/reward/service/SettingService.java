package com.weixin.reward.service;

import com.weixin.reward.dao.SettingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {
    @Autowired
    SettingDao settingDao;
    public String getSerial(){
        return settingDao.getSerial();
    }
}
