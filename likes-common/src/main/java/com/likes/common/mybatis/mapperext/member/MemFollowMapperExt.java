package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.request.UserReq;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.request.VideoRequest;
import com.likes.common.mybatis.entity.MemFollow;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface MemFollowMapperExt {


    MemFollow findByParam(VideoRequest videoRequest);

    List<String> getAttention(String accno);

    Page<PromotionDo> getAttentionUserList(UsersRequest usersRequest, RowBounds rowBounds);

    Page<PromotionDo> getFansList(UsersRequest usersRequest, RowBounds rowBounds);

    MemFollow findByAccno(String accno);

    MemFollow findByParams(UserReq userReq);

    Integer getThisSeeLive(UserReq req);

    List<MemFollow> myAttentionList(String accno);
}