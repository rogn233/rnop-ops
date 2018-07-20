package com.boco.mybatis.dao;

import com.boco.entity.ConnectEntity;
import com.boco.entity.DicMap;
import com.boco.entity.TableEntity;

import java.util.List;

public interface DicMapDao{
    public DicMap findById(long id);
    public List<DicMap> queryAll();
    public int deleteById(long id);
    public int updateById(DicMap entity);
    public int insert(DicMap entity);
    public List<DicMap> queryByTab(String tab_name);
    public List<DicMap> pageQuery(TableEntity tableEntity);
    public int countAll();
}
