package com.boco.entity;

import java.sql.Timestamp;

public class DicMap {
  private long id;
  private String tab_name;
  private String col_name;
  private String label;
  private String zh_name;
  private String type;
  private String attrValue;
  private String attrName;
  private Object others;
  private java.sql.Timestamp insert_time;

  public DicMap() {
  }
  public String getLabel() {
    return zh_name;
  }

  public String getAttrName() {
    return col_name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTab_name() {
    return tab_name;
  }

  public void setTab_name(String tab_name) {
    this.tab_name = tab_name;
  }

  public String getCol_name() {
    return col_name;
  }

  public void setCol_name(String col_name) {
    this.col_name = col_name;
  }

  public String getZh_name() {
    return zh_name;
  }

  public void setZh_name(String zh_name) {
    this.zh_name = zh_name;
  }

  public Timestamp getInsert_time() {
    return insert_time;
  }

  public void setInsert_time(Timestamp insert_time) {
    this.insert_time = insert_time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAttrValue() {
    return attrValue;
  }

  public void setAttrValue(String attrValue) {
    this.attrValue = attrValue;
  }

  public Object getOthers() {
    return others;
  }

  public void setOthers(Object others) {
    this.others = others;
  }

  @Override
  public String toString() {
    return "DicMap{" +
            "id=" + id +
            ", tab_name='" + tab_name + '\'' +
            ", col_name='" + col_name + '\'' +
            ", zh_name='" + zh_name + '\'' +
            ", insert_time=" + insert_time +
            '}';
  }
}
