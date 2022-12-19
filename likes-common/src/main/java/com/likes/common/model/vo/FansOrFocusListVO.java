package com.likes.common.model.vo;

import com.likes.common.model.vo.circle.PostMemberVO;

import java.util.List;

public class FansOrFocusListVO {
    private Integer number;
    private List<PostMemberVO> postMemberVOList;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<PostMemberVO> getPostMemberVOList() {
        return postMemberVOList;
    }

    public void setPostMemberVOList(List<PostMemberVO> postMemberVOList) {
        this.postMemberVOList = postMemberVOList;
    }
}
