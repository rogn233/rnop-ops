package com.boco.adaptor;

import com.boco.entity.ConnectEntity;
import com.boco.entity.LinuxCmdEntity;
import com.boco.util.LinuxCmdEnum;
import com.boco.util.LinuxTypeEnum;
import com.boco.util.SSHConnectUtil;

import java.util.Date;
import java.util.List;

public class SSHMonitorAlarmImpl implements IMonitorAlarm {
    private ConnectEntity connectEntity;
    private List<LinuxCmdEntity> result;
    private LinuxTypeEnum osType;

    @Override
    public void init(Object object) {
        connectEntity = (ConnectEntity) object;
        initOsType();
        LinuxCmdEnum[] cmds = {
                LinuxCmdEnum.DF_H,
                LinuxCmdEnum.LS_RLT
        };
//        SSHConnectUtil sshConnectUtil = new SSHConnectUtil(connectEntity.getName(), connectEntity.getIp(), connectEntity.getPort(), connectEntity.getUsername(), connectEntity.getPassword(), cmds);
//        sshConnectUtil.exec();
//        parse();
    }

    private void initOsType() {
        LinuxCmdEnum[] cmds = {
                LinuxCmdEnum.UNAME_S
        };
//        SSHConnectUtil sshConnectUtil = new SSHConnectUtil(connectEntity.getName(), connectEntity.getIp(), connectEntity.getPort(), connectEntity.getUsername(), connectEntity.getPassword(), cmds);
////        String result =  sshConnectUtil.exec().get(0).getReport();
////        osType = getOsType(result);
    }

    private void parse() {
        for (LinuxCmdEntity cmdEntity : result) {
            if(LinuxCmdEnum.DF_H.equals(cmdEntity.getCmd())){
//                cmdEntity.setTitle("磁盘利用率监控");
            }
            if(LinuxCmdEnum.LS_RLT.equals(cmdEntity.getCmd())){
//                cmdEntity.setTitle("");
            }
        }
    }

    @Override
    public String getAlarmTitle() {
        for (LinuxCmdEntity cmdEntity : result) {
            if(cmdEntity.getCmd().equals(LinuxCmdEnum.DF_H)){
                return "硬盘完整性监测";
            }
        }
        return null;
    }

    @Override
    public String getAlarmContent() {
        return null;
    }

    @Override
    public Date getEventTime() {
        return null;
    }

    @Override
    public String getSerity() {
        return null;
    }

    private LinuxTypeEnum getOsType(String osType) {
        if(osType.startsWith("Sun")){
            return LinuxTypeEnum.SUN;
        }
        if(osType.startsWith("HP")){
            return LinuxTypeEnum.HP;
        }
        if(osType.startsWith("AIX")){
            return LinuxTypeEnum.AIX;
        }
        if(osType.startsWith("Linux")){
            return LinuxTypeEnum.LINUX;
        }
        return LinuxTypeEnum.LINUX;
    }
}
