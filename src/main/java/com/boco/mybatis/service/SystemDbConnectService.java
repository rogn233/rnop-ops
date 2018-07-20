package com.boco.mybatis.service;

import com.boco.entity.SystemDbConnect;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface SystemDbConnectService {
    public SystemDbConnect findById(long id);
    public List<SystemDbConnect> queryAll();
    public int deleteById(long id);
    public int updateById(SystemDbConnect connectEntity);
    public int insertConnect(SystemDbConnect connectEntity);
}
