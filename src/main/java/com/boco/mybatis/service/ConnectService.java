package com.boco.mybatis.service;

import com.boco.entity.ConnectEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface ConnectService extends BaseService<ConnectEntity> {
    public List<ConnectEntity> findByType(String type);
}
