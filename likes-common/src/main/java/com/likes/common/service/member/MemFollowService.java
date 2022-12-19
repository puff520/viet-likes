//package com.likes.common.service.member;
//
//import com.likes.common.model.dto.PromotionDo;
//import com.likes.common.model.request.UserReq;
//import com.likes.common.model.request.UsersRequest;
//import com.likes.common.model.request.VideoRequest;
//import com.likes.common.mybatis.entity.MemFollow;
//import com.likes.common.service.BaseService;
//import com.github.pagehelper.Page;
//import org.apache.ibatis.session.RowBounds;
//
//import java.util.List;
//
//public interface MemFollowService extends BaseService {
//
//    /**
//     * 我的关注列表
//     */
//    List<MemFollow> attentionList(String accno);
//
//    /**
//     * 查询会员关注
//     */
//    MemFollow findByParam(VideoRequest videoRequest);
//
//    /**
//     * 查询会员关注列表
//     */
//    List<String> getAttention(String accno);
//
//    /**
//     * 我的-关注列表
//     */
//    Page<PromotionDo> getAttentionUserList(UsersRequest usersRequest, RowBounds rowBounds);
//
//    /**
//     * 我的粉丝
//     */
//    Page<PromotionDo> getFansList(UsersRequest usersRequest, RowBounds rowBounds);
//
//    MemFollow findByAccno(String accno);
//
//    /**
//     * 查询关注
//     */
//    MemFollow findByParams(UserReq userReq);
//
//    /**
//     * 新增关注数
//     */
//    Integer getThisSeeLive(UserReq req);
//
//    /**
//     * 关注数
//     */
//    Integer getAttentionnum(String accno);
//
//    /**
//     * 原生mapper插入
//     */
//    int insertSelective(MemFollow record);
//
//    /**
//     * 原生mapper更新
//     */
//    int updateByPrimaryKeySelective(MemFollow record);
//}
