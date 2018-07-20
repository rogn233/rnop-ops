package com.boco.controller;

import com.boco.entity.DalDataDetail;
import com.boco.entity.TableEntity;
import com.boco.mybatis.service.DalDataDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/data_monitor")
public class DalDataDetailController {
    @Resource
    private DalDataDetailService service;
    @RequestMapping("/queryAll")
    public List<DalDataDetail> queryAll(){
        return service.queryAll();
    }

    @RequestMapping("/pageQuery")
    public TableEntity pageQuery(TableEntity table){
        table.setRows(service.pageQuery(table));
        table.setTotal(service.countAll());
        return table;
    }
}
