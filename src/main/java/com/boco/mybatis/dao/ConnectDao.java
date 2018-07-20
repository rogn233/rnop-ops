package com.boco.mybatis.dao;

import com.boco.entity.ConnectEntity;
import com.boco.entity.DalDataDetail;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface ConnectDao extends BaseDao<ConnectEntity>{
    public List<ConnectEntity> findByType(String type);
}
