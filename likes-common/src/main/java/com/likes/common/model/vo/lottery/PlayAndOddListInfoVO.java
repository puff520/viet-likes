package com.likes.common.model.vo.lottery;


import com.likes.common.mybatis.entity.LotteryPlayOdds;
import com.likes.common.mybatis.entity.LotteryPlaySetting;

import java.util.List;

public class PlayAndOddListInfoVO {

    private LotteryPlaySetting lotteryPlaySetting; // 玩法配置信息
    private List<LotteryPlayOdds> oddsList; // 具体赔率信息

    public LotteryPlaySetting getLotteryPlaySetting() {
        return lotteryPlaySetting;
    }

    public void setLotteryPlaySetting(LotteryPlaySetting lotteryPlaySetting) {
        this.lotteryPlaySetting = lotteryPlaySetting;
    }

    public List<LotteryPlayOdds> getOddsList() {
        return oddsList;
    }

    public void setOddsList(List<LotteryPlayOdds> oddsList) {
        this.oddsList = oddsList;
    }

}
