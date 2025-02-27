package com.example.demo;


import com.example.demo.impl.CheckState;
import com.example.demo.impl.CloseState;
import com.example.demo.impl.DoingState;
import com.example.demo.impl.EditingState;
import com.example.demo.impl.OpenState;
import com.example.demo.impl.PassState;
import com.example.demo.impl.RefuseState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateHandler {

    private Map<Enum<Status>, State> stateMap = new ConcurrentHashMap<Enum<Status>, State>();

    public StateHandler() {
        stateMap.put(Status.Check, new CheckState());     // 待审核
        stateMap.put(Status.Close, new CloseState());     // 已关闭
        stateMap.put(Status.Doing, new DoingState());     // 活动中
        stateMap.put(Status.Editing, new EditingState()); // 编辑中
        stateMap.put(Status.Open, new OpenState());       // 已开启
        stateMap.put(Status.Pass, new PassState());       // 审核通过
        stateMap.put(Status.Refuse, new RefuseState());   // 审核拒绝
    }

    /** 活动提审 */
    public Result arraignment(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).arraignment(activityId, currentStatus);
    }

    /** 审核通过 */
    public Result checkPass(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).checkPass(activityId, currentStatus);
    }

    /** 审核拒绝 */
    public Result checkRefuse(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).checkRefuse(activityId, currentStatus);
    }

    /** 撤审撤销 */
    public Result checkRevoke(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).checkRevoke(activityId, currentStatus);
    }

    /** 活动关闭 */
    public Result close(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).close(activityId, currentStatus);
    }

    /** 活动开启 */
    public Result open(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).open(activityId, currentStatus);
    }

    /** 活动执行 */
    public Result doing(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).doing(activityId, currentStatus);
    }
    
}
