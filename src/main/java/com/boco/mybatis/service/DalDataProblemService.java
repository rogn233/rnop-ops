package com.boco.mybatis.service;

import com.boco.entity.DalDataDetail;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface DalDataProblemService extends BaseService<DalDataDetail> {
    public int insertBatch(List<DalDataDetail> dalDataDetails);
}
