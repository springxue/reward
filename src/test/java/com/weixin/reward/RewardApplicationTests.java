package com.weixin.reward;

import com.github.wxpay.sdk.WXPayUtil;
import com.weixin.reward.util.RewardWxPayUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Test
    public void getAccessTokenTest(){
        String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa7650780ab7edbbf&secret=fcc9b852cb865e3fedd4cba028f69008";
        Map map=new HashMap<>();
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet accessTokenGet = new HttpGet(accessTokenUrl);    //构建一个GET请求
            HttpResponse response = client.execute(accessTokenGet);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            System.out.println(content);//打印返回的信息
            JSONObject res = JSONObject.fromObject(content);//把信息封装为json
            System.out.println("=========accessToken=======");
            System.out.println(res);
            //把信息封装到map
//            map = parseJSON2Map(res);//这个小工具的代码在下面
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
