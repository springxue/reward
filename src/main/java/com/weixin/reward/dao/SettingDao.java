package com.weixin.reward.dao;

import com.weixin.reward.bean.Setting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface SettingDao {
    public String getSerial();

    public void setSerial(@Param("serial") String serial);

    public int getIndex();

    public void setIndex(@Param("index") int index);
    public String getReward(@Param("index")int index);

    public void setSetting(Setting setting);

    public Setting getSetting();
}
