package com.likes.common.service.member.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.RedisKeys;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.NameValueDO;
import com.likes.common.model.dto.OperatorDO;
import com.likes.common.model.dto.member.MemLoginDO;
import com.likes.common.model.dto.member.UsersBdDO;
import com.likes.common.model.dto.member.UsersDO;
import com.likes.common.model.request.TrialAccountRequest;
import com.likes.common.model.request.UserRequest;
import com.likes.common.model.vo.finance.FinanceMemBaseVo;
import com.likes.common.model.vo.member.UserVO;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.mybatis.mapperext.member.MemLoginMapperExt;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MemLoginServiceImpl implements MemLoginService {

    @Resource
    private MemLoginMapperExt memLoginMapperExt;
    @Resource
    private MemLoginMapper memLoginMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 修改登录表
     */
    @Override
    public void updateLogin(MemLogin memLoginParam) {
        memLoginMapperExt.updateLogin(memLoginParam);
    }

    /**
     * 根据tel查找登录信息
     */
    @Override
    public MemLogin findByMobile(String tel) {
        return memLoginMapperExt.findByMobile(tel);
    }

    @Override
    public MemLogin findByEmail(String email) {
        return memLoginMapperExt.findByMobile(email);
    }

    /**
     * 插入
     */
    @Override
    public int insertSelectiveMemLogin(MemLogin memLogin) {
        int n = memLoginMapperExt.insertSelectiveMemLogin(memLogin);
        RedisBusinessUtil.delAllRegisteredNumCache();
        return n;
    }

    /**
     * 根据tel查找登录信息
     */
    @Override
    public MemLogin findByEmailRegister(String email) {
        return memLoginMapperExt.findByEmailRegister(email);
    }

    /**
     * 查找登录信息
     */
    @Override
    public MemLogin getMemLoginByParam(MemLogin loginParam) {
        return memLoginMapperExt.getMemLoginByParam(loginParam);
    }

    /**
     * 根据账号查找登录信息
     */
    @Override
    public MemLogin selectByAcclogin(String acclogin) {
        return memLoginMapperExt.selectByAcclogin(acclogin);
    }

    /**
     * 查找eureka登录信息
     */
    @Override
    public LoginUser selectEurekaByAcclogin(String acclogin, String password) {
        return memLoginMapperExt.selectEurekaByAcclogin(acclogin, password);
    }

    @Override
    @DS("slave")
    public Page<UserVO> userList(UserRequest req, RowBounds rowBounds) {
        if (req.getStartTime() != null && req.getStartTime() != ""){
            req.setStartTime(req.getStartTime()+" 00:00:00");
        }
        if (req.getEndTime() != null && req.getEndTime() != ""){
            req.setEndTime(req.getEndTime()+" 23:59:59");
        }
        if(StringUtils.isNotBlank(req.getHeadAgent())){
           MemLogin memLogin = this.findByAccloginRegister(req.getHeadAgent());
            if(ObjectUtil.isNotNull(memLogin)){
                req.setHeadAccno(memLogin.getAccno());
            }
        }
        return memLoginMapperExt.userList(req, rowBounds);
    }

    @Override
    public MemLogin selectByAccno(String accno) {
        return memLoginMapperExt.selectByAccno(accno);
    }

    @Override
    public Page<UsersBdDO> userBdList(UserRequest req, RowBounds rowBounds) {
        String key = RedisKeys.LIVE_SYSTEM_USER + JSONObject.toJSONString(req) + JSONObject.toJSONString(rowBounds);
        if (redisTemplate.hasKey(key)) {
            return (Page<UsersBdDO>) redisTemplate.opsForValue().get(key);
        } else {
            Page<UsersBdDO> usersBdDOS = memLoginMapperExt.userBdList(req, rowBounds);
            if (CollectionUtil.isNotEmpty(usersBdDOS)) {
                for (UsersBdDO bdUser : usersBdDOS) {
                    if (bdUser.getPhoneno() != null && bdUser.getPhoneno().length() > 10) {
                        String mobileno = bdUser.getPhoneno();
                        String substring1 = mobileno.substring(0, 3);
                        String substring2 = mobileno.substring(mobileno.length() - 3);
                        bdUser.setPhoneno(substring1 + "******" + substring2);
                    }
                }
                redisTemplate.opsForValue().set(key, usersBdDOS);
                redisTemplate.opsForValue().set(key + "count", usersBdDOS.getTotal());
                return usersBdDOS;
            }
        }
        return null;
    }

    @Override
    public UsersDO getAPPUserDetail(String accno) {
        return memLoginMapperExt.getAPPUserDetail(accno);
    }

    @Override
    public UsersBdDO getBdUserDetail(String accno) {
        return memLoginMapperExt.getBdUserDetail(accno);
    }

//    @Override
//    public Page<UsersDO> userAnchorList(UserRequest req, RowBounds rowBounds) {
//        return memLoginMapperExt.userAnchorList(req, rowBounds);
//    }

    @Override
    public UsersDO getAPPAnchorUserDetail(String accno) {
        return memLoginMapperExt.getAPPAnchorUserDetail(accno);
    }

    @Override
    public MemLogin getRepeat(MemLoginDO memLogin) {
        return memLoginMapperExt.getRepeat(memLogin);
    }

    @Override
    public int insertMemLogin(MemLoginDO memLogin) {
        return memLoginMapperExt.insertMemLogin(memLogin);
    }

    @Override
    public List<NameValueDO> getAddressStatics() {
        return memLoginMapperExt.getAddressStatics();
    }

    @Override
    public List<NameValueDO> getAddressStaticsTen() {
        return memLoginMapperExt.getAddressStaticsTen();
    }

    @Override
    public Integer getAllRegisteredNum() {
        Integer num = RedisBusinessUtil.getAllRegisteredNumCache();
        if (null == num) {
            num = memLoginMapperExt.getAllRegisteredNum();
            RedisBusinessUtil.addAllRegisteredNumCache(num);
        }
        return num;
    }

    @Override
    public Integer getThisWeekRegisteredNum(Map<String, Object> param) {
        return memLoginMapperExt.getThisWeekRegisteredNum(param);
    }

    @Override
    public Integer getAllAnchorNum() {
        return memLoginMapperExt.getAllAnchorNum();
    }

    @Override
    public MemLogin findByAccno(String accno) {
        return memLoginMapperExt.findByAccno(accno);
    }

    @Override
    public MemLogin findByAccloginAndLoginidRegister(String acclogin, Long loginid) {
        return memLoginMapperExt.findByAccloginAndLoginidRegister(acclogin, loginid);
    }

    @Override
    public MemLogin findByAccloginRegister(String acclogin) {
        return memLoginMapperExt.findByAccloginRegister(acclogin);
    }

    @Override
    public List<OperatorDO> getOperatorList() {
        return memLoginMapperExt.getOperatorList();
    }

    @Override
    public Integer getLevelNum(Map<String, Object> param) {
        return memLoginMapperExt.getLevelNum(param);
    }

    @Override
    public List<Map<Integer, String>> getNewOrder() {
        return memLoginMapperExt.getNewOrder();
    }

    @Override
    public Integer getThisWeekAnchorNum(Map<String, Object> param) {
        return memLoginMapperExt.getThisWeekAnchorNum(param);
    }

    @Override
    public int saveMemLogin(MemLoginDO memLogin) {
        return memLoginMapperExt.saveMemLogin(memLogin);
    }

    @Override
    public Page<FinanceMemBaseVo> selectMemForFInance(String email, String nickname, String uniqueId, RowBounds rowBounds) {
        return memLoginMapperExt.selectMemForFInance(email,nickname, uniqueId, rowBounds);
    }

    @Override
    public Page<UserVO> trialList(TrialAccountRequest req, RowBounds toRowBounds) {
        return memLoginMapperExt.trialList(req, toRowBounds);
    }

    @Override
    public int updateByPrimaryKeySelective(MemLogin memLoginParam) {
        return memLoginMapper.updateByPrimaryKeySelective(memLoginParam);
    }
}
