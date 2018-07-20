package com.boco.mybatis.service;

import com.boco.entity.SystemMqConnect;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface SystemMqConnectService {
    public SystemMqConnect findById(long id);
    public List<SystemMqConnect> queryAll();
    public int deleteById(long id);
    public int updateById(SystemMqConnect connectEntity);
    public int insertConnect(SystemMqConnect connectEntity);
}
