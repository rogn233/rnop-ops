package com.boco.mybatis.dao;

import com.boco.entity.DalDataExp;

import java.util.List;

public interface DalDataExpDao{
    public DalDataExp queryExp(DalDataExp dalDataExp);
    public List<DalDataExp> queryExpByHour(DalDataExp dalDataExp);
}
