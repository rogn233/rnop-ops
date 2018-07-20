package com.boco.mybatis.dao;

import com.boco.entity.DalDataDetail;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface DalDataDetailDao extends BaseDao<DalDataDetail>{
    public int insertBatch(List<DalDataDetail> dalDataDetails);
}
