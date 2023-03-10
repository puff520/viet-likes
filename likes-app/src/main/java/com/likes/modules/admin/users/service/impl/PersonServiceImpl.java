package com.likes.modules.admin.users.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.Constants;
import com.likes.common.enums.CertStatusEnum;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.dto.VideoDO;
import com.likes.common.model.dto.bas.BasArticleDO;
import com.likes.common.model.dto.member.MemBaseinfoDO;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.dto.member.MemRelationshipDO;
import com.likes.common.model.dto.member.UserGoldDO;
import com.likes.common.model.dto.order.OrderRequest;
import com.likes.common.model.dto.order.OrderResponse;
import com.likes.common.model.request.ArticleRequest;
import com.likes.common.model.request.PayPasswordReq;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.request.VideoRequest;
import com.likes.common.model.request.body.UserRequestRowBoundsReq;
import com.likes.common.model.vo.member.AttentionUserVO;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemCertification;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemLevel;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.entity.MemRelationship;
import com.likes.common.mybatis.entity.SysFeedback;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.entity.SysTags;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.likes.common.mybatis.entity.TraOrdertracking;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.mybatis.mapper.SysFeedbackMapper;
import com.likes.common.mybatis.mapper.SysTagsMapper;
import com.likes.common.mybatis.mapperext.sys.SysTagsMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemCertificationService;
import com.likes.common.service.member.MemLevelService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.money.TraOrdertrackingService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.service.sys.SysShortmsgService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.DateUtils;
import com.likes.common.util.encrypt.AESUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.users.service.PersonService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String awsBucketNameVideo;
    @Resource
    private SysParamService sysParamService;

    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemRelationshipService memRelationshipService;
    @Resource
    private SysShortmsgService sysShortmsgService;
    @Resource
    private MemLoginMapper memLoginMapper;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private SysFeedbackMapper sysFeedbackMapper;
    @Resource
    private SysTagsMapper sysTagsMapper;
    @Resource
    private SysTagsMapperExt sysTagsMapperExt;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private MemCertificationService memCertificationService;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private TraOrdertrackingService traOrdertrackingMapperService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private MemLevelService memLevelService;

    @Override
    public MemBaseinfoDO getUserInfo(LoginUser loginUserAPP) {
        // ??????????????????
        String accno = loginUserAPP.getAccno();
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(accno);
        memBaseinfo = SpecialProcess(memBaseinfo);
        if (memBaseinfo != null) {
            MemBaseinfoDO memBaseinfoDO = new MemBaseinfoDO();
            BeanUtils.copyProperties(memBaseinfo, memBaseinfoDO);
            if (StringUtils.isNotEmpty(memBaseinfoDO.getHeadimg()) && !Constants.STR_NULL.equals(memBaseinfoDO.getHeadimg().trim())) {
                memBaseinfoDO.setHeadimgurl(memBaseinfoDO.getHeadimg());
            } else {
                memBaseinfoDO.setHeadimg(Constants.STR_NULL);
                memBaseinfoDO.setHeadimgurl(Constants.STR_NULL);
            }
            //??????
            memBaseinfoDO.setGoldnum(memBaseinfo.getGoldnum().setScale(3, BigDecimal.ROUND_HALF_DOWN));
            //?????????????????????????????????
            if (memBaseinfoDO.getBirthday() != null) {
                memBaseinfoDO.setBirth(DateUtils.getDateString(memBaseinfoDO.getBirthday()));
            }
//            //?????????--?????????
//            Integer attentionnum = memFollowService.getAttentionnum(memBaseinfo.getAccno());
//            memBaseinfoDO.setAttentionnum(attentionnum);
//            //?????????
//            memBaseinfoDO.setFansnum(memBaseinfo.getFansnum());

//            // ?????????--?????????
//            Integer collectionnum = basFavoritesRest.getFavoritesNum(memBaseinfoDO.getAccno());
//            memBaseinfoDO.setCollectionnum(collectionnum);
//            // ?????????--?????????
//            Integer resourcesnum = basVideoService.getResourcesnum(memBaseinfoDO.getAccno());
//            memBaseinfoDO.setResourcesnum(resourcesnum);

            //??????????????????--?????????
            MemRelationship mr = memRelationshipService.findByAccno(memBaseinfo.getAccno());
            memBaseinfoDO.setInvitationStatus(false);
            if (mr != null) {
                memBaseinfoDO.setInvitationStatus(true);
                // ???????????????????????????--?????????
                MemBaseinfo refMemBaseinfo = memBaseinfoService.getUserByAccno(mr.getRefaccno());
                if (null != refMemBaseinfo) {
                    memBaseinfoDO.setRefRecomcode(refMemBaseinfo.getRecomcode());
                }
            }

            // ??????--?????????
            MemLevel memLevelUser = memLevelService.selectByAccno(memBaseinfo.getAccno());
            if (memLevelUser == null) {
                memBaseinfoDO.setMemlevel(Constants.LEVEL_ONE);
            } else {
                memBaseinfoDO.setMemlevel(String.valueOf(memLevelUser.getMemlevel()));
            }

            // ????????????
            // isanchor??? ???????????????????????? 0 ?????? / 1 ?????????/ 9 ?????????/ 8 ?????????
            memBaseinfoDO.setIsanchor(CertStatusEnum.UNAUTHORIZED.getCode());
            memBaseinfoDO.setAcclogin(loginUserAPP.getAcclogin());
            return memBaseinfoDO;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_142.getCode(), "????????????");
        }
    }

    /**
     * ????????????????????????????????????????????????UniqueId
     */
    private MemBaseinfo SpecialProcess(MemBaseinfo info) {
        if (null != info && null == info.getUniqueId()) {
            RedisBusinessUtil.deleteAppMember(info.getMemid());
            MemBaseinfo newInfo = memBaseinfoService.getUserByAccnoNoCache(info.getAccno());
            RedisBusinessUtil.addAppMember(newInfo);
            return newInfo;
        }
        return info;
    }

    @Override
    public PageResult getMyPromotion(String accno, PageBounds page) {
        Page<PromotionDo> list = memRelationshipService.getMyPromotionList(accno, page.toRowBounds());
        if (!CollectionUtils.isEmpty(list)) {
            list.getResult().stream().map(v -> {
                String t = v.getMobileno();
                if (t.length() > 8) {
                    String phoneNumber = t.substring(0, 3) + "****" + t.substring(7);
                    v.setMobileno(phoneNumber);
                }
                return v;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }
        return PageResult.getPageResult(page, list);
    }

    @Override
    public Map<String, Object> sharePromotion(String accno) {
        Map<String, Object> map = new HashMap<String, Object>();
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(accno);
        String recomcode = memBaseinfo.getRecomcode();
        String jiami = AESUtils.encryptData(recomcode, AESUtils.KEY);
        try {
            jiami = URLEncoder.encode(jiami, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        SysParameter bs = sysParamService.getByCode(SysParameterEnum.H5_URL.getCode());
        if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "????????????(h5_url)??????");
        }
        String sysparamvalue = bs.getSysparamvalue();

        String url = sysparamvalue + "?param=" + jiami;
        map.put("invitecode", recomcode);
        map.put("downloadUrl", sysParamService.getByCode(SysParameterEnum.DOWNLOAD_URL.getCode()).getSysparamvalue());
        map.put("inviturl", url);
        return map;
    }



    @Override
    //@Transactional
    public String updateNickname(UsersRequest req, LoginUser loginUserAPP) {
        if (loginUserAPP == null || StringUtils.isEmpty(loginUserAPP.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), "?????????");
        }
        if (StringUtils.isBlank(req.getNickname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_143.getCode(), "????????????");
        }

        List<String> nickNameList = new ArrayList<>();
        nickNameList.add("BB??????");
        nickNameList.add("bb??????");
        nickNameList.add("BB");
        nickNameList.add("bb");
        nickNameList.add("??????");
        nickNameList.add("??????");
        if (nickNameList.contains(req.getNickname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_144.getCode(), "????????????????????????");
        }

        // ??????????????????????????????
        MemBaseinfo repeat = memBaseinfoService.getRepeateNickname(req.getNickname().trim());
        if (repeat != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_145.getCode(), "??????????????????");
        }

        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());


        MemBaseinfo newinfo = new MemBaseinfo();
        newinfo.setUpdateUser(loginUserAPP.getAccno());
        newinfo.setMemid(memBaseinfo.getMemid());
        newinfo.setNickname(req.getNickname().trim());
        memBaseinfoService.updateByPrimaryKeySelective(newinfo);

        // ????????????????????? ??????redis
        loginUserAPP.setNickname(req.getNickname().trim());
        RedisBusinessUtil.refreshUser(loginUserAPP, sysParamService);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String updateUserOther(UsersRequest req, LoginUser loginUserAPP) {
        if (loginUserAPP == null || StringUtils.isEmpty(loginUserAPP.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), "?????????");
        }

        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());
        if (StringUtils.isNotEmpty(req.getBirthday())) {
            Date date = DateUtils.parseDate(req.getBirthday(), "yyyy-MM-dd");
            memBaseinfo.setBirthday(date);
        }
        if (null != req.getSex()) {
            memBaseinfo.setSex(req.getSex());
        }

        if (StringUtils.isNotEmpty(req.getDescribes())) {
            memBaseinfo.setDescribes(req.getDescribes());
        }

        if (StringUtils.isNotEmpty(req.getHeadimg()) && !Constants.STR_NULL.equals(req.getHeadimg())) {
            memBaseinfo.setHeadimg(req.getHeadimg());
        }
        memBaseinfo.setUpdateUser(req.getAccno());
        memBaseinfo.setFansnum(null);
        memBaseinfo.setGoldnum(null);
        memBaseinfoService.updateByPrimaryKeySelective(memBaseinfo);

        return Constants.SUCCESS_MSG;
    }




//    @Override
//    public Integer doMyAttention(PromotionDo promotionDo, LoginUser loginUserAPP) {
//        if (null == promotionDo.getMemid()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
//        }
//        if (null == promotionDo.getIsattention()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "????????????");
//        }
//
//        if (promotionDo.getMemid().equals(loginUserAPP.getMemid())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_202.getCode(), "??????????????????");
//        }
//
//        promotionDo.setAccno(loginUserAPP.getAccno());
//        // ??????
//        VideoRequest v = new VideoRequest();
//        v.setMemid(promotionDo.getMemid());
//        v.setAccno(promotionDo.getAccno());
//
//        MemFollow memFollow = memFollowService.findByParam(v);
//
//        if (memFollow != null) {
//            if (promotionDo.getIsattention() == 0 && !memFollow.getIsDelete()) {
//                memFollow.setIsDelete(true);
//                memFollow.setUpdateUser(promotionDo.getAccno());
//                memFollowService.updateByPrimaryKeySelective(memFollow);
//                // ???????????????
//                UsersRequest ur = new UsersRequest();
//                ur.setMemid(v.getMemid());
//                ur.setIsattention(promotionDo.getIsattention());
//                memBaseinfoService.updateFansNum(ur);
//            } else if (promotionDo.getIsattention() == 1 && memFollow.getIsDelete()) {
//                memFollow.setIsDelete(false);
//                memFollow.setUpdateUser(promotionDo.getAccno());
//                memFollowService.updateByPrimaryKeySelective(memFollow);
//                // ???????????????
//                UsersRequest ur = new UsersRequest();
//                ur.setMemid(v.getMemid());
//                ur.setIsattention(promotionDo.getIsattention());
//                memBaseinfoService.updateFansNum(ur);
//            }
//
//            return promotionDo.getIsattention();
//        } else {
//            // throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "?????????????????????");
//            // ????????????
//            // ??????
//            if (promotionDo.getIsattention() == 0) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_203.getCode(), "????????????????????????");
//            }
//            MemFollow m = new MemFollow();
//            m.setMemid(promotionDo.getMemid());
//            m.setAccno(promotionDo.getAccno());
//            m.setCreateUser(promotionDo.getAccno());
//            m.setUpdateUser(promotionDo.getAccno());
//            memFollowService.insertSelective(m);
//
//            // ???????????????
//            UsersRequest ur = new UsersRequest();
//            ur.setMemid(promotionDo.getMemid());
//            ur.setIsattention(1);
//            memBaseinfoService.updateFansNum(ur);
//
//            return promotionDo.getIsattention();
//        }
//
//    }




    @Override
    public long doFeedback(SysFeedback sysFeedback, String accno) {
        if (sysFeedback == null || StringUtils.isEmpty(sysFeedback.getFeedbody())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_146.getCode(), "????????????");
        }
        if (StringUtils.isEmpty(sysFeedback.getFeedimgs())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_147.getCode(), "????????????");
        }
        if (StringUtils.isNotEmpty(sysFeedback.getFeedimgs())) {
            String[] arr = sysFeedback.getFeedimgs().split(",");
            if (arr.length > 9) {
                throw new BusinessException(StatusCode.LIVE_ERROR_148.getCode(), "??????9?????????");
            }
        }
        MemBaseinfo userByAccno = memBaseinfoService.getUserByAccno(accno);
        if (userByAccno != null) {
            sysFeedback.setUniqueId(userByAccno.getUniqueId());
        }
        sysFeedback.setCreateUser(accno);
        sysFeedback.setUpdateUser(accno);
        sysFeedback.setStatus(9);
        if (null == sysFeedback.getFeedtype()) {
            sysFeedback.setFeedtype(1);
        }
        sysFeedback.setAccno(accno);
        sysFeedbackMapper.insertSelective(sysFeedback);
        return sysFeedback.getFeedid();
    }

    @Override
    public String loginout(LoginUser loginUserAPP) {
        if (loginUserAPP != null) {
            if (StringUtils.isNotEmpty(loginUserAPP.getAcctoken())) {
                redisTemplate.delete(loginUserAPP.getAcctoken());
            }
            if (StringUtils.isNotEmpty(loginUserAPP.getAccno())) {
                redisTemplate.delete(loginUserAPP.getAccno());
            }
        }

        return Constants.SUCCESS_MSG;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String fillinRecomcode(UsersRequest usersRequest, LoginUser loginUserAPP) {
        if (StringUtils.isEmpty(usersRequest.getInvitecode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_141.getCode(), "???????????????");
        }

        String recomcode = usersRequest.getInvitecode();

        // ???????????????????????????
        if (recomcode.equals(loginUserAPP.getRecomcode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_149.getCode(), "???????????????????????????");
        }
        // ??????????????????????????? ?????????
        MemRelationship ship = memRelationshipService.findByAccno(loginUserAPP.getAccno());
        if (ship != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_150.getCode(), "??????????????????");
        }
        // ??????????????? ?????? ??? ????????????
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByInvitecode(recomcode);
        if (memBaseinfo == null) {
            // ???????????????
            throw new BusinessException(StatusCode.LIVE_ERROR_151.getCode(), "???????????????");
        }
        // ???????????? ???????????? ?????????
        String recomcodeAccno = memBaseinfo.getAccno();
        List<MemRelationshipDO> allList = memRelationshipService.getAllChild(usersRequest.getAccno());
        if (!CollectionUtils.isEmpty(allList)) {
            List<String> allAccnoList = new ArrayList<String>();
            diguiRecomcode(allList, allAccnoList);
            if (allAccnoList.contains(recomcodeAccno)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_152.getCode(), "????????????????????????");
            }
        }
        try {
            // ????????????
            MemRelationship memRelationship = new MemRelationship();
            memRelationship.setRefaccno(recomcodeAccno);
            memRelationship.setAccno(usersRequest.getAccno());
            memRelationship.setMemname(loginUserAPP.getMemname());
            memRelationship.setCreateUser(loginUserAPP.getAccno());
            memRelationship.setUpdateUser(loginUserAPP.getAccno());
            int i = memRelationshipService.insertSelectiveMemRelationship(memRelationship);
            if (i > 0) {
//                // ??????????????? ??????????????? ?????? ?????????
//                MemFollow memFollow = new MemFollow();
//                memFollow.setAccno(usersRequest.getAccno());
//                memFollow.setMemid(memBaseinfo.getMemid());
//                memFollow.setCreateUser(usersRequest.getAccno());
//                memFollow.setUpdateUser(usersRequest.getAccno());
//                memFollowService.insertSelective(memFollow);

                UsersRequest ur = new UsersRequest();
                ur.setMemid(memBaseinfo.getMemid());
                ur.setIsattention(1);
                memBaseinfoService.updateFansNum(ur);

                //// 20200317 ?????? ??? ????????? ?????????????????????
                if (!LoginUserTypeEnum.isAnchor(loginUserAPP.getLogintype())) {
                    // ????????? ????????????
                    UserGoldDO userGoldDO = memBaseinfoService.getUserRecomcodeGold(usersRequest.getAccno());
                    if (userGoldDO != null && StringUtils.isNotEmpty(userGoldDO.getRefaccno())
                            && null == userGoldDO.getGoldchangid()) {
                        this.doTuijianUser(userGoldDO, memBaseinfo.getAccno());
                    }
                }

                return Constants.SUCCESS_MSG;
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_150.getCode(), "?????????????????????");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("{}.fillinRecomcode ????????????", getClass().getName(), e);
            throw e;
        }
    }

    private void diguiRecomcode(List<MemRelationshipDO> allList, List<String> allAccnoList) {
        for (MemRelationshipDO memRelationship : allList) {
            allAccnoList.add(memRelationship.getAccno());
            // ???????????????
            List<MemRelationshipDO> child = memRelationship.getChildren();
            if (!CollectionUtils.isEmpty(child)) {
                diguiRecomcode(child, allAccnoList);
            }
        }
    }

    public void doTuijianUser(UserGoldDO userGoldDO, String opearteAccno) {
        SysParameter goldnum_parameter = this.sysParamService.getByCode(SysParameterEnum.RECOMMEND_USER.name());
        if (goldnum_parameter == null || StringUtils.isEmpty(goldnum_parameter.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "????????????(recommend_user)??????");
        }
        if (Constants.STATUS_9.equals(goldnum_parameter.getStatus())) {
            return;
        }
        Double goldnum = Double.parseDouble(goldnum_parameter.getSysparamvalue());

        // ??????????????????
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setGoldnum(getTradeOffAmount(new BigDecimal(goldnum)).doubleValue());
        usersRequest.setAccno(userGoldDO.getRefaccno());
        // ????????????????????????
        MemGoldchange memGoldchange = new MemGoldchange();
        memGoldchange.setRefid(userGoldDO.getRelaid());
        memGoldchange.setAccno(userGoldDO.getRefaccno());
//        memGoldchange.setChangetype(GoldchangeEnum.INVITE_USERS.getValue());

        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(userGoldDO.getRefaccno());
        if (memBaseinfo == null || memBaseinfo.getIsDelete()) {
            logger.info(userGoldDO.getRefaccno() + "?????????");
        } else {

            memGoldchange.setGoldnum(getTradeOffAmount(memBaseinfo.getGoldnum()));
            memGoldchange.setQuantity(getTradeOffAmount(new BigDecimal(goldnum)));
            BigDecimal recgoldnum = memGoldchange.getGoldnum().add(memGoldchange.getQuantity());
            memGoldchange.setRecgoldnum(getTradeOffAmount(recgoldnum));
            memGoldchange.setAmount(getTradeOffAmount(new BigDecimal(goldnum)));
            memGoldchange.setIsDelete(false);
            memGoldchange.setCreateUser(opearteAccno);
            memGoldchange.setUpdateUser(opearteAccno);
            memGoldchange.setOpnote("???????????????");
            MemGoldchangeDO memGoldchangeDO = new MemGoldchangeDO();
            BeanUtils.copyProperties(memGoldchange, memGoldchangeDO);
            memGoldchangeDO.setShowChange(getTradeOffAmount(new BigDecimal(goldnum)));
            memGoldchangeDO.setNoWithdrawalAmount(getTradeOffAmount(new BigDecimal(goldnum)));
            memBaseinfoWriteService.updateUserBalance(memGoldchangeDO);
            // ???insert?????????
           /* int i = memGoldchangeService.insertSelectiveMemGoldchange(memGoldchange);
            if (i > 0) {
                memBaseinfoService.updateAddGold(usersRequest);
            } else {
                logger.info("??????????????????");
            }*/
        }
    }


    private Page<BasArticleDO> getBasArticles(Page<BasArticleDO> basArticles) {
        for (BasArticleDO basArticleDO : basArticles) {
            // ????????????
            if (StringUtils.isNotEmpty(basArticleDO.getPicids())) {
                List<String> picidsList = new ArrayList<String>();
                String[] picids = basArticleDO.getPicids().split(",");
                for (String picid : picids) {
                    picidsList.add(picid);
                }
                basArticleDO.setPicidsList(picidsList);
            }
            if (StringUtils.isNotEmpty(basArticleDO.getVideoid())) {
                String url = basArticleDO.getVideoid();
                basArticleDO.setVideobofangurl(url);
            }

//            if (StringUtils.isNotEmpty(basArticleDO.getHeadimg())) {
//                basArticleDO.setHeadimgurl(basArticleDO.getHeadimg());
//            }
            if (StringUtils.isNotEmpty(basArticleDO.getTitle())) {
                SysTags sysTags = sysTagsMapper.selectByPrimaryKey(Long.parseLong(basArticleDO.getTitle()));
                if (sysTags != null && !sysTags.getIsDelete()) {
                    basArticleDO.setTitlename(sysTags.getTagname());
                }
            }

            // ????????????
            if (StringUtils.isNotEmpty(basArticleDO.getUsertags())) {
                String[] userTags = basArticleDO.getUsertags().split(",");
                // ??????????????????
                List<String> tagsNameList = sysTagsMapperExt.getTagBQName(userTags);
                basArticleDO.setUsertagsList(tagsNameList);
            }
        }
        return basArticles;
    }









    @Override
    public Map<String, Object> getAnchorMM(LoginUser loginUserAPP) {
        Map<String, Object> dataMap = new HashMap<>();
        MemBaseinfo memBaseinfo = memBaseinfoService.selectByPrimaryKey(loginUserAPP.getMemid());
        if (memBaseinfo == null || memBaseinfo.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_153.getCode(), "?????????????????????");
        }
        dataMap.put("wechat", memBaseinfo.getWechat());
        dataMap.put("memname", memBaseinfo.getMemname());
        dataMap.put("idcardno", memBaseinfo.getIdcardno());
        dataMap.put("idcardfront", memBaseinfo.getIdcardfront());
        dataMap.put("idcardback", memBaseinfo.getIdcardback());
        if (StringUtils.isNotEmpty(memBaseinfo.getIdcardfront())) {
            dataMap.put("idcardfronturl", memBaseinfo.getIdcardfront());
        } else {
            dataMap.put("idcardfronturl", null);
        }
        if (StringUtils.isNotEmpty(memBaseinfo.getIdcardback())) {
            dataMap.put("idcardbackurl", memBaseinfo.getIdcardback());
        } else {
            dataMap.put("idcardbackurl", null);
        }

        MemCertification memCertification = memCertificationService.findByAccno(loginUserAPP.getAccno());
        if (memCertification == null) {
            dataMap.put("certstatus", null);
            dataMap.put("certdesc", null);
        } else {
            dataMap.put("certstatus", memCertification.getCertstatus());
            dataMap.put("certdesc", memCertification.getCertdesc());
        }
        return dataMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String applyAnchorMM(LoginUser loginUserAPP, MemBaseinfo memBaseinfo) {
        if (StringUtils.isEmpty(memBaseinfo.getMemname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }
        if (StringUtils.isEmpty(memBaseinfo.getIdcardno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "?????????????????????");
        }
        if (StringUtils.isEmpty(memBaseinfo.getIdcardfront())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "?????????????????????");
        }
        if (StringUtils.isEmpty(memBaseinfo.getIdcardback())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
        }

        if (StringUtils.isEmpty(memBaseinfo.getWechat())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_111.getCode(), "???????????????");
        }

        if (LoginUserTypeEnum.ANCHOR.getCode().equals(loginUserAPP.getLogintype())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "???????????????");
        }

        MemCertification memCertification = memCertificationService.findByAccno(loginUserAPP.getAccno());
        if (memCertification != null && memCertification.getCertstatus() == CertStatusEnum.PASS.getCode()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "???????????????");
        }

        if (memCertification != null && memCertification.getCertstatus() == CertStatusEnum.REVIEW.getCode()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "???????????????????????????????????????");
        }

        if (memCertification == null || memCertification.getCertstatus() == CertStatusEnum.NOPASS.getCode()) {
            // ????????????
            MemCertification mCertification = new MemCertification();
            mCertification.setAccno(loginUserAPP.getAccno());
            mCertification.setCertstatus(CertStatusEnum.REVIEW.getCode());
            mCertification.setCreateUser(loginUserAPP.getAccno());
            mCertification.setUpdateUser(loginUserAPP.getAccno());
            mCertification.setIsDelete(false);
            int i = memCertificationService.insertSelectiveMemCertification(mCertification);
            if (i > 0) {
                memBaseinfo.setMemid(loginUserAPP.getMemid());
                memBaseinfo.setFansnum(null);
                memBaseinfo.setGoldnum(null);
                memBaseinfoService.updateByPrimaryKeySelective(memBaseinfo);
                return Constants.SUCCESS_MSG;
            }
        }

        return Constants.FAIL_MSG;
    }



    @Override
    public PageResult myOrderList(PageBounds page, OrderRequest req) {
        Page<OrderResponse> list = traOrderinfomMapperService.getMyOrderList(req, page.toRowBounds());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(o -> {
                o.setRealamt(o.getRealamt().setScale(3, BigDecimal.ROUND_DOWN));
                if (!o.getOrderstatus().equals(Constants.ORDER_ORD13)) {
                    o.setOrdernote(null);
                }
            });
        }
        logger.info("myOrderList,list:{}", JSONObject.toJSONString(list));
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public Map<String, Object> myOrderDetail(OrderRequest req) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom != null) {
            dataMap.put("createdate", DateUtils.formatDate(traOrderinfom.getCreateTime(), "yyyy-MM-dd"));
            dataMap.put("orderno", traOrderinfom.getOrderno());
            dataMap.put("copyno", traOrderinfom.getOrderno());
            dataMap.put("realamt", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
            BigDecimal givepercent = traOrderinfom.getSumamt().subtract(traOrderinfom.getRealamt())
                    .divide(traOrderinfom.getRealamt(), 3, BigDecimal.ROUND_DOWN);
            dataMap.put("givepercent", givepercent.setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
            dataMap.put("rechargegold", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
            dataMap.put("givegold", traOrderinfom.getSumamt().subtract(traOrderinfom.getRealamt())
                    .setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
            dataMap.put("allgold", traOrderinfom.getSumamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());

            dataMap.put("weichat", traOrderinfom.getPaywechat());
            // ?????????????????? ?????????????????????????????????
            // ??????????????? ?????????????????????????????????????????????????????????????????????
            TraOrderinfom isnew = traOrderinfomMapperService.isNew(traOrderinfom.getAccno());
            // ?????????????????? 0??? 9???
            if (isnew != null) {
                dataMap.put("isnew", Constants.NO);
            } else {
                dataMap.put("isnew", Constants.YES);
            }
        }
        return dataMap;
    }

    @Override
    public String cancelOrder(LoginUser loginUserAPP, OrderRequest req) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom != null) {
            // ??????????????????
            if (Constants.ORDER_ORD04.equals(traOrderinfom.getOrderstatus())) {
                traOrderinfom.setOrderstatus(Constants.ORDER_ORD09);
                traOrderinfom.setUpdateUser(loginUserAPP.getAccno());
                int i = traOrderinfomMapperService.updateStatus(traOrderinfom);
                if (i > 0) {
                    // ??????????????????
                    TraOrdertracking traOrdertracking = new TraOrdertracking();
                    traOrdertracking.setOrderid(traOrderinfom.getOrderid());
                    traOrdertracking.setTrackdate(new Date());
                    traOrdertracking.setOrderstatus(Constants.ORDER_ORD09);
                    traOrdertracking.setOperuse(loginUserAPP.getAccno());
                    traOrdertracking.setTrackbody("??????[" + loginUserAPP.getNickname() + "]????????????");
                    traOrdertrackingMapperService.insertSelective(traOrdertracking);

                    return Constants.SUCCESS_MSG;
                }

            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "????????????????????????????????????");
            }
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "???????????????");
        }
        return null;
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public String setPayPassword(LoginUser loginUserAPP, PayPasswordReq req) {
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "??????????????????");
        }

        // ?????? ?????? ?????? ????????????
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "??????????????????????????????");
        }

        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (memLogin != null && Constants.ACCSTATUS_1.equals(memLogin.getAccstatus())) {
            if (StringUtils.isNotEmpty(memLogin.getPaypassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "????????????????????????");
            }

            MemLogin m = new MemLogin();
            m.setLoginid(memLogin.getLoginid());
            m.setPaypassword(req.getPaypassword());
            memLoginMapper.updateByPrimaryKeySelective(m);
            return Constants.SUCCESS_MSG;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "???????????????/??????");
        }
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public String updatePayPassword(LoginUser loginUserAPP, PayPasswordReq req) {
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "??????????????????");
        }
        if (StringUtils.isEmpty(req.getOldpassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "?????????????????????");
        }

        // ?????? ?????? ?????? ????????????
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "??????????????????????????????");
        }
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (memLogin != null && Constants.ACCSTATUS_1.equals(memLogin.getAccstatus())) {
            if (StringUtils.isEmpty(memLogin.getPaypassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "????????????????????????");
            }

            if (!req.getOldpassword().equals(memLogin.getPaypassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "????????????????????????");
            }

            MemLogin m = new MemLogin();
            m.setLoginid(memLogin.getLoginid());
            m.setPaypassword(req.getPaypassword());
            memLoginMapper.updateByPrimaryKeySelective(m);
            return Constants.SUCCESS_MSG;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "???????????????/??????");
        }
    }

    @Override
    public Map<String, Object> doReflushGoldnumBalance(LoginUser loginUser) {
        Map<String, Object> dataMap = new HashMap<>();
        try {
            MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUser.getAccno());
            dataMap.put("goldnum", getTradeOffAmount(memBaseinfo.getGoldnum()));
            return dataMap;
        } catch (Exception e) {
            logger.error("??????????????????", loginUser.getAccno(), e);
            return dataMap;
        }
    }

    @Override
    public String getRecomcode(LoginUser loginUser) {
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUser.getAccno());
        return memBaseinfo.getRecomcode();
    }
}
