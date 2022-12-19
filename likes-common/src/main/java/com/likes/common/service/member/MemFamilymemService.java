//package com.likes.common.service.member;
//
//import com.likes.common.model.dto.AnchorDO;
//import com.likes.common.model.dto.member.MemFamilyDO;
//import com.likes.common.model.dto.member.UserDO;
//import com.likes.common.mybatis.entity.MemFamilymem;
//import com.likes.common.mybatis.entity.MemFamilymemExample;
//import com.likes.common.service.BaseService;
//import com.github.pagehelper.Page;
//import org.apache.ibatis.session.RowBounds;
//
//import java.util.List;
//
//public interface MemFamilymemService extends BaseService {
//
//    /**
//     * 插入家族成员
//     */
//    int insertMemFamilymem(MemFamilymem memFamily);
//
//    /**
//     * 没有在家族的主播成员
//     */
//    Page<UserDO> getNoFamilyAnchorList(MemFamilymem req, RowBounds rowBounds);
//
//    /**
//     * 获取家族的主播成员
//     */
//    Page<MemFamilymem> getFamilyAnchorList(MemFamilymem req, RowBounds rowBounds);
//
//    /**
//     * 删除家族的成员
//     */
//    int doDelFamilyAnchor(MemFamilymem req);
//
//    /**
//     * 查询家族成员
//     */
//    MemFamilymem getMemFamilymem(MemFamilymem param);
//
//    /**
//     * 根据家族id查询家族成员列表
//     */
//    List<MemFamilymem> getAllFamilyAnchor(Long familyid);
//
//    /**
//     * 删除家族的成员
//     */
//    int doDelMemFamilymemAnchor(Long familyid, String accno);
//
//    /**
//     * 根据accno查询家族成员
//     */
//    MemFamilymem getMemFamilymemByAncorAccno(String accno);
//
//    /**
//     * 根据accno查询家族成员
//     */
//    MemFamilyDO findFamilyByAnchorAccno(String accno);
//
//    /**
//     * 获取主播列表
//     */
//    Page<AnchorDO> getAnchorList(Long familyid, RowBounds rowBounds);
//
//    /**
//     * 获取all主播列表
//     */
//    List<AnchorDO> getAllAnchorList(Long familyid);
//
//    int insertSelective(MemFamilymem m);
//
//
//    MemFamilymem selectOneByExample(String accno);
//
//    int countByExample(String accno);
//
//    MemFamilymem selectByPrimaryKey(Long familymemid);
//
//    int updateByPrimaryKeySelective(MemFamilymem mFamilymem);
//}
