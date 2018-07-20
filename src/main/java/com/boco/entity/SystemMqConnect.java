package com.boco.entity;


public class SystemMqConnect {

  private long id;
  private String name;
  private String hostname;
  private String channel;
  private long port;
  private String ccsid;
  private String user;
  private String pass;
  private String qmgr;
  private String qname;


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


  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }


  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }


  public long getPort() {
    return port;
  }

  public void setPort(long port) {
    this.port = port;
  }


  public String getCcsid() {
    return ccsid;
  }

  public void setCcsid(String ccsid) {
    this.ccsid = ccsid;
  }


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }


  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }


  public String getQmgr() {
    return qmgr;
  }

  public void setQmgr(String qmgr) {
    this.qmgr = qmgr;
  }


  public String getQname() {
    return qname;
  }

  public void setQname(String qname) {
    this.qname = qname;
  }

}
