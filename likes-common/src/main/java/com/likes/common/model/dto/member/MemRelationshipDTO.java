package com.likes.common.model.dto.member;

import com.likes.common.mybatis.entity.MemRelationship;

public class MemRelationshipDTO  extends MemRelationship {
    private String memberName;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
