package com.likes.common.service.sys.impl;

import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.sys.SysWhitelistDO;
import com.likes.common.mybatis.entity.BdUser;
import com.likes.common.mybatis.entity.BdUserExample;
import com.likes.common.mybatis.entity.SysWhitelist;
import com.likes.common.mybatis.mapper.BdUserMapper;
import com.likes.common.mybatis.mapper.SysWhitelistMapper;
import com.likes.common.mybatis.mapperext.sys.SysWhitelistMapperExt;
import com.likes.common.service.sys.SysWhitelistService;
import com.likes.common.util.StringUtils;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mang guo
 * @version 1.0
 * @Description
 * @revise
 * @time 2020年3月17日
 * @copyright Copyright @2017, Co., Ltd. All right.1
 */
@Service
public class SysWhitelistServiceImpl implements SysWhitelistService {

    @Resource
    private SysWhitelistMapper sysWhitelistMapper;
    @Resource
    private SysWhitelistMapperExt sysWhitelistMapperExt;
    @Resource
    private BdUserMapper bdUserMapper;

    @Override
    public Long doSave(SysWhitelist sysWhitelist, LoginUser loginUser) {
        sysWhitelist.setSyscode(ModuleConstant.LIVE_MANAGE);
        sysWhitelist.setSysname("后台管理系统");
        sysWhitelist.setStatus(Constants.STATUS_0);
        sysWhitelist.setCreateUser(loginUser.getAccno());
        sysWhitelist.setUpdateUser(loginUser.getAccno());

        sysWhitelistMapper.insertSelective(sysWhitelist);
        return sysWhitelist.getWhiteid();
    }

    @Override
    public PageResult getList(SysWhitelist sysWhite, PageBounds page) {
        Page<SysWhitelistDO> list = sysWhitelistMapperExt.getList(sysWhite, page.toRowBounds());
        for (SysWhitelistDO sysWhitelistDO : list) {
            if (StringUtils.isNotEmpty(sysWhitelistDO.getUpdateUser())) {
                String updateUser = sysWhitelistDO.getUpdateUser();
                BdUserExample bdUserExample = new BdUserExample();
                BdUserExample.Criteria criteria = bdUserExample.createCriteria();
                criteria.andAccnoEqualTo(updateUser);
                BdUser bdUser = bdUserMapper.selectOneByExample(bdUserExample);
                if (bdUser != null) {
                    sysWhitelistDO.setUpdateUser(bdUser.getBdusername());
                }
            }
        }
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public Long doUpdate(SysWhitelist sysWhitelist, LoginUser loginUser) {
        sysWhitelist.setUpdateUser(loginUser.getAccno());
        sysWhitelistMapper.updateByPrimaryKeySelective(sysWhitelist);
        return sysWhitelist.getWhiteid();
    }

    @Override
    public String doDelWhite(SysWhitelist sysWhitelist, LoginUser loginUser) {
        sysWhitelist.setIsDelete(true);
        sysWhitelist.setUpdateUser(loginUser.getAccno());
        sysWhitelistMapper.updateByPrimaryKeySelective(sysWhitelist);
        return Constants.SUCCESS_MSG;
    }


    @Override
    public SysWhitelist findByIp(String syscode, String ipaddress) {
        if (StringUtils.isEmpty(syscode) || StringUtils.isEmpty(ipaddress)) {
            return null;
        }
        return sysWhitelistMapperExt.findByIp(syscode, ipaddress);
    }
}
