package com.boco.mybatis.service;

import com.boco.entity.DalDataExp;
import com.boco.entity.TableEntity;
import com.boco.mybatis.dao.DalDataExpDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DalDataExpServiceImpl implements DalDataExpService {
    @Resource
    private DalDataExpDao dao;
    @Override
    public DalDataExp queryExp(DalDataExp dalDataExp) {
        return dao.queryExp(dalDataExp);
    }

    @Override
    public List<DalDataExp> queryExpByHour(DalDataExp dalDataExp) {
        return dao.queryExpByHour(dalDataExp);
    }
}
