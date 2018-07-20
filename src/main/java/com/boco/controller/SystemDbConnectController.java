package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.ResponseEntity;
import com.boco.entity.SystemDbConnect;
import com.boco.entity.TableEntity;
import com.boco.mybatis.service.SystemCronService;
import com.boco.mybatis.service.SystemDbConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/db_connect")
public class SystemDbConnectController extends BaseController<SystemDbConnect> {
    private SystemDbConnectService service;
    @Autowired
    public SystemDbConnectController(SystemDbConnectService service) {
        this.service = service;
        super.setService(service);
    }
    @RequestMapping("/getAll")
    public List<SystemDbConnect> queryAll(){
        return super.queryAll();
    }
    @RequestMapping("/updateConnect")
    public ResponseEntity updateConnect(SystemDbConnect connectEntity){
        return super.update(connectEntity);
    }
    @RequestMapping("/insertConnect")
    public ResponseEntity insertConnect(SystemDbConnect connectEntity){
        return super.insert(connectEntity);
    }
    @PostMapping("/del")
    public ResponseEntity del(String dicMaps){
        return super.del(dicMaps);
    }
    @Override
    @PostMapping("/pageQuery")
    public TableEntity pageQuery(TableEntity table) {
        return super.pageQuery(table);
    }
}
