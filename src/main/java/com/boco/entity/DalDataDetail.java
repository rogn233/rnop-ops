package com.boco.entity;


import java.sql.Timestamp;

public class DalDataDetail {

  private long id;
  private String source;
  private String datasourcename;
  private String tablename;
  private java.sql.Timestamp start_time;
  private java.sql.Timestamp insert_time;
  private String cmd;
  private long num;
  private long exp_num;

  public long getExp_num() {
    return exp_num;
  }

  public void setExp_num(long exp_num) {
    this.exp_num = exp_num;
  }

  public Timestamp getInsert_time() {
    return insert_time;
  }

  public void setInsert_time(Timestamp insert_time) {
    this.insert_time = insert_time;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  public String getDatasourcename() {
    return datasourcename;
  }

  public void setDatasourcename(String datasourcename) {
    this.datasourcename = datasourcename;
  }


  public String getTablename() {
    return tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }


  public java.sql.Timestamp getStart_time() {
    return start_time;
  }

  public void setStart_time(java.sql.Timestamp start_time) {
    this.start_time = start_time;
  }


  public String getCmd() {
    return cmd;
  }

  public void setCmd(String cmd) {
    this.cmd = cmd;
  }


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }

  @Override
  public String toString() {
    return "DalDataDetail{" +
            "id=" + id +
            ", source='" + source + '\'' +
            ", datasourcename='" + datasourcename + '\'' +
            ", tablename='" + tablename + '\'' +
            ", start_time=" + start_time +
            ", insert_time=" + insert_time +
            ", cmd='" + cmd + '\'' +
            ", num=" + num +
            ", exp_num=" + exp_num +
            '}';
  }
}
