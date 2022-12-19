//package com.likes.modules.admin.user.service;
//
//import com.likes.common.model.LoginUser;
//import com.likes.common.model.common.PageBounds;
//import com.likes.common.model.common.PageResult;
//import com.likes.common.model.dto.member.MemFamilyReq;
//import com.likes.common.model.dto.member.MemFamilymemReq;
//import com.likes.common.model.dto.member.MemFamilymemSingleReq;
//import com.likes.common.mybatis.entity.MemFamily;
//import com.likes.common.mybatis.entity.MemFamilymem;
//
//import java.util.List;
//
//public interface UserFamilyService {
//
//    Long doSaveFamily(MemFamilyReq req, LoginUser loginAdmin);
//
//    Long doUpdateFamily(MemFamilyReq req, LoginUser loginAdmin);
//
//    String doDelFamily(MemFamilyReq req, LoginUser loginAdmin);
//
//    PageResult familyList(MemFamilyReq req, PageBounds page);
//
//    String doSaveManyFamilymem(MemFamilymemReq req, LoginUser loginUser);
//
//    PageResult getNoFamilyAnchorList(MemFamilymem req, PageBounds page);
//
//    PageResult getFamilyAnchorList(MemFamilymem req, PageBounds page);
//
//    String doDelFamilyAnchor(MemFamilymem req, LoginUser loginUser);
//
//    String doUpdateOneFamilymemRcent(MemFamilymemSingleReq req, LoginUser loginUser);
//
//    List<MemFamily> allFamilyList(MemFamilymem req, LoginUser loginUser);
//
//}
