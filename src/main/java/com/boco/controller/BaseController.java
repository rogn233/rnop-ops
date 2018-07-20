package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.ConnectEntity;
import com.boco.entity.ResponseEntity;
import com.boco.entity.SystemCron;
import com.boco.entity.TableEntity;
import com.boco.mybatis.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class BaseController<E> {
    private BaseService service;

    public BaseService getService() {
        return service;
    }

    public void setService(BaseService service) {
        this.service = service;
    }
    public List<ConnectEntity> getAll(){
        return service.queryAll();
    }
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
    public ResponseEntity update(E entity){
        ResponseEntity responseEntity = new ResponseEntity();
        int result = service.updateById(entity);
        if(result > 0){
            responseEntity.setSuccess(true);
            responseEntity.setDesc("更新成功["+result+"条");
        }else{
            responseEntity.setDesc("更新失败["+result+"条");
        }
        return responseEntity;
    }

    public ResponseEntity insert(E entity){
        ResponseEntity responseEntity = new ResponseEntity();
        int result = service.insert(entity);
        if(result > 0){
            responseEntity.setSuccess(true);
            responseEntity.setDesc("插入成功["+result+"条");
        }else{
            responseEntity.setDesc("插入失败["+result+"条");
        }
        return responseEntity;
    }
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

    public List<E> queryAll(){
        return service.queryAll();
    }

    public TableEntity pageQuery(TableEntity table){
        table.setRows(service.pageQuery(table));
        table.setTotal(service.countAll());
        return table;
    }
}
