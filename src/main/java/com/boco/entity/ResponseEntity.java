package com.boco.entity;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public class ResponseEntity {
    private boolean success = false;
    private String desc;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
