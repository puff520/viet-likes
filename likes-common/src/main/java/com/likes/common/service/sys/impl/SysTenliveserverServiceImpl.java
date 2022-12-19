//package com.likes.common.service.sys.impl;
//
//import com.likes.common.model.response.TenLiveserverResp;
//import com.likes.common.mybatis.entity.SysTenliveserver;
//import com.likes.common.mybatis.entity.SysTenliveserverExample;
//import com.likes.common.mybatis.mapper.SysTenliveserverMapper;
//import com.likes.common.mybatis.mapperext.sys.SysTenliveserverMapperExt;
//import com.likes.common.service.sys.SysTenliveserverService;
//import com.likes.common.util.robin.Node;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * ClassName: SysTenliveserverServiceImpl
// * Description: 描述
// *
// * @author hai
// * @since JDK 1.8
// * date: 2020/6/19 21:54
// */
//@Service
//public class SysTenliveserverServiceImpl implements SysTenliveserverService {
//
//    @Resource
//    private SysTenliveserverMapper sysTenliveserverMapper;
//    @Resource
//    private SysTenliveserverMapperExt sysTenliveserverMapperExt;
//
//    @Override
//    public SysTenliveserver selectByPrimaryKey(Long tliveid) {
//        if (null == tliveid || tliveid <= 0) {
//            return null;
//        }
//        return sysTenliveserverMapper.selectByPrimaryKey(tliveid);
//    }
//
//    @Override
//    public List<TenLiveserverResp> getList() {
//        return sysTenliveserverMapperExt.getList();
//    }
//
//    @Override
//    public List<Node> selectByPtliveid() {
//        return sysTenliveserverMapperExt.selectByPtliveid();
//    }
//
//    @Override
//    public List<SysTenliveserver> selectAllAvailableServer() {
//        SysTenliveserverExample example = new SysTenliveserverExample();
//        example.createCriteria().andStatusEqualTo(0).andIsDeleteEqualTo(false);
//        List<SysTenliveserver> sysTenliveservers = sysTenliveserverMapper.selectByExample(example);
//        return null == sysTenliveservers ? new ArrayList<>() : sysTenliveservers;
//    }
//}
