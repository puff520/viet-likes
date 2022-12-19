package com.likes.common.model.dto.chess;

public class ChessKaiYuanStatisticsResponse {

    //下单数
    private Integer nums;
    //下单金额
    private Double sumamt;
    //中奖金额
    private Double winamt;

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getSumamt() {
        return sumamt;
    }

    public void setSumamt(Double sumamt) {
        this.sumamt = sumamt;
    }

    public Double getWinamt() {
        return winamt;
    }

    public void setWinamt(Double winamt) {
        this.winamt = winamt;
    }

}
