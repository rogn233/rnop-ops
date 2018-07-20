package com.boco.controller;

import com.alibaba.fastjson.JSONObject;
import com.boco.entity.*;
import com.boco.entity.ui.FormItem;
import com.boco.mybatis.service.DicMapService;
import com.boco.util.ConstentData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/15 0015.
 */
@Controller
public class ViewController {
    @Resource
    private DicMapService dicMapService;

    @RequestMapping({"/","/index"})
    public ModelAndView index(){
        return new ModelAndView("index");
    }
    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    @RequestMapping("/{page}.html")
    public ModelAndView common(@PathVariable String page){
        return new ModelAndView(page);
    }

    @RequestMapping("/setConnect")
    public ModelAndView setConnect(ConnectEntity entity){
        ModelAndView modelAndView = new ModelAndView("form");
        List<DicMap> items = getItems(entity,"setConnect");
        modelAndView.addObject("items",items);
        String url = "/connect/insertConnect";
        if(entity.getIp() != null){
            url = "/connect/updateConnect";
        }
        modelAndView.addObject("action",url);
        return modelAndView;
    }
    @RequestMapping("/setDbConnect")
    public ModelAndView setConnect(SystemDbConnect entity){
        ModelAndView modelAndView = new ModelAndView("form");
        List<DicMap> items = getItems(entity,"setDbConnect");
        modelAndView.addObject("items",items);
        String url = "/db_connect/insertConnect";
        if(entity.getDriver() != null){
            url = "/db_connect/updateConnect";
        }
        modelAndView.addObject("action",url);
        return modelAndView;
    }

    @RequestMapping("/setMqConnect")
    public ModelAndView setConnect(SystemMqConnect entity){
        ModelAndView modelAndView = new ModelAndView("form");
        List<DicMap> items = getItems(entity,"setMqConnect");
        modelAndView.addObject("items",items);
        String url = "/mq_connect/insertConnect";
        if(entity.getHostname() != null){
            url = "/mq_connect/updateConnect";
        }
        modelAndView.addObject("action",url);
        return modelAndView;
    }

    @RequestMapping("/setQuartz")
    public ModelAndView setQuartz(SystemCron entity){
        ModelAndView modelAndView = new ModelAndView("form");
        List<DicMap> items = getItems(entity,"setQuartz");
        modelAndView.addObject("items",items);
        String url = "/cron/add";
        if(entity.getCron() != null){
            url = "/cron/update";
        }
        modelAndView.addObject("action",url);
        return modelAndView;
    }

    @RequestMapping("/dataMonitor")
    public ModelAndView dataMonitor(DalDataDetail entity){
        ModelAndView modelAndView = new ModelAndView("form");
        List<DicMap> items = getItems(entity,"dataMonitor");
        modelAndView.addObject("items",items);
        modelAndView.addObject("action","");
        return modelAndView;
    }

    private List<DicMap> getItems(Object entity,String tab_name) {
        List<DicMap> dicMaps = new ArrayList<>();
        for (DicMap dicMap : dicMapService.queryByTab(tab_name)) {
            String attrName = dicMap.getCol_name();
            if("type".equals(attrName)){
                dicMap.setOthers(ConstentData.serverConnectList);
            }
            if("-".equals(dicMap.getType())){
                continue;
            }
           JSONObject json = (JSONObject) JSONObject.toJSON(entity);
            if(null != json.get(attrName)){
                dicMap.setAttrValue(String.valueOf(json.get(attrName)));
            }
           dicMaps.add(dicMap);
        }
        return dicMaps;
    }
    @RequestMapping("/setDicMap")
    public ModelAndView setDicMap(DicMap dicMap){
        ModelAndView modelAndView = new ModelAndView("form");
        List<FormItem> items = getDicMapItems(dicMap);
        modelAndView.addObject("items",items);
        String url = "/dic/add";
        if(dicMap.getCol_name() != null){
            url = "/dic/edit";
        }
        modelAndView.addObject("action",url);
        return modelAndView;
    }

    private List<FormItem> getDicMapItems(DicMap dicMap) {
        List<FormItem> items = new ArrayList<>();
        items.add(new FormItem("text","id",String.valueOf(dicMap.getId()),"id",null));
        items.add(new FormItem("text","tab_name",dicMap.getTab_name(),"表格名称",null));
        items.add(new FormItem("text","col_name",dicMap.getCol_name(),"数据库列名",null));
        items.add(new FormItem("text","zh_name",dicMap.getZh_name(),"中文名称",null));
        items.add(new FormItem("select","type",dicMap.getType(),"表单类型",ConstentData.uiTypeList));
        return items;
    }

    @RequestMapping("/table")
    public ModelAndView table(){
        ModelAndView modelAndView = new ModelAndView("table");
        modelAndView.addObject("test",new Date());
        return modelAndView;
    }
}
