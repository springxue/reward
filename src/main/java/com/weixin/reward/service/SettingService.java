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

    public void setSerial(String serial) {
        settingDao.setSerial(serial);
    }

    public String getReward() {
        String serial=settingDao.getSerial();
        int num=Character.getNumericValue(serial.charAt(settingDao.getIndex()));
        int index=settingDao.getIndex();
        String reward=settingDao.getReward(num);
        if(index==7){
            settingDao.setIndex(0);
        }else {
            settingDao.setIndex(index+1);
        }
        return reward;
    }
}
