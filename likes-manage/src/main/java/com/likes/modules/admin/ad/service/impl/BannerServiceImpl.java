package com.likes.modules.admin.ad.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.BdBannerpicinfoDO;
import com.likes.common.model.dto.BdBannerseatDO;
import com.likes.common.model.dto.bas.BasInvestorsDO;
import com.likes.common.mybatis.entity.BasInvestors;
import com.likes.common.mybatis.entity.BdBannerpicinfo;
import com.likes.common.mybatis.entity.BdBannerseat;
import com.likes.common.mybatis.entity.BdBannerseatExample;
import com.likes.common.mybatis.mapper.BdBannerseatMapper;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.ad.BdBannerpicinfoService;
import com.likes.common.service.ad.BdBannerseatService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.JsonUtil;
import com.likes.modules.admin.ad.service.BannerService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BannerServiceImpl extends BaseServiceImpl implements BannerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private BdBannerseatMapper bannerseatMapper;

    @Autowired
    private BdBannerpicinfoService bdBannerpicinfoService;
    @Resource
    private BdBannerseatService bdBannerseatService;

    @Override
    public PageResult bannerseatList(BdBannerseat req, PageBounds page) {
        Page<BdBannerseatDO> list = bdBannerseatService.getList(req, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }

    @Override
    public BdBannerseat getBannerseatDetail(BdBannerseat req) {
        if (null == req.getBseatid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "ID??????");
        }
        BdBannerseat bannerseat = bdBannerseatService.selectByPrimaryKey(req.getBseatid());
        if (bannerseat != null && !bannerseat.getIsDelete()) {
            return bannerseat;
        }
        return null;
    }

    @Override
    public synchronized Long saveBannerseat(BdBannerseat req, LoginUser loginUser) {
        if (StringUtils.isEmpty(req.getSeatcode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }

        if (StringUtils.isEmpty(req.getSeatdesc())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "??????????????????");
        }

        if (StringUtils.isEmpty(req.getSeatname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "??????????????????");
        }

        // ???????????? ????????????
        BdBannerseat bannerseat = bdBannerseatService.getRepeat(req);
        if (bannerseat != null) {
            if (null != req.getIsEnable()) {
                if (req.getIsEnable()) {
                    //?????? ?????????????????????????????????????????????
                    throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "??????????????????");
                }
            } else {
                req.setIsEnable(false);
            }
        } else {
            if (null != req.getIsEnable()) {
                if (!req.getIsEnable()) {
                    //??????
                    if (("??????banner???").equals(req.getSeatname())) {
                        //??????banner?????????????????????????????? ????????????????????????????????????????????????
                        req.setIsEnable(true);
                    }
                    //?????????banner?????????/????????????????????????,?????????????????????????????????
                }
            } else {
                req.setIsEnable(false);
            }
        }
        req.setIsDelete(false);
        req.setCreateUser(loginUser.getAccno());
        req.setUpdateUser(loginUser.getAccno());
        bdBannerseatService.insertSelective(req);
        return req.getBseatid();
    }

    @Override
    public Long updateBannerseat(BdBannerseat req, LoginUser loginUser) {
        if (null == req.getBseatid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "ID??????");
        }
        if (StringUtils.isEmpty(req.getSeatcode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "??????????????????");
        }

        if (StringUtils.isEmpty(req.getSeatdesc())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "??????????????????");
        }

        if (StringUtils.isEmpty(req.getSeatname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "??????????????????");
        }
        if (StringUtils.isEmpty(req.getSeatcode())) {
            // ???????????? ????????????
            BdBannerseat bannerseat = bdBannerseatService.getRepeat(req);
            if (bannerseat != null) {
                if (null != req.getIsEnable() && req.getIsEnable()) {
                    //?????? ?????????????????????????????????????????????
                    bannerseat.setIsEnable(false);
                    bdBannerseatService.updateByPrimaryKeySelective(bannerseat);
                }
            } else {
                if (null != req.getIsEnable()) {
                    if (!req.getIsEnable()) {
                        //??????
                        if (("??????banner???").equals(req.getSeatname())) {
                            //??????banner?????????????????????????????? ????????????????????????????????????????????????
                            req.setIsEnable(true);
                        }
                        //?????????banner?????????/????????????????????????,?????????????????????????????????
                    }
                }
            }
        }

        req.setUpdateUser(loginUser.getAccno());
        bdBannerseatService.updateByPrimaryKeySelective(req);
        return req.getBseatid();
    }

    @Override
    public String delBannerseat(BdBannerseat req, LoginUser loginUser) {
        if (null == req.getBseatid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "ID??????");
        }
        if (null == req.getSeatcode()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "seatcode??????");
        }
        BdBannerseatExample bdBannerseatExample = new BdBannerseatExample();
        BdBannerseatExample.Criteria criteria = bdBannerseatExample.createCriteria();
        criteria.andSeatcodeEqualTo(req.getSeatcode());
        criteria.andIsDeleteEqualTo(false);
        Integer count = bannerseatMapper.countByExample(bdBannerseatExample);
        if (1 == count) {
            //??????
            if (("??????banner???").equals(req.getSeatname())) {
                //??????banner?????????????????????????????? ????????????????????????????????????????????????
                throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "??????banner?????????????????????");
            }
        }

        req.setIsDelete(true);
        req.setUpdateUser(loginUser.getAccno());
        bdBannerseatService.updateByPrimaryKeySelective(req);
        return Constants.SUCCESS_MSG;
    }



    @Override
    public PageResult adList(BdBannerpicinfo req, PageBounds page) {
        Page<BdBannerpicinfoDO> list = bdBannerpicinfoService.adsList(req, page.toRowBounds());
        list.forEach(v -> {
            v.setBndispicurl(v.getBndispic());

        });
        return PageResult.getPageResult(page, list);
    }

    @Override
    public Long saveAd(BdBannerpicinfo req, LoginUser loginUser) {
        if (null == req.getBseatid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "????????????");
        }

        if (null == req.getInvestorsid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "??????ID??????");
        }

        if (StringUtils.isEmpty(req.getBndispic())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "????????????");
        }

        if (req.getExpirydates() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "??????????????????");
        }

        if (req.getExpirydatee() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "??????????????????");
        }

        if (req.getExpirydates().getTime() > req.getExpirydatee().getTime()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "????????????????????????????????????");
        }

        if (null == req.getSortby()) {
            req.setSortby(0);
        }
        req.setIsDelete(false);
        req.setCreateUser(loginUser.getAccno());
        req.setUpdateUser(loginUser.getAccno());
        bdBannerpicinfoService.insertSelective(req);
        return req.getBannerpicid();
    }

    @Override
    public Long updateAd(BdBannerpicinfo req, LoginUser loginUser) {
        if (null == req.getBannerpicid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "ID??????");
        }

        if (null == req.getInvestorsid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "??????ID??????");
        }
        if (StringUtils.isEmpty(req.getBndispic())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "????????????");
        }

        if (req.getExpirydates() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "??????????????????");
        }

        if (req.getExpirydates() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "??????????????????");
        }

        System.out.println(JsonUtil.toJson(req));

        if (req.getExpirydates().getTime() > req.getExpirydatee().getTime()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "????????????????????????????????????");
        }

        if (null == req.getSortby()) {
            req.setSortby(0);
        }
        BdBannerpicinfo sourcesBannerpicinfo = bdBannerpicinfoService.selectByPrimaryKey(req.getBannerpicid());
        if (sourcesBannerpicinfo == null || sourcesBannerpicinfo.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "???????????????");
        }
        sourcesBannerpicinfo.setBndispic(req.getBndispic());
        sourcesBannerpicinfo.setBndisptxt(req.getBndisptxt());
        sourcesBannerpicinfo.setBndlink(req.getBndlink());
        sourcesBannerpicinfo.setBseatid(req.getBseatid());
        sourcesBannerpicinfo.setExpirydatee(req.getExpirydatee());
        sourcesBannerpicinfo.setExpirydates(req.getExpirydates());
        sourcesBannerpicinfo.setSortby(req.getSortby());
        sourcesBannerpicinfo.setInvestorsid(req.getInvestorsid());
        sourcesBannerpicinfo.setSpecparame(req.getSpecparame());
        sourcesBannerpicinfo.setUpdateUser(loginUser.getAccno());
        sourcesBannerpicinfo.setLinktype(req.getLinktype());
        sourcesBannerpicinfo.setTitle(req.getTitle());
        sourcesBannerpicinfo.setPathUrl(req.getPathUrl());
        sourcesBannerpicinfo.setWithinLink(req.getWithinLink());
//        bannerpicinfoMapper.updateByPrimaryKey(sourcesBannerpicinfo);
        bdBannerpicinfoService.updateByPrimaryKey(sourcesBannerpicinfo);
        return req.getBannerpicid();
    }


    @Override
    public String delAd(BdBannerpicinfo req, LoginUser loginUser) {
        if (null == req.getBannerpicid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "ID??????");
        }

        req.setIsDelete(true);
        req.setUpdateUser(loginUser.getAccno());
        bdBannerpicinfoService.updateByPrimaryKeySelective(req);
        return Constants.SUCCESS_MSG;
    }

}
