package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.DicMap;
import com.boco.entity.ResponseEntity;
import com.boco.mybatis.service.DicMapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dic")
public class DicMapController {
    @Resource
    private DicMapService service;

    @PostMapping("/add")
    public ResponseEntity add(DicMap dicMap){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            service.insert(dicMap);
            responseEntity.setSuccess(true);
            responseEntity.setDesc("保存成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseEntity.setDesc("保存失败，可能原因是数据库中已经存在");
        }
        return responseEntity;
    }
    @PostMapping("/edit")
    public ResponseEntity edit(DicMap dicMap){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            service.updateById(dicMap);
            responseEntity.setSuccess(true);
            responseEntity.setDesc("修改成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseEntity.setDesc("修改失败");
        }
        return responseEntity;
    }
    @PostMapping("/del")
    public ResponseEntity del(String dicMaps){
        ResponseEntity responseEntity = new ResponseEntity();
        List<DicMap> dics = JSONArray.parseArray(dicMaps,DicMap.class);
        try {
            for (DicMap dicMap : dics) {
                service.deleteById(dicMap.getId());
            }
            responseEntity.setSuccess(true);
            responseEntity.setDesc("删除成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseEntity.setDesc("删除失败");
        }
        return responseEntity;
    }

    @GetMapping("/queryAll")
    public List<DicMap> queryAll(){
        return service.queryAll();
    }

    @PostMapping("/queryByTab")
    public List<DicMap> queryByTab(String tab_name){
        return service.queryByTab(tab_name);
    }
}
