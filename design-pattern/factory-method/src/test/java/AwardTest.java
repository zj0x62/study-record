import com.alibaba.fastjson.JSON;
import com.example.demo.design.StoreFactory;
import com.example.demo.design.service.ICommodity;
import com.example.demo.normal.AwardReq;
import com.example.demo.normal.AwardResp;
import com.example.demo.normal.PrizeController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:32
 * @Description:
 */
@Slf4j
public class AwardTest {

    @Test
    public void test_awardToUserNormal() {

        PrizeController prizeController = new PrizeController();

        System.out.println("\r\n模拟发放优惠券测试\r\n");
        // 模拟发放优惠券测试
        AwardReq awardReq01 = new AwardReq();
        awardReq01.setUId("10001");
        awardReq01.setAwardType(1);
        awardReq01.setAwardNumber("EGM1023938910232121323432");
        awardReq01.setBizId("791098764902132");
        AwardResp awardResp01 = prizeController.awardToUser(awardReq01);
        log.info("请求参数：{}", JSON.toJSON(awardReq01));
        log.info("测试结果：{}", JSON.toJSON(awardResp01));
        // 模拟方法实物商品
        AwardReq awardReq02 = new AwardReq();
        awardReq02.setUId("10001");
        awardReq02.setAwardType(2);
        awardReq02.setAwardNumber("9820198721311");
        awardReq02.setBizId("1023000020112221113");
        Map<String,String> extMap = new HashMap<String,String>();
        extMap.put("consigneeUserName", "谢飞机");
        extMap.put("consigneeUserPhone", "15200292123");
        extMap.put("consigneeUserAddress", "吉林省.长春市.双阳区.XX街道.檀溪苑小区.#18-2109");
        awardReq02.setExtMap(extMap);
        AwardResp awardResp02 = prizeController.awardToUser(awardReq02);
        log.info("请求参数：{}", JSON.toJSON(awardReq02));
        log.info("测试结果：{}", JSON.toJSON(awardResp02));
        System.out.println("\r\n第三方兑换卡(爱奇艺)\r\n");
        AwardReq awardReq03 = new AwardReq();
        awardReq03.setUId("10001");
        awardReq03.setAwardType(3);
        awardReq03.setAwardNumber("AQY1xjkUodl8LO975GdfrYUio");
        AwardResp awardResp03 = prizeController.awardToUser(awardReq03);
        log.info("请求参数：{}", JSON.toJSON(awardReq03));
        log.info("测试结果：{}", JSON.toJSON(awardResp03));
    }

    @Test
    public void test_awardToUserDesign() {
        StoreFactory factory = new StoreFactory();
        // 1. 优惠券
        ICommodity commodityService1 = factory.getCommodityService(1);
        commodityService1.sendCommodity("10001", "EGM1023938910232121323432", "791098764902132", null);
        // 2. 实物商品
        ICommodity commodityService2 = factory.getCommodityService(2);
        Map<String,String> extMap = new HashMap<String,String>();
        extMap.put("consigneeUserName", "谢飞机");
        extMap.put("consigneeUserPhone", "15200292123");
        extMap.put("consigneeUserAddress", "吉林省.长春市.双阳区.XX街道.檀溪苑小区.#18-2109");
        commodityService2.sendCommodity("10001","9820198721311","1023000020112221113", extMap);
        // 3. 第三方兑换卡(爱奇艺)
        ICommodity commodityService3 = factory.getCommodityService(3);
        commodityService3.sendCommodity("10001","AQY1xjkUodl8LO975GdfrYUio",null,null);
    }
}
