package com.example.demo.normal;

import com.alibaba.fastjson.JSON;
import com.example.demo.card.IQiYiCardService;
import com.example.demo.coupon.CouponResult;
import com.example.demo.coupon.CouponService;
import com.example.demo.goods.DeliverReq;
import com.example.demo.goods.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:21
 * @Description:
 */
public class PrizeController {

    private Logger logger = LoggerFactory.getLogger(PrizeController.class);

    public AwardResp awardToUser(AwardReq req) {
        String reqJson = JSON.toJSONString(req);
        AwardResp awardRes = null;
        try {
            logger.info("奖品发放开始{}。req:{}", req.getUId(), reqJson);
            // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
            if (req.getAwardType() == 1) {
                CouponService couponService = new CouponService();
                CouponResult couponResult = couponService.sendCoupon(req.getUId(), req.getAwardNumber(), req.getBizId());
                if ("0000".equals(couponResult.getCode())) {
                    awardRes = new AwardResp("0000", "发放成功");
                } else {
                    awardRes = new AwardResp("0001", couponResult.getInfo());
                }
            } else if (req.getAwardType() == 2) {
                GoodsService goodsService = new GoodsService();
                DeliverReq deliverReq = new DeliverReq();
                deliverReq.setUserName(queryUserName(req.getUId()));
                deliverReq.setUserPhone(queryUserPhoneNumber(req.getUId()));
                deliverReq.setSku(req.getAwardNumber());
                deliverReq.setOrderId(req.getBizId());
                deliverReq.setConsigneeUserName(req.getExtMap().get("consigneeUserName"));
                deliverReq.setConsigneeUserPhone(req.getExtMap().get("consigneeUserPhone"));
                deliverReq.setConsigneeUserAddress(req.getExtMap().get("consigneeUserAddress"));
                Boolean isSuccess = goodsService.deliverGoods(deliverReq);
                if (isSuccess) {
                    awardRes = new AwardResp("0000", "发放成功");
                } else {
                    awardRes = new AwardResp("0001", "发放失败");
                }
            } else if (req.getAwardType() == 3) {
                String bindMobileNumber = queryUserPhoneNumber(req.getUId());
                IQiYiCardService iQiYiCardService = new IQiYiCardService();
                iQiYiCardService.grantToken(bindMobileNumber, req.getAwardNumber());
                awardRes = new AwardResp("0000", "发放成功");
            }
            logger.info("奖品发放完成{}。", req.getUId());
        } catch (Exception e) {
            logger.error("奖品发放失败{}。req:{}", req.getUId(), reqJson, e);
            awardRes = new AwardResp("0001", e.getMessage());
        }

        return awardRes;
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
}
