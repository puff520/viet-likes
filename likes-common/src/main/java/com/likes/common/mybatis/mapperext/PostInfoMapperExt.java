package com.likes.common.mybatis.mapperext;


import com.likes.common.model.dto.circle.PostSettingDTO;
import com.likes.common.model.vo.circle.PostMemberVO;
import com.likes.common.mybatis.entity.CirclePostComments;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PostInfoMapperExt {
    /**
     * 根据用户名查询用户信息
     */
    PostMemberVO getUserByAccount(@Param("userId") Long userId);

    /**
     * 根据帖子id获取帖子评论列表
     * 暂时不分页
     */
    List<CirclePostComments> getPostCommentsByPostId(@Param("postId") Integer postId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 是否关注
     *
     * @param meId
     * @param otherId
     * @return
     */
    int isFocus(@Param("meId") Integer meId, @Param("otherId") Integer otherId);

    /**
     * 关注的列表
     *
     * @param meId
     * @return
     */
    List<Integer> gzList(@Param("meId") Integer meId);

    /**
     * 回复过我的帖子id
     *
     * @param account 用户名
     * @return
     */
    List<Integer> hadReplyMePostIds(@Param("account") String account);

    /**
     * 回复过我的评论通过reply_account（优化2）
     *
     * @param postId 帖子id
     * @return
     */
    List<CirclePostComments> getPostCommentsReplyIdByMe(@Param("postId") Integer postId, @Param("meAccount") String meAccount);

    /**
     * 回复过我的评论（优化1）
     *
     * @return
     */
    List<CirclePostComments> getPostCommentsReplyByReplyId(@Param("idList") Set<Integer> idList);


    /**
     * 根据用户用户ids查询用户信息
     */
    List<PostMemberVO> getFansOrFocusList(@Param("idList") List<Integer> idList);

    /**
     * 统计某一时间段内的发帖人数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    int countAccountByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 个人设置
     */
    List<PostSettingDTO> getPersonnalSettingList(@Param("userId") Integer userId, @Param("classify") String classify);
}
