package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.*;
import com.boco.entity.ui.FormItem;
import com.boco.mybatis.service.BaseService;
import com.boco.mybatis.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ConnectController extends BaseController<ConnectEntity>{
    private ConnectService service;
    @Autowired
    public ConnectController(ConnectService service) {
        this.service = service;
        super.setService(service);
    }
    @RequestMapping("/getAll")
    public List<ConnectEntity> getAll(){
        return super.getAll();
    }

    @RequestMapping("/listByType")
    public List<ConnectEntity> listByType(String type){
        return service.findByType(type);
    }
    @RequestMapping("/deleteById")
    public ResponseEntity deleteById(long id){
        return super.deleteById(id);
    }
    @RequestMapping("/updateConnect")
    public ResponseEntity updateConnect(ConnectEntity connectEntity){
        return super.update(connectEntity);
    }
    @RequestMapping("/insertConnect")
    public ResponseEntity insertConnect(ConnectEntity connectEntity){
        return super.insert(connectEntity);
    }
    @PostMapping("/del")
    public ResponseEntity del(String dicMaps){
        return super.del(dicMaps);
    }

    @Override
    @RequestMapping("/pageQuery")
    public TableEntity pageQuery(TableEntity table) {
        return super.pageQuery(table);
    }
}
