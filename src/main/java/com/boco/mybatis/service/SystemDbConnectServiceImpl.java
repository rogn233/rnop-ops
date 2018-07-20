package com.boco.mybatis.service;

import com.boco.entity.SystemDbConnect;
import com.boco.mybatis.dao.SystemDbConnectDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemDbConnectServiceImpl implements SystemDbConnectService {
    @Resource
    private SystemDbConnectDao dao;

    @Override
    public SystemDbConnect findById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<SystemDbConnect> queryAll() {
        return dao.queryAll();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(SystemDbConnect connectEntity) {
        return dao.updateById(connectEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertConnect(SystemDbConnect connectEntity) {
        return dao.insertConnect(connectEntity);
    }
}
