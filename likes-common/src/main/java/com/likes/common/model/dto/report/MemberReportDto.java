package com.likes.common.model.dto.report;

import java.math.BigDecimal;

public class MemberReportDto {
    // 我完成的任务数量
    private Integer finshTaskNum;
    // 我的任务收益
    private BigDecimal taskProfit;
    // 下级完成的任务数量
    private Integer agentFinshTaskNum;
    // 下级的任务收益
    private BigDecimal agentTaskProfit;

    public Integer getFinshTaskNum() {
        return finshTaskNum;
    }

    public void setFinshTaskNum(Integer finshTaskNum) {
        this.finshTaskNum = finshTaskNum;
    }

    public BigDecimal getTaskProfit() {
        return taskProfit;
    }

    public void setTaskProfit(BigDecimal taskProfit) {
        this.taskProfit = taskProfit;
    }

    public Integer getAgentFinshTaskNum() {
        return agentFinshTaskNum;
    }

    public void setAgentFinshTaskNum(Integer agentFinshTaskNum) {
        this.agentFinshTaskNum = agentFinshTaskNum;
    }

    public BigDecimal getAgentTaskProfit() {
        return agentTaskProfit;
    }

    public void setAgentTaskProfit(BigDecimal agentTaskProfit) {
        this.agentTaskProfit = agentTaskProfit;
    }
}
