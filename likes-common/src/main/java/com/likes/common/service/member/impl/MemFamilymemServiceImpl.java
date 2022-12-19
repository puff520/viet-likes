//package com.likes.common.service.member.impl;
//
//import com.likes.common.constant.RedisKeys;
//import com.likes.common.model.dto.AnchorDO;
//import com.likes.common.model.dto.member.MemFamilyDO;
//import com.likes.common.model.dto.member.UserDO;
//import com.likes.common.mybatis.entity.MemFamilymem;
//import com.likes.common.mybatis.entity.MemFamilymemExample;
//import com.likes.common.mybatis.mapper.MemFamilymemMapper;
//import com.likes.common.mybatis.mapperext.member.MemFamilymemMapperExt;
//import com.likes.common.service.member.MemFamilymemService;
//import com.likes.common.util.CollectionUtil;
//import com.github.pagehelper.Page;
//import org.apache.ibatis.session.RowBounds;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;
//
//@Service
//public class MemFamilymemServiceImpl implements MemFamilymemService {
//
//    @Resource
//    private MemFamilymemMapperExt memFamilymemMapperExt;
//
//    @Resource
//    private MemFamilymemMapper memFamilymemMapper;
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * 插入家族成员
//     */
//    @Override
//    public Page<UserDO> getNoFamilyAnchorList(MemFamilymem req, RowBounds rowBounds) {
//        return memFamilymemMapperExt.getNoFamilyAnchorList(req, rowBounds);
//    }
//
//    /**
//     * 没有在家族的成员
//     */
//    @Override
//    public int insertMemFamilymem(MemFamilymem memFamily) {
//        int i = memFamilymemMapperExt.insertMemFamilymem(memFamily);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_MEMFAMILYMEM);
//        return i;
//    }
//
//    /**
//     * 获取家族的成员列表
//     */
//    @Override
//    public Page<MemFamilymem> getFamilyAnchorList(MemFamilymem req, RowBounds rowBounds) {
//        return memFamilymemMapperExt.getFamilyAnchorList(req, rowBounds);
//    }
//
//    /**
//     * 删除家族的成员
//     */
//    @Override
//    public int doDelFamilyAnchor(MemFamilymem req) {
//        int i = memFamilymemMapperExt.doDelFamilyAnchor(req);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_MEMFAMILYMEM);
//        return i;
//    }
//
//    /**
//     * 查询家族成员
//     */
//    @Override
//    public MemFamilymem getMemFamilymem(MemFamilymem param) {
//        return memFamilymemMapperExt.getMemFamilymem(param);
//    }
//
//    /**
//     * 根据家族id查询家族成员列表
//     */
//    @Override
//    public List<MemFamilymem> getAllFamilyAnchor(Long familyid) {
//        String key = RedisKeys.LIVE_MEMFAMILYMEM + familyid.toString();
//        if (redisTemplate.hasKey(key)) {
//            return (List<MemFamilymem>) redisTemplate.opsForValue().get(key);
//        } else {
//            List<MemFamilymem> memFamilymems = memFamilymemMapperExt.getAllFamilyAnchor(familyid);
//            if (CollectionUtil.isNotEmpty(memFamilymems)) {
//                redisTemplate.opsForValue().set(key, memFamilymems);
//                return memFamilymems;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 删除家族的成员
//     */
//    @Override
//    public int doDelMemFamilymemAnchor(Long familyid, String accno) {
//        int i = memFamilymemMapperExt.doDelMemFamilymemAnchor(familyid, accno);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_MEMFAMILYMEM);
//        return i;
//    }
//
//    /**
//     * 根据accno查询家族成员
//     */
//    @Override
//    public MemFamilymem getMemFamilymemByAncorAccno(String accno) {
//        return memFamilymemMapperExt.getMemFamilymemByAncorAccno(accno);
//    }
//
//    /**
//     * 根据accno查询家族成员
//     */
//    @Override
//    public MemFamilyDO findFamilyByAnchorAccno(String accno) {
//        return memFamilymemMapperExt.findFamilyByAnchorAccno(accno);
//    }
//
//    /**
//     * 获取主播列表
//     */
//    @Override
//    public Page<AnchorDO> getAnchorList(Long familyid, RowBounds rowBounds) {
//        return memFamilymemMapperExt.getAnchorList(familyid, rowBounds);
//    }
//
//    /**
//     * 获取all主播列表
//     */
//    @Override
//    public List<AnchorDO> getAllAnchorList(Long familyid) {
//        return memFamilymemMapperExt.getAllAnchorList(familyid);
//    }
//
//    @Override
//    public int insertSelective(MemFamilymem m) {
//        int i = memFamilymemMapper.insertSelective(m);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_MEMFAMILYMEM);
//        return i;
//    }
//
//    @Override
//    public MemFamilymem selectOneByExample(String accno) {
//        String key = RedisKeys.LIVE_MEMFAMILYMEM + accno;
//        if (redisTemplate.hasKey(key)) {
//            return (MemFamilymem) redisTemplate.opsForValue().get(key);
//        } else {
//            MemFamilymemExample memFamilymemExample = new MemFamilymemExample();
//            MemFamilymemExample.Criteria criteria = memFamilymemExample.createCriteria();
//            criteria.andAccnoEqualTo(accno);
//            criteria.andIsDeleteEqualTo(false);
//            MemFamilymem memFamilymem = memFamilymemMapper.selectOneByExample(memFamilymemExample);
//            if (null != memFamilymem) {
//                redisTemplate.opsForValue().set(key, memFamilymem);
//                return memFamilymem;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public int countByExample(String accno) {
//        MemFamilymemExample memFamilymemExample = new MemFamilymemExample();
//        MemFamilymemExample.Criteria criteia = memFamilymemExample.createCriteria();
//        criteia.andAccnoEqualTo(accno);
//        criteia.andIsDeleteEqualTo(false);
//        return memFamilymemMapper.countByExample(memFamilymemExample);
//    }
//
//    @Override
//    public MemFamilymem selectByPrimaryKey(Long familymemid) {
//        return memFamilymemMapper.selectByPrimaryKey(familymemid);
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(MemFamilymem mFamilymem) {
//        int i = memFamilymemMapper.updateByPrimaryKeySelective(mFamilymem);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_MEMFAMILYMEM);
//        return i;
//    }
//}
