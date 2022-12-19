package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.dto.member.MemRelationshipDO;
import com.likes.common.mybatis.entity.MemRelationship;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface MemRelationshipMapperExt {


    int insertSelectiveMemRelationship(MemRelationship memRelationship);

    /**
     * 获取 邀请的人
     *
     * @param accno
     * @return
     */
    List<PromotionDo> getMyPromotion(String accno);

    List<String> xiangxia(String accno);

    MemRelationship findByAccno(String accno);

    /**
     * 获取推荐人的邀请码（前端暂时未用到）
     */
    String getRecomcode(String accno);

    List<MemRelationshipDO> selectByPCode(@Param("accno") String accno);

    List<MemRelationshipDO> getAllChild(String refaccno);

    Page<PromotionDo> getMyPromotionList(String accno, RowBounds rowBounds);

    Page<PromotionDo> getMyPromotionList(String accno, String email);

    List<MemRelationship> getRefaccnos();

    /**
     * 统计指定时间新增的下级人数
     */
    Integer countChild(@Param("refaccno") String refaccno, @Param("startDate") String startDate, @Param("endDate") String endDate);


    @Update("update mem_relationship  set sub_num = sub_num +1 where accno =#{accno}")
    Integer addSubNum(@Param("accno") String accno);

    @Select("select * from mem_relationship where accno =#{accno}")
    MemRelationship getHeadAccno(String accno);


    List<Long> allZiji(@Param("relaid") Long relaid);

    Integer updateAgentLine(@Param("ids") List<Long> ids, @Param("toHeadAccno") String toHeadAccno);

    @Update("update mem_relationship  set parent_id = #{parentId}, refaccno =#{refaccno}  where relaid =#{relaid}")
    Integer updateMemRelationship(MemRelationship memRelationship);

    @Select("SELECT email  from mem_baseinfo WHERE accno = (SELECT refaccno from mem_relationship WHERE accno = #{accno})")
    String selectSuperiorName(String accno);

    @Select("SELECT email  from mem_baseinfo WHERE accno = (SELECT head_accno from mem_relationship WHERE accno = #{accno})")
    String selectHeadName(String accno);

}
