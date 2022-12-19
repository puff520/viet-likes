package com.likes.modules.agent.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemLevel;
import com.likes.common.mybatis.entity.MemLevelConfig;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.agent.service.AgentLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author abu
 * @version 1.0
 * @Description
 * @revise
 * @time 2018年6月8日 下午3:24:55
 */
@Service
public class AgentLoginServiceImpl extends BaseServiceImpl implements AgentLoginService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;

    @Override
    public LoginUser login(UsersRequest req, String source) {
        if (com.likes.common.util.StringUtils.isEmpty(req.getEmail())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "邮箱为空");
        }
        if (com.likes.common.util.StringUtils.isBlank(req.getPassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_172.getCode(), "密码为空");
        }
        MemLogin memLogin = null;
        if (com.likes.common.util.StringUtils.isNotBlank(req.getPassword())) {
            memLogin = loginByPassword(req);
        }
        // 存在
        // 获取详细信息
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(memLogin.getAccno());
        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "该账号已被删除");
        }
        // 设置loginUserAPP
        LoginUser loginUserAPP = new LoginUser();
        loginUserAPP.setLogintype(memLogin.getLogintype());
        loginUserAPP.setAccno(memBaseinfo.getAccno());
        loginUserAPP.setMobileno(memBaseinfo.getEmail());
        loginUserAPP.setNickname(memBaseinfo.getNickname());
        loginUserAPP.setMemname(memBaseinfo.getMemname());
        loginUserAPP.setMemid(memBaseinfo.getMemid());
        loginUserAPP.setRecomcode(memBaseinfo.getRecomcode());
        loginUserAPP.setAcclogin(memLogin.getAcclogin());
        loginUserAPP.setSourceType(source);
        loginUserAPP.setHeadimgurl(memBaseinfo.getHeadimg());
        MemLevel memLevel = memLevelMapperExt.selectByAccno(memBaseinfo.getAccno());
        if (memLevel == null) {
            loginUserAPP.setMemlevel(Constants.LEVEL_ONE);
            loginUserAPP.setLevelSeq(1);
        } else {
            MemLevelConfig memLevelConfig = memLevelConfigMapperExt.selectMemLevel(memBaseinfo.getAccno());
            loginUserAPP.setLevelSeq(memLevelConfig.getLevelSeq());
            loginUserAPP.setMemlevel(memLevel.getMemlevel());
        }
        String acctoken = RedisBusinessUtil.createAgentManageSessionID(userSessionKey, loginUserAPP, sysParamService);
        if (acctoken.isEmpty()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21007.getCode(), "系统异常，请稍后重试");
        }
        loginUserAPP.setAcctoken(acctoken);
        //记录在线人数
        RedisBusinessUtil.saveAgentOnline(memBaseinfo.getMemid().toString());
        return loginUserAPP;
    }


    private MemLogin loginByPassword(UsersRequest req) {
        if (com.likes.common.util.StringUtils.isBlank(req.getPassword())) {
            return null;
        }
        // 账号密码登陆
        // 获取用户是否存在
        // 先验证账号
        MemLogin loginParam = new MemLogin();
        loginParam.setAcclogin(req.getEmail());
        MemLogin memLogin = memLoginService.getMemLoginByParam(loginParam);
        if (memLogin == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1130.getCode(), "账号不存在");
        }
        if (!Constants.ACCOUNT_ONE.equals(memLogin.getAccstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_116.getCode(), "账号已禁用");
        }
        if (!req.getPassword().equals(memLogin.getPasswordmd5())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1131.getCode(), "账号/密码错误");
        }
        return memLogin;
    }


}
