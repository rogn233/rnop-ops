package com.boco.quartz;

import com.boco.entity.SystemCron;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuartzManager {
    @Resource(name = "Scheduler")
    private Scheduler scheduler;

    private CronTrigger getCronTrigger(SystemCron job) throws SchedulerException {
        TriggerKey triggerKey = getTriggerKey(job);
        return (CronTrigger) scheduler.getTrigger(triggerKey);
    }

    private TriggerKey getTriggerKey(SystemCron job) {
        return TriggerKey.triggerKey(job.getName(), job.getJob());
    }

    /**
     * 新増任务
     * @param job
     * @param cls
     * @return
     */
    public boolean addQuartzJob(SystemCron job, Class cls){
        System.out.println("向任务调度中添加定时任务");
        try {
            CronTrigger trigger = getCronTrigger(job);
            if(trigger == null){
                JobDetail jobDetail = JobBuilder.newJob(cls)
                        .withIdentity(job.getName(), job.getJob()).build();
                jobDetail.getJobDataMap().put(job.getName(), job.getJob());
                trigger = createCronTrigger(job);
                scheduler.scheduleJob(jobDetail, trigger);
            }else {
                System.out.println("提示消息:任务已存在");
            }
            return true;
        } catch (Exception e) {
            System.out.println("向任务调度中添加定时任务异常！" + e.getMessage());
        }
        return false;
    }
    public JobDetail getJobDetail(SystemCron job){
        try {
            return scheduler.getJobDetail(getJobKey(job));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }


    private CronTrigger createCronTrigger(SystemCron job) {
        CronTrigger trigger;
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
        scheduleBuilder.withMisfireHandlingInstructionDoNothing(); //恢复后，不执行漏掉的任务
        trigger = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getJob())
            .withSchedule(scheduleBuilder).build();
        return trigger;
    }

    public boolean runJob(SystemCron job){
        System.out.println("立即运行任务调度中的定时任务");
        try {  
            if (null == job) {
                System.out.println("定时任务信息为空，无法立即运行");
                return false;
            }
            JobKey jobKey = getJobKey(job);
            if(null == jobKey){
                System.out.println("任务调度中不存在[" + job.getName() + "]定时任务，不予立即运行！");
                return false;
            }  
            scheduler.triggerJob(jobKey);
            return true;
        } catch (Exception e) {
            System.out.println("立即运行任务调度中的定时任务异常！" + e.getMessage());
        }
        return false;
    }

    private JobKey getJobKey(SystemCron job) {
        return JobKey.jobKey(job.getName(), job.getJob());
    }

    /**
     * 修改
     * @param job
     * @return
     */
    public boolean updateQuartzJob(SystemCron job){
        System.out.println("修改任务调度中的定时任务");
        try {  
            if (null == job) {
                System.out.println("修改调度任务参数不正常！");  
                return false;
            }
            TriggerKey triggerKey = getTriggerKey(job);
            CronTrigger trigger = createCronTrigger(job);
            scheduler.rescheduleJob(triggerKey, trigger);
            return true;
        } catch (Exception e) {  
            System.out.println("修改任务调度中的定时任务异常！" + e.getMessage());
        }
        return false;
    }

    /**
     * 挂起
     * @param job
     * @return
     */
    public boolean pauseJob(SystemCron job){
        System.out.println("暂停任务调度中的定时任务");  
        try {  
            if (null == job) {  
                System.out.println("暂停调度任务参数不正常！");  
                return false;
            }
            JobKey jobKey = getJobKey(job);
            if(null == jobKey){  
                System.out.println("任务调度中不存在[" + job.getName() + "]定时任务，不予进行暂停！");
                return false;
            }
            scheduler.pauseJob(jobKey);
            return true;
        } catch (Exception e) {  
            System.out.println("暂停任务调度中的定时任务异常！" + e.getMessage());
        }
        return false;
    }

    /**
     * 激活
     * @param job
     * @return
     */
    public boolean resumeJob(SystemCron job){
        System.out.println("恢复任务调度中的定时任务");  
        try {  
            if (null == job) {  
                System.out.println("恢复调度任务参数不正常！");  
                return false;
            }
            JobKey jobKey = getJobKey(job);
            if(null == jobKey){  
                System.out.println("任务调度中不存在[" + job.getName() + "]定时任务，不予进行恢复！");
                return false;
            }  
            scheduler.resumeJob(jobKey);
            return true;
        } catch (Exception e) {  
            System.out.println("恢复任务调度中的定时任务异常！" + e.getMessage());
        }
        return false;
    }
    private boolean deleteJob(SystemCron job){
        System.out.println("删除任务调度中的定时任务");  
        try {  
            if (null == job) {  
                System.out.println("删除调度任务参数不正常！");  
                return false;
            }
            JobKey jobKey = getJobKey(job);
            if(null == jobKey){  
                System.out.println("任务调度中不存在[" + job.getName() + "]定时任务，不予进行删除！");
                return false;
            }  
            scheduler.deleteJob(jobKey);
            return true;
        } catch (Exception e) {  
            System.out.println("删除任务调度中的定时任务异常！" + e.getMessage());
        }
        return false;
    }

    /**
     * 删除
     * @param job
     * @return
     */
    public boolean deleteTrigger(SystemCron job){
        System.out.println("删除任务调度定时器");
        if(deleteJob(job)){
            try {
                TriggerKey triggerKey = getTriggerKey(job);
                if(null == triggerKey){
                    System.out.println("停止任务定时器参数不正常，不予进行停止！");
                    return false;
                }
                System.out.println("停止任务定时器");
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                return true;
            } catch (Exception e) {
                System.out.println("删除任务调度定时器异常！" + e.getMessage());
            }
        }
        return false;
    }

    /**
     * 获取状态
     * @param job
     * @return
     * @throws SchedulerException
     */
    public String getTriggerStatus(SystemCron job) throws SchedulerException {
        if(Trigger.TriggerState.PAUSED.equals(scheduler.getTriggerState(getTriggerKey(job)))){
            return "挂起";
        }
        return "激活";
    }
}  