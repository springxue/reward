//package com.weixin.reward.controller;
//
//import com.weixin.reward.bean.WeiXinPrePay;
//import com.weixin.reward.util.ConfigManager;
//import com.weixin.reward.util.PayCommonUtil;
//import com.weixin.reward.util.Result;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
//@Controller
//@RequestMapping("/pay")
//public class PayController {
//
//
//    String randomString = PayCommonUtil.getRandomString(32);
//    //支付成功后的回调函数
//    public static String wxnotify = "http://com.zhuohuicalss/pay/notifyWeiXinPay";
//
//    public PayController() {
//        System.out.println("MainController构造函数");
//    }
//
//
//    /**
//     * @param totalAmount 支付金额
//     * @param description 描述
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/wxpay", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Result wxpay(HttpServletRequest request) {
//        Result result = new Result();
//        Long userId = new Long(1);//baseController.getUserId();
//
//        BigDecimal totalAmount = new BigDecimal(request.getParameter("totalPrice"));
//        String trade_no = "";
//        String description = "";
//        try {
//            trade_no = new String(request.getParameter("orderNum").getBytes("ISO-8859-1"), "UTF-8");
//            description = request.getParameter("description");
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String openId = "";
//
//        Map<String, String> map = weixinPrePay(trade_no, totalAmount, description, openId, request);
//        SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
//        //应用ID
//        finalpackage.put("appid", ConfigManager.getInstance().getConfigItem("WXAppID")/*PayCommonUtil.APPID*/);
//        //商户号
//        finalpackage.put("partnerid", ConfigManager.getInstance().getConfigItem("MCH_ID"));
//        Long time = (System.currentTimeMillis() / 1000);
//        //时间戳
//        finalpackage.put("timestamp", time.toString());
//        //随机字符串
//        finalpackage.put("noncestr", map.get("nonce_str"));
//        //预支付交易会话ID
//        finalpackage.put("prepayid", map.get("prepay_id"));
//        //扩展字段
//        finalpackage.put("package", "Sign=WXPay");
//
//        WeiXinPrePay prePay = new WeiXinPrePay();
//        prePay.setAppId(ConfigManager.getInstance().getConfigItem("WXAppID"));
//        prePay.setMchId(ConfigManager.getInstance().getConfigItem("MCH_ID"));
//        prePay.setTimeStamp(time.toString());
//        prePay.setNonceStr(map.get("nonce_str"));
//        prePay.setPrepayId(map.get("prepay_id"));
//        prePay.setSignType("MD5");
//        String sign = PayCommonUtil.createSign("UTF-8", finalpackage);
//        prePay.setPaySign(sign);
//        result.setData(prePay);
//        result.setStateCode("SUCCESS");
//        result.setDesc("微信支付加载成功");
//
//        return result;
//    }
//}
//
//
