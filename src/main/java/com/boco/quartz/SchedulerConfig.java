package com.boco.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {
    @Resource
    private MyJobFactory myJobFactory;  //自定义的factory

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        factory.setJobFactory(myJobFactory);
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(20);
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        System.out.println(111111);
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name="Scheduler")
    public Scheduler scheduler() throws IOException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        try {
            if(!scheduler.isStarted()){
                System.out.println("启动调度器");
                scheduler.start();
            }
            if(scheduler.isStarted()){
                System.out.println("调度器启动成功");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }

}