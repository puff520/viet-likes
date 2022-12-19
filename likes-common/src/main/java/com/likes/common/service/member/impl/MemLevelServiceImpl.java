package com.likes.common.service.member.impl;

import cn.hutool.core.util.ObjectUtil;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.GiftReq;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.*;
import com.likes.common.mybatis.mapperext.member.MemGoldchangeMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.mybatis.mapperext.member.MemRelationshipMapperExt;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.member.MemLevelService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.money.TraOrdertrackingService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName: MemLevelServiceImpl
 * Description: 描述
 *
 * @author dapan
 * @since JDK 1.8
 * date: 2020/6/19 14:42
 */
@Service
public class MemLevelServiceImpl implements MemLevelService {

    @Resource
    private MemLevelMapper memLevelMapper;

    @Resource
    private MemLevelMapperExt memLevelMapperExt;

    @Resource
    private MemLevelConfigService memLevelConfigService;

    @Resource
    private MemLevelRecordMapper memLevelRecordMapper; // TODO

    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;

    @Resource
    private MemRelationshipMapperExt memRelationshipMapperExt;

    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;

    @Resource
    private MemRelationshipServiceImpl memRelationshipService;

    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemberCreditMapper memberCreditMapper;
    @Resource
    private MemberCreditChangeMapper memberCreditChangeMapper;
    @Resource
    private MemLevelConfigMapper memLevelConfigMapper;

    @Resource
    private TraOrdertrackingService traOrdertrackingMapperService;

    private static final Logger logger = LoggerFactory.getLogger(MemLevelServiceImpl.class);

    // Mapper###############################################################

    /**
     * 根据条件 查询记录数
     */
    @Override
    public int countByExample(MemLevelExample example) {
        return memLevelMapper.countByExample(example);
    }

    /**
     * 根据条件 删除记录
     */
    @Override
    public int deleteByExample(MemLevelExample example) {
        return memLevelMapper.deleteByExample(example);
    }

//    /**
//     * 根据主键 删除记录
//     */
//    @Override
//    public int deleteByPrimaryKey(Long levelid) {
//        return memLevelMapper.deleteByPrimaryKey(levelid);
//    }

    /**
     * 添加记录
     */
    @Override
    public int insert(MemLevel record) {
        int n = memLevelMapper.insert(record);
        RedisBusinessUtil.delete(RedisKeys.APP_MEMBER_LEVEL + record.getAccno());
        return n;
    }

    /**
     * 添加记录（非空）
     */
    @Override
    public int insertSelective(MemLevel record) {
        int n = memLevelMapper.insertSelective(record);
        RedisBusinessUtil.delete(RedisKeys.APP_MEMBER_LEVEL + record.getAccno());
        return n;
    }

    /**
     * 根据条件 查询单条记录
     */
    @Override
    public MemLevel selectOneByExample(MemLevelExample example) {
        return memLevelMapper.selectOneByExample(example);
    }

    /**
     * 根据条件 查询记录
     */
    @Override
    public List<MemLevel> selectByExample(MemLevelExample example) {
        return memLevelMapper.selectByExample(example);
    }

//    /**
//     * 根据主键 查询记录
//     */
//    @Override
//    public MemLevel selectByPrimaryKey(Long levelid) {
//        return memLevelMapper.selectByPrimaryKey(levelid);
//    }

    /**
     * 根据条件 更新记录（非空）
     */
    @Override
    public int updateByExampleSelective(@Param("record") MemLevel record, @Param("example") MemLevelExample example) {
        return memLevelMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据条件 更新记录
     */
    @Override
    public int updateByExample(@Param("record") MemLevel record, @Param("example") MemLevelExample example) {
        return memLevelMapper.updateByExample(record, example);
    }

    /**
     * 根据主键 更新记录（非空）
     */
    @Override
    public int updateByPrimaryKeySelective(MemLevel record) {
        return memLevelMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键 更新记录等级和等级是否锁定，并删除缓存
     */
    @Override
    public int updateLevelAndLocked(MemLevel record, String accno) {
        int n = updateByPrimaryKeySelective(record);
        RedisBusinessUtil.delete(RedisKeys.APP_MEMBER_LEVEL + accno);
        return 0;
    }

    /**
     * 根据主键 更新记录
     */
    @Override
    public int updateByPrimaryKey(MemLevel record) {
        return memLevelMapper.updateByPrimaryKey(record);
    }

    // MapperExt###############################################################

    /**
     * 根据账号 查询记录
     */
    @Override
    public MemLevel selectByAccno(String accno) {
        MemLevel memLevel = RedisBusinessUtil.get(RedisKeys.APP_MEMBER_LEVEL + accno);
        memLevel = memLevelMapperExt.selectByAccno(accno);
        if (!ObjectUtils.isEmpty(memLevel)) {
            RedisBusinessUtil.set(RedisKeys.APP_MEMBER_LEVEL + accno, memLevel);
        }
        return memLevel;
    }

    /**
     * 更新 会员当前积分
     */
    @Override
    public int updateMemscore(GiftReq memLevelParam) {
        int n = memLevelMapperExt.updateMemscore(memLevelParam);
        RedisBusinessUtil.delete(RedisKeys.APP_MEMBER_LEVEL + memLevelParam.getSendAccno());
        return n;
    }

    /**
     * 更新 当前等级，距离下一级所需积分，最后修改人
     */
    @Override
    public int updateLevel(MemLevel param, MemLevel sendMemLevel) {
        //用户等级已锁定不需要计算等级,等于原等级
        if (null != sendMemLevel && null != sendMemLevel.getLocked() && sendMemLevel.getLocked()) {
            param.setMemlevel(sendMemLevel.getMemlevel());
        }
        int n = memLevelMapperExt.updateLevel(param);
        RedisBusinessUtil.delete(RedisKeys.APP_MEMBER_LEVEL + param.getAccno());
        return n;
    }

    // Others###############################################################

    @Override
    @Transactional
    public boolean buyVIPLevel(MemBaseinfo chongzhiBaseinfo, TraOrderinfom traOrderinfom, LoginUser loginUser, Long levelId) {
        if (null == traOrderinfom.getBuyVip()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10003.getCode(), "购买等级为空");
        }
        MemLevelConfig memLevelConfig = memLevelConfigService.selectByPrimaryKey(levelId);
        if (memLevelConfig == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_162.getCode(), "查不到会员等级");
        }
        MemLevel sendMemLevel = selectByAccno(chongzhiBaseinfo.getAccno());
        MemLevelConfig beforeLevel = memLevelConfigMapperExt.selectMemLevel(loginUser.getAccno());
        if (sendMemLevel == null) {
            //当前用户 会员已到期 处理
            List<MemLevelConfig> leveList = memLevelConfigService.selectMemLevlConfigByLevelSeq();
            sendMemLevel = memLevelMapperExt.getByAccnoAndLevelId(chongzhiBaseinfo.getAccno(), leveList.get(0).getId());
        }
        sendMemLevel.setLevelid(null);
        sendMemLevel.setLevelConfigId(memLevelConfig.getId());
        sendMemLevel.setMemlevel(memLevelConfig.getLevel());
        sendMemLevel.setLevellog("用户购买VIP【" + memLevelConfig.getLevel() + "】");
        sendMemLevel.setCreateTime(new Date());
        sendMemLevel.setIsDelete(false);
        sendMemLevel.setExpireTime(DateUtils.getEndOfDay(DateUtils.addDays(new Date(), memLevelConfig.getExpireTime())));
        if (loginUser != null) {
            sendMemLevel.setCreateUser(loginUser.getAccno());
            sendMemLevel.setUpdateUser(loginUser.getAccno());
        }
        memLevelMapper.insertSelective(sendMemLevel);
        MemLevelRecord memLevelRecord = new MemLevelRecord();
        memLevelRecord.setAccno(loginUser.getAccno());
        String superiorName = memRelationshipMapperExt.selectSuperiorName(loginUser.getAccno());
        String headName = memRelationshipMapperExt.selectHeadName(loginUser.getAccno());
        memLevelRecord.setUserName(chongzhiBaseinfo.getEmail());
        memLevelRecord.setSuperiorName(superiorName);
        memLevelRecord.setHeadName(headName);
        memLevelRecord.setBeforeLevel(beforeLevel.getLevel());
        memLevelRecord.setCurrentLevel("vip" + traOrderinfom.getBuyVip());
        memLevelRecord.setChangeAmount(traOrderinfom.getRealamt());
        memLevelRecord.setChangeType(1);
        memLevelRecord.setCreateTime(new Date());
        memLevelRecordMapper.insertSelective(memLevelRecord);
        MemBaseinfo vipBaseinfo = new MemBaseinfo();
        vipBaseinfo.setLevel(traOrderinfom.getBuyVip().intValue());
        vipBaseinfo.setMemid(chongzhiBaseinfo.getMemid());
        memBaseinfoService.updateByPrimaryKeySelective(vipBaseinfo);

        if (vipBaseinfo.getLevel() >= 1) {
            //上级代理
            MemRelationship current = memRelationshipService.findByAccno(chongzhiBaseinfo.getAccno());
            if (ObjectUtil.isNotNull(current)) {
                MemRelationship superMem = memRelationshipService.findByAccno(current.getRefaccno());
                if (ObjectUtil.isNotNull(superMem)) {
                    memberCreditMapper.modifyIntegral(1, superMem.getAccno());
                    MemberCreditChange creditChange = new MemberCreditChange();
                    creditChange.setAccno(superMem.getAccno());
                    creditChange.setIntegral(1);
                    creditChange.setRefAcclogin(chongzhiBaseinfo.getEmail());
                    creditChange.setCreateTime(new Date());
                    memberCreditChangeMapper.insertSelective(creditChange);
                }
            }
        }


        // 订单生产成功后 ，在写入订单操作轨迹
        int i = traOrderinfomMapperService.insertOrder(traOrderinfom);
        if (i < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10004.getCode(), "下单失败");
        }
        traOrderinfom = traOrderinfomMapperService.selectByPrimaryKey(traOrderinfom.getOrderid());
        // 订单轨迹信息
        TraOrdertracking traOrdertracking = new TraOrdertracking();
        traOrdertracking.setOrderid(traOrderinfom.getOrderid());
        traOrdertracking.setTrackdate(new Date());
        traOrdertracking.setOrderstatus(Constants.ORDER_ORD04);
        traOrdertracking.setOperuse(loginUser.getAccno());
        traOrdertracking.setTrackbody("用户[" + loginUser.getNickname() + "]充值金额" + traOrderinfom.getRealamt() + "元");
        traOrdertrackingMapperService.insertSelective(traOrdertracking);


        RedisBusinessUtil.delete(RedisKeys.APP_MEMBER_LEVEL + chongzhiBaseinfo.getAccno());
        buyVIP(chongzhiBaseinfo, memLevelConfig, traOrderinfom.getRealamt());
        return true;
    }

    @Override
    public Integer getVipAmount(LoginUser loginUser, Long levelId) {
        BigDecimal befor = new BigDecimal(0);
        BigDecimal after = new BigDecimal(0);
        MemLevelConfig befroConfig = null;
        MemLevel memLevel = memLevelMapperExt.selectByAccno(loginUser.getAccno());
        if (!ObjectUtils.isEmpty(memLevel)) {
            MemLevelConfigExample example = new MemLevelConfigExample();
            MemLevelConfigExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeleteEqualTo(true);
            criteria.andIdEqualTo(memLevel.getLevelConfigId());
            befroConfig = memLevelConfigMapper.selectOneByExample(example);
            befor = befroConfig.getRechargeAmount();
        } else {
            //当前用户 会员到期处理
            List<MemLevelConfig> leveList = memLevelConfigService.selectMemLevlConfigByLevelSeq();
            if (!leveList.get(0).getId().equals(levelId)) {
                throw new BusinessException(StatusCode.ACROSS_THE_LEVEL_130013.getCode(), StatusCode.ACROSS_THE_LEVEL_130013.getValue());
            }
            MemLevelConfigExample example = new MemLevelConfigExample();
            MemLevelConfigExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeleteEqualTo(false);
            criteria.andIdEqualTo(leveList.get(0).getId());
            befroConfig = memLevelConfigMapper.selectOneByExample(example);
            befor = befroConfig.getRechargeAmount();
        }
        MemLevelConfig memLevelConfig = memLevelConfigService.selectByPrimaryKey(levelId);
        if (memLevelConfig == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_162.getCode(), "查不到会员等级");
        }
        after = memLevelConfig.getRechargeAmount();
        BigDecimal suxuAmount = new BigDecimal(0); //after.subtract(befor);
        if (befroConfig.getLevelSeq() >= memLevelConfig.getLevelSeq()) {
            List<MemLevelConfig> pro_memLevelConfigs = memLevelConfigService.selectMemLevlConfigDownLevelSeq(memLevelConfig.getLevelSeq());
            if (pro_memLevelConfigs != null && pro_memLevelConfigs.size() > 0) {
                suxuAmount = memLevelConfig.getRechargeAmount().subtract(pro_memLevelConfigs.get(0).getRechargeAmount());
            } else {
                suxuAmount = memLevelConfig.getRechargeAmount();
            }
        } else {
            suxuAmount = after.subtract(befor);
        }
        return suxuAmount.intValue();
    }

    /**
     * 付钱购买VIP
     *
     * @param chongzhiBaseinfo
     * @param memLevelConfig
     */
    private void buyVIP(MemBaseinfo chongzhiBaseinfo, MemLevelConfig memLevelConfig, BigDecimal amount) {
        // 扣减余额，帐变

        MemGoldchangeDO change = new MemGoldchangeDO();
        change.setAccno(chongzhiBaseinfo.getAccno());
        change.setShowChange(BigDecimal.ZERO.subtract(amount));
        change.setQuantity(BigDecimal.ZERO.subtract(amount));
        change.setChangetype(GoldchangeEnum.BUY_VIP.getValue());
        change.setOpnote("用户:【" + chongzhiBaseinfo.getAccno() + "】 购买VIP【" + memLevelConfig.getLevel() + "】,支付" + change.getQuantity());
        memBaseinfoWriteService.updateUserBalance(change);
        MemberLevelResponse response = memLevelConfigService.getMemLevelConfig(chongzhiBaseinfo.getAccno());
        if (response.getLevelSeq() < 1) {
            return;
        }
        //返佣
        memRelationshipService.returnBrokerage(memLevelConfig, chongzhiBaseinfo, amount);
    }


    /**
     * 用户注册初始化等级
     *
     * @param accno
     * @return
     */
    @Override
    public boolean initMemLevel(String accno) {
        MemLevel memLevel = new MemLevel();
        memLevel.setAccno(accno);
        //从配置文件读取最低等级进行初始化
//        List<MemLevelConfig> leveList = memLevelConfigService.selectMemLevelConfigByAmount(new Double(0));
        List<MemLevelConfig> leveList = memLevelConfigService.selectMemLevlConfigByLevelSeq();
        MemLevelConfig lingMap = leveList.get(0);
//        MemLevelConfig oneMap = null;
//        if (leveList.size() == 2) {
//            oneMap = leveList.get(1);
        memLevel.setMemlevel(lingMap.getLevel());
        memLevel.setLevelConfigId(lingMap.getId());
        memLevel.setMemscore(new BigDecimal(0));
//            memLevel.setNextlevscore(oneMap.getRechargeAmount());
//        } else {//如果没有配置0级
//            memLevel.setMemlevel("0");
//            memLevel.setMemscore(new BigDecimal(0));
//            memLevel.setNextlevscore(new BigDecimal(100));
//        }
        memLevel.setIsDelete(false);
        memLevel.setUpdateTime(new Date());
        memLevel.setCreateTime(new Date());
        memLevel.setCreateUser(accno);
        memLevel.setUpdateUser(accno);
        memLevel.setLevellog("新用户注册初始化");
        memLevel.setExpireTime(DateUtils.addDays(new Date(), lingMap.getExpireTime()));

        System.out.println(DateUtils.formatDate(memLevel.getExpireTime()));
        return memLevelMapper.insertSelective(memLevel) > 0;
    }

    @Override
    public List<MemLevel> selectAllMemLevel() {
        List<MemLevel> list = RedisBusinessUtil.get(RedisKeys.DATA_VALUE_LEVEL_CACHE);
        if (ObjectUtils.isEmpty(list)) {
            MemLevelExample memLevelExample = new MemLevelExample();
            MemLevelExample.Criteria criteria = memLevelExample.createCriteria();
            criteria.andIsDeleteEqualTo(false);
            list = memLevelMapper.selectByExample(memLevelExample);
            RedisBusinessUtil.set(RedisKeys.DATA_VALUE_LEVEL_CACHE, list);
        }
        return list;
    }

    @Override
    public void updateMemLevelOnly(String accno, String memlevel) {
       /* MemLevel memLevel = new MemLevel();
        memLevel.setAccno(accno);
        String levelLog = "试玩账号修改等级为" + memlevel;
        int i = memLevelMapper.updateLevel(accno, memlevel, levelLog);*/
    }

    @Override
    public int checkUserMemberLevelExpire(Integer levelSeq, String accno) {
        return memLevelMapperExt.checkUserMemberLevelExpire(levelSeq, accno);
    }


    public static void main(String[] args) {
        System.out.println(DateUtils.formatDate(DateUtils.addMonths(new Date(), 12)));
    }
}
