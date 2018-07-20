package com.boco.mybatis.service;

import com.boco.entity.DalDataDetail;
import com.boco.entity.SystemCron;
import com.boco.entity.SystemMqConnect;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface SystemCronService {
    public SystemCron findById(long id);
    public List<SystemCron> queryAll();
    public int deleteById(long id);
    public int updateById(SystemCron connectEntity);
    public int insert(SystemCron connectEntity);
    public List<SystemCron> pageQuery(TableEntity tableEntity);
    public int countAll();
}
