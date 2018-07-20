package com.boco.mybatis.service;

import com.boco.entity.ConnectEntity;
import com.boco.entity.TableEntity;
import com.boco.mybatis.dao.ConnectDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
@Service
public class ConnectServiceImpl implements ConnectService {
    @Resource
    private ConnectDao dao;

    @Override
    public ConnectEntity findById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<ConnectEntity> queryAll() {
        return dao.queryAll();
    }

    @Override
    public List<ConnectEntity> findByType(String type) {
        return dao.findByType(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(ConnectEntity connectEntity) {
        return dao.updateById(connectEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ConnectEntity entity) {
        return dao.insert(entity);
    }

    @Override
    public List<ConnectEntity> pageQuery(TableEntity tableEntity) {
        return dao.pageQuery(tableEntity);
    }

    @Override
    public int countAll() {
        return dao.countAll();
    }

}
