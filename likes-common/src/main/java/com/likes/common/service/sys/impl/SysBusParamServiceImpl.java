package com.likes.common.service.sys.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.response.SysBusparameterResp;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.mapper.SysBusparameterMapper;
import com.likes.common.mybatis.mapperext.sys.SysBusparameterMapperExt;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.util.*;
import com.likes.common.util.redis.RedisBaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * ClassName: SysBusParamServiceImpl
 * Description: 系统业务配置项
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 16:55
 */
@Service
public class SysBusParamServiceImpl implements SysBusParamService {
    private static final Logger logger = LoggerFactory.getLogger(SysBusParamServiceImpl.class);
    @Resource
    private SysBusparameterMapper sysBusparameterMapper;
    @Resource
    private SysBusparameterMapperExt sysBusparameterMapperExt;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public JSONArray list(SysBusparameter sysBusparameter, PageBounds page) {
        List<SysBusparameter> list = sysBusparameterMapperExt.getList(sysBusparameter);
        if (!CollectionUtils.isEmpty(list)) {
            JSONArray tree = CommonFunction.listToTree(JSONArray.parseArray(JSON.toJSONString(list)), "busparamcode",
                    "pbusparamcode", "children");
            return tree;
        }
        return null;
    }

    @Override
    public List<SysBusparameterResp> listAll(SysBusparameter sysBusparameter) {
        return this.sysBusparameterMapperExt.selectByCode(sysBusparameter.getBusparamcode());
    }

//    @Override
//    public List<SysBusparameter> getParamSub(String code) {
//        return null;
//    }

    @Override
    public Long doSaveParam(SysBusparameter sysBusparameter, LoginUser loginUser) {
        if (null == sysBusparameter.getSortby()) {
            sysBusparameter.setSortby(0);
        }
        if (null == sysBusparameter.getStatus()) {
            sysBusparameter.setStatus(0);
        }

        sysBusparameter.setStatus(0);
        sysBusparameter.setCreateUser(loginUser.getAccno());
        sysBusparameter.setUpdateUser(loginUser.getAccno());

        sysBusparameterMapper.insertSelective(sysBusparameter);
        RedisBusinessUtil.delBusParamcodeCache();
        return sysBusparameter.getBusparamid();
    }

    @Override
    public Integer doSaveParam(String refcode, String code, String value, LoginUser loginUser) {
        SysBusparameter sysBusparameter = new SysBusparameter();
        sysBusparameter.setPbusparamcode(refcode);
        sysBusparameter.setBusparamcode(code);
        sysBusparameter.setBusparamname(value);
        sysBusparameter.setStatus(0);
        sysBusparameter.setCreateUser(loginUser.getAccno());
        sysBusparameter.setUpdateUser(loginUser.getAccno());
        sysBusparameter.setUpdateTime(new Date());
        Integer result = sysBusparameterMapper.updateByCode(sysBusparameter);
        RedisBusinessUtil.delBusParamcodeCache();
        if(code.equals("rankingStart") || code.equals("rankingEnd")){
            RedisBaseUtil.delete("likes_ranking");
        }
        return result;
    }

    @Override
    public Integer doUpdateParam(SysBusparameter sysBusparameter, LoginUser loginUser) {
        /*if (StringUtils.isNotEmpty(sysBusparameter.getBusparamcode())) {
            // 查询code 是否 存在
            SysBusparameter s = sysBusparameterMapperExt.getRepeat(sysBusparameter);
            if (s != null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_2004.getCode(), "代码重复");
            }
        }*/
        sysBusparameter.setUpdateUser(loginUser.getAccno());
        sysBusparameterMapper.updateByPrimaryKeySelective(sysBusparameter);
        RedisBusinessUtil.delBusParamcodeCache();
        return sysBusparameterMapper.selectByPrimaryKey(sysBusparameter.getBusparamid()).getStatus();
    }

    @Override
    public SysBusparameter getDetail(Long busparamid) {
        SysBusparameter sysBusparameter = sysBusparameterMapper.selectByPrimaryKey(busparamid);
        if (sysBusparameter != null && !sysBusparameter.getIsDelete()) {
            return sysBusparameter;
        }
        return null;
    }

    @Override
    public String doDeleteParam(Long busparamid, LoginUser loginUser) {
        if (null == busparamid) {
            throw new BusinessException(StatusCode.LIVE_ERROR_997.getCode(), "ID为空");
        }
        SysBusparameter sysBusparameter = new SysBusparameter();
        sysBusparameter.setBusparamid(busparamid);
        sysBusparameter.setUpdateUser(loginUser.getAccno());
        sysBusparameter.setIsDelete(true);
        sysBusparameterMapper.updateByPrimaryKeySelective(sysBusparameter);
        // 是否删除所有的子节点 预留
        return Constants.SUCCESS_MSG;
    }

    @Override
    public PageResult getList(SysBusparameter sysBusparameter, PageBounds page) {
        Page<SysBusparameter> list = sysBusparameterMapperExt.getList2(sysBusparameter, page.toRowBounds());
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public List<SysBusparameter> selectByParedubpcode(String pbusparamcode) {
        List<SysBusparameter> list = null;
        if (StringUtils.isNotEmpty(pbusparamcode)) {
            list = sysBusparameterMapperExt.selectByParedubpcode(pbusparamcode,false);
        }
        return null == list ? new ArrayList<>() : list;
    }

    @Override
    public List<SysBusparameter> getList(SysBusparameter sysBusparameter) {
        return sysBusparameterMapperExt.getList(sysBusparameter);
    }

    @Override
    public SysBusparameter getRepeat(SysBusparameter sysBusparameter) {
        return sysBusparameterMapperExt.getRepeat(sysBusparameter);
    }

    @Override
    public SysBusparameter selectByBusparamcode(String busparamcode) {
        if (StringUtils.isEmpty(busparamcode)) {
            return null;
        }
        SysBusparameter sysBusparameter = RedisBusinessUtil.getBusParamcodeCache(busparamcode);
        if (sysBusparameter != null) {
            return sysBusparameter;
        }
        sysBusparameter = sysBusparameterMapperExt.selectByBusparamcode(busparamcode);
        RedisBusinessUtil.addBusParamcodeCache(busparamcode, sysBusparameter);
        return sysBusparameter;
    }

    @Override
    public List<String> getChild(String busparamcode) {
        List<String> list = null;
        if (StringUtils.isNotEmpty(busparamcode)) {
            list = sysBusparameterMapperExt.getChild(busparamcode);
        }
        return null == list ? new ArrayList<>() : list;
    }

    @Override
    public List<SysBusparameter> getByCodes(List<String> list) {
        List<SysBusparameter> result = null;
        if (CollectionUtil.isNotEmpty(list)) {
            result = sysBusparameterMapperExt.getByCodes(list);
        }
        return null == result ? new ArrayList<>() : result;
    }

    @Override
    public List<SysBusparameterResp> selectByCode(String busparamcode) {
        List<SysBusparameterResp> list = null;
        if (StringUtils.isNotEmpty(busparamcode)) {
            list = sysBusparameterMapperExt.selectByCode(busparamcode);
        }
        return null == list ? new ArrayList<>() : list;
    }

    @Override
    public Page<SysBusparameter> getList2(SysBusparameter req, RowBounds rowBounds) {
        Page<SysBusparameter> page = null;
        if (null != req) {
            page = sysBusparameterMapperExt.getList2(req, rowBounds);
        }
        //TODO 返回非 PageResult？？
        return null == page ? new Page<>() : page;
    }

    @Override
    public List<SysBusparameter> getNormList(String pbusparamcode) {
        List<SysBusparameter> list = null;
        if (StringUtils.isNotEmpty(pbusparamcode)) {
            list = sysBusparameterMapperExt.getNormList(pbusparamcode);
        }
        return null == list ? new ArrayList<>() : list;
    }

    @Override
    public SysBusparameter getRandomOne(String pbusparamcode) {
        return StringUtils.isEmpty(pbusparamcode) ? null : sysBusparameterMapperExt.getRandomOne(pbusparamcode);
    }

    @Override
    public List<String> queryAllBusKey() {
        List<String> keys = sysBusparameterMapperExt.queryAllBusKey();
        return null == keys ? new ArrayList<>() : keys;
    }

    @Override
    public boolean deleteAllCaches() {
        List<String> keys = queryAllBusKey();
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
        logger.info("sysBusParams deleteAllCaches keys.size:{} effects:{}", keys.size(), effects);
        return null != effects && effects > 0;
    }

    @Override
    public String getRandomHeadImg() {
        List<String> list =new LinkedList<>();
        list.add("http://img.smart-parttime.com/picture/ec4dacce3f9b454a82a73c302bc81b87.png");
        list.add("http://img.smart-parttime.com/picture/c7f3784618404e3d8ee86508e5e30823.png");
        list.add("http://img.smart-parttime.com/picture/6b3bade9f77e452f9bc79d136767704a.png");
        list.add("http://img.smart-parttime.com/picture/32a360a21b2b4844b5c04a3296adb591.png");
        list.add("http://img.smart-parttime.com/picture/0fb8ab7fc77a4f7294d3a22b3fe47a7f.png");
        list.add("http://img.smart-parttime.com/picture/02904f8ac17c4f8c97476a926b1d9898.png");

        if (!CollectionUtil.isEmpty(list)) {
            String sysBusparameter = list.get(RandomUtil.getRandomOne(0, list.size()));
            return sysBusparameter;
        }
        return null;
    }

    @Override
    public void updateNote(String noteinfo) {
        RedisBusinessUtil.set(RedisKeys.WEB_NOTE_INFO,noteinfo);
    }

    @Override
    public String getNote() {
        String result = RedisBusinessUtil.get(RedisKeys.WEB_NOTE_INFO);
        if (StringUtils.isEmpty(result)) {
            return "";
        }
        return  result;
    }
}
