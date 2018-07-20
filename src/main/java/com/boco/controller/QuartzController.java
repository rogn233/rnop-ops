package com.boco.controller;

import com.alibaba.fastjson.JSONArray;
import com.boco.entity.ResponseEntity;
import com.boco.entity.SystemCron;
import com.boco.mybatis.service.SystemCronService;
import com.boco.quartz.QuartzManager;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cron")
public class QuartzController {
    @Resource
    private QuartzManager quartzManager;
    @Resource
    private SystemCronService service;

    @RequestMapping("/add")
    public ResponseEntity add(SystemCron job){
        ResponseEntity entity = new ResponseEntity();
        try {
            if(quartzManager.addQuartzJob(job,Class.forName(job.getJob()))){
                job.setStatus(quartzManager.getTriggerStatus(job));
                int result = service.insert(job);
                entity.setSuccess(true);
                entity.setDesc("新増["+result+"]个任务成功");
            }
        } catch (ClassNotFoundException | SchedulerException e) {
            e.printStackTrace();
        }
        return entity;
    }
    @RequestMapping("/queryAll")
    public List<SystemCron> queryAll(){
        return service.queryAll();
    }
    @RequestMapping("/update")
    public ResponseEntity update(SystemCron job){
        ResponseEntity entity = new ResponseEntity();
        entity.setDesc("定时策略更新失败");
        updJob(job, entity, quartzManager.updateQuartzJob(job), "更新[");
        return entity;
    }
    @PostMapping("/pause")
    public ResponseEntity pause(String dicMaps){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setDesc("挂起策略失败");
        List<SystemCron> dics = JSONArray.parseArray(dicMaps,SystemCron.class);
        int result = 0;
        try {
            for (SystemCron job : dics) {
                if("挂起".equals(job.getStatus())){
                    responseEntity.setDesc("不能挂起已挂起的任务");
                    return responseEntity;
                }
                if(quartzManager.pauseJob(job)){
                    job.setStatus(quartzManager.getTriggerStatus(job));
                    result += service.updateById(job);
                }
            }
            responseEntity.setSuccess(true);
            responseEntity.setDesc("挂起["+result+"]个策略成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseEntity.setDesc("挂起失败");
        }
        return responseEntity;
    }

    @PostMapping("/del")
    public ResponseEntity del(String dicMaps){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setDesc("删除策略失败");
        List<SystemCron> dics = JSONArray.parseArray(dicMaps,SystemCron.class);
        int result = 0;
        try {
            for (SystemCron job : dics) {
                if(quartzManager.deleteTrigger(job)){
                    result += service.deleteById(job.getId());
                }
            }
            responseEntity.setSuccess(true);
            responseEntity.setDesc("删除["+result+"]个策略成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseEntity.setDesc("删除失败");
        }
        return responseEntity;
    }

    private void updJob(SystemCron job, ResponseEntity entity, boolean b, String s) {
        if (b) {
            try {
                job.setStatus(quartzManager.getTriggerStatus(job));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            int result = service.updateById(job);
            entity.setSuccess(true);
            entity.setDesc(s +result+"]条策略成功");
        }
    }

    @PostMapping("/resume")
    public ResponseEntity resume(String dicMaps){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setDesc("激活策略失败");
        List<SystemCron> dics = JSONArray.parseArray(dicMaps,SystemCron.class);
        int result = 0;
        try {
            for (SystemCron job : dics) {
                if("激活".equals(job.getStatus())){
                    responseEntity.setDesc("不能激活已激活的任务");
                    return responseEntity;
                }
                if(quartzManager.resumeJob(job)){
                    job.setStatus(quartzManager.getTriggerStatus(job));
                    result += service.updateById(job);
                }
            }
            responseEntity.setSuccess(true);
            responseEntity.setDesc("激活["+result+"]个策略成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseEntity.setDesc("激活失败");
        }
        return responseEntity;
    }
}
