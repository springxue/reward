package com.weixin.reward;

import com.github.wxpay.sdk.WXPayUtil;
import com.weixin.reward.util.RewardWxPayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RewardApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("je;;pwpr");
    }
//    @Test
//    public void xmlTest(){
//        String xml="<xml>\n" +
//                "<appid>wxa7650780ab7edbbf</appid>\n" +
//                "<body>test</body>\n" +
//                "<mch_id>1547881691</mch_id>\n" +
//                "<nonce_str>F1A3AEEDACEB45518941718E7F9EC170</nonce_str>\n" +
//                "<notify_url>http://localhost:8086/reward/getPrePayNotify</notify_url>\n" +
//                "<openid>ooNi15B1TuvknFnIkwt1s5CgjXJw</openid>\n" +
//                "<out_trade_no>201908010125346</out_trade_no>\n" +
//                "<sign>2F1801B2685B1F37ADCEEED671405A76</sign>\n" +
//                "<sign_type>MD5</sign_type>\n" +
//                "<spbill_create_ip>192.168.50.150</spbill_create_ip>\n" +
//                "<total_fee>1</total_fee>\n" +
//                "<trade_type>JSAPI</trade_type>\n" +
//                "</xml>";
//        String resul=RewardWxPayUtils.doPostByXml("https://api.mch.weixin.qq.com/pay/unifiedorder",xml);
//        System.out.println(resul);
//    }
//
//    @Test
//    public void getTimeStamp(){
//        System.out.println(new Date().getTime()/1000);
//    }
}
