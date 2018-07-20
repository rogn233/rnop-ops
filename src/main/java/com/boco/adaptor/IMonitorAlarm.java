package com.boco.adaptor;

import java.util.Date;

public interface IMonitorAlarm {
    public void init(Object object);
    public String getAlarmTitle();
    public String getAlarmContent();
    public Date getEventTime();
    public String getSerity();
}
