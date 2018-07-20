package com.boco.mybatis.service;

import com.boco.entity.SystemMqConnect;
import com.boco.mybatis.dao.SystemMqConnectDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemMqConnectServiceImpl implements SystemMqConnectService {
    @Resource
    private SystemMqConnectDao dao;
    @Override
    public SystemMqConnect findById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<SystemMqConnect> queryAll() {
        return dao.queryAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(SystemMqConnect connectEntity) {
        return dao.updateById(connectEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertConnect(SystemMqConnect connectEntity) {
        return dao.insertConnect(connectEntity);
    }
}
