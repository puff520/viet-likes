package com.likes.common.model.vo.circle;


import java.util.List;

public class ListPostVO {
    private PostMemberVO postMember;    //发帖人信息
    private CirclePostVO circlePost;    //帖子信息
    private List<PostCommentsVO> postCommentsVOList;    //  评论列表

    public PostMemberVO getPostMember() {
        return postMember;
    }

    public CirclePostVO getCirclePost() {
        return circlePost;
    }

    public void setCirclePost(CirclePostVO circlePost) {
        this.circlePost = circlePost;
    }

    public void setPostMember(PostMemberVO postMember) {
        this.postMember = postMember;
    }

    public List<PostCommentsVO> getPostCommentsVOList() {
        return postCommentsVOList;
    }

    public void setPostCommentsVOList(List<PostCommentsVO> postCommentsVOList) {
        this.postCommentsVOList = postCommentsVOList;
    }
}
