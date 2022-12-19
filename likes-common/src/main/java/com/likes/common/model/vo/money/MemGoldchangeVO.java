package com.likes.common.model.vo.money;

import com.likes.common.mybatis.entity.MemGoldchange;

public class MemGoldchangeVO extends MemGoldchange {

    /**
     * 帐变类型的名称
     */
    private String changeTypeName;

    /**
     * 暱称
     */
    private String nickname;

    /**
     * 说明: 会员ID
     */
    private String uniqueId;

    public String getChangeTypeName() {
        return changeTypeName;
    }

    public void setChangeTypeName(String changeTypeName) {
        this.changeTypeName = changeTypeName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
