package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.ResponseEntity;
import com.boco.entity.SystemMqConnect;
import com.boco.mybatis.service.SystemMqConnectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mq_connect")
public class SystemMqConnectController {
    @Resource
    private SystemMqConnectService service;

    @RequestMapping("/getAll")
    public List<SystemMqConnect> getAll(){
        return service.queryAll();
    }
    @RequestMapping("/updateConnect")
    public ResponseEntity updateConnect(SystemMqConnect connectEntity){
        ResponseEntity responseEntity = new ResponseEntity();
        int result = service.updateById(connectEntity);
        if(result > 0){
            responseEntity.setSuccess(true);
            responseEntity.setDesc("更新成功["+result+"条");
        }else{
            responseEntity.setDesc("更新失败["+result+"条");
        }
        return responseEntity;
    }
    @RequestMapping("/insertConnect")
    public ResponseEntity insertConnect(SystemMqConnect connectEntity){
        ResponseEntity responseEntity = new ResponseEntity();
        int result = service.insertConnect(connectEntity);
        if(result > 0){
            responseEntity.setSuccess(true);
            responseEntity.setDesc("插入成功["+result+"条");
        }else{
            responseEntity.setDesc("插入失败["+result+"条");
        }
        return responseEntity;
    }
    @PostMapping("/del")
    public ResponseEntity del(String dicMaps){
        ResponseEntity responseEntity = new ResponseEntity();
        List<SystemMqConnect> dics = JSONArray.parseArray(dicMaps,SystemMqConnect.class);
        try {
            for (SystemMqConnect connectEntity : dics) {
                service.deleteById(connectEntity.getId());
            }
            responseEntity.setSuccess(true);
            responseEntity.setDesc("删除成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseEntity.setDesc("删除失败");
        }
        return responseEntity;
    }
}
