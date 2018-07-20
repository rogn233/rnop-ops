package com.boco.entity;

public class DalDataExp {
    private Integer data_hour;
    private String datasourcename;
    private String tablename;
    private Long exp_num;

    public Integer getData_hour() {
        return data_hour;
    }

    public void setData_hour(Integer data_hour) {
        this.data_hour = data_hour;
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

    public Long getExp_num() {
        return exp_num;
    }

    public void setExp_num(Long exp_num) {
        this.exp_num = exp_num;
    }
}
