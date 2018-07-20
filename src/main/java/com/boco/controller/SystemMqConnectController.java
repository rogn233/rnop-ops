package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.ResponseEntity;
import com.boco.entity.SystemMqConnect;
import com.boco.entity.TableEntity;
import com.boco.mybatis.service.SystemDbConnectService;
import com.boco.mybatis.service.SystemMqConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mq_connect")
public class SystemMqConnectController extends BaseController<SystemMqConnect> {
    private SystemMqConnectService service;

    @Autowired
    public SystemMqConnectController(SystemMqConnectService service) {
        this.service = service;
        super.setService(service);
    }

    @RequestMapping("/getAll")
    public List<SystemMqConnect> queryAll(){
        return super.queryAll();
    }
    @RequestMapping("/updateConnect")
    public ResponseEntity updateConnect(SystemMqConnect connectEntity){
        return super.update(connectEntity);
    }
    @RequestMapping("/insertConnect")
    public ResponseEntity insertConnect(SystemMqConnect connectEntity){
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
