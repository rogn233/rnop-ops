package com.boco.util;

public enum LinuxCmdEnum {
    LS_RLT("ls -rlt"),
    DF_H("df -h | awk '{print $5 \"\\t\"$6}'"),
    UNAME_S("uname -s"),
    DF_K("df -k");
    ;
    private String cmd;
    private LinuxCmdEnum(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }
}
