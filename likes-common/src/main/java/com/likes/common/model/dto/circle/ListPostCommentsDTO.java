package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class ListPostCommentsDTO extends PageBaseInfo {
    private Integer type;   //类型
    private Integer postId; //帖子id

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
