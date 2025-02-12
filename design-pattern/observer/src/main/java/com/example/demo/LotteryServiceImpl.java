package com.example.demo;

import java.util.Date;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 13:11
 * @Description:
 */
public class LotteryServiceImpl extends LotteryService {

    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    protected LotteryResult doDraw(String uid) {
        String lottery = minibusTargetService.lottery(uid);
        return new LotteryResult(uid, lottery, new Date());
    }
}
