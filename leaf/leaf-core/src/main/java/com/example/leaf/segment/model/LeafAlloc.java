package com.example.leaf.segment.model;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 9:58
 * @Description:
 */
public class LeafAlloc {

    private String key;

    private long maxId;

    private int step;

    private String updateTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
