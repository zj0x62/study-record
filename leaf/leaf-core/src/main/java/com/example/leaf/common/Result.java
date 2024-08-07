package com.example.leaf.common;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 9:24
 * @Description:
 */
public class Result {

    private long id;

    private Status status;

    public Result() {

    }

    public Result(long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
