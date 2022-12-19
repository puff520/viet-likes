package com.likes.common.service.member.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemLevelConfigDto;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.MemLevelConfigMapper;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.LanguageUtil;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: MemLevelConfigServiceImpl
 * Description: 描述
 *
 * @author dapan
 * @since JDK 1.8
 * date: 2020/6/19 14:42
 */
@Service
public class MemLevelConfigServiceImpl implements MemLevelConfigService {

    @Resource
    private MemLevelConfigMapper memLevelConfigMapper;

    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    // Mapper###############################################################


    @Resource
    private SysParamService sysParamService;


    /**
     * 根据条件 删除记录
     */
    @Override
    public int deleteByExample(MemLevelConfigExample example) {
        int n = memLevelConfigMapper.deleteByExample(example);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    /**
     * 根据主键 删除记录
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        int n = memLevelConfigMapper.deleteByPrimaryKey(id);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    /**
     * 添加记录
     */
    @Override
    public int insert(MemLevelConfig record) {
        int n = memLevelConfigMapper.insert(record);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    /**
     * 添加记录（非空）
     */
    @Override
    public int insertSelective(MemLevelConfig record) {
        int n = memLevelConfigMapper.insertSelective(record);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    /**
     * 根据条件 查询单条记录
     */
    @Override
    public MemLevelConfig selectOneByExample(MemLevelConfigExample example) {
        return memLevelConfigMapper.selectOneByExample(example);
    }

    /**
     * 根据条件 查询记录
     */
    @Override
    public List<MemLevelConfig> selectByExample(MemLevelConfigExample example) {

        return memLevelConfigMapper.selectByExample(example);
    }

    /**
     * 根据主键 查询记录
     */
    @Override
    public MemLevelConfig selectByPrimaryKey(Long id) {
//        MemLevelConfig memLevelConfig = RedisBusinessUtil.get(RedisKeys.MEM_LEVEL_CONFIG + id);
//        if (!ObjectUtils.isEmpty(memLevelConfig)) {
//            return memLevelConfig;
//        }
        MemLevelConfig memLevelConfig = memLevelConfigMapper.selectByPrimaryKey(id);
        if (!ObjectUtils.isEmpty(memLevelConfig)) {
            RedisBusinessUtil.set(RedisKeys.MEM_LEVEL_CONFIG + id, memLevelConfig);
        }
        return memLevelConfig;
    }


    /**
     * 根据条件 更新记录（非空）
     */
    @Override
    public int updateByExampleSelective(@Param("record") MemLevelConfig record, @Param("example") MemLevelConfigExample example) {
        int n = memLevelConfigMapper.updateByExampleSelective(record, example);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    /**
     * 根据条件 更新记录
     */
    @Override
    public int updateByExample(@Param("record") MemLevelConfig record, @Param("example") MemLevelConfigExample example) {
        int n = memLevelConfigMapper.updateByExample(record, example);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    /**
     * 根据主键 更新记录（非空）
     */
    @Override
    public int updateByPrimaryKeySelective(MemLevelConfig record) {
        int n = memLevelConfigMapper.updateByPrimaryKeySelective(record);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    /**
     * 根据主键 更新记录
     */
    @Override
    public int updateByPrimaryKey(MemLevelConfig record) {
        int n = memLevelConfigMapper.updateByPrimaryKey(record);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
        RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
        return n;
    }

    // MapperExt###############################################################

    /**
     * 依据充值金额 查询出当前等级和下一个等级
     */
    @Override
    public List<MemLevelConfig> selectMemLevelConfigByAmount(Double amount) {
        return memLevelConfigMapperExt.selectMemLevelConfigByAmount(amount);
    }

    @Override
    public List<MemLevelConfig> selectMemLevlConfigByLevelSeq() {
        return memLevelConfigMapperExt.selectMemLevlConfigByLevelSeq();
    }

    @Override
    public List<MemLevelConfig> selectMemLevlConfigDownLevelSeq(Integer leveseq) {
        return memLevelConfigMapperExt.selectMemLevlConfigDownLevelSeq(leveseq);
    }

    // live-manage###############################################################

    /**
     * 分页查询会员等级配置
     */
    @Override
    public PageResult<List<MemLevelConfig>> memLevelConfigListForManager(Integer pageNo, Integer pageSize) {
        Example example = new Example(MemLevelConfig.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("recharge_amount desc");

        int totalCount = memLevelConfigMapper.selectCountByExample(example);
        PageResult<List<MemLevelConfig>> pageResult = PageResult.getPageResult(pageNo, pageSize, totalCount);
        if (totalCount > 0) {
            PageHelper.startPage((pageNo - 1) * pageSize, pageSize);
            List<MemLevelConfig> list = memLevelConfigMapper.selectByExample(example);
            pageResult.setData(list);
        }
        return pageResult;
    }


    /**
     * 新增会员等级配置
     */
    @Override
    public boolean insertForManager(MemLevelConfig memLevelConfig) {

        Example example = new Example(MemLevelConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("levelSeq", memLevelConfig.getLevelSeq());

        int count = memLevelConfigMapper.selectCountByExample(example);
        if (count != 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_164.getCode(), "会员等级重复");
        }
        if (memLevelConfigMapper.insertSelective(memLevelConfig) > 0) {
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
            return true;
        } else {
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
            return false;
        }
    }

    /**
     * 修改会员等级配置
     */
    @Override
    public boolean updateForManager(MemLevelConfig memLevelConfig) {
        if (memLevelConfigMapper.updateByPrimaryKeySelective(memLevelConfig) > 0) {
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
            return true;
        } else {
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
            return false;
        }
    }

    /**
     * 删除会员等级配置
     */
    @Override
    public boolean deleteForManager(Long id) {
        if (memLevelConfigMapper.deleteByPrimaryKey(id) > 0) {
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG_ALL);
            return true;
        } else {
            RedisBusinessUtil.delete(RedisKeys.MEM_LEVEL_CONFIG);
            return false;
        }
    }


    /**
     * 获取所有的级别配置列表
     */
    @Override
    public List<MemLevelConfig> memLevelConfigAllList() {
        List<MemLevelConfig> memLevelConfigList = RedisBusinessUtil.get(RedisKeys.MEM_LEVEL_CONFIG);
        if (CollectionUtils.isEmpty(memLevelConfigList)) {

            Example example = new Example(MemLevelConfig.class);
            Example.Criteria criteria = example.createCriteria();
            example.setOrderByClause("recharge_amount desc");
            memLevelConfigList = memLevelConfigMapper.selectByExample(example);
        }
        return memLevelConfigList;
    }

    /**
     * 获取所有的级别配置Map
     */
    @Override
    public HashMap<String, MemLevelConfig> memLevelConfigAllMap() {
        HashMap<String, MemLevelConfig> map = new HashMap<>();
        List<MemLevelConfig> list = memLevelConfigAllList();
        for (MemLevelConfig memLevelConfig : list) {
            map.put(memLevelConfig.getLevel(), memLevelConfig);
        }
        return map;
    }

    @Override
    @DS("slave")
    public MemberLevelResponse getMemLevelConfig(String accno) {
        MemberLevelResponse response = new MemberLevelResponse();
        if (StringUtils.isNotEmpty(accno)) {
            MemLevel memLevel = memLevelMapperExt.selectByAccno(accno);
            String startDate = DateUtils.getDateString(memLevel.getCreateTime());
            String endDate = DateUtils.getDateString(memLevel.getExpireTime());
            String effectiveDate = startDate + "-" + endDate;
            response.setEffectiveDate(effectiveDate);
            if (!ObjectUtils.isEmpty(memLevel)) {
                chuliMemLevelConfig(response, memLevel);
                response.setRemainingTime(DateUtils.getDatePoor(memLevel.getExpireTime(), new Date()));
                if (response.getLevelSeq().equals(0)) {
                    String startDate1 = DateUtils.getDateString(memLevel.getCreateTime());
                    String endDate1 = DateUtils.getDateString(DateUtils.addDays(memLevel.getCreateTime(), 365));
                    String effectiveDate1 = startDate1 + "-" + endDate1;
                    response.setEffectiveDate(effectiveDate1);
                }
            } else {
                // 当前用户的会员等级都过期时处理
                memLevel = memLevelMapperExt.getMiniLevelByAccno(accno);
                chuliMemLevelConfig(response, memLevel);
                Date date = new Date();
                response.setRemainingTime(DateUtils.getDatePoor(date, date));
                if (response.getLevelSeq().equals(0)) {
                    String startDate1 = DateUtils.getDateString(memLevel.getCreateTime());
                    String endDate1 = DateUtils.getDateString(DateUtils.addDays(memLevel.getCreateTime(), 365));
                    String effectiveDate1 = startDate1 + "-" + endDate1;
                    response.setEffectiveDate(effectiveDate1);

                }
            }
        }
        return response;
    }

    private void chuliMemLevelConfig(MemberLevelResponse response, MemLevel memLevel) {
        Example example = new Example(MemLevelConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", memLevel.getLevelConfigId());
        MemLevelConfig memLevelConfigs = memLevelConfigMapper.selectOneByExample(example);
        response.setLevel(memLevelConfigs.getLevel());
        response.setLevelSeq(memLevelConfigs.getLevelSeq());
        response.setTaskNum(memLevelConfigs.getDoTaskTimes());
        response.setLevelId(memLevelConfigs.getId());
    }

    @Override
    public MemLevelConfig getMemLevelConfigForLevel(String level) {
        if (StringUtils.isBlank(level)) {
            return new MemLevelConfig();
        }
        return memLevelConfigAllList().stream().filter(memLevelConfig -> level.equals(memLevelConfig.getLevel())).findFirst().orElse(new MemLevelConfig());
    }
    @Override
    public  MemLevelConfig getMemLevelConfigForConfigId(Long configId){
        return memLevelConfigAllList().stream().filter(memLevelConfig -> configId.equals(memLevelConfig.getId())).findFirst().orElse(new MemLevelConfig());
    }


    @Override
    @DS("slave")
    public List<MemLevelConfigDto> memLevelConfigAlllist( ) {
        List<MemLevelConfigDto> lists = memLevelConfigMapperExt.GetMemLevelCountByAccno();
        return lists;
    }



}
