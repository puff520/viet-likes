package com.likes.common.service.member.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysRecord;
import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.dto.member.MemRelationshipDO;
import com.likes.common.model.dto.report.DayReportDTO;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.MemRelationshipMapper;
import com.likes.common.mybatis.mapper.SysRecordMapper;
import com.likes.common.mybatis.mapperext.member.MemRelationshipMapperExt;
import com.likes.common.service.member.BdUserService;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class MemRelationshipServiceImpl implements MemRelationshipService {

    @Resource
    private MemRelationshipMapperExt memRelationshipMapperExt;
    @Resource
    private MemRelationshipMapper memRelationshipMapper;
    @Resource
    private MemBaseinfoWriteServiceImpl memBaseinfoWriteService;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private BdUserService bdUserService;
    @Resource
    private SysRecordMapper sysRecordMapper;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemLevelConfigService memLevelConfigService;

    /**
     * 插入(删除上级关系缓存)
     */
    @Override
    public int insertSelectiveMemRelationship(MemRelationship memRelationship) {
        int n = memRelationshipMapperExt.insertSelectiveMemRelationship(memRelationship);
        if (n > 0) {
            RedisBusinessUtil.delUserRelationshipCache(memRelationship.getAccno());
        }
        return n;
    }

    /**
     * 获取 邀请的人
     */
    @Override
    public List<PromotionDo> getMyPromotion(String accno) {
        return memRelationshipMapperExt.getMyPromotion(accno);
    }

    /**
     * 废弃
     */
    @Override
    public List<String> xiangxia(String accno) {
        return memRelationshipMapperExt.xiangxia(accno);
    }

    /**
     * 根据accno查找关系信息
     */
    @Override
    public MemRelationship findByAccno(String accno) {
        MemRelationship relationship = memRelationshipMapperExt.findByAccno(accno);
        return relationship;
    }


    /**
     * 获取推荐人的所有下级关系信息
     */
    @Override
    public List<MemRelationshipDO> getAllChild(String refaccno) {
        return memRelationshipMapperExt.getAllChild(refaccno);
    }

    /**
     * 获取推荐人的下级关系信息
     */
    @Override
    public Page<PromotionDo> getMyPromotionList(String accno, RowBounds rowBounds) {
        return memRelationshipMapperExt.getMyPromotionList(accno, rowBounds);
    }

    @Override
    public List<PromotionDo> getMyPromotionListInfo(String accno, String email) {
        if (StringUtils.isNotBlank(email)) {
            MemLogin memLogin = memLoginService.findByAccloginRegister(email);
            accno = memLogin.getAccno();
        }
        List<PromotionDo> tmp = memRelationshipMapperExt.getMyPromotionList(accno, email);
        return tmp;
    }

    @Override
    public List<MemRelationship> getRefaccnos() {
        return memRelationshipMapperExt.getRefaccnos();
    }

    /**
     * 统计指定时间新增的下级人数
     */
    @Override
    public Integer countChild(String refaccno, String startDate, String endDate) {
        return memRelationshipMapperExt.countChild(refaccno, startDate, endDate);
    }

    /**
     * 计算购买等级返佣
     *
     * @param memLevelConfig
     * @param memBaseinfo
     * @param amount
     */
    @Override
    public void returnBrokerage(MemLevelConfig memLevelConfig, MemBaseinfo memBaseinfo, BigDecimal amount) {
        SysParameter rebate1 = this.sysParamService.getByCode("vip1_rebate");
        if (rebate1 == null || StringUtils.isBlank(rebate1.getSysparamvalue())) {
            return;
        }
        SysParameter rebate2 = this.sysParamService.getByCode("vip2_rebate");
        if (rebate2 == null || StringUtils.isBlank(rebate2.getSysparamvalue())) {
            return;
        }
        SysParameter rebate3 = this.sysParamService.getByCode("vip3_rebate");
        if (rebate3 == null || StringUtils.isBlank(rebate3.getSysparamvalue())) {
            return;
        }
        String startParam = String.format("%s,%s,%s", rebate1.getSysparamvalue(), rebate2.getSysparamvalue(), rebate3.getSysparamvalue());
        String accno = memBaseinfo.getAccno();
        String[] vals = startParam.split(",");
        for (int i = 0; i < vals.length; i++) {
            String str = vals[i].trim();
            if (!NumberUtils.isNumber(str)) {
                throw new BusinessException("165", "等级配置有误");
            }
            // 只有三级返佣
            if (i == 3) {
                return;
            }
            MemRelationship b = findByAccno(accno);
            if (b == null) {
                return;
            }
            accno = b.getRefaccno();
            if (accno.equals("ROOT")) {
                return;
            }
            BigDecimal rate = NumberUtils.createBigDecimal(str);
            BigDecimal brokerageMoney = amount.multiply(rate).divide(BigDecimal.valueOf(100));

            MemberLevelResponse response = memLevelConfigService.getMemLevelConfig(accno);
            if (response.getLevelSeq() < 1) {
                continue;
            }

            MemGoldchangeDO change = new MemGoldchangeDO();
            change.setAccno(accno);
            change.setShowChange(brokerageMoney);
            change.setQuantity(brokerageMoney);
            if (i == 0) {
                change.setChangetype(GoldchangeEnum.BUVIP_LEVEL_1.getValue());
            } else if (i == 1) {
                change.setChangetype(GoldchangeEnum.BUVIP_LEVEL_2.getValue());
            } else if (i == 2) {
                change.setChangetype(GoldchangeEnum.BUVIP_LEVEL_3.getValue());
            }
            change.setOpnote("用户:【" + b.getAccno() + "】 购买VIP【" + memLevelConfig.getLevel() + "】,支付" + change.getQuantity());
            memBaseinfoWriteService.updateUserBalance(change);

        }

    }

    @Override
    @Transactional
    public Object moveAgent(String source, String target, String password, LoginUser admin) {
        if (StringUtils.isBlank(source) || StringUtils.isBlank(target) || StringUtils.isBlank(password)) {
            throw new BusinessException("999", "参数错误");
        }
        BdUser bduser = bdUserService.selectByAccno(admin.getAccno());
        if (!password.equals(bduser.getPasswordmd5())) {
            throw new BusinessException("166", "密码错误");
        }

        MemLogin s = memLoginService.findByAccloginRegister(source);
        if (s == null) {
            throw new BusinessException("167", "被迁移账号不存在");
        }
        MemLogin t = memLoginService.findByAccloginRegister(target);
        if (t == null) {
            throw new BusinessException("168", "接收迁移账号不存在");
        }

        MemRelationship fromMemRelationship = memRelationshipMapperExt.getHeadAccno(s.getAccno());
        MemRelationship toMemRelationship = memRelationshipMapperExt.getHeadAccno(t.getAccno());
        if (fromMemRelationship.getRefaccno().equals("7df4efdbc7dd4142838f4aa02c4dbcae")) {
            throw new BusinessException("168", "顶级代理不允许迁移");
        }
        if (fromMemRelationship.getAccno().equals(toMemRelationship.getAccno())) {
            throw new BusinessException("168", "不能向自己迁移");
        }

        List<Long> ids = memRelationshipMapperExt.allZiji(fromMemRelationship.getRelaid());
        ids.add(fromMemRelationship.getRelaid());

        List<Long> idss = new LinkedList<>();
        idss.addAll(ids);

        List<Long> idts = memRelationshipMapperExt.allZiji(toMemRelationship.getRelaid());
        idts.add(toMemRelationship.getRelaid());

        if (ids.retainAll(idts)) {
            if (ids.size() > 0) {
                throw new BusinessException("168", "被迁移账号不能包含自己或自己的下级");
            }
        }
        MemRelationship update = new MemRelationship();
        update.setParentId(toMemRelationship.getRelaid());
        update.setRefaccno(toMemRelationship.getAccno());
        update.setRelaid(fromMemRelationship.getRelaid());

        memRelationshipMapperExt.updateMemRelationship(update);

        int row = memRelationshipMapperExt.updateAgentLine(idss, toMemRelationship.getHeadAccno());
        if (row < 1) {
            throw new BusinessException("168", "会员迁移出错");
        }
        SysRecord sysRecord = new SysRecord() {
        };
        sysRecord.setOperationer(admin.getAccno());
        sysRecord.setOperationdate(new Date());
        sysRecord.setRecordevent(1);
        sysRecord.setRecordremark("被迁移账号[" + source + "]->接收迁移账号[" + target + "],共迁移 " + idss.size() + " 条记录");
        sysRecordMapper.insertSelective(sysRecord);
        return true;
    }

    private List<MemRelationship> getMemRelationshipsByRef(String sourceAccno) {
        MemRelationshipExample example = new MemRelationshipExample();
        example.createCriteria().andRefaccnoEqualTo(sourceAccno);
        return memRelationshipMapper.selectByExample(example);
    }

    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal(300);
        BigDecimal brokerageMoney = amount.multiply(new BigDecimal(70)).divide(BigDecimal.valueOf(100));
        System.out.println(brokerageMoney);
    }

}
