package com.boco.util;

import com.boco.entity.LinuxCmdEntity;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.config.hosts.HostConfigEntry;
import org.apache.sshd.client.future.AuthFuture;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SSHConnectUtil {
    private String serverName;
    private String hostName;
    private int port;
    private String username;
    private String password;
    private String[] cmds;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getCmds() {
        return cmds;
    }

    public void setCmds(String[] cmds) {
        this.cmds = cmds;
    }

    private Logger log = LoggerFactory.getLogger(SSHConnectUtil.class);

    public SSHConnectUtil(String serverName,String hostName, int port, String username, String password, String... cmds) {
        this.serverName = serverName;
        this.hostName = hostName;
        this.port = port;
        this.username = username;
        this.password = password;
        this.cmds = cmds;
    }
    public List<LinuxCmdEntity> exec(){
        List<LinuxCmdEntity> result = new ArrayList<>();
        SshClient client = SshClient.setUpDefaultClient();
        client.start();
        try {
            HostConfigEntry hostConfigEntry = new HostConfigEntry();
            hostConfigEntry.setHostName(hostName);
            hostConfigEntry.setPort(port);
            hostConfigEntry.setUsername(username);

            ConnectFuture connect = client.connect(hostConfigEntry);
            if(!connect.await(5000L)){
                log.error(getLog(serverName,"连接超时"));
                return result;
            }
            ClientSession session = connect.getSession();
            session.addPasswordIdentity(password);
            AuthFuture auth = session.auth();
            if(!auth.await(5000L)){
                log.error(getLog(serverName,"鉴权超时"));
                return result;
            }
            if(auth.isSuccess()){
                log.info(getLog(serverName,"登陆成功，准备执行业务指令"));
                for (String cmd : cmds) {
                    LinuxCmdEntity cmdEntity = new LinuxCmdEntity();
                    cmdEntity.setCmd(cmd);
                    cmdEntity.setReport(session.executeRemoteCommand(cmd,null,Charset.forName("GBK")));
                    result.add(cmdEntity);
                }

            }else {
                log.error(getLog(serverName,"登陆失败"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            client.stop();
            log.info(getLog(serverName,"退出成功"));
        }
        return result;
    }

    private String getLog(String name,String logString){
        return "["+name+"]"+logString;
    }

    public static void main(String[] args) throws IOException {
    }
}
