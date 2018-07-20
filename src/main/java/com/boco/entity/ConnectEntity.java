package com.boco.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public class ConnectEntity {
    private long id;
    private String type;
    private String ip;
    private Integer port;
    private String username;
    private String password;
    private Timestamp createTime;
    private String sign; //备注
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return this.createTime == null ? new Timestamp(System.currentTimeMillis()) : this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

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

    @Override
    public String toString() {
        return "ConnectEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", sign='" + sign + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
