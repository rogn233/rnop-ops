package com.boco.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public class BTableEntity {
    private boolean rel = false;
    private String msg = "获取失败";
    private Object list;
    private Integer count;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Integer getCount() {
        List list = (List) getList();
        return list != null ? list.size() : count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
