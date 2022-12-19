//package com.likes.common.service.member.impl;
//
//import com.likes.common.mybatis.entity.MemFaceset;
//import com.likes.common.mybatis.mapper.MemFacesetMapper;
//import com.likes.common.mybatis.mapperext.member.MemFacesetMapperExt;
//import com.likes.common.service.member.MemFacesetService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class MemFacesetServiceImpl implements MemFacesetService {
//    @Resource
//    private MemFacesetMapper memFacesetMapper;
//    @Resource
//    private MemFacesetMapperExt memFacesetMapperExt;
//
//    @Override
//    public MemFaceset findByAccno(String accno) {
//
//        return memFacesetMapperExt.findByAccno(accno);
//    }
//}
