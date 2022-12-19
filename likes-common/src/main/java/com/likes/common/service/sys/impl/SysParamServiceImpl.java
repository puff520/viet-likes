package com.likes.common.service.sys.impl;

import com.likes.common.config.CacheConfigs;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.vo.sys.SystemInfoSetVO;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.entity.SysParameterExample;
import com.likes.common.mybatis.mapper.SysParameterMapper;
import com.likes.common.mybatis.mapperext.sys.SysParameterMapperExt;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import com.likes.common.util.cache.CacheUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysParamServiceImpl implements SysParamService {
    private static final Logger logger = LoggerFactory.getLogger(SysParamServiceImpl.class);
    @Resource
    private SysParameterMapper sysParameterMapper;
    @Resource
    private SysParameterMapperExt sysParameterMapperExt;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public SysParameter getByCode(SysParameterEnum sysParameterEnum) throws BusinessException {
        return getByCode(null == sysParameterEnum ? null : sysParameterEnum.getCode());
    }

    @Override
    public SysParameter getByCode(String sysparamcode) throws BusinessException {
        SysParameter sysParameter = RedisBusinessUtil.getSysParameter(sysparamcode);
        if (sysParameter == null) {
            sysParameter = sysParameterMapperExt.selectByCode(sysparamcode);
            RedisBusinessUtil.addSysParameter(sysParameter);
        }
        return sysParameter;
    }

    @Override
    public PageResult getList(SysParameter req, PageBounds page) {
        Page<SysParameter> list = sysParameterMapperExt.getList(req, page.toRowBounds());
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public SysParameter getDetails(Long sysparamid) {
        SysParameter sysParameter = sysParameterMapper.selectByPrimaryKey(sysparamid);
        if (sysParameter != null && !sysParameter.getIsDelete()) {
            return sysParameter;
        }
        return null;
    }

    @Override
    public Long save(SysParameter req, LoginUser loginUser) {
        //查询是否有相同的代码
        /*SysParameter s = sysParameterMapperExt.getRepeat(req);
        if (s != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_2004.getCode(), "代码重复");
        }*/
        if (req.getStatus() == null) {
            req.setStatus(Constants.STATUS_0);
        }

        req.setStatus(0);
        req.setCreateUser(loginUser.getAccno());
        req.setUpdateUser(loginUser.getAccno());
        sysParameterMapper.insertSelective(req);
        RedisBusinessUtil.deleteSysParameter(req.getSysparamcode());
        return req.getSysparamid();
    }

    @Override
    public Integer edit(SysParameter sysParameter) {
        if (null == sysParameter) {
            return 0;
        }
        return sysParameterMapper.updateByPrimaryKeySelective(sysParameter);
    }

    @Override
    public Integer edit(SysParameter req, LoginUser loginUser) {
        if (StringUtils.isNotEmpty(req.getSysparamcode())) {
            //查询是否有相同的代码
            /*SysParameter s = sysParameterMapperExt.getRepeat(req);
            if (s != null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_2004.getCode(), "代码重复");
            }*/
            req.setUpdateUser(loginUser.getAccno());
            sysParameterMapper.updateByPrimaryKeySelective(req);
            RedisBusinessUtil.deleteSysParameter(req.getSysparamcode());
            Integer status = CacheUtils.getSysParameter(req.getSysparamcode().toUpperCase(), sysParameterMapperExt).getStatus();
            return status;
        }
        req.setUpdateUser(loginUser.getAccno());
        sysParameterMapper.updateByPrimaryKeySelective(req);
        SysParameter sysParameter = sysParameterMapper.selectByPrimaryKey(req.getSysparamid());

        // 删除客服缓存
        if (SysParameterEnum.KEFU_QQ_LINK_NUMBER.name().equals(sysParameter.getSysparamcode())) {
            RedisBusinessUtil.deleteFuzzyMatchCache(SysParameterEnum.KEFU_QQ_LINK_NUMBER.name());
        }

        RedisBusinessUtil.deleteSysParameter(sysParameter.getSysparamcode());
        RedisBusinessUtil.addSysParameter(sysParameter);

        return sysParameter.getStatus();
    }

    @Override
    public String doDel(Long sysparaid, LoginUser loginUser) {
        SysParameter sysParameter = sysParameterMapper.selectByPrimaryKey(sysparaid);
        SysParameter req = new SysParameter();
        req.setSysparamid(sysparaid);
        req.setIsDelete(true);
        req.setUpdateUser(loginUser.getAccno());
        sysParameterMapper.updateByPrimaryKeySelective(req);
        RedisBusinessUtil.deleteSysParameter(sysParameter.getSysparamcode());
        return Constants.SUCCESS_MSG;
    }

    @Override
    public List<String> getSameCodeParamList(String code, String sort) throws BusinessException {
        List<String> list = RedisBusinessUtil.getSameParamsList(code, sort);
        if (CollectionUtil.isEmpty(list)) {
            list = sysParameterMapperExt.getSameCodeParamList(code, sort);
            RedisBusinessUtil.addSameParamsList(code, sort, list);
        }
        return null == list ? new ArrayList<>() : list;
    }

    @Override
    public List<SysParameter> queryByCodeNames(List<String> codeNames) {
        List<SysParameter> list = null;
        if (CollectionUtil.isNotEmpty(codeNames)) {
            list = sysParameterMapperExt.queryByCodeNames(codeNames);
        }
        return null == list ? new ArrayList<>() : list;
    }

    @Override
    public Map<String, String> getParamValueByCode(List<String> codes) {
        Map<String, String> map = new HashMap<>();
        if (CollectionUtil.isNotEmpty(codes)) {
            List<SysParameter> systemInfos = sysParameterMapperExt.queryByCodeNames(codes);
            for (SysParameter info : systemInfos) {
                map.put(info.getSysparamcode(), info.getSysparamvalue());
            }
        }
        return map;
    }


    /**
     * 获取系统设置的信息
     *
     * @return
     */
    @Override
    public SystemInfoSetVO listSystemInfoSet() {
        List<SysParameter> list = queryList();
        ArrayList<String> names = new ArrayList<>(4);
        names.add(SysParameterEnum.ASTRICT_IP_LOGIN_STATUS.getCode());
        names.add(SysParameterEnum.ASTRICT_IP_GROUP.getCode());
        names.add(SysParameterEnum.HINT_REFRESH_TIME.getCode());
        names.add(SysParameterEnum.HINT_VOICE_STATUS.getCode());

        SystemInfoSetVO systemInfoSetVO = new SystemInfoSetVO();
        if (CollectionUtil.isEmpty(list)) {
            return systemInfoSetVO;
        }

        for (SysParameter systemInfo : list) {
            String systemInfoName = systemInfo.getSysparamcode();
            if (SysParameterEnum.ASTRICT_IP_LOGIN_STATUS.getCode().equalsIgnoreCase(systemInfoName)) {
                systemInfoSetVO.setAstrictIpLoginStatus(systemInfo.getSysparamvalue());
                continue;
            }
            if (SysParameterEnum.ASTRICT_IP_GROUP.getCode().equalsIgnoreCase(systemInfoName)) {
                systemInfoSetVO.setAstrictIpGroup(systemInfo.getSysparamvalue());
                continue;
            }
            if (SysParameterEnum.HINT_REFRESH_TIME.getCode().equalsIgnoreCase(systemInfoName)) {
                systemInfoSetVO.setHintRefreshTime(systemInfo.getSysparamvalue());
                continue;
            }
            if (SysParameterEnum.HINT_VOICE_STATUS.getCode().equalsIgnoreCase(systemInfoName)) {
                systemInfoSetVO.setHintVoiceStatus(systemInfo.getSysparamvalue());
                continue;
            }
        }

        return systemInfoSetVO;
    }

    @Override
    public Map<String, Object> listProductSet() {
        List<SysParameter> list = queryList();
        Map<String, Object> result = new HashMap<>();
        if (CollectionUtil.isNotEmpty(list)) {
            for (SysParameter info : list) {
                result.put(info.getSysparamcode(), info.getSysparamvalue());
            }
        }
        return result;
    }

    @Override
    public Map<String, SysParameter> getProductsByName(List<String> names) {
        if (CollectionUtil.isEmpty(names)) {
            return null;
        }
        List<SysParameter> list = queryList();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<SysParameter> systemInfos = list.stream().filter(item -> names.contains(item.getSysparamcode())).collect(Collectors.toList());
        Map<String, SysParameter> map = new HashMap<>();
        for (SysParameter info : systemInfos) {
            map.put(info.getSysparamcode(), info);
        }
        return map;
    }

    @Override
    public List<SysParameter> queryList() {
        List<SysParameter> list = RedisBusinessUtil.getSysParameterList();
        if (CollectionUtil.isEmpty(list)) {
            SysParameterExample example = new SysParameterExample();
            list = sysParameterMapper.selectByExample(example);
            RedisBusinessUtil.addSysParameterList(list);
        }
        return list;
    }

    @Override
    public List<SysParameter> queryList(SysParameter systemInfo) {
        if (null == systemInfo || org.apache.commons.lang3.StringUtils.isEmpty(systemInfo.getSysparamvalue())) {
            return new ArrayList<>();
        }
        List<SysParameter> list = queryList();
        if (CollectionUtil.isNotEmpty(list)) {
            list = list.stream().filter(item -> systemInfo.getSysparamvalue().equals(item.getSysparamvalue())).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public SysParameter queryByName(String name) {
        return CacheUtils.getSysParameter(name, sysParameterMapperExt);
    }

    @Override
    public List<SysParameter> reloadAll() {
        List<SysParameter> list = sysParameterMapper.selectByExample(null);
        if (CollectionUtil.isNotEmpty(list)) {
            redisTemplate.execute(new SessionCallback() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {
                    for (SysParameter info : list) {
                        if (null == info) {
                            continue;
                        }
                        String key = info.getSysparamcode().toUpperCase();
                        String keyVal = info.getSysparamcode().toUpperCase() + Constants.SYSTEM_INFO_VALUE_SUFFIX;
                        operations.opsForValue().set(key, info);
                        operations.opsForValue().set(keyVal, info.getSysparamvalue());
                    }
                    return null;
                }
            });
        }
        return list;
    }

    @Override
    public String queryRecommendAuth() {
        String value = RedisBusinessUtil.getSysParameterValue(SysParameterEnum.REGISTER_ACCOUNT_CAN_RECOMMEND);
        if (org.apache.commons.lang3.StringUtils.isEmpty(value)) {
            SysParameter info = this.queryByName(SysParameterEnum.REGISTER_ACCOUNT_CAN_RECOMMEND.getCode());
            value = null == info ? null : info.getSysparamvalue();
        }
        value = org.apache.commons.lang3.StringUtils.isEmpty(value) ? "0" : value;
        redisTemplate.opsForValue().set(SysParameterEnum.REGISTER_ACCOUNT_CAN_RECOMMEND.getCode() + Constants.SYSTEM_INFO_VALUE_SUFFIX, value);
        return value;
    }

    @Override
    public List<String> queryAllKey() {
        List<String> keys = sysParameterMapperExt.queryAllKey();
        return null == keys ? new ArrayList<>() : keys;
    }

    @Override
    public boolean deleteAllCaches() {
        List<String> keys = queryAllKey();
        Long effects = null;
        if (keys.size() > 0) {
            Set<String> keySet = new HashSet<>();
            for (String key : keys) {
                key = key.toUpperCase();
                keySet.add(key);
                keySet.add(key + Constants.SYSTEM_INFO_VALUE_SUFFIX);
            }
            effects = redisTemplate.delete(keySet);

        }
        logger.info("sysParams deleteAllCaches keys.size:{} effects:{}", keys.size(), effects);
        return null != effects && effects > 0;
    }

    @Override
    public List<SysParameter> queryBasAnchorPlatformConfigs(String platformCode) {
        List<SysParameter> basAnchorPlatformConfigs = (List<SysParameter>) redisTemplate.opsForValue().get(RedisKeys.LIVE_BAS_ANCHOR_PLATFORM_CONFIG_KEY + platformCode);
        if (CollectionUtil.isNotEmpty(basAnchorPlatformConfigs)) {
            return basAnchorPlatformConfigs;
        }

        SysParameterExample example = new SysParameterExample();
        example.createCriteria()
                .andSysparamcodeEqualTo(Constants.LIVE_BAS_ANCHOR_PLATFORM + platformCode)
                .andIsDeleteEqualTo(false)
                .andStatusEqualTo(Constants.STATUS_0);
        basAnchorPlatformConfigs = sysParameterMapper.selectByExample(example);
        RedisBusinessUtil.set(RedisKeys.LIVE_BAS_ANCHOR_PLATFORM_CONFIG_KEY + platformCode, basAnchorPlatformConfigs);

        return null == basAnchorPlatformConfigs ? new ArrayList<>() : basAnchorPlatformConfigs;
    }

    @Override
    public Map<String, Object> queryBasAnchorPlatformInfos(String platformCode) {
        if (StringUtils.isEmpty(platformCode)) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "主播平台码不能为空");
        }
        List<SysParameter> basAnchorPlatformConfigs = queryBasAnchorPlatformConfigs(platformCode);
        Map<String, Object> result = new HashMap<>();
        if (basAnchorPlatformConfigs.size() > 0) {
            for (SysParameter sysParameter : basAnchorPlatformConfigs) {
                result.put(sysParameter.getSysparamname(), sysParameter.getSysparamvalue());
            }
        }

        if (CollectionUtil.isEmpty(result)) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "平台ID不存在");
        }
        return result;
    }

    @Override
    public Map<String, String> getkefuwechat() {
        SysParameter sp = this.getByCode(SysParameterEnum.LIVE_HOME_KEFU.name());
        Map<String, String> map = new HashMap<>(2);
        map.put("link", "");
        map.put("qq", "");
        if (sp == null || org.apache.commons.lang.StringUtils.isEmpty(sp.getSysparamvalue())) {
            return map;
        }
        map.put("link", sp.getSysparamvalue());
        map.put("qq", this.getSameCodeParamsValueString(SysParameterEnum.KEFU_QQ_LINK_NUMBER.name()));
        return map;
    }

    @Override
    public void initSysParams() {
        List<String> codes = new ArrayList<>();
        codes.add(SysParameterEnum.PLATFORM_NAME.getCode());
        List<SysParameter> sysParameters = sysParameterMapperExt.queryByCodeNames(codes);
        if (CollectionUtil.isEmpty(sysParameters)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数平台码配置异常");
        }

        Map<String, SysParameter> parameterMap = new HashMap<>();
        for (SysParameter parameter : sysParameters) {
            parameterMap.put(parameter.getSysparamcode().toUpperCase(), parameter);
        }

        SysParameter platform = parameterMap.get(SysParameterEnum.PLATFORM_NAME.name());
        if (null == platform || StringUtils.isEmpty(platform.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数平台码配置异常");
        }
        CacheConfigs.platform = platform.getSysparamvalue().toUpperCase();
        logger.info("平台码:{} ", CacheConfigs.platform);

    }


}
