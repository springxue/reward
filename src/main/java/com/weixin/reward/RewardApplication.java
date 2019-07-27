package com.weixin.reward;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weixin.reward.dao")
public class RewardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RewardApplication.class, args);
    }

}
