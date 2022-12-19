package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.member.MemLoginDO;
import com.likes.common.model.dto.sys.SysFunctionorgForRoleDO;
import com.likes.common.model.request.RoleFunctionorgRequest;
import com.likes.common.mybatis.entity.SysBduserrole;
import com.likes.common.mybatis.entity.SysFunctionorg;
import com.likes.common.mybatis.entity.SysRoleinfo;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.sys.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PermissionService permissionService;

    @Syslog("系统用户保存")
    @ResponseBody
    @RequestMapping(name = "系统用户保存", value = "/doSaveBdUser", method = RequestMethod.POST)
    public ResultInfo doBdUser(MemLoginDO memLogin) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(memLogin.getAcclogin()) || StringUtils.isEmpty(memLogin.getPhoneno()) || StringUtils.isEmpty(memLogin.getPassword())
                    || StringUtils.isEmpty(memLogin.getBdusername()) || StringUtils.isEmpty(memLogin.getWechat())) {
                return ResultInfo.error("账号,手机号,密码,昵称,微信,不能为为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.doSaveBdUser(memLogin, loginAdmin));
            deleteFuzzyMatchCache(RedisKeys.LIVE_SYSTEM_USER);
            LogUtils.logUserModifyOperates(getClass().getName() + ".doBdUser", memLogin, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doBdUser 系统用户保存出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
        } catch (Exception e) {
            response = ResultInfo.error("系统用户保存失败");
            logger.error("{}.doBdUser 系统用户保存出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
        }
        logger.info("/doSaveBdUser耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统用户详细", value = "/getBdUserDetail", method = RequestMethod.GET)
    public ResultInfo getBdUserDetail(MemLoginDO memLogin) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.getBdUserDetail(memLogin, loginAdmin));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getBdUserDetail 取系统用户详细失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
        } catch (Exception e) {
            logger.error("{}.getBdUserDetail 取系统用户详细失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
            return ResultInfo.error("获取系统用户详细失败");
        }
        logger.info("/getBdUserDetail耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("系统用户编辑")
    @ResponseBody
    @RequestMapping(name = "系统用户编辑", value = "/doUpdateBdUser", method = RequestMethod.POST)
    public ResultInfo doUpdateBdUser(MemLoginDO memLogin) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == memLogin.getLoginid()) {
                return ResultInfo.error("登录ID不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.doUpdateBdUser(memLogin, loginAdmin));
            deleteFuzzyMatchCache(RedisKeys.LIVE_SYSTEM_USER);
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateBdUser", memLogin, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateBdUser 系统用户编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
        } catch (Exception e) {
            response = ResultInfo.error("系统用户编辑失败");
            logger.error("{}.doUpdateBdUser 系统用户编辑出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
        }
        logger.info("/doUpdateBdUser耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("系统用户禁用启用")
    @ResponseBody
    @RequestMapping(name = "系统用户禁用启用", value = "/doAccstatus", method = RequestMethod.POST)
    public ResultInfo doAccstatus(MemLoginDO memLogin) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(memLogin.getAccno()) || null == memLogin.getAccstatus()) {
                return ResultInfo.error("唯一标识,状态,不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.doAccstatus(memLogin, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doAccstatus", memLogin, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doAccstatus 系统用户禁用启用失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
        } catch (Exception e) {
            response = ResultInfo.error("系统用户禁用启用失败");
            logger.error("{}.doAccstatus 系统用户禁用启用出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(memLogin), e);
        }
        logger.info("/doAccstatus耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("创建角色,角色编辑")
    @ResponseBody
    @RequestMapping(name = "创建角色,角色编辑", value = "/doRrole", method = RequestMethod.POST)
    public ResultInfo saveRole(SysRoleinfo sysRoleinfo) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.saveRole(sysRoleinfo, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".saveRole", sysRoleinfo, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.saveRole 创建角色,角色编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        } catch (Exception e) {
            response = ResultInfo.error("操作失败，请稍后重试！");
            logger.error("{}.saveRole 创建角色,角色编辑出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        }
        logger.info("/saveRole耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("角色删除")
    @ResponseBody
    @RequestMapping(name = "角色删除", value = "/deleteRole", method = RequestMethod.POST)
    public ResultInfo deleteRole(SysRoleinfo sysRoleinfo) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysRoleinfo.getSysroleid() || null == sysRoleinfo.getSysrolestatus() || sysRoleinfo.getSysrolestatus() == 0) {
                return ResultInfo.error("参数不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.deleteRole(sysRoleinfo, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".deleteRole", sysRoleinfo, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.deleteRole 角色删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        } catch (Exception e) {
            response = ResultInfo.error("角色删除失败");
            logger.error("{}.deleteRole 角色删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        }
        logger.info("/deleteRole耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "角色管理", value = "/roleList", method = RequestMethod.GET)
    public ResultInfo roleList(SysRoleinfo sysRoleinfo, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            //LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.roleList(sysRoleinfo, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.roleList 获取角色管理失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        } catch (Exception e) {
            logger.error("{}.roleList 获取角色管理失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
            return ResultInfo.error("获取角色管理失败");
        }
        logger.info("/roleList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    /**
     * 用于系统用户新增/编辑角色
     *
     * @param sysRoleinfo
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "角色列表", value = "/roleListForSystemUser", method = RequestMethod.GET)
    public ResultInfo roleListForSystemUser(SysRoleinfo sysRoleinfo, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            //LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.roleListForSystemUser(sysRoleinfo, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.roleListForSystemUser 获取角色列表失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        } catch (Exception e) {
            logger.error("{}.roleListForSystemUser 获取角色列表失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
            return ResultInfo.error("获取角色列表失败");
        }
        logger.info("/roleListForSystemUser耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    //角色详细
    @Syslog("配置权限")
    @ResponseBody
    @RequestMapping(name = "配置权限", value = "/detailRoleFunctionorg", method = RequestMethod.GET)
    public ResultInfo detailRoleFunctionorg(SysRoleinfo sysRoleinfo) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysRoleinfo.getSysroleid()) {
                return ResultInfo.error("角色ID为空");
            }
            response.setData(permissionService.detailRoleFunctionorg(sysRoleinfo));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.detailRoleFunctionorg 配置权限失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        } catch (Exception e) {
            logger.error("{}.detailRoleFunctionorg 配置权限失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
            return ResultInfo.error("配置权限失败");
        }
        logger.info("/detailRoleFunctionorg耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("配置权限,角色功能关系设置")
    @ResponseBody
    @RequestMapping(name = "配置权限,角色功能关系设置", value = "/doRoleFunctionorg", method = RequestMethod.POST)
    public ResultInfo doRoleFunctionorg(@RequestBody RoleFunctionorgRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getSysroleid() || CollectionUtils.isEmpty(req.getSfunidList())) {
                return ResultInfo.error("角色为空或没有选择功能");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.doRoleFunctionorg(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doRoleFunctionorg", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doRoleFunctionorg 配置权限,角色功能关系设置失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("配置权限,角色功能关系设置失败");
            logger.error("{}.doRoleFunctionorg 配置权限,角色功能关系设置失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        //设置该角色的 redis 缓存
        try {
            List<SysFunctionorgForRoleDO> funlists = permissionService.getSysFunctionorgForRole(req.getSysroleid());
            if (!CollectionUtils.isEmpty(funlists)) {
                permissionService.doSetRoleFunctionorgForInterface(req.getSysroleid(), funlists);
            }
        } catch (Exception e) {
            logger.error("{}.doRoleFunctionorg 配置角色的redis失败:{},params:{}", getClass().getName(), e.getMessage(), req.getSysroleid(), e);
        }
        logger.info("/doRoleFunctionorg耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("管理用户")
    //后台用户角色关系列表
    @ResponseBody
    @RequestMapping(name = "管理用户", value = "/adminRroleList", method = RequestMethod.GET)
    public ResultInfo adminRroleList(SysRoleinfo sysRoleinfo) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            //LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.adminRroleList(sysRoleinfo));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.adminRroleList 管理用户获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
        } catch (Exception e) {
            logger.error("{}.adminRroleList 管理用户获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysRoleinfo), e);
            return ResultInfo.error("管理用户失败");
        }
        logger.info("/adminRroleList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("后台用户角色关系新增")
    @ResponseBody
    @RequestMapping(name = "后台用户角色关系新增", value = "/adminRroleAdd", method = RequestMethod.POST)
    public ResultInfo adminRroleAdd(SysBduserrole sys) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sys.getSysroleid() || StringUtils.isEmpty(sys.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "角色ID，或用户标识为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.adminRroleAdd(sys, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".adminRroleAdd", sys, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.adminRroleAdd 后台用户角色关系新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sys), e);
        } catch (Exception e) {
            logger.error("{}.adminRroleAdd 后台用户角色关系新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sys), e);
            response = ResultInfo.error("后台用户角色关系新增失败");
        }
        logger.info("/adminRroleAdd{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("后台用户角色关系删除")
    @ResponseBody
    @RequestMapping(name = "后台用户角色关系删除", value = "/adminRroleDel", method = RequestMethod.POST)
    public ResultInfo adminRroleDel(SysBduserrole sys) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sys.getSysroleid() || StringUtils.isEmpty(sys.getAccno())) {
                return ResultInfo.error("角色ID,用户标识不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.adminRroleDel(sys, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".adminRroleDel", sys, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.adminRroleDel 后台用户角色关系删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sys), e);
        } catch (Exception e) {
            logger.error("{}.adminRroleDel 后台用户角色关系删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sys), e);
            response = ResultInfo.error("后台用户角色关系删除失败");
        }
        logger.info("/adminRroleDel耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    //功能获取树
    @ResponseBody
    @RequestMapping(name = "模块管理", value = "/getSysFunctionorgTree", method = RequestMethod.GET)
    public ResultInfo getSysFunctionorgTree() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            //LoginUser loginAdmin = getLoginAdmin();
            Map result = new HashMap();
            result.put("data",permissionService.getSysFunctionorgTree());
            response.setData(result);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getSysFunctionorgTree 获取模块管理获取失败:{},params:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("{}.getSysFunctionorgTree 获取模块管理获取失败:{},params:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error("获取模块管理数据失败");
        }
        logger.info("/getSysFunctionorgTree耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    //用于模块编辑
    @ResponseBody
    @RequestMapping(name = "功能获取选择树", value = "/getSysSelectFunctionorgTree", method = RequestMethod.GET)
    public ResultInfo getSysSelectFunctionorgTree(Long sfunid) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (sfunid == null) {
                return ResultInfo.error("功能ID不能为空");
            }
            response.setData(permissionService.getSysSelectFunctionorgTree(sfunid));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getSysSelectFunctionorgTree 获取功能选择树失败:{},params:{}", getClass().getName(), e.getMessage(), sfunid, e);
        } catch (Exception e) {
            logger.error("{}.getSysSelectFunctionorgTree 获取功能选择树失败:{},params:{}", getClass().getName(), e.getMessage(), sfunid, e);
            return ResultInfo.error("获取功能选择树失败");
        }
        logger.info("/getSysSelectFunctionorgTree耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("模块新增,模块编辑,模块禁用启用")
    @ResponseBody
    @RequestMapping(name = "模块新增,模块编辑,模块禁用启用", value = "/doSetSysFunctionorg", method = RequestMethod.POST)
    public ResultInfo doSetSysFunctionorg(SysFunctionorg s) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(permissionService.doSetSysFunctionorg(s, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSetSysFunctionorg", s, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.adminRroleDel 模块操作失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(s), e);
        } catch (Exception e) {
            logger.error("{}.adminRroleDel 模块操作失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(s), e);
            response = ResultInfo.error("模块操作失败");
        }
        logger.info("/doSetSysFunctionorg耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("模块删除")
    @ResponseBody
    @RequestMapping(name = "模块删除", value = "/doDelSysFunctionorg", method = RequestMethod.POST)
    public ResultInfo doDelSysFunctionorg(SysFunctionorg s) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            if (null == s.getSfunid()) {
                return ResultInfo.error("功能ID为空");
            }
            response.setData(permissionService.doDelSysFunctionorg(s, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelSysFunctionorg", s, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.adminRroleDel 模块删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(s), e);
        } catch (Exception e) {
            logger.error("{}.adminRroleDel 模块删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(s), e);
            response = ResultInfo.error("模块删除失败");
        }
        logger.info("/doDelSysFunctionorg耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

}
