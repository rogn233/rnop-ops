package com.boco.mybatis.service;

import com.boco.entity.SystemCron;
import com.boco.entity.TableEntity;

import java.util.List;

public interface BaseService<E> {
    public E findById(long id);
    public List<E> queryAll();
    public int deleteById(long id);
    public int updateById(E entity);
    public int insert(E entity);
    public List<E> pageQuery(TableEntity tableEntity);
    public int countAll();
}
