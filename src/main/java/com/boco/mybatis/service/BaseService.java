package com.boco.mybatis.service;

public interface BaseService {
    public Object findById(long id);
    public Object queryAll();
    public int deleteById(long id);
    public int updateById(Object entity);
    public int insert(Object entity);
}
