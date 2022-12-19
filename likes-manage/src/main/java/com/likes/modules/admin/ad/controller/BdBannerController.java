package com.likes.modules.admin.ad.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.BasInvestors;
import com.likes.common.mybatis.entity.BdBannerpicinfo;
import com.likes.common.mybatis.entity.BdBannerseat;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.ad.service.BannerService;
import com.likes.modules.admin.common.service.AWSS3Service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/ad")
public class BdBannerController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private BannerService bannerService;

    @Resource
    private AWSS3Service s3Service;

    @ResponseBody
    @RequestMapping(name = "广告位管理", value = "/bannerseatList", method = RequestMethod.GET)
    public ResultInfo bannerseatList(PageBounds page, BdBannerseat req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(bannerService.bannerseatList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.bannerseatList,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告位管理出错");
            logger.error("{}.bannerseatList,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/bannerseatList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "广告位详细", value = "/getBannerseatDetail", method = RequestMethod.GET)
    public ResultInfo getBannerseatDetail(BdBannerseat req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getBseatid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "ID为空");
            }
            response.setData(bannerService.getBannerseatDetail(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getBannerseatDetail,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告位详细出错");
            logger.error("{}.getBannerseatDetail,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/getBannerseatDetail耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("创建广告位")
    @ResponseBody
    @RequestMapping(name = "创建广告位", value = "/saveBannerseat", method = RequestMethod.POST)
    public ResultInfo saveBannerseat(BdBannerseat req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(req.getSeatcode())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "位置代码为空");
            }

            if (StringUtils.isEmpty(req.getSeatdesc())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "位置描述为空");
            }

            if (StringUtils.isEmpty(req.getSeatname())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "位置名称为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(bannerService.saveBannerseat(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".saveBannerseat", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.saveBannerseat,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("创建广告位出错");
            logger.error("{}.saveBannerseat,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/saveBannerseat耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("广告位编辑")
    @ResponseBody
    @RequestMapping(name = "广告位编辑", value = "/updateBannerseat", method = RequestMethod.POST)
    public ResultInfo updateBannerseat(BdBannerseat req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getBseatid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "ID为空");
            }
            if (StringUtils.isEmpty(req.getSeatcode())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "位置代码为空");
            }

            if (StringUtils.isEmpty(req.getSeatdesc())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "位置描述为空");
            }

            if (StringUtils.isEmpty(req.getSeatname())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "位置名称为空");
            }

            LoginUser loginUser = getLoginAdmin();
            response.setData(bannerService.updateBannerseat(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateBannerseat", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.updateBannerseat,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告位编辑出错");
            logger.error("{}.updateBannerseat,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/updateBannerseat耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("广告位删除")
    @ResponseBody
    @RequestMapping(name = "广告位删除", value = "/delBannerseat", method = RequestMethod.POST)
    public ResultInfo delBannerseat(BdBannerseat req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getBseatid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "ID为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(bannerService.delBannerseat(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".delBannerseat", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delBannerseat,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告位删除出错");
            logger.error("{}.delBannerseat,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/delBannerseat耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }




    @ResponseBody
    @RequestMapping(name = "广告列表管理", value = "/adList", method = RequestMethod.GET)
    public ResultInfo investorsList(PageBounds page, BdBannerpicinfo req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(bannerService.adList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.investorsList,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告列表管理出错");
            logger.error("{}.investorsList,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/adList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("广告列表管理,广告新增")
    @ResponseBody
    @RequestMapping(name = "广告列表管理,广告新增", value = "/saveAd", method = RequestMethod.POST)
    public ResultInfo saveAd(BdBannerpicinfo req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getBseatid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "位置为空");
            }
//            req.setInvestorsid(1);
//            if (null == req.getInvestorsid()) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "商家ID为空");
//            }
            if (null == req.getLinktype()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "类型不能为空");
            }
            if (req.getLinktype() == 1) {
                if (StringUtils.isEmpty(req.getTitle())) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "标题不能为空");
                }
                if (StringUtils.isEmpty(req.getBndisptxt())) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "广告文本不能为空");
                }
                if (req.getBndisptxt().length() > 100) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "广告文本超过100");
                }
            }
            if (req.getLinktype() == 2) {
                if (StringUtils.isEmpty(req.getTitle())) {
                    throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "标题不能为空");
                }
            }
            if (StringUtils.isEmpty(req.getBndispic())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "图片为空");
            }

            if (req.getExpirydates() == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "开始时间为空");
            }

            if (req.getExpirydatee() == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "结束时间为空");
            }

            if (req.getExpirydates().getTime() > req.getExpirydatee().getTime()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "开始时间不能大于结束时间");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(bannerService.saveAd(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".saveAd", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.saveAd,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告列表管理,广告新增出错");
            logger.error("{}.saveAd,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/saveAd耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("广告列表管理,广告编辑")
    @ResponseBody
    @RequestMapping(name = "广告列表管理,广告编辑", value = "/updateAd", method = RequestMethod.POST)
    public ResultInfo updateAd(BdBannerpicinfo req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getBannerpicid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "ID为空");
            }

            if (null == req.getInvestorsid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "商家ID为空");
            }
            if (StringUtils.isEmpty(req.getBndispic())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "图片为空");
            }

            if (req.getExpirydates() == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "开始时间为空");
            }

            if (req.getExpirydatee() == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "结束时间为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(bannerService.updateAd(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateAd", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.updateAd,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告列表管理,广告编辑出错");
            logger.error("{}.updateAd,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/updateAd耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("广告列表管理,广告删除")
    @ResponseBody
    @RequestMapping(name = "广告列表管理,广告删除", value = "/delAd", method = RequestMethod.POST)
    public ResultInfo delAd(BdBannerpicinfo req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getBannerpicid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "ID为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(bannerService.delAd(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".delAd", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delAd,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("广告列表管理,广告删除出错");
            logger.error("{}.delAd,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/delAd耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

}
