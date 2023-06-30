package com.zw.cloud.common.utils;

import org.junit.jupiter.api.Test;

public class DateTimeUtilsTest {

    @Test
    void getWeekPeriodsByMonth() {
        DateTimeUtils.getWeekPeriodsByMonth(DateTimeUtils.parse2date("2023-06-30"));
    }
}
