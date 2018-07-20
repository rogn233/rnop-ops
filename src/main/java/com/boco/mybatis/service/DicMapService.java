package com.boco.mybatis.service;

import com.boco.entity.DicMap;

import java.util.List;

public interface DicMapService extends BaseService<DicMap>{
    public List<DicMap> queryByTab(String tab_name);
}
