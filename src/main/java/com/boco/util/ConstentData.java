package com.boco.util;

import java.util.ArrayList;
import java.util.List;

public class ConstentData {
    public static final List<String> uiTypeList =new ArrayList<>();
    public static final List<String> serverConnectList =new ArrayList<>();
    static {
        uiTypeList.add("text");
        uiTypeList.add("select");
        uiTypeList.add("number");
        uiTypeList.add("password");
        uiTypeList.add("-");

        serverConnectList.add("SSH");
        serverConnectList.add("TELNET");
        serverConnectList.add("FTP");
    }
}
