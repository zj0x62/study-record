package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 15:47
 * @Description:
 */
public class Link {

    private String fromId; // 雇员ID
    private String toId;   // 雇员ID

    public Link(String fromId, String toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}
