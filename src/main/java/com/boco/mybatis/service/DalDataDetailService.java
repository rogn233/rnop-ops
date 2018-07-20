package com.boco.mybatis.service;

import com.boco.entity.DalDataDetail;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface DalDataDetailService {
    public DalDataDetail findById(long id);
    public List<DalDataDetail> queryAll();
    public int deleteById(long id);
    public int updateById(DalDataDetail dalDataDetail);
    public int insert(DalDataDetail dalDataDetail);
    public int insertBatch(List<DalDataDetail> dalDataDetails);
    public List<DalDataDetail> pageQuery(TableEntity tableEntity);
    public int countAll();
}
