package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class PushOrderListDTO extends PageBaseInfo {
    private Integer lotteryId;  //彩种id
    private Integer type;   //

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
