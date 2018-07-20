package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.ResponseEntity;
import com.boco.entity.SystemDbConnect;
import com.boco.mybatis.service.SystemDbConnectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/db_connect")
public class SystemDbConnectController {
    @Resource
    private SystemDbConnectService service;

    @RequestMapping("/getAll")
    public List<SystemDbConnect> getAll(){
        return service.queryAll();
    }
    @RequestMapping("/updateConnect")
    public ResponseEntity updateConnect(SystemDbConnect connectEntity){
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
    public ResponseEntity insertConnect(SystemDbConnect connectEntity){
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
        List<SystemDbConnect> dics = JSONArray.parseArray(dicMaps,SystemDbConnect.class);
        try {
            for (SystemDbConnect connectEntity : dics) {
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
