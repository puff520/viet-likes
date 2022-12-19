package com.likes.common.model.dto.member;

import com.likes.common.mybatis.entity.MemRelationship;

import java.util.List;

public class MemRelationshipDO extends MemRelationship {

    private List<MemRelationshipDO> children;

    public List<MemRelationshipDO> getChildren() {
        return children;
    }

    public void setChildren(List<MemRelationshipDO> children) {
        this.children = children;
    }
}
