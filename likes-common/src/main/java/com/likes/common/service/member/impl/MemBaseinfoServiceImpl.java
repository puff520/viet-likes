package com.likes.common.service.member.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.likes.common.constant.Constants;
import com.likes.common.model.GreatPersonList;
import com.likes.common.model.dto.bas.BasMemInfoStatusReq;
import com.likes.common.model.dto.bas.BasMemInfoStatusResp;
import com.likes.common.model.dto.member.MemBaseinfoDO;
import com.likes.common.model.dto.member.UserDO;
import com.likes.common.model.dto.member.UserGoldDO;
import com.likes.common.model.request.UserRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.vo.ProfitAndLossCountVO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemBaseinfoExample;
import com.likes.common.mybatis.entity.TraAgentclearing;
import com.likes.common.mybatis.mapper.MemBaseinfoMapper;
import com.likes.common.mybatis.mapperext.member.MemBaseinfoMapperExt;
import com.likes.common.mybatis.mapperext.tra.TraOrderinfomMapperExt;
import com.likes.common.service.code.UniqueCodeService;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzy
 * @create 2018-08-22 16:18
 **/
@Service
public class MemBaseinfoServiceImpl implements MemBaseinfoService {

    private static final Logger logger = LoggerFactory.getLogger(MemBaseinfoServiceImpl.class);

    @Resource
    private MemBaseinfoMapper memBaseinfoMapper;
    @Autowired
    private MemBaseinfoMapperExt memBaseinfoMapperExt;
    @Autowired
    private TraOrderinfomMapperExt traOrderinfomMapperExt;
    @Autowired
    private UniqueCodeService uniqueCodeService;

    @Override
    public boolean updateMemNameById(String memName, Long memId) {
        MemBaseinfo memBaseinfo = new MemBaseinfo();
        memBaseinfo.setMemname(memName);
        memBaseinfo.setMemid(memId);
        int row = memBaseinfoMapper.updateByPrimaryKeySelective(memBaseinfo);
        RedisBusinessUtil.deleteAppMember(memId);
        return row > 0;

    }

    @Override
    public void updateMemBalance(BigDecimal amount, BigDecimal namount, BigDecimal payamount, String accno) {
        memBaseinfoMapperExt.updateMemBalance(amount, namount, payamount, accno);
        RedisBusinessUtil.deleteAppMember(getUserByAccno(accno).getMemid());
    }

    //非MemBaseinfo表,不用管
    @Override
    public BigDecimal getOrderBetRecordAmount(List<Integer> list) {
        return memBaseinfoMapperExt.getOrderBetRecordAmount(list);
    }

    //非MemBaseinfo表,不用管
    @Override
    public BigDecimal getOrderRobotRecordAmount(List<Integer> list) {
        return memBaseinfoMapperExt.getOrderRobotRecordAmount(list);
    }


    //不用管
    @Override
    public Integer countAvailableAppMember() {
        Example example = new Example(MemBaseinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete", false);
        criteria.andEqualTo("freezeStatus", Constants.DEFAULT_INTEGER);
        return memBaseinfoMapper.selectCountByExample(example);
    }

//    //非MemBaseinfo表,不用管
//    @Override
//    public List<MemLevelConfig> memLevelConfigAllList() {
//        List<MemLevelConfig> memLevelConfigList = (List<MemLevelConfig>) RedisBusinessUtil.get(RedisKeys.MEM_LEVEL_CONFIG);
//        if (CollectionUtils.isEmpty(memLevelConfigList)) {
//            MemLevelConfigExample example = new MemLevelConfigExample();
//            example.createCriteria().andIsDeleteEqualTo(false);
//            example.setOrderByClause("recharge_amount desc");
//            memLevelConfigList = memLevelConfigService.selectByExample(example);
//            RedisBusinessUtil.set(RedisKeys.MEM_LEVEL_CONFIG, memLevelConfigList);
//        }
//        return memLevelConfigList;
//    }

    //暂时不用管
    @Override
    public MemBaseinfo getUserByInvitecode(String recomcode) {
        return memBaseinfoMapperExt.getUserByInvitecode(recomcode);
    }

    /**
     * 增加accno 与 memid 的映射方式
     *
     * @param accno
     * @return
     */
    @Override
    public MemBaseinfo getUserByAccno(String accno) {
        Long mid = RedisBusinessUtil.getAccnoMapMemidByAccno(accno);
        if (mid == null) {
            MemBaseinfo baseinfo = memBaseinfoMapperExt.getUserByAccno(accno);
            //RedisBusinessUtil.addAccnoMapMemid(baseinfo);
            // 优化 获取memid时MemBaseinfo就可以存入缓存
            RedisBusinessUtil.addAppMember(baseinfo);
            return baseinfo;
        }

        return getMemById(mid);
    }

    /**
     * uniqueId 与 memid 的映射方式
     *
     * @param uniqueId
     * @return
     */
    @Override
    public MemBaseinfo getUserByUniqueId(String uniqueId) {
        Long mid = RedisBusinessUtil.getMemidCacheByUniqueId(uniqueId);
        if (mid == null) {
            MemBaseinfoExample example = new MemBaseinfoExample();
            example.createCriteria().andUniqueIdEqualTo(uniqueId).andIsDeleteEqualTo(false);
            MemBaseinfo baseinfo = memBaseinfoMapper.selectOneByExample(example);
            //RedisBusinessUtil.addMemidCacheByUniqueId(baseinfo);
            RedisBusinessUtil.addAppMember(baseinfo);
            return baseinfo;
        }

        return getMemById(mid);
    }

    @Override
    public MemBaseinfo getUserByAccnoNoCache(String accno) {
        return memBaseinfoMapperExt.getUserByAccno(accno);
    }

    //走缓存
    @Override
    public MemBaseinfo getMemById(Long uid) {
        if (uid == null) {
            return null;
        }
        MemBaseinfo memBaseinfo  = memBaseinfoMapper.selectByPrimaryKey(uid.longValue());

        return memBaseinfo;
    }

    //暂时不用管
    @Override
    public BigDecimal getBalanceById(Integer memberId) {
        return memBaseinfoMapperExt.getBalanceById(memberId);
    }

    //暂时不用管
    @Override
    public MemBaseinfo getUserByInvitecodeAll(String recomcode) {
        return memBaseinfoMapperExt.getUserByInvitecodeAll(recomcode);
    }

    //删缓存
    @Override
    public void updateFansNum(UsersRequest ur) {
        memBaseinfoMapperExt.updateFansNum(ur);
        RedisBusinessUtil.deleteAppMember(ur.getMemid());
    }

//    //暂时不用管
//    @Override
//    public Integer getAttentionnum(String accno) {
//        return memBaseinfoMapperExt.getAttentionnum(accno);
//    }

//    //暂时不用管
//    @Override
//    public Integer getCollectionnum(String accno) {
//        return memBaseinfoMapperExt.getCollectionnum(accno);
//    }

    //暂时不用管
    @Override
    public Integer getResourcesnum(String accno) {
        return memBaseinfoMapperExt.getResourcesnum(accno);
    }

    //删缓存
    @Override
    public void updateAddGold(UsersRequest usersRequest) {
        MemBaseinfo mem = getUserByAccno(usersRequest.getAccno());
        memBaseinfoMapperExt.updateAddGold(usersRequest);
        RedisBusinessUtil.deleteAppMember(mem.getMemid());
    }

    @Override
    public UserGoldDO getUserRecomcodeGold(String accno) {
        return memBaseinfoMapperExt.getUserRecomcodeGold(accno);
    }

    //删缓存
    @Override
    public void updateSubtractGold(UsersRequest usersRequest) {
        memBaseinfoMapperExt.updateSubtractGold(usersRequest);
        RedisBusinessUtil.deleteAppMember(usersRequest.getMemid());
    }

    //删缓存
    @Override
    public void updateAddress(Long uid, MemBaseinfo memBaseinfo) {
        memBaseinfoMapperExt.updateAddress(memBaseinfo);
        RedisBusinessUtil.deleteAppMember(uid);
    }

    //暂时不用管
    @Override
    public MemBaseinfo getRepeateNickname(String nickname) {
        return memBaseinfoMapperExt.getRepeateNickname(nickname);
    }

    //删缓存
    @Override
    public void updateAddGold(UserRequest r) {
        memBaseinfoMapperExt.updateAddGold(r);
        MemBaseinfo m = getUserByAccno(r.getAccno());
        if (!ObjectUtils.isEmpty(m)) {
            RedisBusinessUtil.deleteAppMember(m.getMemid());
        }
    }

    @Override
    public List<UserDO> findUsers(UserDO userDO) {
        return memBaseinfoMapperExt.findUsers(userDO);
    }

    @Override
    public List<UserDO> findAllUsers() {
        return memBaseinfoMapperExt.findAllUsers();
    }

    @Override
    public Page<MemBaseinfoDO> applyAnchorList(UserRequest req, RowBounds rowBounds) {
        return memBaseinfoMapperExt.applyAnchorList(req, rowBounds);
    }

    //删缓存
    @Override
    public int updatePayAgent(TraAgentclearing req) {
        int record = memBaseinfoMapperExt.updatePayAgent(req);
        if (record > 0) {
            //清理缓存
            MemBaseinfo memBaseinfo = this.getUserByAccno(req.getAccno());
            RedisBusinessUtil.deleteAppMember(memBaseinfo.getMemid());
        }
        return record;
    }

    //暂时不用管
    @Override
    public MemBaseinfo selectByAccno(String accno) {
        return getUserByAccno(accno);
    }

    @Override
    public List<String> getNickNameList(String[] arrstr) {
        return memBaseinfoMapperExt.getNickNameList(arrstr);
    }

    @Override
    public Page<GreatPersonList> getGpList(RowBounds toRowBounds) {
        return memBaseinfoMapperExt.getGpList(toRowBounds);
    }

    //走缓存
    @Override
    public MemBaseinfo selectById(Long uid) {
        return this.getMemById(uid);
    }

    //删缓存
    @Override
    public int updateMemberAmount(BigDecimal amount, BigDecimal pamount, BigDecimal bamount, BigDecimal namount, BigDecimal wamount, BigDecimal waitamount, String accno, Long userId) {
        int i = memBaseinfoMapperExt.updateMemberAmount(amount, pamount, bamount, namount, wamount, waitamount, accno);
        RedisBusinessUtil.deleteAppMember(userId);
        return i;
    }

    //删缓存 增加打码量字段
    @Override
    public int updateMemberAmount(BigDecimal amount, BigDecimal pamount, BigDecimal bamount, BigDecimal namount, BigDecimal consumeAcmount, BigDecimal wamount, BigDecimal waitamount, String accno, Long userId) {
        int i = memBaseinfoMapperExt.updatePersonalFinancialInfo(amount, pamount, bamount, namount, consumeAcmount, wamount, waitamount, accno);
        logger.info("用户【{}】发生帐变,帐变金额:{},充值金额:{}, 投注变动金额:{},不可提金额:{},消费金额:{},提现金额:{},待开奖金额:{}", userId, amount, pamount, bamount, namount, consumeAcmount, wamount, waitamount);
        RedisBusinessUtil.deleteAppMember(userId);
        return i;
    }

    //不用管
    @Override
    public BigDecimal countAllBalanceAmount(String startTime, String endTime) {
        return memBaseinfoMapperExt.countAllBalanceAmount(startTime, endTime);
    }

    //走缓存
    @Override
    public String selectAccountbyId(Long uid) {
        if (uid == null) {
            return null;
        }
        MemBaseinfo memBaseinfo = RedisBusinessUtil.getAppMember(uid);
        if (memBaseinfo == null) {
            memBaseinfo = memBaseinfoMapper.selectByPrimaryKey(uid.longValue());
            RedisBusinessUtil.addAppMember(memBaseinfo);
        }
        return memBaseinfo == null ? null : memBaseinfo.getAccno();
    }

    //删缓存
    @Override
    public int updateMemberForbit(MemBaseinfo memBaseinfo) {
        int num = memBaseinfoMapperExt.updateMemberForbit(memBaseinfo);
        RedisBusinessUtil.deleteAppMember(memBaseinfo.getMemid());
        return num;
    }

    //不用管
    @Override
    public BigDecimal countFirstDepositAmount(ProfitAndLossCountVO vo) {
        return memBaseinfoMapperExt.countFirstDepositAmount(vo);
    }

    @Override
    public int updateWithdrawalAmount(MemBaseinfo memBaseinfo) {
        int i = memBaseinfoMapperExt.updateWithdrawalAmount(memBaseinfo);
        RedisBusinessUtil.deleteAppMember(memBaseinfo.getMemid());
        return i;
    }

    //走缓存
    @Override
    public MemBaseinfo selectOneByExample(Long uid, MemBaseinfoExample example) {
        if (example == null || uid == null) {
            return null;
        }
        MemBaseinfo memBaseinfo = RedisBusinessUtil.getAppMember(uid);
        if (memBaseinfo == null) {
            memBaseinfo = memBaseinfoMapper.selectOneByExample(example);
            RedisBusinessUtil.addAppMember(memBaseinfo);
        }
        return memBaseinfo;
    }

    //不用管
    @Override
    public MemBaseinfo selectOneByExample(MemBaseinfoExample example) {
        return memBaseinfoMapper.selectOneByExample(example);
    }

    //走缓存
    @Override
    public MemBaseinfo selectByPrimaryKey(Long uid) {
        if (uid == null) {
            return null;
        }
        MemBaseinfo memBaseinfo = RedisBusinessUtil.getAppMember(uid);
        if (memBaseinfo == null) {
            memBaseinfo = memBaseinfoMapper.selectByPrimaryKey(uid);
            RedisBusinessUtil.addAppMember(memBaseinfo);
        }
        return memBaseinfo;
    }

    //不走走缓存
    @Override
    public MemBaseinfo selectByPrimaryKeyNoCache(Long uid) {
        return memBaseinfoMapper.selectByPrimaryKey(uid);
    }

    //删缓存
    @Override
    public int updateByPrimaryKeySelective(MemBaseinfo record) {
        int num = memBaseinfoMapper.updateByPrimaryKeySelective(record);
        RedisBusinessUtil.deleteAppMember(record.getMemid());
        return num;
    }

    //不用管
    @Override
    public List<MemBaseinfo> selectByExample(MemBaseinfoExample example) {
        return memBaseinfoMapper.selectByExample(example);
    }

    //不用管
    @Override
    public int insertSelective(MemBaseinfo record) {
        return memBaseinfoMapper.insertSelective(record);
    }

    @Override
    public void delUserHeadimgByPrimaryKey(Long memid) {
        memBaseinfoMapperExt.delUserHeadimgByPrimaryKey(memid);
        RedisBusinessUtil.deleteAppMember(memid);
    }

    @Override
    public int updateMemorigin(String accno, String origin) {
        int effects = memBaseinfoMapperExt.updateMemorigin(accno, origin);
        if (effects > 0) {
            Long memid = RedisBusinessUtil.getAccnoMapMemidByAccno(accno);
            if (memid != null) {
                RedisBusinessUtil.deleteAppMember(memid);
            }
        }
        return effects;
    }

    @Override
    public int updateSuperiorId(String accno, String uniqueId) {
        return memBaseinfoMapperExt.updateSuperiorId(accno,uniqueId);
    }

    @Override
    public Page<BasMemInfoStatusResp> getMemInfoStatusList(BasMemInfoStatusReq basMemInfoStatusReq, RowBounds rowBounds) {
        return memBaseinfoMapperExt.getMemInfoStatusList(basMemInfoStatusReq, rowBounds);
    }

    @Override
    public void handMemUniqueId() {
        int offset = 0;
        int size = 1000;
        List<MemBaseinfo> memBaseinfos = memBaseinfoMapperExt.selectNonUniqueIdMembers(offset, size);

        while (CollectionUtil.isNotEmpty(memBaseinfos)) {
            Map<Long, String> data = new HashMap<>();
            for (MemBaseinfo memBaseinfo : memBaseinfos) {
                data.put(memBaseinfo.getMemid(), uniqueCodeService.getMemUniqueId());
            }
            memBaseinfoMapperExt.updateMemUniqueId(data);
            memBaseinfos = memBaseinfoMapperExt.selectNonUniqueIdMembers(offset + 1, size);
        }
    }

    /**
     * 根据账号列表 获取昵称、性别、头像
     */
    @Override
    public List<Map<String, Object>> getNicknameSexHeadimg(List<String> accnoList) {
        return memBaseinfoMapperExt.getNicknameSexHeadimg(accnoList);
    }

    @Override
    public void updateLastLoginDev(String accno, String source) {
        try {
            if (StringUtils.isBlank(source) || StringUtils.isBlank(accno)) {
                return;
            }
            MemBaseinfo memBaseinfo = new MemBaseinfo();
            memBaseinfo.setLastLoginDev(source);
            MemBaseinfoExample example = new MemBaseinfoExample();
            example.createCriteria().andAccnoEqualTo(accno);
            memBaseinfoMapper.updateByExampleSelective(memBaseinfo, example);
        } catch (Exception e) {
            logger.error("updateLastLoginDev occur error. accno:{}, source:{}", accno, source, e);
        }

    }

    @Override
    public void updateRegisterIp(String accno, String registerIp) {
        try {
            if (StringUtils.isBlank(accno) || StringUtils.isBlank(registerIp)) {
                return;
            }
            MemBaseinfo memBaseinfo = new MemBaseinfo();
            memBaseinfo.setRegisterIp(registerIp);
            MemBaseinfoExample example = new MemBaseinfoExample();
            example.createCriteria().andAccnoEqualTo(accno);
            memBaseinfoMapper.updateByExampleSelective(memBaseinfo, example);
        } catch (Exception e) {
            logger.error("updateLastLoginDev occur error. accno:{}, source:{}", accno, registerIp, e);
        }
    }

    /**
     * 补历史数据用
     * 提现总次数,首次提现金额,最大提现金额,充值总次数,首次充值金额,最大充值金额
     */
    @Override
    public void handMemWithdrawalAndPayInfo() {
        //线上总共1000多，一次取出
        List<String> list = traOrderinfomMapperExt.getAllWithdrawalAndPayAccno();
        for (String accno : list) {
            MemBaseinfo mem = getUserByAccno(accno);
            if (null == mem) {
                logger.info("处理历史数据,用户不存在,accno:{}", accno);
                continue;
            }
            List<Double> payList = traOrderinfomMapperExt.getPayTotalByAccno(accno);
            List<Double> withdrawalList = traOrderinfomMapperExt.getWithdrawalTotalByAccno(accno);
            boolean updateFlag = false;

            MemBaseinfo update = new MemBaseinfo();
            Integer payNum = payList.get(0).intValue();
            if (!Constants.DEFAULT_INTEGER.equals(payNum) && payList.size() == 4) {
                Double payFirst = payList.get(1);
                Double payMax = payList.get(2);
                Double payAmount = payList.get(3);
                if (null != payFirst && null != payMax) {
                    update.setPayNum(payNum);
                    update.setPayFirst(new BigDecimal(String.valueOf(payFirst)));
                    update.setPayMax(new BigDecimal(String.valueOf(payMax)));
                    update.setPayAmount(new BigDecimal(String.valueOf(payAmount)));
                    updateFlag = true;
                }
            }
            Integer withdrawalNum = withdrawalList.get(0).intValue();
            if (!Constants.DEFAULT_INTEGER.equals(withdrawalNum) && withdrawalList.size() == 4) {
                Double withdrawalFirst = withdrawalList.get(1);
                Double withdrawalMax = withdrawalList.get(2);
                Double withdrawalAmount = withdrawalList.get(3);
                if (null != withdrawalFirst && null != withdrawalMax) {
                    update.setWithdrawalNum(withdrawalNum);
                    update.setWithdrawalFirst(new BigDecimal(String.valueOf(withdrawalFirst)));
                    update.setWithdrawalMax(new BigDecimal(String.valueOf(withdrawalMax)));
                    update.setWithdrawalAmount(new BigDecimal(String.valueOf(withdrawalAmount)));
                    updateFlag = true;
                }
            }
            if (updateFlag) {
                update.setMemid(mem.getMemid());
                if ((null != update.getWithdrawalAmount() && !update.getWithdrawalAmount().equals(mem.getWithdrawalAmount()))
                        || (null != update.getPayAmount() && !update.getPayAmount().equals(mem.getPayAmount()))) {
                    logger.info("历史的入款或提现累计有问题:memid:{},accno:{},提现:{}_{},入款:{}_{}", mem.getMemid(), accno,
                            mem.getWithdrawalAmount(), update.getWithdrawalAmount(), mem.getPayAmount(), update.getPayAmount());
                }
                logger.info("处理历史数据,memid:{},accno:{},update:{}", mem.getMemid(), accno, JSONObject.toJSONString(update));
                updateByPrimaryKeySelective(update);
            }
        }
        logger.info("处理历史数据完成");
    }
}
