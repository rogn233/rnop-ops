package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.DicMap;
import com.boco.entity.ResponseEntity;
import com.boco.entity.TableEntity;
import com.boco.mybatis.service.ConnectService;
import com.boco.mybatis.service.DicMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dic")
public class DicMapController extends BaseController<DicMap>{
    private DicMapService service;
    @Autowired
    public DicMapController(DicMapService service) {
        this.service = service;
        super.setService(service);
    }
    @PostMapping("/add")
    public ResponseEntity add(DicMap dicMap){
        return super.insert(dicMap);
    }
    @PostMapping("/edit")
    public ResponseEntity edit(DicMap dicMap){
        return super.update(dicMap);
    }
    @PostMapping("/del")
    public ResponseEntity del(String dicMaps){
        return super.del(dicMaps);
    }
    @GetMapping("/queryAll")
    public List<DicMap> queryAll(){
        return super.queryAll();
    }
    @PostMapping("/queryByTab")
    public List<DicMap> queryByTab(String tab_name){
        return service.queryByTab(tab_name);
    }

    @Override
    @PostMapping("/pageQuery")
    public TableEntity pageQuery(TableEntity table) {
        return super.pageQuery(table);
    }
}
