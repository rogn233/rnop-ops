package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.BTableEntity;
import com.boco.entity.ConnectEntity;
import com.boco.entity.DicMap;
import com.boco.entity.ResponseEntity;
import com.boco.entity.ui.FormItem;
import com.boco.mybatis.service.ConnectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/16 0016.
 */
@RestController
@RequestMapping("/connect")
public class ConnectController {
    @Resource
    private ConnectService service;

    @RequestMapping("/getAll")
    public List<ConnectEntity> getAll(){
        return service.queryAll();
    }

    @RequestMapping("/listByType")
    public List<ConnectEntity> listByType(String type){
        return service.findByType(type);
    }

    @RequestMapping("/deleteById")
    public ResponseEntity deleteById(long id){
        ResponseEntity responseEntity = new ResponseEntity();
        int result = service.deleteById(id);
        if(result > 0){
            responseEntity.setSuccess(true);
            responseEntity.setDesc("删除成功["+result+"条");
        }else{
            responseEntity.setDesc("删除失败["+result+"条");
        }
        return responseEntity;
    }
    @RequestMapping("/updateConnect")
    public ResponseEntity updateConnect(ConnectEntity connectEntity){
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
    public ResponseEntity insertConnect(ConnectEntity connectEntity){
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
        List<ConnectEntity> dics = JSONArray.parseArray(dicMaps,ConnectEntity.class);
        try {
            for (ConnectEntity connectEntity : dics) {
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
