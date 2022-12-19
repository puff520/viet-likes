package com.likes.common.service.member;

import com.likes.common.model.LoginUser;
import com.likes.common.model.request.GiftReq;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemLevel;
import com.likes.common.mybatis.entity.MemLevelExample;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.likes.common.service.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: MemLevelService
 * Description: 描述
 *
 * @author dapan
 * @since JDK 1.8
 * date: 2020/6/19 14:42
 */
public interface MemLevelService extends BaseService {

    // Mapper###############################################################

    /**
     * 根据条件 查询记录数
     */
    int countByExample(MemLevelExample example);

    /**
     * 根据条件 删除记录
     */
    int deleteByExample(MemLevelExample example);

//    /**
//     * 根据主键 删除记录
//     */
//    int deleteByPrimaryKey(Long levelid);

    /**
     * 添加记录
     */
    int insert(MemLevel record);

    /**
     * 添加记录（非空）
     */
    int insertSelective(MemLevel record);

    /**
     * 根据条件 查询单条记录
     */
    MemLevel selectOneByExample(MemLevelExample example);

    /**
     * 根据条件 查询记录
     */
    List<MemLevel> selectByExample(MemLevelExample example);

//    /**
//     * 根据主键 查询记录
//     */
//    MemLevel selectByPrimaryKey(Long levelid);

    /**
     * 根据条件 更新记录（非空）
     */
    int updateByExampleSelective(@Param("record") MemLevel record, @Param("example") MemLevelExample example);

    /**
     * 根据条件 更新记录
     */
    int updateByExample(@Param("record") MemLevel record, @Param("example") MemLevelExample example);

    /**
     * 根据主键 更新记录（非空）
     */
    int updateByPrimaryKeySelective(MemLevel record);

    /**
     * 根据主键 更新记录等级和等级是否锁定，并删除缓存
     */
    int updateLevelAndLocked(MemLevel record, String accno);

    /**
     * 根据主键 更新记录
     */
    int updateByPrimaryKey(MemLevel record);

    // MapperExt###############################################################

    /**
     * 根据账号 查询记录
     */
    MemLevel selectByAccno(String accno);

    /**
     * 更新 会员当前积分
     */
    int updateMemscore(GiftReq memLevelParam);

    /**
     * 更新 当前等级，距离下一级所需积分，最后修改人
     */
    int updateLevel(MemLevel param, MemLevel sendMemLevel);

    // Others###############################################################

    boolean buyVIPLevel(MemBaseinfo chongzhiBaseinfo, TraOrderinfom traOrderinfom, LoginUser loginUser,Long levelId);


    Integer getVipAmount(LoginUser loginUser,Long levelId);

  /*  *//**
     * 更新会员等级（依据memLevelConfig）
     *
     * @param chongzhiBaseinfo
     * @param gold
     * @param loginUser
     * @return
     *//*
    MemLevel updateMemLevel(MemBaseinfo chongzhiBaseinfo, Double gold, LoginUser loginUser);*/

    /**
     * 用户注册初始化等级
     *
     * @param accno
     * @return
     */
    boolean initMemLevel(String accno);

    List<MemLevel> selectAllMemLevel();

    void updateMemLevelOnly(String accno, String memlevel);

    int checkUserMemberLevelExpire(Integer levelSeq, String accno);
}
