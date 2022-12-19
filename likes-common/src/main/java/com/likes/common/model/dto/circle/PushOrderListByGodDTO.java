package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class PushOrderListByGodDTO extends PageBaseInfo {
    private Integer godId;  //大神id
    private Integer finishStatus;   //状态：1推单未开奖3已开奖结算

    public Integer getGodId() {
        return godId;
    }

    public void setGodId(Integer godId) {
        this.godId = godId;
    }

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }
}
