package com.example.demo;

import com.example.demo.event.EventManager;
import com.example.demo.event.EventType;
import com.example.demo.event.listener.MQEventListener;
import com.example.demo.event.listener.MessageEventListener;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 11:27
 * @Description:
 */
public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManager(EventType.MESSAGE, EventType.MQ);
        eventManager.subscribe(EventType.MESSAGE, new MessageEventListener());
        eventManager.subscribe(EventType.MQ, new MQEventListener());
    }

    public LotteryResult draw(String uid) {
        LotteryResult lotteryResult = doDraw(uid);
        eventManager.notify(EventType.MESSAGE, lotteryResult);
        eventManager.notify(EventType.MQ, lotteryResult);
        return lotteryResult;
    }

    protected abstract LotteryResult doDraw(String uid);
}
