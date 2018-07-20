package com.boco.mybatis.service;

import com.boco.entity.DalDataDetail;
import com.boco.entity.TableEntity;
import com.boco.mybatis.dao.DalDataDetailDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DalDataDetailServiceImpl implements DalDataDetailService {
    @Resource
    private DalDataDetailDao dao;
    @Override
    public DalDataDetail findById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<DalDataDetail> queryAll() {
        return dao.queryAll();
    }

    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    public int updateById(DalDataDetail dalDataDetail) {
        return dao.updateById(dalDataDetail);
    }

    @Override
    public int insert(DalDataDetail dalDataDetail) {
        return dao.insert(dalDataDetail);
    }

    @Override
    public int insertBatch(List<DalDataDetail> dalDataDetails) {
        return dao.insertBatch(dalDataDetails);
    }

    @Override
    public List<DalDataDetail> pageQuery(TableEntity tableEntity) {
        return dao.pageQuery(tableEntity);
    }

    @Override
    public int countAll() {
        return dao.countAll();
    }
}
