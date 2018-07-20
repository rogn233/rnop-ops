package com.boco.entity.ui;

/**
 * Created by Administrator on 2018/6/17 0017.
 */
public class FormItem {
    private String type;
    private String attrName;
    private String attrValue;
    private String label;
    private Object others;

    public FormItem(String type, String attrName, String attrValue, String label, Object others) {
        this.type = type;
        this.attrName = attrName;
        this.attrValue = attrValue;
        this.label = label;
        this.others = others;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }
}
