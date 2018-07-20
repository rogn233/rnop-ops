package com.boco.mybatis.service;

import com.boco.entity.DalDataExp;

import java.util.List;

public interface DalDataExpService {
    public DalDataExp queryExp(DalDataExp dalDataExp);
    public List<DalDataExp> queryExpByHour(DalDataExp dalDataExp);
}
