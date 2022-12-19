package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class ListPostDTO extends PageBaseInfo {
    private Integer type;   //类型
    private Integer memberId;   //他人的用户id
    private Integer lotteryId; //彩种ID
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

}
