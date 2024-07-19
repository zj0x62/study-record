package com.example.leaf.common;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 9:15
 * @Description:
 */
public class CheckVO {

    private long timestamp;

    private int workID;

    public CheckVO(long timestamp, int workID) {
        this.timestamp = timestamp;
        this.workID = workID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }
}
