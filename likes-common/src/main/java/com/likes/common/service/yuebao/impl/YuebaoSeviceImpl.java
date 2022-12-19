package com.likes.common.service.yuebao.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.likes.common.enums.AssetOperateEnum;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.YueaboOperateEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.AssetChangeBO;
import com.likes.common.model.LoginUser;
import com.likes.common.model.YuebaoChangeBO;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.req.YuebaoOperateReq;
import com.likes.common.model.req.YuebaoRecordReq;
import com.likes.common.model.vo.AssetExpireVO;
import com.likes.common.model.vo.YuebaoBaseInfoVO;
import com.likes.common.model.vo.YuebaoRecordVO;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.*;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.yuebao.YuebaoChangeService;
import com.likes.common.service.yuebao.YuebaoService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.SnowflakeIdWorker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
@Log4j2
@Transactional
public class YuebaoSeviceImpl implements YuebaoService {

    @Resource
    private MemYuebaoMapper memYuebaoMapper;
    @Resource
    private MemYuebaoRecordMapper memYuebaoRecordMapper;
    @Resource
    private MemAssetRecordMapper memAssetRecordMapper;

    @Resource
    private MemAssetCfgMapper memAssetCfgMapper;
    @Resource
    private YuebaoChangeService yuebaoChangeService;
    @Resource
    private MemAssetMapper memAssetMapper;
    @Autowired
    private MemBaseinfoWriteService memGoldchangeService;
    @Autowired
    private MemBaseinfoService memBaseinfoService;

    /**
     * 查询余额宝账户总览
     *
     * @param loginUser
     * @return
     */
    @Override
    public YuebaoBaseInfoVO queryBaseInfo(LoginUser loginUser) {
        YuebaoBaseInfoVO baseInfoVO = new YuebaoBaseInfoVO();
        MemYuebao memYuebao = memYuebaoMapper.selectMemYuebaoByAccno(loginUser.getAccno());
        if (ObjectUtil.isNull(memYuebao)) {
            return baseInfoVO;
        }
        baseInfoVO.setMoney(memYuebao.getAmount());
        baseInfoVO.setTotalIncome(memYuebao.getTotalIncome());
        baseInfoVO.setYesterdayIncome(memYuebao.getYesterdayEarn());
        return baseInfoVO;
    }

    @Override
    public PageResult operateList(YuebaoRecordReq req, LoginUser loginUser) {
        PageBounds pageBounds = new PageBounds(req.getPageNo(), req.getPageSize());
        Page<YuebaoRecordVO> pageInfo = memYuebaoRecordMapper.operateList(loginUser.getAccno(), req.getChangeType(), pageBounds.toRowBounds());
        List<MemAssetCfg> assetCfgLIst = memAssetCfgMapper.selectAll();

        // 获取理财名称
        pageInfo.stream().map(recordVo -> assetCfgLIst.stream().filter(assetCfg -> recordVo.getAssetCfgId().equals(assetCfg.getId())).findFirst().map(assetCfg -> {
            if (req.getLanguage().equals("en")) {
                recordVo.setAssetName(assetCfg.getNameEn());
                if (recordVo.getAssetCfgId().equals(1L)) {
                    recordVo.setAssetName(assetCfgLIst.get(0).getNameEn());
                }
            } else if (req.getLanguage().equals("es")) {
                recordVo.setAssetName(assetCfg.getNameSp());
                if (recordVo.getAssetCfgId().equals(1L)) {
                    recordVo.setAssetName(assetCfgLIst.get(0).getNameSp());
                }
            } else if (req.getLanguage().equals("ar")) {
                recordVo.setAssetName(assetCfg.getNameAb());
                if (recordVo.getAssetCfgId().equals(1L)) {
                    recordVo.setAssetName(assetCfgLIst.get(0).getNameAb());
                }
            } else if (req.getLanguage().equals("fr")) {
                recordVo.setAssetName(assetCfg.getNameFn());
                if (recordVo.getAssetCfgId().equals(1L)) {
                    recordVo.setAssetName(assetCfgLIst.get(0).getNameFn());
                }
            }

            return recordVo;
        }).orElse(null)).collect(Collectors.toList());

        return PageResult.getPageResult(pageBounds, pageInfo);
    }


    @Override
    public PageResult earnList(YuebaoRecordReq req, LoginUser loginUser) {
        PageBounds pageBounds = new PageBounds(req.getPageNo(), req.getPageSize());
        Page<YuebaoRecordVO> pageInfo = memAssetRecordMapper.earnList(loginUser.getAccno(), pageBounds.toRowBounds());
        List<MemAssetCfg> assetCfgLIst = memAssetCfgMapper.selectAll();

        // 获取理财名称
        pageInfo.stream().map(recordVo -> assetCfgLIst.stream().filter(assetCfg -> recordVo.getAssetCfgId().equals(assetCfg.getId())).findFirst().map(assetCfg -> {
            if (req.getLanguage().equals("en")) {
                recordVo.setAssetName(assetCfg.getNameEn());
            } else if (req.getLanguage().equals("es")) {
                recordVo.setAssetName(assetCfg.getNameSp());
            } else if (req.getLanguage().equals("ar")) {
                recordVo.setAssetName(assetCfg.getNameAb());
            } else if (req.getLanguage().equals("fr")) {
                recordVo.setAssetName(assetCfg.getNameFn());
            }
            return recordVo;
        }).orElse(null)).collect(Collectors.toList());

        return PageResult.getPageResult(pageBounds, pageInfo);
    }

    @Override
    public boolean intoYuebao(YuebaoOperateReq req, LoginUser loginUser) {
        MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(loginUser.getAccno());
        if (membaseinfo.getGoldnum().intValue() < req.getOperateAmount().intValue()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11006.getCode(), "余额不足");
        }
        if (null == req.getOperateAmount()) {
            throw new BusinessException("金额为空");
        }
        if (req.getOperateAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("金额不能为负数");
        }
        if (new BigDecimal(req.getOperateAmount().intValue()).compareTo(req.getOperateAmount()) != 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11071.getCode(), "存入金额不能为小数");
        }
        if (membaseinfo.getLevel() < 1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11072.getCode(), "vip0无法存入余额宝");
        }

        //用户余额账变
        MemGoldchangeDO dto2 = new MemGoldchangeDO();
        dto2.setOpnote("用户手动转入余额宝");
        dto2.setQuantity(req.getOperateAmount().negate());
        dto2.setCreatTime(new Date());
        dto2.setAccno(loginUser.getAccno());
        dto2.setChangetype(GoldchangeEnum.YUEBAO_INTO.getValue());
        memGoldchangeService.updateUserBalance(dto2);

        YuebaoChangeBO dto = new YuebaoChangeBO();
        dto.setAccno(loginUser.getAccno());
        dto.setAssetCfgId(1L);
        dto.setBuyAssetCfgId(1L);
        dto.setChangeAmount(req.getOperateAmount());
        dto.setOperateEnum(YueaboOperateEnum.INTO);
        yuebaoChangeService.updateYuebaoAmount(dto);
        return true;
    }


    @Override
    public boolean outYuebao(YuebaoOperateReq req, LoginUser loginUser) {
        MemYuebao memYuebao = memYuebaoMapper.selectMemYuebaoByAccno(loginUser.getAccno());
        if (ObjectUtil.isNull(memYuebao)) {
            throw new BusinessException("请先开通余额宝");
        }
        if (memYuebao.getAmount().intValue() < req.getOperateAmount().intValue()) {
            throw new BusinessException("余额宝余额不足");
        }
        if (req.getOperateAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("金额不能为负数");
        }
        MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(loginUser.getAccno());
        if (membaseinfo.getLevel() < 1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11072.getCode(), "vip0无法存入余额宝");
        }
        YuebaoChangeBO yuebao = new YuebaoChangeBO();
        yuebao.setAccno(loginUser.getAccno());
        yuebao.setAssetCfgId(1L);
        yuebao.setBuyAssetCfgId(1L);
        yuebao.setChangeAmount(req.getOperateAmount());
        yuebao.setOperateEnum(YueaboOperateEnum.OUT);
        yuebaoChangeService.updateYuebaoAmount(yuebao);

        //用户余额账变
        MemGoldchangeDO balance = new MemGoldchangeDO();
        balance.setOpnote("理财产品转出到余额" + req.getAssetCfgId());
        balance.setQuantity(req.getOperateAmount());
        balance.setCreatTime(new Date());
        balance.setAccno(loginUser.getAccno());
        balance.setChangetype(GoldchangeEnum.YUEBAO_OUT.getValue());
        memGoldchangeService.updateUserBalance(balance);
        return true;
    }


    @Override
    public boolean deposit(YuebaoOperateReq req, LoginUser loginUser) {
        MemYuebao memYuebao = memYuebaoMapper.selectMemYuebaoByAccno(loginUser.getAccno());
        if (ObjectUtil.isNull(memYuebao)) {
            throw new BusinessException("请先开通余额宝");
        }
        if (req.getOperateAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("金额不能为负数");
        }
        if (memYuebao.getAmount().intValue() < req.getOperateAmount().intValue()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11081.getCode(), "余额宝余额不足");
        }
        int row = memAssetMapper.countInto(req.getAssetCfgId(), loginUser.getAccno());
        if (row >= 3) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11084.getCode(), "每天最大只能存入3次");
        }
        Example assetExample = new Example(MemAsset.class);
        Example.Criteria assetCriteria = assetExample.createCriteria();
        assetCriteria.andEqualTo("accno", loginUser.getAccno());
        assetCriteria.andEqualTo("assetCfgId", req.getAssetCfgId());
        MemAsset memAsset = memAssetMapper.selectOneByExample(assetExample);
        BigDecimal yczAmount = memAsset == null ? getTradeOffAmount(null) : memAsset.getAmount();
        Example cfgExample = new Example(MemAssetCfg.class);
        Example.Criteria cfgCriteria = cfgExample.createCriteria();
        cfgCriteria.andEqualTo("id", req.getAssetCfgId());
        MemAssetCfg memAssetCfg = memAssetCfgMapper.selectOneByExample(cfgExample);
        if (yczAmount.add(req.getOperateAmount()).intValue() > memAssetCfg.getMaxDepositAmount().intValue()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11083.getCode(), "大于最大可存金额");
        }

        YuebaoChangeBO dto1 = new YuebaoChangeBO();
        dto1.setAccno(loginUser.getAccno());
        dto1.setAssetCfgId(1L);
        dto1.setBuyAssetCfgId(req.getAssetCfgId());
        dto1.setChangeAmount(req.getOperateAmount());
        dto1.setOperateEnum(YueaboOperateEnum.OUT);
        yuebaoChangeService.updateYuebaoAmount(dto1);

        AssetChangeBO dto = new AssetChangeBO();
        dto.setAccno(loginUser.getAccno());
        dto.setAssetCfgId(req.getAssetCfgId());
        dto.setBuyAssetCfgId(1L);
        dto.setChangeAmount(req.getOperateAmount());

        dto.setAssetOperateEnum(AssetOperateEnum.DEPOSIT);
        yuebaoChangeService.updateAssetAmount(dto);
        memAsset = memAssetMapper.selectOneByExample(assetExample);

        MemAsset modifyMemAsset = new MemAsset();
        if (ObjectUtil.isNotNull(memAsset)) {
            Date now = new Date();
            Date takeTime = new Date();
            if (req.getAssetCfgId().equals(1L)) {
                takeTime = DateUtils.addDays(now, 1);
            } else if (req.getAssetCfgId().equals(2L)) {
                takeTime = DateUtils.addDays(now, 7);
            } else if (req.getAssetCfgId().equals(3L)) {
                takeTime = DateUtils.addDays(now, 14);
            } else if (req.getAssetCfgId().equals(4L)) {
                takeTime = DateUtils.addDays(now, 30);
            }
            modifyMemAsset.setTakeTime(takeTime);
            modifyMemAsset.setId(memAsset.getId());

            if (memAsset.getEarnStatus().equals(0)) {
                modifyMemAsset.setTodayEarn(getTradeOffAmount(null));
                modifyMemAsset.setYesterdayEarn(getTradeOffAmount(null));
                modifyMemAsset.setTotalEarn(getTradeOffAmount(null));
                modifyMemAsset.setUsableAmount(req.getOperateAmount());
                modifyMemAsset.setId(memAsset.getId());
                modifyMemAsset.setIntoTime(new Date());
                modifyMemAsset.setUpdateTime(new Date());
                modifyMemAsset.setEarnStatus(1);
                deductEarn(req, loginUser, memAsset.getTotalEarn());
            } else {
                if (now.before(memAsset.getTakeTime())) {
                    modifyMemAsset.setTodayEarn(getTradeOffAmount(null));
                    modifyMemAsset.setYesterdayEarn(getTradeOffAmount(null));
                    modifyMemAsset.setTotalEarn(getTradeOffAmount(null));
                    modifyMemAsset.setUsableAmount(memAsset.getAmount());
                } else {
                    if (0 < memAsset.getUsableAmount().doubleValue()) {
                        throw new BusinessException(StatusCode.LIVE_ERROR_11086.getCode(), "请先提取理财产品!");
                    }
                }
            }

            int j = memAssetMapper.updateByPrimaryKeySelective(modifyMemAsset);
            if (j != 1) {
                throw new BusinessException(StatusCode.OPERATION_FAILED.getCode(), "操作失败");
            }
        }
        return true;
    }

    @Override
    public boolean take(YuebaoOperateReq req, LoginUser loginUser) {
        BigDecimal usableAmount = usableAmount(req, loginUser);
        if (usableAmount.intValue() < req.getOperateAmount().intValue()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11082.getCode(), "理财产品余额不足");
        }
        if (req.getOperateAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("金额不能为负数");
        }
        MemAsset param = new MemAsset();
        param.setAccno(loginUser.getAccno());
        param.setAssetCfgId(req.getAssetCfgId());
        MemAsset memAsset = memAssetMapper.selectOne(param);
        MemAsset modifyMemAsset = new MemAsset();
        if (ObjectUtil.isNull(memAsset)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1200.getCode(), "记录不存在");
        }
        int row = memAssetMapper.countTake(req.getAssetCfgId(), loginUser.getAccno());
        if (row >= 3) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11085.getCode(), "每天最大只能提取3次");
        }
        BigDecimal sxf = new BigDecimal(0);
        BigDecimal takeAmount = req.getOperateAmount();
        Date now = new Date();
        if (memAsset.getTakeTime() != null && now.before(memAsset.getTakeTime()) && req.getAssetCfgId() > 1) {
            sxf = getTradeOffAmount(req.getOperateAmount().multiply(new BigDecimal(0.1)));
            takeAmount = takeAmount.subtract(sxf);

            AssetChangeBO deductDto = new AssetChangeBO();
            deductDto.setAccno(loginUser.getAccno());
            deductDto.setAssetCfgId(req.getAssetCfgId());
            deductDto.setBuyAssetCfgId(req.getAssetCfgId());
            deductDto.setChangeAmount(sxf);
            deductDto.setOperateEnum(YueaboOperateEnum.DEDUCT);
            deductDto.setAssetOperateEnum(AssetOperateEnum.TAKE);
            yuebaoChangeService.updateAssetAmount(deductDto);

        }

        AssetChangeBO assetChangeBO = new AssetChangeBO();
        assetChangeBO.setAccno(loginUser.getAccno());
        assetChangeBO.setAssetCfgId(req.getAssetCfgId());
        assetChangeBO.setBuyAssetCfgId(1L);
        assetChangeBO.setChangeAmount(getTradeOffAmount(takeAmount));
        assetChangeBO.setOperateEnum(YueaboOperateEnum.OUT);
        assetChangeBO.setAssetOperateEnum(AssetOperateEnum.TAKE);
        yuebaoChangeService.updateAssetAmount(assetChangeBO);

        YuebaoChangeBO change3 = new YuebaoChangeBO();
        change3.setAccno(loginUser.getAccno());
        change3.setAssetCfgId(1L);
        change3.setBuyAssetCfgId(req.getAssetCfgId());
        change3.setChangeAmount(getTradeOffAmount(takeAmount));

        change3.setOperateEnum(YueaboOperateEnum.INTO);
        change3.setAssetOperateEnum(AssetOperateEnum.TAKE);
        yuebaoChangeService.updateYuebaoAmount(change3);

        BigDecimal usableAmount2 = new BigDecimal(0);
        if (memAsset.getTotalEarn().doubleValue() > 0D) {
            usableAmount2 = memAsset.getUsableAmount();
        } else {
            usableAmount2 = memAsset.getAmount();
        }
        if (usableAmount2.subtract(req.getOperateAmount()).doubleValue() < 0.1D) {
            modifyMemAsset.setTakeTime(DateUtils.fiveZero());
            modifyMemAsset.setEarnStatus(0);
            modifyMemAsset.setId(memAsset.getId());
            memAssetMapper.updateByPrimaryKeySelective(modifyMemAsset);
        }
        return true;
    }


    @Override
    public BigDecimal usableAmount(YuebaoOperateReq req, LoginUser loginUser) {
        MemAsset param = new MemAsset();
        param.setAccno(loginUser.getAccno());
        param.setAssetCfgId(req.getAssetCfgId());
        MemAsset memAsset = memAssetMapper.selectOne(param);
        if (ObjectUtil.isNull(memAsset)) {
            return getTradeOffAmount(null);
        }
        Date now = new Date();
        if (memAsset.getTakeTime() != null && now.before(memAsset.getTakeTime()) && req.getAssetCfgId() > 1) {
            return memAsset.getAmount();
        } else {
            return memAsset.getUsableAmount();
        }
    }

    @Override
    public AssetExpireVO expire(YuebaoOperateReq req, LoginUser loginUser) {
        AssetExpireVO assetExpireVO = new AssetExpireVO();
        MemAsset param = new MemAsset();
        param.setAccno(loginUser.getAccno());
        param.setAssetCfgId(req.getAssetCfgId());
        MemAsset memAsset = memAssetMapper.selectOne(param);
        if (ObjectUtil.isNull(memAsset)) {
            assetExpireVO.setIsExpire(1);
            assetExpireVO.setAssetCfgId(req.getAssetCfgId());
            return assetExpireVO;
        }
        assetExpireVO.setExpireTime(DateUtils.formatDate(memAsset.getTakeTime()));
        Date now = new Date();
        if (memAsset.getTakeTime() != null && now.after(memAsset.getTakeTime())) {
            assetExpireVO.setIsExpire(1);
            assetExpireVO.setAssetCfgId(memAsset.getAssetCfgId());
        }
        return assetExpireVO;
    }

    public boolean deductEarn(YuebaoOperateReq req, LoginUser loginUser, BigDecimal totalEarn) throws RuntimeException {
        MemAssetRecord assetRecord = new MemAssetRecord();
        assetRecord.setAccno(loginUser.getAccno());
        assetRecord.setAssetCfgId(req.getAssetCfgId());
        assetRecord.setChangeAmount(totalEarn.negate());
        assetRecord.setChangeType(-1);
        assetRecord.setCreateTime(new Date());
        assetRecord.setSerialNo(SnowflakeIdWorker.generateId() + "");
        assetRecord.setCreateUser(loginUser.getAccno());
        memAssetRecordMapper.insertSelective(assetRecord);
        return true;
    }

}
