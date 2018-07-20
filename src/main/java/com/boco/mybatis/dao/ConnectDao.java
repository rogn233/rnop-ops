package com.boco.mybatis.dao;

import com.boco.entity.ConnectEntity;
import com.boco.entity.DalDataDetail;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface ConnectDao {
    public ConnectEntity findById(long id);
    public List<ConnectEntity> queryAll();
    public List<ConnectEntity> findByType(String type);
    public int deleteById(long id);
    public int updateById(ConnectEntity connectEntity);
    public int insertConnect(ConnectEntity connectEntity);
    public List<ConnectEntity> pageQuery(TableEntity tableEntity);
    public int countAll();
}
