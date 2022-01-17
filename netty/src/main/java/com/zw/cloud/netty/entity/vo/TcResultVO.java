package com.zw.cloud.netty.entity.vo;

import java.io.Serializable;
import java.util.List;

public class TcResultVO implements Serializable {
    private ResultValue value;

    public ResultValue getValue() {
        return value;
    }

    public void setValue(ResultValue value) {
        this.value = value;
    }

    public static class ResultValue {
        private List<ValueData> list;

        public List<ValueData> getList() {
            return list;
        }

        public void setList(List<ValueData> list) {
            this.list = list;
        }
    }

    public static class ValueData {
        private Integer lotteryDrawNum;
        private String lotteryDrawResult;

        public String getLotteryDrawResult() {
            return lotteryDrawResult;
        }

        public Integer getLotteryDrawNum() {
            return lotteryDrawNum;
        }

        public void setLotteryDrawNum(Integer lotteryDrawNum) {
            this.lotteryDrawNum = lotteryDrawNum;
        }

        public void setLotteryDrawResult(String lotteryDrawResult) {
            this.lotteryDrawResult = lotteryDrawResult;
        }
    }
}
