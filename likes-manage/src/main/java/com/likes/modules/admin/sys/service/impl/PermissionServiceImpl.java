package com.likes.modules.admin.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysRoleinfoDO;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemLoginDO;
import com.likes.common.model.dto.member.UserDO;
import com.likes.common.model.dto.sys.SysFunctionorgDO;
import com.likes.common.model.dto.sys.SysFunctionorgForRoleDO;
import com.likes.common.model.dto.sys.SysRoleinfoForRoleDO;
import com.likes.common.model.request.RoleFunctionorgRequest;
import com.likes.common.model.vo.member.BdUserDetailVO;
import com.likes.common.mybatis.entity.BdUser;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.entity.SysBduserrole;
import com.likes.common.mybatis.entity.SysFuncinterface;
import com.likes.common.mybatis.entity.SysFunctionorg;
import com.likes.common.mybatis.entity.SysInfolog;
import com.likes.common.mybatis.entity.SysReffuncinitfc;
import com.likes.common.mybatis.entity.SysRolefunc;
import com.likes.common.mybatis.entity.SysRoleinfo;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.mybatis.mapper.SysBduserroleMapper;
import com.likes.common.mybatis.mapperext.sys.SysBduserroleMapperExt;
import com.likes.common.service.member.BdUserService;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.sys.SysFuncinterfaceService;
import com.likes.common.service.sys.SysFunctionorgService;
import com.likes.common.service.sys.SysReffuncinitfcService;
import com.likes.common.service.sys.SysRolefuncService;
import com.likes.common.service.sys.SysRoleinfoService;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.RandomUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.common.service.CommonService;
import com.likes.modules.admin.sys.service.PermissionService;
import com.github.pagehelper.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CommonService commonService;
    @Resource
    private SysRoleinfoService sysRoleinfoService;
    @Resource
    private SysBduserroleMapper sysBduserroleMapper;
    @Resource
    private SysBduserroleMapperExt sysBduserroleMapperExt;
    @Resource
    private SysFunctionorgService sysFunctionorgService;
    @Resource
    private SysFuncinterfaceService sysFuncinterfaceService;
    @Resource
    private SysReffuncinitfcService sysReffuncinitfcService;
    @Resource
    private SysRolefuncService sysRolefuncService;
    @Resource
    private MemLoginMapper memLoginMapper;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemBaseinfoService MemBaseinfoService;
    @Resource
    private BdUserService bdUserService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean deleteRole(SysRoleinfo sysRoleinfo, LoginUser loginAdmin) {
        try {
            // 判断下面是否有用户
            List<UserDO> zuneiList = sysBduserroleMapperExt.getZuneiList(sysRoleinfo.getSysroleid());
            if (!CollectionUtils.isEmpty(zuneiList)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "有组内成员，不能进行此操作");
            }

            //插入日志
            SysInfolog sysInfolog = new SysInfolog();
            sysInfolog.setAccno(loginAdmin.getAccno());
            sysInfolog.setOptcontent("[" + loginAdmin.getMemid() +
                    "]禁用角色[sysroleid=" + sysRoleinfo.getSysroleid() + ",sysrolestatus=" + sysRoleinfo.getSysrolestatus() + "]");
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("编辑角色");
            sysInfolog.setOrginfo("saveRole");
            commonService.insertSelective(sysInfolog);

            sysRoleinfoService.updateByPrimaryKeySelective(sysRoleinfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("禁用错误：" + sysRoleinfo.getSysroleid() + sysRoleinfo.getSysrolestatus(), e);
        }
        return false;
    }

    @Override
    public boolean checkUserAuthByToken(String acctoken, String url) {

        if (StringUtils.isEmpty(acctoken) || !RedisBusinessUtil.exists(acctoken)) {
            logger.info("获取redis中的acctoken:{}失败,用户未登录或已过期", acctoken);
            return false;
        }
        Object jsonstr = RedisBusinessUtil.get(acctoken);
        try {
            LoginUser user = JSONObject.parseObject((String) jsonstr, LoginUser.class);
            if (Objects.isNull(user)) {
                logger.info("{}  LoginUser is null", acctoken);
                return false;
            }
            if (Objects.isNull(RedisBusinessUtil.get(user.getAccno()))) {
                logger.info("token:{} not exist", acctoken);
                return false;
            }
            if (!acctoken.equals(RedisBusinessUtil.get(user.getAccno()))) {
                logger.info("token:{} have replace ", acctoken);
                return false;
            }
//            List<String> InterfaceUrls = JSONArray.parseArray(RedisBusinessUtil.get(RedisKeys.LIVE_ROLE_INTERFACES + user.getSysroleid()), String.class);
//            if (!InterfaceUrls.contains(url)) {
//                return false;
//            }
            return true;
        } catch (Exception e) {
            logger.error("{} parse LoginUser:{} occur error.", acctoken, jsonstr, e);
            return false;
        }
    }

    @Override
    public Long saveRole(SysRoleinfo sysRoleinfo, LoginUser loginAdmin) {
        if (null == sysRoleinfo.getSysroleid()) {
            if (StringUtils.isEmpty(sysRoleinfo.getSysrolename())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "角色名称为空");
            }
            // 检查
            SysRoleinfo s = sysRoleinfoService.checkDuplicates(sysRoleinfo);
            if (s != null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "角色名称重复");
            }
            // 新增
            //sysRoleinfo.setSysrolestatus(0);
            if (null == sysRoleinfo.getSysrolestatus()) {
                sysRoleinfo.setSysrolestatus(0);
            }
            sysRoleinfoService.insertSelective(sysRoleinfo);
            return sysRoleinfo.getSysroleid();
        } else {
            if (StringUtils.isNotEmpty(sysRoleinfo.getSysrolename())) {
                // 检查
                SysRoleinfo s = sysRoleinfoService.checkDuplicates(sysRoleinfo);
                if (s != null) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "角色名称重复");
                }
            }
            //TODO 之前逻辑暂时注释掉
            /*if (sysRoleinfo.getSysrolestatus() != 0) {
                // 判断下面是否有用户
                List<UserDO> zuneiList = sysBduserroleMapperExt.getZuneiList(sysRoleinfo.getSysroleid());
                if (!CollectionUtils.isEmpty(zuneiList)) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "有组内成员，不能进行此操作");
                }

                //插入日志
                SysInfolog sysInfolog = new SysInfolog();
                sysInfolog.setAccno(loginAdmin.getAccno());
                sysInfolog.setOptcontent("[" + loginAdmin.getAcclogin() +
                        "]禁用角色[sysroleid=" + sysRoleinfo.getSysroleid() + ",sysrolestatus=" + sysRoleinfo.getSysrolestatus() + "]");
                sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
                sysInfolog.setModelname("编辑角色");
                sysInfolog.setOrginfo("saveRole");
                commonService.insertSelective(sysInfolog);
            }*/
            sysRoleinfoService.updateByPrimaryKeySelective(sysRoleinfo);
            return sysRoleinfo.getSysroleid();
        }

    }

    @Override
    public Map<String, Object> adminRroleList(SysRoleinfo sysRoleinfo) {
        if (null == sysRoleinfo.getSysroleid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "角色ID为空");
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();

        List<UserDO> zuneiList = sysBduserroleMapperExt.getZuneiList(sysRoleinfo.getSysroleid());
        if (CollectionUtils.isEmpty(zuneiList)) {
            zuneiList = new ArrayList<UserDO>();
        }
        List<UserDO> zuwaiList = sysBduserroleMapperExt.getZuwaiList();
        if (CollectionUtils.isEmpty(zuwaiList)) {
            zuwaiList = new ArrayList<UserDO>();
        }
        dataMap.put("zuneiList", zuneiList);
        dataMap.put("zuwaiList", zuwaiList);
        return dataMap;
    }

    @Override
    public synchronized Long adminRroleAdd(SysBduserrole sys, LoginUser loginAdmin) {
        // 检查
        SysBduserrole sys2 = new SysBduserrole();
        sys2.setAccno(sys.getAccno());
        sys2.setSysroleid(null);
        SysBduserrole sysBduserrole = sysBduserroleMapperExt.checkExist(sys2);
        if (sysBduserrole != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "该用户存在角色");
        }

        // BdUser bdUser = bdUserMapper.selectByAccno(sys.getAccno());
        // bdUser.setBduserid(bdUser.getBduserid());
        sys.setCreateUser(loginAdmin.getAccno());
        sys.setUpdateUser(loginAdmin.getAccno());
        sysBduserroleMapper.insertSelective(sys);
        return sys.getRefurid();
    }

    @Override
    public synchronized String adminRroleDel(SysBduserrole sys, LoginUser loginAdmin) {
        // 查询是否存在
        SysBduserrole sysBduserrole = sysBduserroleMapperExt.checkExist(sys);
        if (sysBduserrole == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "该用户不存在角色");
        }
        sysBduserrole.setIsDelete(true);
        sysBduserrole.setUpdateUser(loginAdmin.getAccno());
        sysBduserroleMapper.updateByPrimaryKeySelective(sysBduserrole);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public List<SysFunctionorgDO> getSysFunctionorgTree() {
        try {
            List<SysFunctionorgDO> sysFunctionorgTree = sysFunctionorgService.getSysFunctionorgTree();
            return sysFunctionorgTree;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取模块功能列表失败：", e);
        }
        return new ArrayList<>();
    }

    @Override
    public synchronized Long doSetSysFunctionorg(SysFunctionorg s, LoginUser loginAdmin) {

        if (null == s.getSfunid()) {
            // 新增
            if (StringUtils.isEmpty(s.getSfunname())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "模块名称为空");
            }
            if (StringUtils.isEmpty(s.getOfsystem())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "所属系统为空");
            }
            if (StringUtils.isEmpty(s.getSfuntype())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "模块类型为空");
            }
            if (StringUtils.isEmpty(s.getSfunurl())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "功能/url为空");
            }
            // 检查
            SysFunctionorg sysFunctionorg = sysFunctionorgService.getOneSysFunctionorg(s);
            if (sysFunctionorg != null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "名称重复");
            }
            s.setCreateUser(loginAdmin.getAccno());
            s.setUpdateUser(loginAdmin.getAccno());

            sysFunctionorgService.insertSelective(s);

            //查询是否存在对于的接口 根据名称查询  接口名称 包含该模块名称
            List<SysFuncinterface> list = sysFuncinterfaceService.searchInterfaceBySfunname(s.getSfunname());
            if (CollectionUtils.isNotEmpty(list)) {
                Long sfunid = s.getSfunid();
                //建立模块🐟接口关系
                for (SysFuncinterface sysFuncinterface : list) {
                    Long itfcid = sysFuncinterface.getItfcid();
                    SysReffuncinitfc sysReffuncinitfc = new SysReffuncinitfc();
                    sysReffuncinitfc.setItfcid(itfcid);
                    sysReffuncinitfc.setSfunid(sfunid);
                    sysReffuncinitfc.setCreateUser(loginAdmin.getAccno());
                    sysReffuncinitfc.setUpdateUser(loginAdmin.getAccno());
                    sysReffuncinitfcService.insertSelective(sysReffuncinitfc);
                }
            }
            return s.getSfunid();
        } else {
            // 修改
            // 检查
            if (s.getParsfunid() != null) {
                if (s.getParsfunid().equals(s.getSfunid())) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_1110.getCode(), "父节点不能是自己");
                }
            }
            SysFunctionorg sysFunctionorg = sysFunctionorgService.getOneSysFunctionorg(s);
            if (sysFunctionorg != null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "名称重复");
            }
            s.setUpdateUser(loginAdmin.getAccno());
            sysFunctionorgService.updateByPrimaryKeySelective(s);
            return s.getSfunid();
        }
    }

    @Override
    public String doDelSysFunctionorg(SysFunctionorg s, LoginUser loginAdmin) {
        s.setIsDelete(true);
        s.setUpdateUser(loginAdmin.getAccno());
        sysFunctionorgService.updateByPrimaryKeySelective(s);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public PageResult roleList(SysRoleinfo sysRoleinfo, PageBounds page) {
        Page<SysRoleinfoForRoleDO> list = null;
        try {
            list = sysRoleinfoService.roleList(sysRoleinfo, page.toRowBounds());
            if (CollectionUtils.isNotEmpty(list)) {
                //之前的for
			/*for (SysRoleinfoForRoleDO s : list) {
				//根据每个id 反向查询所有节点， 在去重，获取对象，转为树
				List<SysFunctionorgForRoleDO> list2 = getSysFunctionorgForRole(s.getSysroleid());
				if (CollectionUtils.isNotEmpty(list2)) {
					JSONArray tree = CommonFunction.listToTree(JSONArray.parseArray(JSON.toJSONString(list2)), "sfunid",
							"parsfunid", "children");
					s.setFunctionlist(tree);
				}else {
					s.setFunctionlist(new JSONArray());
				}
			}*/

                //修改后
                PageResult pageResult = PageResult.getPageResult(page);
                pageResult.setTotalCount((int) list.getTotal());

                List<SysRoleinfoForRoleDO> sysRoleinfoForRoleDOs = list.getResult();
                logger.info("前的size {}", list.size());
                // 使用异步的方式
                List<CompletableFuture<SysRoleinfoForRoleDO>> futures = sysRoleinfoForRoleDOs.stream()
                        .map(s -> CompletableFuture.supplyAsync(() -> {
                            // 计算
                            return getTree(s);
                        })).collect(Collectors.toList());

                // join 操作等待所有异步操作的结果
                sysRoleinfoForRoleDOs = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
                logger.info("后的size {}", list.size());
                pageResult.setData(sysRoleinfoForRoleDOs);
                return pageResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取角色列表失败：" + sysRoleinfo, e);
        }

        return PageResult.getPageResult(page, list);
    }


    private SysRoleinfoForRoleDO getTree(SysRoleinfoForRoleDO s) {
        //根据每个id 反向查询所有节点， 在去重，获取对象，转为树
        List<SysFunctionorgForRoleDO> list2 = getSysFunctionorgForRole(s.getSysroleid());
        if (CollectionUtils.isNotEmpty(list2)) {
            JSONArray tree = CommonFunction.listToTree(JSONArray.parseArray(JSON.toJSONString(list2)), "sfunid",
                    "parsfunid", "children");
            s.setFunctionlist(tree);
        } else {
            s.setFunctionlist(new JSONArray());
        }
        return s;
    }

    @Override
    public List<SysFunctionorgForRoleDO> getSysFunctionorgForRole(long sysroleid) {
        //获取角色  对于  的 节点
        List<Long> roleSfunidList = sysRolefuncService.getRoleSfunidList(sysroleid);
        if (CollectionUtils.isNotEmpty(roleSfunidList)) {
            //存在 就向上 查询 每个 节点 的 父节点
            Set<Long> set = new HashSet<Long>();
            for (Long sfunid : roleSfunidList) {
                List<Long> parSfunidListNode = sysFunctionorgService.getParSfunidListNode(sfunid);
                set.addAll(parSfunidListNode);
            }
            //在查询出所有的 节点
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String doRoleFunctionorg(RoleFunctionorgRequest req, LoginUser loginAdmin) {
        List<SysRolefunc> sysRolefuncs = sysRolefuncService.getSysRolefuncList(req.getSysroleid(), loginAdmin.getAccno());
        if (sysRolefuncs.size() > Constants.DEFAULT_INTEGER) {
            // 先删除
            SysRolefunc sysRolefunc = new SysRolefunc();
            sysRolefunc.setIsDelete(true);
            sysRolefunc.setSysroleid(req.getSysroleid());
            sysRolefunc.setUpdateUser(loginAdmin.getAccno());
            sysRolefuncService.delRoleFunctionorg(sysRolefunc);
        }

        // 在新增
        try {
            List<SysRolefunc> sysRolefuncList = new ArrayList<>();

            //获取里面的id 循坏  ， 更新 为 0  ，如果没有 就新增
            for (Long sfunid : req.getSfunidList()) {
                /*SysRolefunc param = new SysRolefunc();
                param.setSfunid(sfunid);
                param.setSysroleid(req.getSysroleid());
                param.setUpdateUser(loginAdmin.getAccno());
                param.setIsDelete(true);
                int i = sysRolefuncMapperExt.updateSysRolefuncOne(param);
                if (i == 0) {*/

                //这时就新增这条数据
                SysRolefunc s = new SysRolefunc();
                s.setSysroleid(req.getSysroleid());
                s.setIsDelete(false);
                s.setUpdateUser(loginAdmin.getAccno());
                s.setCreateUser(loginAdmin.getAccno());
                s.setSfunid(sfunid);
                sysRolefuncList.add(s);

                /*Set<Long> set = new HashSet<Long>();
                List<SysRoleinfoForRoleDO> sfunidList = sysFunctionorgService.getSfunidList(sfunid);
                if (CollectionUtils.isNotEmpty(sfunidList)) {
                    for (SysRoleinfoForRoleDO sysRoleinfoForRoleDO : sfunidList) {
                        if (null != sysRoleinfoForRoleDO) {
                            if (null != sysRoleinfoForRoleDO.getAid()) {
                                set.add(sysRoleinfoForRoleDO.getAid());
                            }
                            if (null != sysRoleinfoForRoleDO.getBid()) {
                                set.add(sysRoleinfoForRoleDO.getBid());
                            }
                            if (null != sysRoleinfoForRoleDO.getCid()) {
                                set.add(sysRoleinfoForRoleDO.getCid());
                            }
                            if (null != sysRoleinfoForRoleDO.getDid()) {
                                set.add(sysRoleinfoForRoleDO.getDid());
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(set)) {
                    List<SysRolefunc> sysRolefuncList = new ArrayList<>();
                    for (Long id : set) {
                        //这时就新增这条数据
                        SysRolefunc s = new SysRolefunc();
                        s.setSysroleid(req.getSysroleid());
                        s.setIsDelete(false);
                        s.setUpdateUser(loginAdmin.getAccno());
                        s.setCreateUser(loginAdmin.getAccno());
                        s.setSfunid(id);
                        sysRolefuncList.add(s);
                    }
                    if (null != sysRolefuncList) {
                        sysRolefuncService.insertSysRolefuncList(sysRolefuncList);
                    }
                }*/

            }
            sysRolefuncService.insertSysRolefuncList(sysRolefuncList);
            //}
            return Constants.SUCCESS_MSG;
        } catch (BusinessException e) {
            new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "设置失败");
        }
        return null;
        //List<SysRolefunc> list = new ArrayList<SysRolefunc>();
		/*for (Long  sfunid : req.getSfunidList()) {
			SysRolefunc s = new SysRolefunc();
			s.setIsdelete(Constants.isdelete_0);
			s.setSysroleid(req.getSysroleid());
			s.setUpdateUser(loginAdmin.getAccno());
			s.setModifydate(new Date());
			s.setCreateUser(loginAdmin.getAccno());
			s.setCreatedate(new Date());
			s.setSfunid(sfunid);
			//list.add(s);
		}*/
        //sysRolefuncMapper.insertList(list);


		/*以前用的这个
		 * SysRolefuncRequest sysRolefuncRequest = new SysRolefuncRequest();
		sysRolefuncRequest.setSfunidList(req.getSfunidList());
		sysRolefuncRequest.setIsdelete(Constants.isdelete_0);
		sysRolefuncRequest.setSysroleid(req.getSysroleid());
		sysRolefuncRequest.setUpdateUser(loginAdmin.getAccno());
		sysRolefuncRequest.setModifydate(new Date());
		sysRolefuncRequest.setCreateUser(loginAdmin.getAccno());
		sysRolefuncRequest.setCreatedate(new Date());
		sysRolefuncMapper.insertList2(sysRolefuncRequest);*/
        //return Constants.SUCCESS_MSG;
    }

    @Override
    public JSONArray getSysSelectFunctionorgTree(Long sfunid) {
        SysFunctionorg sysFunctionorg = sysFunctionorgService.selectByPrimaryKey(sfunid);
        if (sysFunctionorg != null) {
            // 获取所有数据
            List<SysFunctionorgDO> list = sysFunctionorgService.getSysFunctionorgList(sysFunctionorg.getParsfunid());

            JSONArray tree = CommonFunction.listToTree(JSONArray.parseArray(JSON.toJSONString(list)), "sfunid",
                    "parsfunid", "children");
            return tree;
        }

        return null;
    }

    @Override
    public SysRoleinfoDO detailRoleFunctionorg(SysRoleinfo sysRoleinfo) {
        SysRoleinfo s = sysRoleinfoService.selectByPrimaryKey(sysRoleinfo.getSysroleid());
        if (s != null) {
            SysRoleinfoDO sysRoleinfoDO = new SysRoleinfoDO();
            sysRoleinfoDO.setSysroleid(s.getSysroleid());
            sysRoleinfo.setSysrolename(s.getSysrolename());
            sysRoleinfo.setSysrolestatus(s.getSysrolestatus());
            SysFunctionorgDO do1 = new SysFunctionorgDO();
            do1.setSysroleid(s.getSysroleid());
            //禁用的功能不暂示
            List<SysFunctionorgDO> syList = sysFunctionorgService.getSysFunctionorgTreeByRoleAll(do1);
            sysRoleinfoDO.setFunctionlist(syList);
            return sysRoleinfoDO;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long doSaveBdUser(MemLoginDO memLogin, LoginUser loginAdmin) {
        // 账号判断重复
        memLogin.setLoginid(null);
        BdUser bdUserAcclogin = bdUserService.getAcclogin(memLogin.getAcclogin());
        if (null != bdUserAcclogin) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "账号重复");
        }
        //手机号判断重复
        BdUser bdUserParam = new BdUser();
        bdUserParam.setPhoneno(memLogin.getPhoneno());
        BdUser bdUserPhoneno = bdUserService.getRepeatPhoneno(bdUserParam);
        if (null != bdUserPhoneno) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "手机号重复");
        }

        String accno = RandomUtil.uuid();
        // 新增bd_user
        BdUser bdUser = new BdUser();
        bdUser.setAccno(accno);
        bdUser.setBdusername(memLogin.getBdusername());
        bdUser.setWechat(memLogin.getWechat());
        bdUser.setPhoneno(memLogin.getPhoneno());
        bdUser.setEmail(memLogin.getEmail());
        bdUser.setCreateUser(loginAdmin.getAccno());
        bdUser.setUpdateUser(loginAdmin.getAccno());
        bdUser.setAccstatus(Constants.ACCSTATUS_1);
        bdUser.setLoginnum(Constants.DB_GAME_TYPE);
        bdUser.setPasswordmd5(memLogin.getPassword());
        bdUser.setPassword(memLogin.getPassword());
        bdUser.setAcclogin(memLogin.getAcclogin());
        int m = bdUserService.insertSelective(bdUser);
        if (m <= 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "新增失败,账号已存在");
        }
        // 新增角色权限
        if (null != memLogin.getSysroleid()) {
            SysBduserrole sysBduserrole = new SysBduserrole();
            sysBduserrole.setAccno(accno);
            sysBduserrole.setSysroleid(memLogin.getSysroleid());
            sysBduserrole.setCreateUser(loginAdmin.getAccno());
            sysBduserrole.setUpdateUser(loginAdmin.getAccno());
            sysBduserroleMapper.insertSelective(sysBduserrole);
        }
        return bdUser.getBduserid();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long doUpdateBdUser(MemLoginDO m, LoginUser loginAdmin) {
      /*  MemLogin memLogin = memLoginService.selectByAccno(m.getAccno());
        if (memLogin == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户不存在");
        }*/
        BdUser bdUser = bdUserService.selectByAccno(m.getAccno());
        if (bdUser == null || bdUser.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户不存在");
        }
        if (StringUtils.isNotEmpty(m.getAcclogin())) {
            m.setLoginid(null);
            // 账号判断重复
            BdUser mLogin = bdUserService.getAcclogin(m.getAcclogin());
            if (mLogin != null && !(mLogin.getBduserid().equals(bdUser.getBduserid()))) {
                throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "账号重复");
            }
        }

        if (StringUtils.isNotEmpty(m.getPhoneno())) {
            //手机号判断重复
            BdUser bdUserParam = new BdUser();
            bdUserParam.setBduserid(null);
            bdUserParam.setPhoneno(m.getPhoneno());
            BdUser bdUserPhoneno = bdUserService.getRepeatPhoneno(bdUserParam);
            if (bdUserPhoneno != null && !(bdUserPhoneno.getBduserid().equals(bdUser.getBduserid()))) {
                throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "手机号重复");
            }
        }

        if (StringUtils.isNotEmpty(m.getAcclogin())) {
            bdUser.setAcclogin(m.getAcclogin());
        }

        if (!Constants.PASSWORD_XING.equals(m.getPassword())) {
            bdUser.setPasswordmd5(m.getPassword());
        }
        if (StringUtils.isNotEmpty(m.getPhoneno())) {
            bdUser.setPhoneno(m.getPhoneno());
        }
        bdUser.setWechat(m.getWechat());
        bdUser.setBdusername(m.getBdusername());
        bdUser.setEmail(m.getEmail());
        bdUser.setUpdateUser(loginAdmin.getAccno());
        bdUserService.updateByPrimaryKeySelective(bdUser);

        // 角色
        // 先删除 ，在新增
        SysBduserrole sBduserrole = new SysBduserrole();
        sBduserrole.setAccno(m.getAccno());
        sBduserrole.setUpdateUser(loginAdmin.getAccno());
        sysBduserroleMapperExt.delBduserrole(sBduserrole);
        // 新增角色权限
        if (null != m.getSysroleid()) {
            SysBduserrole sysBduserrole = new SysBduserrole();
            sysBduserrole.setAccno(m.getAccno());
            sysBduserrole.setSysroleid(m.getSysroleid());
            sysBduserrole.setCreateUser(loginAdmin.getAccno());
            sysBduserrole.setUpdateUser(loginAdmin.getAccno());
            sysBduserroleMapper.insertSelective(sysBduserrole);
        }
        return bdUser.getBduserid();
    }

    @Override
    public BdUserDetailVO getBdUserDetail(MemLoginDO m, LoginUser loginAdmin) {
        if (StringUtils.isEmpty(m.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "唯一标识为空");
        }

        BdUserDetailVO bdUserDetailVO = new BdUserDetailVO();
       /* MemLogin memLogin = memLoginService.selectByAccno(m.getAccno());
        if (memLogin == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户不存在");
        }*/
        BdUser bdUser = bdUserService.selectByAccno(m.getAccno());
        if (bdUser == null || bdUser.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户不存在");
        }
        bdUserDetailVO.setLoginid(bdUser.getBduserid());
        bdUserDetailVO.setAcclogin(bdUser.getAcclogin());
        bdUserDetailVO.setPassword(Constants.PASSWORD_XING);
        bdUserDetailVO.setAccno(m.getAccno());
        bdUserDetailVO.setPhoneno(bdUser.getPhoneno());
        bdUserDetailVO.setWechat(bdUser.getWechat());
        bdUserDetailVO.setEmail(bdUser.getEmail());
        bdUserDetailVO.setBdusername(bdUser.getBdusername());


        // 查询是否存在角色
        //SysBduserrole sys2 = new SysBduserrole();
        //sys2.setAccno(m.getAccno());
        //sys2.setSysroleid(null);
        //SysBduserrole sysBduserrole = sysBduserroleMapper.checkExist(sys2);
        SysRoleinfo sysRoleinfo = sysRoleinfoService.getRoleByAccno(m.getAccno());
        if (sysRoleinfo != null && sysRoleinfo.getSysrolestatus() == 0) {
            //判断角色  是否存在
            bdUserDetailVO.setSysroleid(sysRoleinfo.getSysroleid());
        } else {
            bdUserDetailVO.setSysroleid(null);
        }
		/*if (StringUtils.isNotEmpty(m.getAcclogin())) {
			m.setLoginid(memLogin.getLoginid());
			// 账号判断重复
			MemLogin mLogin = memLoginMapper.getRepeat(m);
			if (mLogin != null) {
				throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "账号重复");
			}
		}*/
        return bdUserDetailVO;
    }

    @Override
    //@Transactional
    public String doAccstatus(MemLoginDO m, LoginUser loginAdmin) {
        MemLogin memLogin = memLoginService.selectByAccno(m.getAccno());
        if (memLogin == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户不存在");
        }
        BdUser bdUser = bdUserService.selectByAccno(m.getAccno());
        if (bdUser == null || bdUser.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "用户不存在");
        }
        memLogin.setAccstatus(m.getAccstatus());
        memLoginMapper.updateByPrimaryKeySelective(memLogin);
        bdUser.setUpdateUser(m.getAccno());
        try {
            bdUserService.updateByPrimaryKeySelective(bdUser);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新失败：" + bdUser, e);
        }

        return Constants.SUCCESS_MSG;
    }

    @Override
    public void doSetRoleFunctionorgForInterface(Long sysroleid, List<SysFunctionorgForRoleDO> funlists) {
        //获取对于模块 对于的 的接口
        List<Long> param = funlists.stream().map(o -> o.getSfunid()).filter(Objects::nonNull).collect(Collectors.toList());
        List<String> InterfaceUrls = sysFuncinterfaceService.getInterfaceUrlsByRole(param);
        //TODO 预留 测试完后加密
        RedisBusinessUtil.set(RedisKeys.LIVE_ROLE_INTERFACES + sysroleid, JsonUtil.toJson(InterfaceUrls));
    }

    @Override
    public PageResult roleListForSystemUser(SysRoleinfo sysRoleinfo, PageBounds page) {
        Page<SysRoleinfoForRoleDO> list = sysRoleinfoService.roleList(sysRoleinfo, page.toRowBounds());
        if (CollectionUtils.isNotEmpty(list)) {
            return PageResult.getPageResult((int) list.getTotal(), page, list);
        }
        return PageResult.getPageResult(Constants.BANK_CARD_BINGDING_PROHABIT, page, new Page<SysRoleinfoForRoleDO>());
    }


}
