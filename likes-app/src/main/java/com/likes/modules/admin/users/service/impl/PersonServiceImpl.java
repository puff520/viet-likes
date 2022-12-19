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
        // 获取详细信息
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
            //金额
            memBaseinfoDO.setGoldnum(memBaseinfo.getGoldnum().setScale(3, BigDecimal.ROUND_HALF_DOWN));
            //生日日期，用于前端展示
            if (memBaseinfoDO.getBirthday() != null) {
                memBaseinfoDO.setBirth(DateUtils.getDateString(memBaseinfoDO.getBirthday()));
            }
//            //关注数--走缓存
//            Integer attentionnum = memFollowService.getAttentionnum(memBaseinfo.getAccno());
//            memBaseinfoDO.setAttentionnum(attentionnum);
//            //粉丝数
//            memBaseinfoDO.setFansnum(memBaseinfo.getFansnum());

//            // 收藏数--走缓存
//            Integer collectionnum = basFavoritesRest.getFavoritesNum(memBaseinfoDO.getAccno());
//            memBaseinfoDO.setCollectionnum(collectionnum);
//            // 资源数--走缓存
//            Integer resourcesnum = basVideoService.getResourcesnum(memBaseinfoDO.getAccno());
//            memBaseinfoDO.setResourcesnum(resourcesnum);

            //是否被邀请过--走缓存
            MemRelationship mr = memRelationshipService.findByAccno(memBaseinfo.getAccno());
            memBaseinfoDO.setInvitationStatus(false);
            if (mr != null) {
                memBaseinfoDO.setInvitationStatus(true);
                // 是否已经填写邀请码--走缓存
                MemBaseinfo refMemBaseinfo = memBaseinfoService.getUserByAccno(mr.getRefaccno());
                if (null != refMemBaseinfo) {
                    memBaseinfoDO.setRefRecomcode(refMemBaseinfo.getRecomcode());
                }
            }

            // 等级--走缓存
            MemLevel memLevelUser = memLevelService.selectByAccno(memBaseinfo.getAccno());
            if (memLevelUser == null) {
                memBaseinfoDO.setMemlevel(Constants.LEVEL_ONE);
            } else {
                memBaseinfoDO.setMemlevel(String.valueOf(memLevelUser.getMemlevel()));
            }

            // 是否主播
            // isanchor： 是否已经认证主播 0 通过 / 1 待审核/ 9 未通过/ 8 未认证
            memBaseinfoDO.setIsanchor(CertStatusEnum.UNAUTHORIZED.getCode());
            memBaseinfoDO.setAcclogin(loginUserAPP.getAcclogin());
            return memBaseinfoDO;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_142.getCode(), "无效用户");
        }
    }

    /**
     * 特殊处理用户信息，保证用户信息有UniqueId
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
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(h5_url)异常");
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
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), "未登录");
        }
        if (StringUtils.isBlank(req.getNickname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_143.getCode(), "昵称为空");
        }

        List<String> nickNameList = new ArrayList<>();
        nickNameList.add("BB直播");
        nickNameList.add("bb直播");
        nickNameList.add("BB");
        nickNameList.add("bb");
        nickNameList.add("逼逼");
        nickNameList.add("官方");
        if (nickNameList.contains(req.getNickname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_144.getCode(), "昵称包含非法字符");
        }

        // 查询昵称是否已经存在
        MemBaseinfo repeat = memBaseinfoService.getRepeateNickname(req.getNickname().trim());
        if (repeat != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_145.getCode(), "该昵称已存在");
        }

        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());


        MemBaseinfo newinfo = new MemBaseinfo();
        newinfo.setUpdateUser(loginUserAPP.getAccno());
        newinfo.setMemid(memBaseinfo.getMemid());
        newinfo.setNickname(req.getNickname().trim());
        memBaseinfoService.updateByPrimaryKeySelective(newinfo);

        // 昵称修改成功后 刷新redis
        loginUserAPP.setNickname(req.getNickname().trim());
        RedisBusinessUtil.refreshUser(loginUserAPP, sysParamService);
        return Constants.SUCCESS_MSG;
    }

    @Override
    public String updateUserOther(UsersRequest req, LoginUser loginUserAPP) {
        if (loginUserAPP == null || StringUtils.isEmpty(loginUserAPP.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), "未登录");
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
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "关注人为空");
//        }
//        if (null == promotionDo.getIsattention()) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "关注为空");
//        }
//
//        if (promotionDo.getMemid().equals(loginUserAPP.getMemid())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_202.getCode(), "不能关注自己");
//        }
//
//        promotionDo.setAccno(loginUserAPP.getAccno());
//        // 查询
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
//                // 更新粉丝数
//                UsersRequest ur = new UsersRequest();
//                ur.setMemid(v.getMemid());
//                ur.setIsattention(promotionDo.getIsattention());
//                memBaseinfoService.updateFansNum(ur);
//            } else if (promotionDo.getIsattention() == 1 && memFollow.getIsDelete()) {
//                memFollow.setIsDelete(false);
//                memFollow.setUpdateUser(promotionDo.getAccno());
//                memFollowService.updateByPrimaryKeySelective(memFollow);
//                // 更新粉丝数
//                UsersRequest ur = new UsersRequest();
//                ur.setMemid(v.getMemid());
//                ur.setIsattention(promotionDo.getIsattention());
//                memBaseinfoService.updateFansNum(ur);
//            }
//
//            return promotionDo.getIsattention();
//        } else {
//            // throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "没有关注过此人");
//            // 新增关注
//            // 插入
//            if (promotionDo.getIsattention() == 0) {
//                throw new BusinessException(StatusCode.LIVE_ERROR_203.getCode(), "不能新增取消关注");
//            }
//            MemFollow m = new MemFollow();
//            m.setMemid(promotionDo.getMemid());
//            m.setAccno(promotionDo.getAccno());
//            m.setCreateUser(promotionDo.getAccno());
//            m.setUpdateUser(promotionDo.getAccno());
//            memFollowService.insertSelective(m);
//
//            // 更新粉丝数
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
            throw new BusinessException(StatusCode.LIVE_ERROR_146.getCode(), "内容为空");
        }
        if (StringUtils.isEmpty(sysFeedback.getFeedimgs())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_147.getCode(), "图片为空");
        }
        if (StringUtils.isNotEmpty(sysFeedback.getFeedimgs())) {
            String[] arr = sysFeedback.getFeedimgs().split(",");
            if (arr.length > 9) {
                throw new BusinessException(StatusCode.LIVE_ERROR_148.getCode(), "最多9张图片");
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
            throw new BusinessException(StatusCode.LIVE_ERROR_141.getCode(), "邀请码为空");
        }

        String recomcode = usersRequest.getInvitecode();

        // 不能填自己的邀请码
        if (recomcode.equals(loginUserAPP.getRecomcode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_149.getCode(), "不能填自己的邀请码");
        }
        // 查询是否已经填写过 邀请码
        MemRelationship ship = memRelationshipService.findByAccno(loginUserAPP.getAccno());
        if (ship != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_150.getCode(), "已填过邀请码");
        }
        // 验证邀请码 是否 有 有效用户
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByInvitecode(recomcode);
        if (memBaseinfo == null) {
            // 用户不存在
            throw new BusinessException(StatusCode.LIVE_ERROR_151.getCode(), "无效邀请码");
        }
        // 不能填写 子节点的 邀请码
        String recomcodeAccno = memBaseinfo.getAccno();
        List<MemRelationshipDO> allList = memRelationshipService.getAllChild(usersRequest.getAccno());
        if (!CollectionUtils.isEmpty(allList)) {
            List<String> allAccnoList = new ArrayList<String>();
            diguiRecomcode(allList, allAccnoList);
            if (allAccnoList.contains(recomcodeAccno)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_152.getCode(), "不能用下级邀请码");
            }
        }
        try {
            // 新建关系
            MemRelationship memRelationship = new MemRelationship();
            memRelationship.setRefaccno(recomcodeAccno);
            memRelationship.setAccno(usersRequest.getAccno());
            memRelationship.setMemname(loginUserAPP.getMemname());
            memRelationship.setCreateUser(loginUserAPP.getAccno());
            memRelationship.setUpdateUser(loginUserAPP.getAccno());
            int i = memRelationshipService.insertSelectiveMemRelationship(memRelationship);
            if (i > 0) {
//                // 关系成功后 ，主动关注 这个 邀请人
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

                //// 20200317 修改 为 只计数 不给主播加金币
                if (!LoginUserTypeEnum.isAnchor(loginUserAPP.getLogintype())) {
                    // 成功后 写入金币
                    UserGoldDO userGoldDO = memBaseinfoService.getUserRecomcodeGold(usersRequest.getAccno());
                    if (userGoldDO != null && StringUtils.isNotEmpty(userGoldDO.getRefaccno())
                            && null == userGoldDO.getGoldchangid()) {
                        this.doTuijianUser(userGoldDO, memBaseinfo.getAccno());
                    }
                }

                return Constants.SUCCESS_MSG;
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_150.getCode(), "已经填过邀请码");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("{}.fillinRecomcode 执行异常", getClass().getName(), e);
            throw e;
        }
    }

    private void diguiRecomcode(List<MemRelationshipDO> allList, List<String> allAccnoList) {
        for (MemRelationshipDO memRelationship : allList) {
            allAccnoList.add(memRelationship.getAccno());
            // 获取子节点
            List<MemRelationshipDO> child = memRelationship.getChildren();
            if (!CollectionUtils.isEmpty(child)) {
                diguiRecomcode(child, allAccnoList);
            }
        }
    }

    public void doTuijianUser(UserGoldDO userGoldDO, String opearteAccno) {
        SysParameter goldnum_parameter = this.sysParamService.getByCode(SysParameterEnum.RECOMMEND_USER.name());
        if (goldnum_parameter == null || StringUtils.isEmpty(goldnum_parameter.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(recommend_user)异常");
        }
        if (Constants.STATUS_9.equals(goldnum_parameter.getStatus())) {
            return;
        }
        Double goldnum = Double.parseDouble(goldnum_parameter.getSysparamvalue());

        // 更新金币参数
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setGoldnum(getTradeOffAmount(new BigDecimal(goldnum)).doubleValue());
        usersRequest.setAccno(userGoldDO.getRefaccno());
        // 插入金币变动数据
        MemGoldchange memGoldchange = new MemGoldchange();
        memGoldchange.setRefid(userGoldDO.getRelaid());
        memGoldchange.setAccno(userGoldDO.getRefaccno());
//        memGoldchange.setChangetype(GoldchangeEnum.INVITE_USERS.getValue());

        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(userGoldDO.getRefaccno());
        if (memBaseinfo == null || memBaseinfo.getIsDelete()) {
            logger.info(userGoldDO.getRefaccno() + "已删除");
        } else {

            memGoldchange.setGoldnum(getTradeOffAmount(memBaseinfo.getGoldnum()));
            memGoldchange.setQuantity(getTradeOffAmount(new BigDecimal(goldnum)));
            BigDecimal recgoldnum = memGoldchange.getGoldnum().add(memGoldchange.getQuantity());
            memGoldchange.setRecgoldnum(getTradeOffAmount(recgoldnum));
            memGoldchange.setAmount(getTradeOffAmount(new BigDecimal(goldnum)));
            memGoldchange.setIsDelete(false);
            memGoldchange.setCreateUser(opearteAccno);
            memGoldchange.setUpdateUser(opearteAccno);
            memGoldchange.setOpnote("邀请新用户");
            MemGoldchangeDO memGoldchangeDO = new MemGoldchangeDO();
            BeanUtils.copyProperties(memGoldchange, memGoldchangeDO);
            memGoldchangeDO.setShowChange(getTradeOffAmount(new BigDecimal(goldnum)));
            memGoldchangeDO.setNoWithdrawalAmount(getTradeOffAmount(new BigDecimal(goldnum)));
            memBaseinfoWriteService.updateUserBalance(memGoldchangeDO);
            // 再insert处枷锁
           /* int i = memGoldchangeService.insertSelectiveMemGoldchange(memGoldchange);
            if (i > 0) {
                memBaseinfoService.updateAddGold(usersRequest);
            } else {
                logger.info("已经加过金币");
            }*/
        }
    }


    private Page<BasArticleDO> getBasArticles(Page<BasArticleDO> basArticles) {
        for (BasArticleDO basArticleDO : basArticles) {
            // 图片集合
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

            // 标签集合
            if (StringUtils.isNotEmpty(basArticleDO.getUsertags())) {
                String[] userTags = basArticleDO.getUsertags().split(",");
                // 获取标签名称
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
            throw new BusinessException(StatusCode.LIVE_ERROR_153.getCode(), "用户信息不存在");
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
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "真实姓名为空");
        }
        if (StringUtils.isEmpty(memBaseinfo.getIdcardno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "身份证号码为空");
        }
        if (StringUtils.isEmpty(memBaseinfo.getIdcardfront())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "身份证前照为空");
        }
        if (StringUtils.isEmpty(memBaseinfo.getIdcardback())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "身份证后照为空");
        }

        if (StringUtils.isEmpty(memBaseinfo.getWechat())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_111.getCode(), "微信号为空");
        }

        if (LoginUserTypeEnum.ANCHOR.getCode().equals(loginUserAPP.getLogintype())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "已经是主播");
        }

        MemCertification memCertification = memCertificationService.findByAccno(loginUserAPP.getAccno());
        if (memCertification != null && memCertification.getCertstatus() == CertStatusEnum.PASS.getCode()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "已经是主播");
        }

        if (memCertification != null && memCertification.getCertstatus() == CertStatusEnum.REVIEW.getCode()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "已发起申请，请耐心等待审核");
        }

        if (memCertification == null || memCertification.getCertstatus() == CertStatusEnum.NOPASS.getCode()) {
            // 加入数据
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
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单编号为空");
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
            // 检查用户是否 已经存在完成的充值订单
            // 这样来检查 该用户是否是新用户，新用户需要在订单详细页引导
            TraOrderinfom isnew = traOrderinfomMapperService.isNew(traOrderinfom.getAccno());
            // 是否弹出引导 0是 9否
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
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单编号为空");
        }
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom != null) {
            // 查看订单状态
            if (Constants.ORDER_ORD04.equals(traOrderinfom.getOrderstatus())) {
                traOrderinfom.setOrderstatus(Constants.ORDER_ORD09);
                traOrderinfom.setUpdateUser(loginUserAPP.getAccno());
                int i = traOrderinfomMapperService.updateStatus(traOrderinfom);
                if (i > 0) {
                    // 订单轨迹信息
                    TraOrdertracking traOrdertracking = new TraOrdertracking();
                    traOrdertracking.setOrderid(traOrderinfom.getOrderid());
                    traOrdertracking.setTrackdate(new Date());
                    traOrdertracking.setOrderstatus(Constants.ORDER_ORD09);
                    traOrdertracking.setOperuse(loginUserAPP.getAccno());
                    traOrdertracking.setTrackbody("用户[" + loginUserAPP.getNickname() + "]取消订单");
                    traOrdertrackingMapperService.insertSelective(traOrdertracking);

                    return Constants.SUCCESS_MSG;
                }

            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "订单状态为待支付才能取消");
            }
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "订单不存在");
        }
        return null;
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public String setPayPassword(LoginUser loginUserAPP, PayPasswordReq req) {
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "支付密码为空");
        }

        // 检查 密码 是否 过于简单
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
        }

        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (memLogin != null && Constants.ACCSTATUS_1.equals(memLogin.getAccstatus())) {
            if (StringUtils.isNotEmpty(memLogin.getPaypassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "用户已有支付密码");
            }

            MemLogin m = new MemLogin();
            m.setLoginid(memLogin.getLoginid());
            m.setPaypassword(req.getPaypassword());
            memLoginMapper.updateByPrimaryKeySelective(m);
            return Constants.SUCCESS_MSG;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "用户不存在/禁用");
        }
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public String updatePayPassword(LoginUser loginUserAPP, PayPasswordReq req) {
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "支付密码为空");
        }
        if (StringUtils.isEmpty(req.getOldpassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "旧支付密码为空");
        }

        // 检查 密码 是否 过于简单
        List<String> jPwd = CommonFunction.jiandanPwd();
        if (jPwd.contains(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10000.getCode(), "您设置的密码过于简单");
        }
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (memLogin != null && Constants.ACCSTATUS_1.equals(memLogin.getAccstatus())) {
            if (StringUtils.isEmpty(memLogin.getPaypassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "用户没有支付密码");
            }

            if (!req.getOldpassword().equals(memLogin.getPaypassword())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "旧支付密码不正确");
            }

            MemLogin m = new MemLogin();
            m.setLoginid(memLogin.getLoginid());
            m.setPaypassword(req.getPaypassword());
            memLoginMapper.updateByPrimaryKeySelective(m);
            return Constants.SUCCESS_MSG;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_1101.getCode(), "用户不存在/禁用");
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
            logger.error("刷新余额失败", loginUser.getAccno(), e);
            return dataMap;
        }
    }

    @Override
    public String getRecomcode(LoginUser loginUser) {
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUser.getAccno());
        return memBaseinfo.getRecomcode();
    }
}
