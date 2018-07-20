package com.boco.mybatis.dao;

import com.boco.entity.DalDataDetail;

import java.util.List;

public interface DalDataProblemDao extends BaseDao<DalDataDetail> {
    public int insertBatch(List<DalDataDetail> dalDataDetails);
}
