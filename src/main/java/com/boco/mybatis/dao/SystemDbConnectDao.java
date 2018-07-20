package com.boco.mybatis.dao;

import com.boco.entity.ConnectEntity;
import com.boco.entity.SystemDbConnect;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface SystemDbConnectDao {
    public SystemDbConnect findById(long id);
    public List<SystemDbConnect> queryAll();
    public int deleteById(long id);
    public int updateById(SystemDbConnect connectEntity);
    public int insertConnect(SystemDbConnect connectEntity);
    public List<SystemDbConnect> pageQuery(TableEntity tableEntity);
    public int countAll();
}
