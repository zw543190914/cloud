package com.zw.cloud.xxljobserver.core.alarm;

import com.zw.cloud.xxljobserver.core.model.XxlJobInfo;
import com.zw.cloud.xxljobserver.core.model.XxlJobLog;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
