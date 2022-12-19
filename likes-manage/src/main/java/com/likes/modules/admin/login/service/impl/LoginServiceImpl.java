/**
 *
 */
package com.likes.modules.admin.login.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.sys.SysFunctionDO;
import com.likes.common.model.dto.sys.SysFunctionorgForRoleDO;
import com.likes.common.mybatis.entity.*;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.BdUserService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.sys.*;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.encrypt.MD5;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.login.service.LoginService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author abu
 * @Description
 * @revise
 * @time 2018年6月8日 下午3:24:55
 * @version 1.0
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemLoginService memLoginService;
    @Resource
    private SysRolefuncService sysRolefuncService;
    @Resource
    private SysRoleinfoService sysRoleinfoService;
    @Resource
    private SysFunctionorgService sysFunctionorgService;
    @Resource
    private SysFuncinterfaceService sysFuncinterfaceService;
    @Resource
    private SysInfologService sysInfologService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private SysWhitelistService sysWhitelistService;
    @Resource
    private BdUserService bdUserService;


    @Override
    public void doModPassword(String newpsd, String oldpsd, LoginUser user) throws BusinessException {
        if (StringUtils.isEmpty(newpsd) || StringUtils.isEmpty(oldpsd)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11001.getCode(), "无效数据");
        }
        if (newpsd.equals(oldpsd)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您新设置的密码与原始密码重复");
        }
        // 检查 密码 是否 过于简单
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(newpsd)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
        }

        BdUser ml = this.bdUserService.selectByAccno(user.getAccno());
        if (null == ml) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "该账号信息不存在或已被删除");
        }
        if (!ml.getPasswordmd5().equals(oldpsd)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "旧密码错误");
        }
        ml.setPasswordmd5(newpsd);
        this.bdUserService.updateByPrimaryKeySelective(ml);
    }


    // APP后台管理管理登录
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginUser doLogin(String acclogin, String password, String clintipadd, String serverIp) {
        if (StringUtils.isEmpty(acclogin)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "账号不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21002.getCode(), "密码不能为空");
        }

        // 获取登录白名单
        SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode("manage_ip_white");
        if (sysBusparameter != null && sysBusparameter.getBusparamname().equals("0")) {
            SysParameter sysParameter = sysParamService.getByCode(SysParameterEnum.MANAGE_ISIP.getCode());
            if (sysParameter != null) {
                String ip = BaseUtil.getIpAddress(request);
                logger.info("ip白名单:{}", ip);
                // 代表启用了 后台登录白名单 查询
                if ("1".equals(sysParameter.getSysparamvalue())) {
                    SysWhitelist sysWhitelist = sysWhitelistService.findByIp(ModuleConstant.LIVE_MANAGE, ip);
                    if (sysWhitelist == null) {
                        logger.info("白名单列表信息:{}", JSONObject.toJSON(sysWhitelist));
                        throw new BusinessException(StatusCode.LIVE_ERROR_21099.getCode(), "请联系管理员添加服务白名单");
                    }
                }
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_21099.getCode(), "请联系管理员添加服务白名单");
            }
        }

        String LOGINNUMHOUMD5 = MD5.md5(Constants.LOGINNUMHOU + acclogin);
        Integer loginnum = RedisBusinessUtil.get(LOGINNUMHOUMD5);
        if (loginnum != null) {
            if (loginnum < 20) {
                Long seconds = RedisBusinessUtil.getExpire(LOGINNUMHOUMD5);
                if (seconds > 0) {
                    RedisBusinessUtil.set(LOGINNUMHOUMD5, loginnum + 1);
                    RedisBusinessUtil.setExpire(LOGINNUMHOUMD5, seconds);
                }
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "您的登录次数已达到最大限制，请12小时后再尝试。");
            }
        } else {
            RedisBusinessUtil.set(LOGINNUMHOUMD5, 1, 12 * 60 * 60l);
        }

        BdUser login = this.bdUserService.selectByAcclogin(acclogin);
        if (null == login)
        // throw new BusinessException(21003, "账号不存在/删除");
        {
            throw new BusinessException(StatusCode.LIVE_ERROR_21004.getCode(), "账号或密码错误");
        }
        if (!password.equals(login.getPasswordmd5())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21004.getCode(), "账号或密码错误");
        }
        if (!acclogin.equals(login.getAcclogin())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21004.getCode(), "账号或密码错误");
        }
        if (login.getAccstatus() == 9) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21005.getCode(), "账号已禁用");
        }

        LoginUser org = new LoginUser();
     /*   if (login.getLogintype() == null || (login.getLogintype().intValue() != 8 && login.getLogintype().intValue() != 9))
            throw new BusinessException(StatusCode.LIVE_ERROR_21006.getCode(), "您没有后台权限");*/

        // BeanUtils.copyProperties(login, org);
        //  BdUser bu = bdUserService.selectByAccno(login.getAccno());
//        BdUser bu = this.bdUserMapperExt.selectByAccno(login.getAccno());
        //  if (bu != null)
        BeanUtils.copyProperties(login, org);

        // 返回后台登录角色权限 把角色 存入对象
        SysRoleinfo sysRoleinfo = sysRoleinfoService.getRoleByAccno(login.getAccno());
        if (sysRoleinfo == null || sysRoleinfo.getSysrolestatus().equals(Constants.ACCSTATUS_9)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21009.getCode(), "无角色权限，不能访问该系统");
        }
        Long sysroleid = null;
        String rolename = null;
        if (sysRoleinfo != null) {
            sysroleid = sysRoleinfo.getSysroleid();
            org.setSysroleid(sysroleid);
            rolename = sysRoleinfo.getSysrolename();
            org.setRolename(rolename);
        }
        org.setSourceType(Constants.WEB_STRING);
        org.setLogintype(LoginUserTypeEnum.BACKADMIN.getCode());
        String acctoken = RedisBusinessUtil.createAppManageSessionID(userSessionKey, org, sysParamService);
        if (acctoken.isEmpty()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21007.getCode(), "系统异常，请稍后重试");
        }
        org.setAcctoken(acctoken);

        if (sysroleid != null) {
            List<SysFunctionorgForRoleDO> funlists = this.getSysFunctionorgForRole(sysroleid);
            if (CollectionUtils.isNotEmpty(funlists)) {
                if (!RedisBusinessUtil.exists(RedisKeys.LIVE_ROLE_INTERFACES + sysroleid)) {
                    // 设置角色 对于的 模块 对于的 接口
                    List<Long> param = funlists.stream().map(o -> o.getSfunid()).filter(Objects::nonNull).collect(Collectors.toList());
                    List<String> InterfaceUrls = sysFuncinterfaceService.getInterfaceUrlsByRole(param);
                    RedisBusinessUtil.set(RedisKeys.LIVE_ROLE_INTERFACES + sysroleid, JsonUtil.toJson(InterfaceUrls));
                } else {
                    String roleidforinterfaces = RedisBusinessUtil.get(RedisKeys.LIVE_ROLE_INTERFACES + sysroleid);
                    if ("[]".equals(roleidforinterfaces)) {
                        // 设置角色 对于的 模块 对于的 接口
                        List<Long> param = funlists.stream().map(o -> o.getSfunid()).filter(Objects::nonNull).collect(Collectors.toList());
                        List<String> InterfaceUrls = sysFuncinterfaceService.getInterfaceUrlsByRole(param);
                        RedisBusinessUtil.set(RedisKeys.LIVE_ROLE_INTERFACES + sysroleid, JsonUtil.toJson(InterfaceUrls));
                    }
                }

                List<SysFunctionDO> funlist = getSysFunctionDOForRoleDOList(funlists);
                JSONArray tree = CommonFunction.listToTree(JSONArray.parseArray(JSON.toJSONString(funlist)), "sfunid", "parsfunid", "children");
                org.setFunctions(tree);

                // 此处 后面可以设置 角色 对应的接口 到redis 预留
            } else {
                org.setFunctions(new JSONArray());
            }
        } else {
            org.setFunctions(new JSONArray());
        }

        // 更新用户登陆次数
        BdUser memLoginParam = new BdUser();
        memLoginParam.setAccno(login.getAccno());
        memLoginParam.setAcclogin(login.getAcclogin());
        memLoginParam.setLastlogindate(new Date());
        memLoginParam.setClintipadd(clintipadd);
        memLoginParam.setBduserid(login.getBduserid());
        bdUserService.updateByPrimaryKeySelective(memLoginParam);

        // 更新用户登陆时间
        SysInfolog sysInfolog = new SysInfolog();
        sysInfolog.setAccno(login.getAccno());
        sysInfolog.setOptcontent("【" + login.getAcclogin() + "】登录");
        sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
        sysInfolog.setModelname("后台用户登录");
        sysInfolog.setOrginfo("login");
        sysInfolog.setOptip(clintipadd);
        sysInfolog.setServerip(serverIp);
        sysInfologService.insert(sysInfolog);

        //记录web的登录人
        if (login.getBduserid() != null && login.getBduserid().longValue() > 0) {
            RedisBusinessUtil.saveMemberOnline(Constants.WEB_STRING, login.getBduserid().toString());
        }

        // 表示登录成功，清楚掉 LOGINNUM
        if (RedisBusinessUtil.exists(LOGINNUMHOUMD5)) {
            RedisBusinessUtil.delete(LOGINNUMHOUMD5);
        }

        return org;
    }

    private List<SysFunctionDO> getSysFunctionDOForRoleDOList(List<SysFunctionorgForRoleDO> funlists) {
        return funlists.stream().map(o -> {
            SysFunctionDO df = new SysFunctionDO();
            df.setParsfunid(o.getParsfunid());
            df.setSfunid(o.getSfunid());
            df.setName(o.getSfunname());
            df.setSfuntype(o.getSfuntype());
            df.setUrl(o.getSfunurl());
            return df;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private List<SysFunctionorgForRoleDO> getSysFunctionorgForRole(Long sysroleid) {
        // 获取角色 对于 的 节点
        List<Long> roleSfunidList = sysRolefuncService.getRoleSfunidList(sysroleid);
        if (CollectionUtils.isNotEmpty(roleSfunidList)) {
            // 存在 就向上 查询 每个 节点 的 父节点
            Set<Long> set = new HashSet<Long>();
            for (Long sfunid : roleSfunidList) {
                logger.info("sfunid:" + sfunid);
                List<Long> parSfunidListNode = sysFunctionorgService.getParSfunidListNode(sfunid);
                set.addAll(parSfunidListNode);
            }
            // 在查询出所有的 节点
            List<Long> allSfunid = new ArrayList<Long>(set);
            List<SysFunctionorgForRoleDO> dataList = sysFunctionorgService.getSysFunctionorgAllList(allSfunid);
            if (CollectionUtils.isNotEmpty(dataList)) {
                dataList.forEach(o -> {
                    o.setCheckbox(1);
                    o.setSysroleid(sysroleid);
                });
            }
            return dataList;
        }
        return null;
    }

}
