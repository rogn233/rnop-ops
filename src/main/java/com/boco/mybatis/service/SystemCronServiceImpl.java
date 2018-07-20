package com.boco.mybatis.service;

import com.boco.entity.SystemCron;
import com.boco.entity.TableEntity;
import com.boco.mybatis.dao.SystemCronDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemCronServiceImpl implements SystemCronService {
    @Resource
    private SystemCronDao dao;
    @Override
    public SystemCron findById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<SystemCron> queryAll() {
        return dao.queryAll();
    }

    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    public int updateById(SystemCron connectEntity) {
        return dao.updateById(connectEntity);
    }

    @Override
    public int insert(SystemCron connectEntity) {
        return dao.insert(connectEntity);
    }

    @Override
    public List<SystemCron> pageQuery(TableEntity tableEntity) {
        return dao.pageQuery(tableEntity);
    }

    @Override
    public int countAll() {
        return dao.countAll();
    }
}
