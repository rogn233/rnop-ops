package com.boco.quartz.job;

import com.boco.entity.ConnectEntity;
import com.boco.mybatis.service.ConnectService;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.config.hosts.HostConfigEntry;
import org.apache.sshd.client.future.AuthFuture;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.util.io.NoCloseOutputStream;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonitorServer extends QuartzJobBean {
    private Logger log = LoggerFactory.getLogger("MonitorServer");

    @Resource
    private ConnectService service;
    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("开始执行服务器监控程序");
        List<ConnectEntity> connectEntityList = service.queryAll();
        for (ConnectEntity connectEntity : connectEntityList) {
            String connetName = connectEntity.getName();
            if("SSH".equals(connectEntity.getType())){
                log.info(getLog(connetName,"开始处理SSH登陆"));
                SshClient client = SshClient.setUpDefaultClient();
                client.start();
                try {
                    HostConfigEntry hostConfigEntry = new HostConfigEntry();
                    hostConfigEntry.setHostName(connectEntity.getIp());
                    hostConfigEntry.setPort(connectEntity.getPort());
                    hostConfigEntry.setUsername(connectEntity.getUsername());

                    ConnectFuture connect = client.connect(hostConfigEntry);
                    if(!connect.await(5000L)){
                        log.error(getLog(connetName,"连接超时"));
                        continue;
                    }
                    ClientSession session = connect.getSession();
                    session.addPasswordIdentity(connectEntity.getPassword());
                    AuthFuture auth = session.auth();
                    if(!auth.await(5000L)){
                        log.error(getLog(connetName,"鉴权超时"));
                        continue;
                    }
                    if(auth.isSuccess()){
                        log.info(getLog(connetName,"登陆成功，准备执行业务指令"));
                        ChannelExec exec = session.createExecChannel("df -h");
                        exec.setOut(new NoCloseOutputStream(System.out));
                        exec.open();
                        ClientChannelEvent event = ClientChannelEvent.EOF;
                        List<ClientChannelEvent> events = new ArrayList<>();
                        events.add(event);
                        exec.waitFor(events,0);
                        log.info(getLog(connetName,"准备退出"));
                        exec.close(false);
                    }else {
                        log.error(getLog(connetName,"登陆失败"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    client.stop();
                    log.info(getLog(connetName,"退出成功"));
                }

            }
        }
    }

    private String getLog(String name,String logString){
        return "["+name+"]"+logString;
    }
}
