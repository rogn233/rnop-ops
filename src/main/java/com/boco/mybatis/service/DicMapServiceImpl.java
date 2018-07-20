package com.boco.mybatis.service;

import com.boco.entity.DicMap;
import com.boco.entity.TableEntity;
import com.boco.mybatis.dao.DicMapDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DicMapServiceImpl implements DicMapService {
    @Resource
    private DicMapDao dao;
    @Override
    public DicMap findById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<DicMap> queryAll() {
        return dao.queryAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(DicMap entity) {
        return dao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(DicMap entity) {
        return dao.insert(entity);
    }

    @Override
    public List<DicMap> pageQuery(TableEntity tableEntity) {
        return dao.pageQuery(tableEntity);
    }

    @Override
    public int countAll() {
        return dao.countAll();
    }

    @Override
    public List<DicMap> queryByTab(String tab_name) {
        return dao.queryByTab(tab_name);
    }
}
