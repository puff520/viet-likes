//package com.likes.common.service.member.impl;
//
//import com.likes.common.model.dto.PromotionDo;
//import com.likes.common.model.request.UserReq;
//import com.likes.common.model.request.UsersRequest;
//import com.likes.common.model.request.VideoRequest;
//import com.likes.common.mybatis.entity.MemBaseinfo;
//import com.likes.common.mybatis.entity.MemFollow;
//import com.likes.common.mybatis.mapper.MemFollowMapper;
//import com.likes.common.mybatis.mapperext.member.MemFollowMapperExt;
//import com.likes.common.service.member.MemBaseinfoService;
//import com.likes.common.service.member.MemFollowService;
//import com.likes.common.util.redis.RedisBusinessUtil;
//import com.github.pagehelper.Page;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.session.RowBounds;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MemFollowServiceImpl implements MemFollowService {
//
//    @Resource
//    private MemFollowMapperExt memFollowMapperExt;
//
//    @Resource
//    private MemFollowMapper memFollowMapper;
//
//    @Resource
//    private MemBaseinfoService memBaseinfoService;
//
//    /**
//     * 获取我的关注
//     */
//    @Override
//    public List<MemFollow> attentionList(String accno) {
//        List<MemFollow> list = RedisBusinessUtil.getUserAttention(accno);
//        if (list == null) {
//            list = memFollowMapperExt.myAttentionList(accno);
//            RedisBusinessUtil.addUserAttention(accno, list);
//        }
//        return list;
//    }
//
//    /**
//     * 查询会员关注--走缓存
//     */
//    @Override
//    public MemFollow findByParam(VideoRequest videoRequest) {
//        if (null == videoRequest && null == videoRequest.getMemid() && null == videoRequest.getAccno()) {
//            return null;
//        }
//        List<MemFollow> list = attentionList(videoRequest.getAccno());
//        for (MemFollow follow : list) {
//            if (videoRequest.getMemid().equals(follow.getMemid()) && videoRequest.getAccno().equals(follow.getAccno())) {
//                return follow;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 查询会员关注列表--走缓存
//     */
//    @Override
//    public List<String> getAttention(String accno) {
//        List<String> accnoList = new ArrayList<String>();
//        List<MemFollow> list = attentionList(accno);
//        for (MemFollow follow : list) {
//            if (null != follow.getMemid()) {
//                MemBaseinfo refMem = memBaseinfoService.getMemById(follow.getMemid());
//                if (null != refMem && StringUtils.isNotBlank(refMem.getAccno())) {
//                    accnoList.add(refMem.getAccno());
//                }
//            }
//        }
//        return accnoList;
//    }
//
//    /**
//     * 我的-关注列表
//     */
//    @Override
//    public Page<PromotionDo> getAttentionUserList(UsersRequest usersRequest, RowBounds rowBounds) {
//        return memFollowMapperExt.getAttentionUserList(usersRequest, rowBounds);
//    }
//
//    /**
//     * 我的粉丝
//     */
//    @Override
//    public Page<PromotionDo> getFansList(UsersRequest usersRequest, RowBounds rowBounds) {
//        return memFollowMapperExt.getFansList(usersRequest, rowBounds);
//    }
//
//    @Override
//    public MemFollow findByAccno(String accno) {
//        return memFollowMapperExt.findByAccno(accno);
//    }
//
//    /**
//     * 查询关注(冗余相同功能的接口)
//     */
//    @Override
//    public MemFollow findByParams(UserReq userReq) {
//        VideoRequest req = new VideoRequest();
//        req.setMemid(userReq.getMemid());
//        req.setAccno(userReq.getAccno());
//        return findByParam(req);
//    }
//
//    /**
//     * 新增关注数
//     */
//    @Override
//    public Integer getThisSeeLive(UserReq req) {
//        return memFollowMapperExt.getThisSeeLive(req);
//    }
//
//    /**
//     * 获取我关注的人数--走缓存
//     */
//    @Override
//    public Integer getAttentionnum(String accno) {
//        List<MemFollow> list = attentionList(accno);
//        return null == list ? 0 : list.size();
//    }
//
//    /**
//     * 原生mapper插入
//     */
//    @Override
//    public int insertSelective(MemFollow record) {
//        int n = memFollowMapper.insertSelective(record);
//        RedisBusinessUtil.delUserAttention(record.getAccno());
//        return n;
//    }
//
//    /**
//     * 原生mapper更新
//     */
//    @Override
//    public int updateByPrimaryKeySelective(MemFollow record) {
//        int n = memFollowMapper.updateByPrimaryKeySelective(record);
//        RedisBusinessUtil.delUserAttention(record.getAccno());
//        return n;
//    }
//}
