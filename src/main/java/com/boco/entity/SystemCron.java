package com.boco.entity;

import java.sql.Timestamp;

public class SystemCron {
  private long id;
  private String name;
  private String cron;
  private String job;
  private Timestamp create_time;
  /**
   * 任务状态 0禁用 1启用 2删除
   */
  private String status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }


  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Timestamp create_time) {
    this.create_time = create_time;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "SystemCron{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", cron='" + cron + '\'' +
            ", job='" + job + '\'' +
            ", create_time=" + create_time +
            ", status=" + status +
            '}';
  }
}
