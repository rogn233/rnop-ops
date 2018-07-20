package com.boco.mybatis.dao;

import com.boco.entity.ConnectEntity;
import com.boco.entity.DicMap;
import com.boco.entity.TableEntity;

import java.util.List;

public interface DicMapDao extends BaseDao<DicMap>{
    public List<DicMap> queryByTab(String tab_name);
}
