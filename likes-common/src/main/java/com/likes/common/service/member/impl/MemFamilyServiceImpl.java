//package com.likes.common.service.member.impl;
//
//import com.likes.common.constant.RedisKeys;
//import com.likes.common.model.dto.member.MemFamilyReq;
//import com.likes.common.model.dto.member.MemFamilyResponse;
//import com.likes.common.model.response.FamilyIncarnateResponse;
//import com.likes.common.mybatis.entity.MemFamily;
//import com.likes.common.mybatis.entity.MemFamilyExample;
//import com.likes.common.mybatis.mapper.MemFamilyMapper;
//import com.likes.common.mybatis.mapperext.member.MemFamilyMapperExt;
//import com.likes.common.service.member.MemFamilyService;
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
//public class MemFamilyServiceImpl implements MemFamilyService {
//
//    @Resource
//    private MemFamilyMapperExt memFamilyMapperExt;
//
//    @Autowired
//    private MemFamilyMapper memFamilyMapper;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * 通过accno查询家族
//     */
//    @Override
//    public MemFamily getMemFamilyByAccno(String accno) {
//        String key = RedisKeys.LIVE_FAMILY + "getMemFamilyByAccno" + accno;
//        if (redisTemplate.hasKey(key)) {
//            return (MemFamily) redisTemplate.opsForValue().get(key);
//        } else {
//            MemFamily memFamily = memFamilyMapperExt.getMemFamilyByAccno(accno);
//            if (null != memFamily) {
//                redisTemplate.opsForValue().set(key, memFamily);
//                return memFamily;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 更新家族成员数量（增加）
//     */
//    @Override
//    public int updateAddMemnums(Long familyid, int size) {
//        int i = memFamilyMapperExt.updateAddMemnums(familyid, size);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_FAMILY);
//        return i;
//    }
//
//    /**
//     * 通过家族名称或id查询家族
//     */
//    @Override
//    public MemFamily getRepeat(MemFamilyReq req) {
//        return memFamilyMapperExt.getRepeat(req);
//    }
//
//    /**
//     * 通过家族id更新(删除、恢复)家族
//     */
//    @Override
//    public int doDelFamily(MemFamily memFamily) {
//        int i = memFamilyMapperExt.doDelFamily(memFamily);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_FAMILY);
//        return i;
//    }
//
//    /**
//     * 查询家族列表
//     */
//    @Override
//    public Page<MemFamilyResponse> familyList(MemFamilyReq req, RowBounds rowBounds) {
//        return memFamilyMapperExt.familyList(req, rowBounds);
//    }
//
//    /**
//     * 更新家族成员数量（减少）
//     */
//    @Override
//    public int updateSubtractMemnums(Long familyid, int size) {
//        int i = memFamilyMapperExt.updateSubtractMemnums(familyid, size);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_FAMILY);
//        return i;
//    }
//
//    /**
//     * 查询所有家族列表
//     */
//    @Override
//    public List<MemFamily> findAllFamilyList() {
//        String key = RedisKeys.LIVE_FAMILY + "findAllFamilyList";
//        if (redisTemplate.hasKey(key)) {
//            return (List<MemFamily>) redisTemplate.opsForValue().get(key);
//        } else {
//            MemFamilyExample memFamilyExample = new MemFamilyExample();
//            MemFamilyExample.Criteria criteria = memFamilyExample.createCriteria();
//            criteria.andIsDeleteEqualTo(false);
//            List<MemFamily> families = memFamilyMapper.selectByExample(memFamilyExample);
//            if (CollectionUtil.isNotEmpty(families)) {
//                redisTemplate.opsForValue().set(key, families);
//                return families;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 根据id查询一个家族
//     */
//    @Override
//    public FamilyIncarnateResponse findOneByFamilyId(Long familyid) {
//        String key = RedisKeys.LIVE_FAMILY + familyid.toString();
//        if (redisTemplate.hasKey(key)) {
//            return (FamilyIncarnateResponse) redisTemplate.opsForValue().get(key);
//        } else {
//            FamilyIncarnateResponse oneByFamilyId = memFamilyMapperExt.findOneByFamilyId(familyid);
//            if (null != oneByFamilyId) {
//                redisTemplate.opsForValue().set(key, oneByFamilyId);
//                return oneByFamilyId;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 查询所有家族列表
//     */
//    @Override
//    public List<MemFamily> allFamilyList() {
//        return memFamilyMapperExt.allFamilyList();
//    }
//
//    /**
//     * 查询accno所属的家族
//     */
//    @Override
//    public MemFamily getByAnchorAccno(String accno) {
//        return memFamilyMapperExt.getByAnchorAccno(accno);
//    }
//
//    /**
//     * 根据accno查家族
//     */
//    @Override
//    public MemFamily getFamilyByAccno(String accno) {
//        String key = RedisKeys.LIVE_FAMILY + "getFamilyByAccno" + accno;
//        if (redisTemplate.hasKey(key)) {
//            return (MemFamily) redisTemplate.opsForValue().get(key);
//        } else {
//            MemFamily memFamily = memFamilyMapperExt.getFamilyByAccno(accno);
//            if (null != memFamily) {
//                redisTemplate.opsForValue().set(key, memFamily);
//                return memFamily;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public MemFamily selectByPrimaryKey(Long familyid) {
//        String key = RedisKeys.LIVE_FAMILY + "selectByPrimaryKey" + familyid.toString();
//        if (redisTemplate.hasKey(key)) {
//            return (MemFamily) redisTemplate.opsForValue().get(key);
//        } else {
//            MemFamily memFamily = memFamilyMapper.selectByPrimaryKey(familyid);
//            if (null != memFamily) {
//                redisTemplate.opsForValue().set(key, memFamily);
//                return memFamily;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public int insertSelective(MemFamily memFamily) {
//        int i = memFamilyMapper.insertSelective(memFamily);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_FAMILY);
//        return i;
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(MemFamily memFamily) {
//        int i = memFamilyMapper.updateByPrimaryKeySelective(memFamily);
//        deleteFuzzyMatchCache(RedisKeys.LIVE_FAMILY);
//        return i;
//    }
//}
