//package com.likes.common.service.member;
//
//import com.likes.common.model.dto.member.MemFamilyReq;
//import com.likes.common.model.dto.member.MemFamilyResponse;
//import com.likes.common.model.request.FamilyIncarnateRequest;
//import com.likes.common.model.response.FamilyIncarnateResponse;
//import com.likes.common.mybatis.entity.MemFamily;
//import com.likes.common.service.BaseService;
//import com.github.pagehelper.Page;
//import org.apache.ibatis.session.RowBounds;
//
//import java.util.List;
//
//public interface MemFamilyService extends BaseService {
//
//    /**
//     * 通过accno查询家族
//     */
//    MemFamily getMemFamilyByAccno(String accno);
//
//    /**
//     * 更新家族成员数量（增加）
//     */
//    int updateAddMemnums(Long familyid, int size);
//
//    /**
//     * 通过家族名称或id查询家族
//     */
//    MemFamily getRepeat(MemFamilyReq req);
//
//    /**
//     * 通过家族id更新(删除、恢复)家族
//     */
//    int doDelFamily(MemFamily memFamily);
//
//    /**
//     * 查询家族列表
//     */
//    Page<MemFamilyResponse> familyList(MemFamilyReq req, RowBounds rowBounds);
//
//    /**
//     * 更新家族成员数量（减少）
//     */
//    int updateSubtractMemnums(Long familyid, int size);
//
//    /**
//     * 查询所有家族列表
//     */
//    List<MemFamily> findAllFamilyList();
//
//    /**
//     * 根据id查询一个家族
//     */
//    FamilyIncarnateResponse findOneByFamilyId(Long familyid);
//
//    /**
//     * 查询所有家族列表
//     */
//    List<MemFamily> allFamilyList();
//
//    /**
//     * 查询accno所属的家族
//     */
//    MemFamily getByAnchorAccno(String accno);
//
//    /**
//     * 根据accno查家族
//     */
//    MemFamily getFamilyByAccno(String accno);
//
//    MemFamily selectByPrimaryKey(Long familyid);
//
//    int insertSelective(MemFamily memFamily);
//
//    int updateByPrimaryKeySelective(MemFamily memFamily);
//}
