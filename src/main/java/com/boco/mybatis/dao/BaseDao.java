package com.boco.mybatis.dao;

import com.boco.entity.ConnectEntity;
import com.boco.entity.TableEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
public interface BaseDao<E> {
    public E findById(long id);
    public List<E> queryAll();
    public int deleteById(long id);
    public int updateById(E entity);
    public int insert(E entity);
    public List<E> pageQuery(TableEntity tableEntity);
    public int countAll();
}
