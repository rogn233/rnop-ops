package com.boco.entity;

import com.boco.util.LinuxCmdEnum;

import java.util.Arrays;
import java.util.List;

public class LinuxCmdEntity {
    private String cmd;
    private String report;
    private String[] headers;
    private List<String[]> contents;

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<String[]> getContents() {
        return contents;
    }

    public void setContents(List<String[]> contents) {
        this.contents = contents;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "LinuxCmdEntity{" +
                "cmd=" + cmd +
                ", report='" + report + '\'' +
                ", headers=" + Arrays.toString(headers) +
                ", contents=" + contents +
                '}';
    }
}
