package com.likes.common.service.member;

import com.github.pagehelper.PageInfo;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.dto.member.MemRelationshipDO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemLevelConfig;
import com.likes.common.mybatis.entity.MemRelationship;
import com.likes.common.service.BaseService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;

public interface MemRelationshipService extends BaseService {

    /**
     * 插入
     */
    int insertSelectiveMemRelationship(MemRelationship memRelationship);

    /**
     * 获取 邀请的人
     */
    List<PromotionDo> getMyPromotion(String accno);

    /**
     * 废弃
     */
    List<String> xiangxia(String accno);

    /**
     * 根据accno查找关系信息
     */
    MemRelationship findByAccno(String accno);

//    /**
//     * 获取推荐人的邀请码
//     */
//    String getRecomcode(String accno);

    /**
     * 获取推荐人的所有下级关系信息
     */
    List<MemRelationshipDO> getAllChild(String refaccno);

    /**
     * 获取推荐人的下级关系信息
     */
    Page<PromotionDo> getMyPromotionList(String accno, RowBounds rowBounds);

    List<PromotionDo> getMyPromotionListInfo(String accno,String email);


    /**
     * 获取所有可用的代理人
     */
    List<MemRelationship> getRefaccnos();

    /**
     * 统计指定时间新增的下级人数
     */
    Integer countChild(String refaccno, String startDate, String endDate);

    void returnBrokerage(MemLevelConfig memLevelConfig, MemBaseinfo memBaseinfo, BigDecimal amount);

    Object moveAgent(String sourceAccno, String targetAcno, String password, LoginUser loginUser);

}
