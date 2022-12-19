package com.likes.common.util.redis;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParamStatusEnum;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.AppinfoDO;
import com.likes.common.model.dto.InfSysnoticeDO;
import com.likes.common.model.dto.godplan.GodPlanDTO;
import com.likes.common.model.dto.lottery.LotteryInfo;
import com.likes.common.model.dto.order.OrderTotalDTO;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.model.response.EntryOrderResponse;
import com.likes.common.model.response.IncarnateOrderResponse;
import com.likes.common.model.vo.MeiqiaPrivateChatVo;
import com.likes.common.model.vo.ThirdChatGroupVo;
import com.likes.common.model.vo.circle.FansAndFocusNumberVO;
import com.likes.common.model.vo.lottery.OptionSelectVo;
import com.likes.common.mybatis.entity.AdBasic;
import com.likes.common.mybatis.entity.AdPhoto;
import com.likes.common.mybatis.entity.AdSite;
import com.likes.common.mybatis.entity.AeGame;
import com.likes.common.mybatis.entity.AeRoom;
import com.likes.common.mybatis.entity.AgGame;
import com.likes.common.mybatis.entity.AgPayType;
import com.likes.common.mybatis.entity.AgPlatform;
import com.likes.common.mybatis.entity.AgRound;
import com.likes.common.mybatis.entity.AmlhcLotterySg;
import com.likes.common.mybatis.entity.App;
import com.likes.common.mybatis.entity.AppVestBag;
import com.likes.common.mybatis.entity.AusactLotterySg;
import com.likes.common.mybatis.entity.AuspksLotterySg;
import com.likes.common.mybatis.entity.AussscLotterySg;
import com.likes.common.mybatis.entity.AzksLotterySg;
import com.likes.common.mybatis.entity.BasVideo;
import com.likes.common.mybatis.entity.BdUser;
import com.likes.common.mybatis.entity.BjpksLotterySg;
import com.likes.common.mybatis.entity.CqsscLotterySg;
import com.likes.common.mybatis.entity.DbGame;
import com.likes.common.mybatis.entity.DzksLotterySg;
import com.likes.common.mybatis.entity.DzpceggLotterySg;
import com.likes.common.mybatis.entity.DzxyftLotterySg;
import com.likes.common.mybatis.entity.Fc3dLotterySg;
import com.likes.common.mybatis.entity.Fc7lcLotterySg;
import com.likes.common.mybatis.entity.FcssqLotterySg;
import com.likes.common.mybatis.entity.FivebjpksLotterySg;
import com.likes.common.mybatis.entity.FivelhcLotterySg;
import com.likes.common.mybatis.entity.FivesscLotterySg;
import com.likes.common.mybatis.entity.GodPlan;
import com.likes.common.mybatis.entity.GodPlanIssue;
import com.likes.common.mybatis.entity.JsbjpksLotterySg;
import com.likes.common.mybatis.entity.JssscLotterySg;
import com.likes.common.mybatis.entity.KyKind;
import com.likes.common.mybatis.entity.KyServer;
import com.likes.common.mybatis.entity.LhcXsReferrer;
import com.likes.common.mybatis.entity.Lottery;
import com.likes.common.mybatis.entity.LotteryCategory;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemCertification;
import com.likes.common.mybatis.entity.MemFollow;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.entity.MemRelationship;
import com.likes.common.mybatis.entity.MemberDeviceCalc;
import com.likes.common.mybatis.entity.MemberOnlineCalc;
import com.likes.common.mybatis.entity.MemberYoukeCalc;
import com.likes.common.mybatis.entity.MgGame;
import com.likes.common.mybatis.entity.OnelhcLotterySg;
import com.likes.common.mybatis.entity.PceggLotterySg;
import com.likes.common.mybatis.entity.ReturnLotterySet;
import com.likes.common.mybatis.entity.ReturnThirdSet;
import com.likes.common.mybatis.entity.ReturnWaterSet;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.entity.SysFunctionorg;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.entity.SysPayset;
import com.likes.common.mybatis.entity.SysSensitiveWord;
import com.likes.common.mybatis.entity.Tc7xcLotterySg;
import com.likes.common.mybatis.entity.TcdltLotterySg;
import com.likes.common.mybatis.entity.TcplwLotterySg;
import com.likes.common.mybatis.entity.TenbjpksLotterySg;
import com.likes.common.mybatis.entity.TensscLotterySg;
import com.likes.common.mybatis.entity.ThirdChatGroup;
import com.likes.common.mybatis.entity.ThirdPrivateChatGroup;
import com.likes.common.mybatis.entity.TjsscLotterySg;
import com.likes.common.mybatis.entity.TxffcLotterySg;
import com.likes.common.mybatis.entity.VipGrade;
import com.likes.common.mybatis.entity.XjplhcLotterySg;
import com.likes.common.mybatis.entity.XjsscLotterySg;
import com.likes.common.mybatis.entity.XyftLotterySg;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.RandomUtil;
import com.likes.common.util.StringUtils;
import com.likes.common.util.TimeHelper;
import com.likes.common.util.encrypt.MD5;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 纯 redis 业务操作工具类，不包含 DB 访问，主要场景是在controller里直接用，不用远程调用进入server等应用
 */
public class RedisBusinessUtil extends RedisBaseUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisBusinessUtil.class);

    /** 统计用户离线时间 */
    private static RedisBigMap userOfflineRedisBigMap;

    /////////////////////////////////////////业务方法/////////////////////////////////////////

    /**
     * 删除彩种类缓存
     */
    public static void deleteLotteryCaches() {

        logger.info("entry deleteLotteryCache...");
        List<String> keys = new ArrayList<>();
        keys.add(RedisKeys.LOTTERY_CATEGORY_LIST_KEY);
        keys.add(RedisKeys.LOTTERY_CATEGORY_MAP_KEY);
        keys.add(RedisKeys.LOTTERY_LIST_KEY);
        keys.add(RedisKeys.LOTTERY_MAP_KEY);
        keys.add(RedisKeys.LOTTERY_ALL_LIST_KEY);
        keys.add(RedisKeys.LOTTERY_ALL_MAP_KEY);
        keys.add(RedisKeys.LOTTERY_PLAY_LIST_KEY);
        keys.add(RedisKeys.LOTTERY_PLAY_MAP_KEY);
        keys.add(RedisKeys.LOTTERY_PLAY_SETTING_ALL_DATA);
        keys.add(RedisKeys.LOTTERY_PLAY_ODDS_ALL_DATA);
        keys.add(RedisKeys.LOTTERY_FAVORITE_USER_DATA_DEFAULT);
        keys.add(RedisKeys.LOTTERY_FAVORITE_DEFAULT);
        keys.add(RedisKeys.LOTTERY_ALL_INNER_LIST);

        //删除彩种压缩包缓存
        keys.add(SysParameterEnum.LOTTERY_VERSION_ZIP_URL.getCode());
        keys.add(SysParameterEnum.LOTTERY_VERSION_ZIP_URL.getCode() + Constants.SYSTEM_INFO_VALUE_SUFFIX);

        redisTemplate.delete(keys);
        logger.info("deleteLotteryCache success.");

        String favoriteUser = RedisKeys.LOTTERY_FAVORITE_USER_PREFIX + "*";
        String favoriteUserData = RedisKeys.LOTTERY_FAVORITE_USER_DATA_PREFIX + "*";
        String lotteryAllInfo = RedisKeys.LOTTERY_ALL_INFO + "*";
        String lotteryInfo = RedisKeys.LOTTERY_KEY + "*";

        Set<String> favoriteUserSet = redisTemplate.keys(favoriteUser);
        Set<String> favoriteUserDataSet = redisTemplate.keys(favoriteUserData);
        Set<String> lotteryAllInfoSet = redisTemplate.keys(lotteryAllInfo);
        Set<String> lotteryInfoSet = redisTemplate.keys(lotteryInfo);

        Set<String> keySet = new HashSet<>();
        keySet.addAll(favoriteUserSet);
        keySet.addAll(favoriteUserDataSet);
        keySet.addAll(lotteryAllInfoSet);
        keySet.addAll(lotteryInfoSet);
        if (keySet.size() > 0) {
            redisTemplate.delete(keySet);
            logger.info("deleteLotteryCache for keySet success.");
        }
    }

    public static void updateCacheForValueByKey(String key, Object value, String type) {
        if (null == value || "delete".equals(type)) {
            redisTemplate.delete(key);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 获取用户缓存
     *
     * @param id
     */
    public static MemBaseinfo getAppMember(Long id) {
        if (null == id || id <= 0) {
            return null;
        }
        return (MemBaseinfo) redisTemplate.opsForValue().get(RedisKeys.APP_MEMBER + id);
    }

    /**
     * 添加用户缓存
     *
     * @param appMember
     */
    public static void addAppMember(MemBaseinfo appMember) {
        if (null == appMember || null == appMember.getMemid()) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.APP_MEMBER + appMember.getMemid(), appMember);
        //添加ACCNO 与 MEMID的关系映射
        redisTemplate.opsForValue().set(RedisKeys.ACCNO_MAP_MEMID + appMember.getAccno(), appMember.getMemid());
        //建立用户 uniqueId 和 memid 的关系
        redisTemplate.opsForValue().set(RedisKeys.UNIQUE_ID_MAP_MEMID + appMember.getUniqueId(), appMember.getMemid(), 7, TimeUnit.DAYS);
    }

    /**
     * 添加ACCNO 与 MEMID的关系映射
     *
     * @param appMember
     *//*
    public static void addAccnoMapMemid(MemBaseinfo appMember) {
        if (null == appMember || null == appMember.getMemid()) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.ACCNO_MAP_MEMID + appMember.getAccno(), appMember.getMemid());
    }*/

    /**
     * 查询ACCNO 与 MEMID的关系映射
     *
     * @param accno
     */
    public static Long getAccnoMapMemidByAccno(String accno) {
        if (StringUtils.isBlank(accno)) {
            return null;
        }
        Object o = redisTemplate.opsForValue().get(RedisKeys.ACCNO_MAP_MEMID + accno);
        return null == o ? null : ((Integer) o).longValue();
    }

    /**
     * 删除用户缓存
     *
     * @param id
     */
    public static void deleteAppMember(Long id) {
        if (null != id && id > 0) {
            redisTemplate.delete(RedisKeys.APP_MEMBER + id);
        }
    }

    /**
     * 批量删除用户缓存
     *
     * @param members
     */
    public static void deleteAppMembers(List<MemBaseinfo> members) {
        if (null == members || members.size() == 0) {
            return;
        }
        deleteAppMemberByIdList(members.stream().map(item -> item.getMemid().intValue()).collect(Collectors.toList()));
    }

    /**
     * 根据id list 批量删除用户缓存
     *
     * @param idList
     */
    public static void deleteAppMemberByIdList(List<Integer> idList) {
        if (null == idList || idList.size() == 0) {
            return;
        }
        List<String> keys = new ArrayList<>(idList.size());
        for (Integer id : idList) {
            keys.add(RedisKeys.APP_MEMBER + id);
        }
        redisTemplate.delete(keys);
        logger.info("deleteAppMemberByIdList success.");
    }

    /**
     * 缓存根据acclogin获取登录用户表信息
     *
     * @param acclogin
     * @return
     */
    public static MemLogin getMemLoginAcclogin(String acclogin) {
        if (StringUtils.isBlank(acclogin)) {
            return null;
        }
        return (MemLogin) redisTemplate.opsForValue().get(RedisKeys.MEM_LOGIN_ACCLOGIN + acclogin);
    }

    /**
     * 根据acclogin删除用户登录表缓存
     *
     * @param acclogin
     */
    public static void deleteMemLoginAcclogin(String acclogin) {
        if (StringUtils.isNotEmpty(acclogin)) {
            redisTemplate.delete(RedisKeys.MEM_LOGIN_ACCLOGIN + acclogin);
        }
    }


    /**
     * 缓存根据获sfunid取权限功能表信息
     *
     * @param sfunid
     * @return
     */
    public static SysFunctionorg getSysFunctionorgSfunid(Long sfunid) {
        if (null == sfunid || sfunid == 0) {
            return null;
        }
        return (SysFunctionorg) redisTemplate.opsForValue().get(RedisKeys.SYSTEM_SYSFUNCTIONORG_SFUNID + sfunid);
    }

    /**
     * 根据loginid删除限功能表缓存
     *
     * @param sfunid
     */
    public static void deleteSysFunctionorgSfunid(Long sfunid) {
        if (null != sfunid && sfunid > 0) {
            redisTemplate.delete(RedisKeys.SYSTEM_SYSFUNCTIONORG_SFUNID + sfunid);
        }
    }


    /**
     * 缓存根据获parsfunid取权限功能列表信息
     *
     * @param parsfunid
     * @return
     */
    public static List<SysFunctionorg> getSysFunctionorgParsfunid(Long parsfunid) {
        if (null == parsfunid || parsfunid == 0) {
            return null;
        }
        return (List<SysFunctionorg>) redisTemplate.opsForValue().get(RedisKeys.SYSTEM_SYSFUNCTIONORG_PARSFUNID + parsfunid);
    }

    /**
     * 根据parsfunid删除限功能列表缓存
     *
     * @param parsfunid
     */
    public static void deleteSysFunctionorgParsfunid(Long parsfunid) {
        if (null != parsfunid && parsfunid > 0) {
            redisTemplate.delete(RedisKeys.SYSTEM_SYSFUNCTIONORG_PARSFUNID + parsfunid);
        }
    }


    public static BdUser getBdUserByAccno(String accno) {
        if (StringUtils.isBlank(accno)) {
            return null;
        }
        return (BdUser) redisTemplate.opsForValue().get(RedisKeys.BD_USER_INFO + accno);
    }

    public static void setBdUserByAccn(BdUser bdUser) {
        if (bdUser != null) {
            redisTemplate.opsForValue().set(RedisKeys.BD_USER_INFO + bdUser.getAccno(), bdUser);
        }

    }


    /**
     * 缓存用户是否大小写敏感标识
     *
     * @param value
     */
    public static void addUserAccountSensitiveCase(String value) {
        redisTemplate.opsForValue().set(RedisKeys.USER_ACCOUNT_SENSITIVE_CASE, value);
    }

    /**
     * 缓存注册型用户能否评论标识
     */
    public static void addRegisterAccountCanRecommend(SysParameter sysParameter) {
        redisTemplate.opsForValue().set(RedisKeys.REGISTER_ACCOUNT_CAN_RECOMMEND, sysParameter);
    }

    /**
     * 用户名大小写是否敏感
     *
     * @return
     */
    public static boolean userAccountSensitiveCase() {
        String userAccountSensitiveCase = (String) redisTemplate.opsForValue().get(RedisKeys.USER_ACCOUNT_SENSITIVE_CASE);
        return Boolean.TRUE.toString().equals(userAccountSensitiveCase);
    }

    /**
     * 注册型用户是否能评论
     *
     * @return
     */
    public static boolean registerAccountCanRecommend() {
        String registerAccountCanRecommend = (String) redisTemplate.opsForValue().get(RedisKeys.REGISTER_ACCOUNT_CAN_RECOMMEND + Constants.SYSTEM_INFO_VALUE_SUFFIX);
        return Constants.FUNCTION_ENABLE_FLAG.equals(registerAccountCanRecommend);
    }

    /**
     * 获取内部彩种（非第三方）列表信息
     *
     * @return
     */
    public static List<Map<String, Object>> getLotteryAllInnerList() {
        return (List<Map<String, Object>>) redisTemplate.opsForValue().get(RedisKeys.LOTTERY_ALL_INNER_LIST);
    }

    /**
     * 缓存内部彩种（非第三方）列表信息
     *
     * @param list
     */
    public static void addLotteryAllInfo(List list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LOTTERY_ALL_INNER_LIST, list);
    }

    /**
     * 获取彩种所有信息
     *
     * @param type
     * @return
     */
    public static List<LotteryInfo> getLotteryAllInfo(String type) {
        String key = RedisKeys.LOTTERY_ALL_INFO;
        if (StringUtils.isNotEmpty(type)) {
            key = key + "_" + type;
        }
        return (List<LotteryInfo>) redisTemplate.opsForValue().get(key);
    }

    /**
     * 缓存彩种及赔率所有信息
     *
     * @param type
     * @param list
     */
    public static void addLotteryAllInfo(String type, List<LotteryInfo> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        String key = RedisKeys.LOTTERY_ALL_INFO;
        if (StringUtils.isNotEmpty(type)) {
            key = key + "_" + type;
        }
        redisTemplate.opsForValue().set(key, list);
    }

    /**
     * 更新 uri 白名单
     *
     * @param key
     * @param values
     * @param type   操作类型(add:添加; delete:删除)
     */
    public static void updateWhiteUri(String key, String values, String type) {
        Set<String> uris = (Set<String>) redisTemplate.opsForValue().get(key);
        if (null == uris) {
            uris = new HashSet<>();
        }
        if ("delete".equals(type)) {
            uris.remove(StringUtils.splitStringList(values));
        } else {
            uris.addAll(StringUtils.splitStringList(values));
        }
        redisTemplate.opsForValue().set(key, uris);
        logger.info("updateWhiteUri key:{}; values:{}; type:{} success.", key, values, type);
    }

    /**
     * 是否在 uri 白名单
     *
     * @param uri
     * @return
     */
    public static boolean isWhiteUri(String uri) {
        List<String> uris = null;
        if (redisTemplate.hasKey(RedisKeys.WHITE_URIS)) {
            uris = (List<String>) redisTemplate.opsForValue().get(RedisKeys.WHITE_URIS);
        }
        if (null == uris || uris.size() == 0) {
            uris = new ArrayList<>();
        }
        uris.add("login");//登录
        //活动相关
        uris.add("activity/list.json");
        uris.add("activity/getDetail.json");
        uris.add("activity/getPopupActivity.json");
        uris.add("lottery/all/list");//首页彩票列表
        uris.add("queryLotteryVersionZIP");//前端获取彩种赔率等信息
        uris.add("editionUpdate");//版本更新
        uris.add("getNewestSgInfobyids");//赛过
        uris.add("sendDeviceIdIp");//IP统计
        uris.add("toLogin.json");
        return contains(uri, uris);
    }

    /**
     * 判断是否需要绑定手机号
     *
     * @param uri
     * @return
     */
    public static boolean needToBindingMobile(String uri) {
        List<String> uris = (List<String>) redisTemplate.opsForValue().get(RedisKeys.MUST_MOBILE_BINDING_URIS);
        if (null == uris || uris.size() == 0) {
            uris = new ArrayList<>();
        }
        uris.addAll(getBindingMobileUriList());
        uris.add(0, "userWithdraw");
        uris.add(0, "adduserBankcard");
        uris.add(0, "updatePayPwd");
        return contains(uri, uris);
    }

    /**
     * 注册型用户是否有执行权限
     *
     * @param uri
     * @return
     */
    public static boolean canExecuteByRegisterUser(String uri) {
        List<String> uris = (List<String>) redisTemplate.opsForValue().get(RedisKeys.HAS_AUTH_BY_REGISTER_USER_URIS);
        if (null == uris || uris.size() == 0) {
            uris = new ArrayList<>();
        }
        uris.addAll(getBindingMobileUriList());
        return contains(uri, uris);
    }

    /**
     * 需要绑定手机的uri
     *
     * @return
     */
    private static List<String> getBindingMobileUriList() {
        List<String> uris = new ArrayList<>();
        uris.add("circle/createPost");
        uris.add("addRecommendByAPP");
        uris.add("addRecommendCommend");
        uris.add("getSelfHistoryLhcXsPost");
        uris.add("updateRecommendByAPP");
        uris.add("addRecommendFollow");
        uris.add("deleteRecommendFollow");
        uris.add("pageLhcXsRecommendFollow");
        uris.add("addphptoCommentsByAPP");
        uris.add("circle/god/applyGod");
        uris.add("lotteryDraw");
        uris.add("redPackDraw");
        uris.add("wechat/sendOut");
        uris.add("wechat/addFollow");
        return uris;
    }

    private static boolean contains(String uri, List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (uri.contains(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加第三方聊天群缓存
     *
     * @param thirdChatGroups
     */
    public static void addThirdChatGroups(List<ThirdChatGroup> thirdChatGroups) {
        RedisBusinessUtil.addAllThirdChatGroups(thirdChatGroups);
    }

    /**
     * 获取第三方聊天群组新写
     *
     * @return
     */
    public static List<ThirdChatGroup> getAllThirdChatGroups() {
        return (List<ThirdChatGroup>) redisTemplate.opsForValue().get(RedisKeys.THIRD_ALL_CHAT_GROUP_LIST);
    }

    /**
     * 获取第三方聊天群vos
     *
     * @return
     */
    public static List<ThirdChatGroupVo> getThirdChatGroupVos() {
        return (List<ThirdChatGroupVo>) redisTemplate.opsForValue().get(RedisKeys.THIRD_ALL_CHAT_GROUP_VO_LIST);
    }

    /**
     * 美恰聊天室返回列表
     *
     * @return
     */
    public static List<MeiqiaPrivateChatVo> getMeiqiaChatVos() {
        return (List<MeiqiaPrivateChatVo>) redisTemplate.opsForValue().get(RedisKeys.MEIQIA_ALL_CHAT_VO_LIST);
    }

    /**
     * 美恰聊天室返回列表
     *
     * @return
     */
    public static void addMeiqiaChatVos(List<MeiqiaPrivateChatVo> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.MEIQIA_ALL_CHAT_VO_LIST, list);
    }

    /**
     * 天龙第三方聊天群list
     *
     * @param list
     */
    public static void addAllThirdChatGroups(List<ThirdChatGroup> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.THIRD_ALL_CHAT_GROUP_LIST, list);
    }


    public static void addAllPrivateThirdChatGroups(List<ThirdPrivateChatGroup> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.THIRD_CHAT_PRIVATE_GROUP_LIST, list);
    }


    /**
     * 私聊聊天室VO
     *
     * @param list
     */
    public static void addThirdPrivateChatGroups(List<ThirdChatGroupVo> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.THIRD_PRIVATE_CHAT_GROUP_VO_LIST, list);
    }


    /**
     * 私聊聊天室列表
     *
     * @return
     */
    public static List<ThirdPrivateChatGroup> getThirdPrivateChatGroups() {
        return (List<ThirdPrivateChatGroup>) redisTemplate.opsForValue().get(RedisKeys.THIRD_CHAT_PRIVATE_GROUP_LIST);
    }


    /**
     * 获取私聊聊天群vos
     *
     * @return
     */
    public static List<ThirdChatGroupVo> getThirdPrivateChatGroupVos() {
        return (List<ThirdChatGroupVo>) redisTemplate.opsForValue().get(RedisKeys.THIRD_PRIVATE_CHAT_GROUP_VO_LIST);
    }


    /**
     * 添加第三方聊天vo缓存
     *
     * @param list
     */
    public static void addThirdChatGroupVos(List<ThirdChatGroupVo> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.THIRD_ALL_CHAT_GROUP_VO_LIST, list);
    }

    /**
     * 删除第三天聊天缓存
     */
    public static void deleteThirdChatGroups() {
        List<String> list = new ArrayList<>();
        list.add(RedisKeys.THIRD_ALL_CHAT_GROUP_LIST);
        list.add(RedisKeys.THIRD_ALL_CHAT_GROUP_VO_LIST);
        redisTemplate.delete(list);
    }


    /**
     * 删除第三方私聊聊天缓存
     */
    public static void deletePrivateThirdChatGroups() {
        List<String> list = new ArrayList<>();
        list.add(RedisKeys.THIRD_CHAT_PRIVATE_GROUP_LIST);
        list.add(RedisKeys.THIRD_PRIVATE_CHAT_GROUP_VO_LIST);
        redisTemplate.delete(list);
    }

    public static App getAppCache(Integer id) {
        id = null == id || id <= 0 ? 1 : id;
        App app = (App) redisTemplate.opsForValue().get(RedisKeys.APP_KEY + id);
        return app;
    }

    public static void addAppCache(App app) {
        if (ObjectUtils.isEmpty(app)) {
            return;
        }
        Integer id = app.getId();
        id = null == id || id <= 0 ? 1 : id;
        redisTemplate.opsForValue().set(RedisKeys.APP_KEY + id, app);
    }

    public static void deleteAppCache(Integer id) {
        id = null == id || id <= 0 ? 1 : id;
        redisTemplate.delete(RedisKeys.APP_KEY + id);
    }

    public static boolean hasRandomDataInRedis(String type, int size) {
        return redisTemplate.opsForList().size(RedisKeys.SYSTEM_RANDOM_CODE_CACHES_PREFIX + type) > size;
    }

    public static String popRandomCode(String type) {
        return (String) redisTemplate.opsForList().leftPop(RedisKeys.SYSTEM_RANDOM_CODE_CACHES_PREFIX + type);
    }

    public static void pushAllRandomCode(String type, List<String> codes) {
        redisTemplate.opsForList().rightPushAll(RedisKeys.SYSTEM_RANDOM_CODE_CACHES_PREFIX + type, codes);
    }

    public static List<VipGrade> getVipGradeList() {
        return (List<VipGrade>) redisTemplate.opsForValue().get(RedisKeys.VIP_GRADE_LIST_KEY);
    }

    public static void addVipGradeList(List<VipGrade> vipGrades) {
        if (CollectionUtil.isEmpty(vipGrades)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.VIP_GRADE_LIST_KEY, vipGrades);
    }

    public static void deleteVipGrade() {
        redisTemplate.delete(RedisKeys.VIP_GRADE_LIST_KEY);
    }

    public static void addCacheForValueAndMinutes(Object key, Object value, Long time, TimeUnit minutes) {
        if (null == key || StringUtils.isEmpty(key.toString())) {
            return;
        }
        redisTemplate.opsForValue().set(key, value, time, minutes);
    }

    public static void deleteCacheForValueList(List<String> keyList) {
        if (CollectionUtil.isEmpty(keyList)) {
            return;
        }
        redisTemplate.delete(keyList);
    }


    public static List<LhcXsReferrer> getReferrerAll() {
        return (List<LhcXsReferrer>) redisTemplate.opsForValue().get(RedisKeys.REFERRE_MEMBER_ALL);
    }

    public static List<AdSite> getAdSiteCacheList() {
        return (List<AdSite>) redisTemplate.opsForValue().get(RedisKeys.AD_SITE_CACHE_LIST);
    }

    public static void addAdSiteCacheList(List<AdSite> list) {
        redisTemplate.opsForValue().set(RedisKeys.AD_SITE_CACHE_LIST, list);
    }

    public static Map<String, List<AdSite>> getAdSiteCacheCategory() {
        return (Map<String, List<AdSite>>) redisTemplate.opsForValue().get(RedisKeys.AD_SITE_CACHE_CATEGORY);
    }

    public static void addAdSiteCacheCategory(Map<String, List<AdSite>> map) {
        redisTemplate.opsForValue().set(RedisKeys.AD_SITE_CACHE_CATEGORY, map);
    }

    public static List<AdBasic> getAdBasicCacheList() {
        return (List<AdBasic>) redisTemplate.opsForValue().get(RedisKeys.AD_BASIC_CACHE_LIST);
    }

    public static void addAdBasicCache(List<AdBasic> list) {
        redisTemplate.opsForValue().set(RedisKeys.AD_BASIC_CACHE_LIST, list);
        for (AdBasic adBasic : list) {
            redisTemplate.opsForValue().set(RedisKeys.AD_BASIC_CACHE_ITEM + adBasic.getId(), adBasic);
        }
    }

    public static void deleteAdBasicCache() {
        Set<String> keys = redisTemplate.keys(RedisKeys.AD_BASIC_CACHE_ITEM + "*");
        if (CollectionUtil.isEmpty(keys)) {
            keys = new HashSet<>();
        }
        keys.add(RedisKeys.AD_BASIC_CACHE_LIST);
        redisTemplate.delete(keys);
    }

    public static List<AdPhoto> getAdPhotoCacheList() {
        return (List<AdPhoto>) redisTemplate.opsForValue().get(RedisKeys.AD_PHOTO_CACHE_LIST);
    }

    public static void addAdPhotoCache(List<AdPhoto> list) {
        redisTemplate.opsForValue().set(RedisKeys.AD_PHOTO_CACHE_LIST, list);
        for (AdPhoto adPhoto : list) {
            redisTemplate.opsForValue().set(RedisKeys.AD_PHOTO_CACHE_ITEM + adPhoto.getId(), adPhoto);
        }
    }

    public static void deleteAdPhotoCache() {
        Set<String> keys = redisTemplate.keys(RedisKeys.AD_PHOTO_CACHE_ITEM + "*");
        if (CollectionUtil.isEmpty(keys)) {
            keys = new HashSet<>();
        }
        keys.add(RedisKeys.AD_PHOTO_CACHE_LIST);
        redisTemplate.delete(keys);
    }

    public static AppVestBag getAppVestBagCache(String code) {
        AppVestBag appVestBag = (AppVestBag) redisTemplate.opsForValue().get(RedisKeys.APP_VEST_BAG + code);
        return appVestBag;

    }

    public static MemberDeviceCalc getMemberDeviceCalc() {
        return (MemberDeviceCalc) redisTemplate.opsForValue().get(RedisKeys.MEMBER_DEVICE_CALC);
    }

    public static void addMemberDeviceCalc(MemberDeviceCalc memberDeviceCalc) {
        if (null == memberDeviceCalc) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.MEMBER_DEVICE_CALC, memberDeviceCalc);
    }

    public static MemberYoukeCalc getMemberYoukeCalc() {
        return (MemberYoukeCalc) redisTemplate.opsForValue().get(RedisKeys.MEMBER_YOUKE_CALC);
    }

    public static void addMemberYoukeCalc(MemberYoukeCalc memberYoukeCalc) {
        redisTemplate.opsForValue().set(RedisKeys.MEMBER_YOUKE_CALC, memberYoukeCalc);
    }

    public static MemberOnlineCalc getMemberOnlineCalc() {
        return (MemberOnlineCalc) redisTemplate.opsForValue().get(RedisKeys.MEMBER_ONLINE_CALC);
    }

    public static void addMemberOnlineCalc(MemberOnlineCalc memberOnlineCalc) {
        redisTemplate.opsForValue().set(RedisKeys.MEMBER_ONLINE_CALC, memberOnlineCalc);
    }

    public static void deletePayForList() {
        redisTemplate.delete(RedisKeys.PAY_FOR_LIST);
    }

    public static void addSysParameter(SysParameter info) {
        if (null == info || StringUtils.isEmpty(info.getSysparamcode())) {
            return;
        }
        redisTemplate.opsForValue().set(info.getSysparamcode().toUpperCase(), info);
        redisTemplate.opsForValue().set(info.getSysparamcode().toUpperCase() + Constants.SYSTEM_INFO_VALUE_SUFFIX, info.getSysparamvalue());
    }

    public static void addSysParameterList(List<SysParameter> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        redisTemplate.opsForList().rightPushAll(RedisKeys.SYSTEM_INFO_LIST, list);
    }

    public static List<SysParameter> getSysParameterList() {
        return redisTemplate.opsForList().range(RedisKeys.SYSTEM_INFO_LIST, 0, -1);
    }

    public static SysParameter getSysParameter(SysParameterEnum sysParameterEnum) {
        if (null == sysParameterEnum) {
            return null;
        }
        return (SysParameter) redisTemplate.opsForValue().get(sysParameterEnum.getCode());
    }

    public static SysParameter getSysParameter(String key) {
        if (null == key) {
            return null;
        }
        return (SysParameter) redisTemplate.opsForValue().get(key);
    }

    public static String getSysParameterValue(SysParameterEnum sysParameterEnum) {
        if (null == sysParameterEnum) {
            return null;
        }
        return (String) redisTemplate.opsForValue().get(sysParameterEnum.getCode() + Constants.SYSTEM_INFO_VALUE_SUFFIX);
    }

    public static void setSystemInfoValue(SysParameter info) {
        if (null == info) {
            return;
        }
        redisTemplate.opsForValue().set(info.getSysparamcode() + Constants.SYSTEM_INFO_VALUE_SUFFIX, info.getSysparamvalue());
    }

    /**
     * 删除系统配置
     *
     * @param code
     */
    public static void deleteSysParameter(String code) {
        if (null == code || "".equals(code.trim())) {
            return;
        }
        code = code.trim().toUpperCase();
        List<String> keys = Arrays.asList(code, code + Constants.SYSTEM_INFO_VALUE_SUFFIX);
        redisTemplate.delete(keys);
    }

    public static void deleteSystemInfoCaches(List<String> keys) {
        if (CollectionUtil.isEmpty(keys)) {
            return;
        }
        List<String> infoKeys = new ArrayList<>();
        for (String key : keys) {
            infoKeys.add(key + Constants.SYSTEM_INFO_VALUE_SUFFIX);
        }
        keys.addAll(infoKeys);
        keys.add(RedisKeys.SYSTEM_INFO_LIST);
        redisTemplate.delete(keys);
    }

    public static void deleteSystemInfoCaches(Map<String, String> map) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : entries) {
            keys.add(entry.getKey());
            keys.add(entry.getKey() + Constants.SYSTEM_INFO_VALUE_SUFFIX);
        }
        keys.add(RedisKeys.SYSTEM_INFO_LIST);
        redisTemplate.delete(keys);
    }

    public static List<GodPlanIssue> getGodPlanIssue(Integer godId) {
        if (null == godId || godId <= 0) {
            return null;
        }
        return (List<GodPlanIssue>) redisTemplate.opsForValue().get(RedisKeys.GOD_PLAN_LOTTERY_ISSUE_GODID_KEY + godId);
    }

    public static void addGodPlanIssue(Integer godId, List<GodPlanIssue> godPlanIssueList) {
        if (CollectionUtil.isEmpty(godPlanIssueList)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.GOD_PLAN_LOTTERY_ISSUE_GODID_KEY + godId, godPlanIssueList);
    }

    public static List<GodPlanDTO> getGodPlanDTO(Integer lotteryId) {
        if (null == lotteryId || lotteryId <= 0) {
            return null;
        }
        return (List<GodPlanDTO>) redisTemplate.opsForValue().get(RedisKeys.GOD_PLAN_DTO_LOTTERY + lotteryId);
    }

    public static void addGodPlanDTO(Integer lotteryId, List<GodPlanDTO> godPlanDTOList) {
        if (CollectionUtil.isEmpty(godPlanDTOList)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.GOD_PLAN_DTO_LOTTERY + lotteryId, godPlanDTOList);
    }

    /**
     * 删除前端大神和大神计划的缓存
     *
     * @param lotteryId
     */
    public static void deleteGodPlanDTOCaches(Integer lotteryId) {
        /* 做一次清空大神和大神计划两个缓存的操作 */
        List<GodPlanDTO> godPlanDTOList = getGodPlanDTO(lotteryId);
        if (!CollectionUtils.isEmpty(godPlanDTOList)) {
            RedisBusinessUtil.deleteGodPlanLotteryIssueCaches(lotteryId, godPlanDTOList);
        }
    }

    public static void deleteGodPlanLotteryIssueCaches(int lotteryId, List<GodPlanDTO> godPlanDTOList) {
        if (CollectionUtils.isEmpty(godPlanDTOList)) {
            return;
        }
        List<String> keys = new ArrayList<>();
        for (GodPlanDTO godPlanDTO : godPlanDTOList) {
            keys.add(RedisKeys.GOD_PLAN_LOTTERY_ISSUE_GODID_KEY + godPlanDTO.getGodId());
        }
        keys.add(RedisKeys.GOD_PLAN_DTO_LOTTERY + lotteryId);
        redisTemplate.delete(keys);
    }

    /**
     * 更新前端大神和大神计划缓存并更新后台大神缓存
     *
     * @param godPlan
     */
    public static void updateGodPlanDTOCache(GodPlan godPlan) {
        deleteGodPlanDTOCaches(godPlan.getLotteryId());
    }

    public static LoginUser getAppLoginUser(String acctoken, SysParamService sysParamService, HttpServletRequest request) {
        LoginUser user = null;
        Object jsonstr = redisTemplate.opsForValue().get(acctoken);
        if (jsonstr != null) {
            Long sessiontime = Constants.TOKEN_EXPIRES;
            SysParameter sp = sysParamService.getByCode(SysParameterEnum.SESSION_TIME.name());
            if (sp != null) {
                sessiontime = Long.parseLong(sp.getSysparamvalue()) * 60;
            }
            user = JSONObject.parseObject((String) jsonstr, LoginUser.class);
            if (user != null && (LoginUserTypeEnum.ORDINARY.getCode().equals(user.getLogintype()) || LoginUserTypeEnum.ANCHOR.getCode().equals(user.getLogintype()) || LoginUserTypeEnum.AGENT.getCode().equals(user.getLogintype()))) {
                set(acctoken, jsonstr, sessiontime);
                set(user.getAccno(), acctoken, sessiontime);
                request.getSession().setAttribute(Constants.APP_LOGIN_INFO, user);
            }
        }
        return user;
    }

    public static void refreshUser(LoginUser loginUserAPP, SysParamService sysParamService) {
        // 刷新用户对象
        SysParameter bs = sysParamService.getByCode(SysParameterEnum.SESSION_TIME.name());
        if (bs == null || org.apache.commons.lang.StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(session_time)异常");
        }
        Long userSessionIdOutTime = (Long.parseLong(bs.getSysparamvalue())) * 60;
        String secret = JSON.toJSONString(loginUserAPP);
        set(loginUserAPP.getAcctoken(), secret, userSessionIdOutTime);
    }

    /**
     * 刷新主播暱称
     */
    public static void refreshAnchorUser(LoginUser loginUserAPP, Long userSessionIdOutTime) {
        String secret = JSON.toJSONString(loginUserAPP);
        set(loginUserAPP.getAcctoken(), secret, userSessionIdOutTime);
    }

    //--------------------------LIVE-MANAGE--------------------------------------------
    public static String createSessionID(String userSessionKey, LoginUser loginUserAPP, SysParamService sysParamService) {
        String sessionid = null;
        if (null == loginUserAPP) {
            return null;
        }

        String seckey = userSessionKey + RandomUtil.uuid();
        sessionid = MD5.md5(seckey, "UTF-8");
        loginUserAPP.setAcctoken(sessionid);
        // 生成了新的 sessionid ，这是要使 以前旧的sessionid 失效
        // 获取手机号 对应的 sessionid
		/*String sessionid_old = String.valueOf(this.get(loginUserAPP.getAccno()));
		if (StringUtils.isNotEmpty(sessionid_old))
			this.remove(sessionid_old, loginUserAPP.getAccno());*/
        // 重新设置sessionid 相关值
        SysParameter bs = sysParamService.getByCode(SysParameterEnum.SESSION_TIME.name());
        if (bs == null || org.apache.commons.lang.StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(session_time)异常");
        }

        Long userSessionIdOutTime = (Long.parseLong(bs.getSysparamvalue())) * 60;
        // 通过sessionid 的 获取 缓存对象
        String secret = JSON.toJSONString(loginUserAPP);
        set("Login_info:"+loginUserAPP.getAccno(), sessionid, userSessionIdOutTime);
        set("Login_info:"+sessionid, secret, userSessionIdOutTime);

        // 预留 若有 业务 需要 ，根据实际情况开
        // 通过mobileno / accno 获取 缓存对象
        // this.set(Constants.S_SESSIONID_TEL_USER + loginUserAPP.getMobileno(),
        // JSON.toJSONString(loginUserAPP), userSessionIdOutTime);
        // this.set(Constants.S_SESSIONID_ACCNO_USER + loginUserAPP.getAccno(),
        // JSON.toJSONString(loginUserAPP), userSessionIdOutTime);
        // 根据accno / mobileno 获取 sessionId
        // this.set(Constants.S_TEL_SESSIONID + loginUserAPP.getMobileno(), sessionid,
        // userSessionIdOutTime);
        // this.set(Constants.S_TEL_SESSIONID + loginUserAPP.getAccno(), sessionid,
        // userSessionIdOutTime);

        return sessionid;
    }

    public static String createAgentSessionID(String userSessionKey, LoginUser loginUserAPP, SysParamService sysParamService) {
        String sessionid = null;
        if (null == loginUserAPP) {
            return null;
        }
        String seckey = userSessionKey + RandomUtil.uuid();
        sessionid = MD5.md5(seckey, "UTF-8");
        loginUserAPP.setAcctoken(sessionid);
        String secret = JSON.toJSONString(loginUserAPP);
        set(loginUserAPP.getAccno(), sessionid, 30*60L);
        set(sessionid, secret, 30*60L);
        return sessionid;
    }

    public static String createAppManageSessionID(String userSessionKey, LoginUser loginUser, SysParamService sysParamService) {
        String sessionid = null;
        if (loginUser == null) {
            return null;
        }
        String seckey = userSessionKey + RandomUtil.uuid();
        sessionid = MD5.md5(seckey, "UTF-8");
        loginUser.setAcctoken(sessionid);
        // 重新设置sessionid 相关值
        SysParameter bs = sysParamService.getByCode(SysParameterEnum.SESSION_TIME_BACK.name());
        if (bs == null || org.apache.commons.lang.StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(session_time_back)异常");
        }
        Long userSessionIdOutTime = (Long.parseLong(bs.getSysparamvalue())) * 60;
        // 通过sessionid 的 获取 缓存对象
        set(loginUser.getAccno(), sessionid, userSessionIdOutTime);
        set(sessionid, JSON.toJSONString(loginUser), userSessionIdOutTime);

        return sessionid;
    }

    public static String createAgentManageSessionID(String userSessionKey, LoginUser loginUser, SysParamService sysParamService) {
        String sessionid = null;
        if (loginUser == null) {
            return null;
        }
        String seckey = userSessionKey + RandomUtil.uuid();
        sessionid = MD5.md5(seckey, "UTF-8");
        loginUser.setAcctoken(sessionid);
        SysParameter bs = sysParamService.getByCode(SysParameterEnum.SESSION_TIME_BACK.name());
        if (bs == null || org.apache.commons.lang.StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(session_time_back)异常");
        }
        Long userSessionIdOutTime = (Long.parseLong(bs.getSysparamvalue())) * 60;
        // 通过sessionid 的 获取 缓存对象
        set("agent"+loginUser.getAccno(), sessionid, userSessionIdOutTime);
        set("agent"+sessionid, JSON.toJSONString(loginUser), userSessionIdOutTime);

        return sessionid;
    }


    //--------------------------LIVE-MANAGE--------------------------------------------


    //--------------------------WEBSITE--------------------------------------------
    public static String createYellowWebSessionID(String userSessionKey, LoginUser loginUser) {
        String sessionid = null;
        if (null == loginUser) {
            return null;
        }
        String seckey = RandomUtil.uuid() + userSessionKey + RandomUtil.uuid();
        sessionid = MD5.md5(seckey, "UTF-8");
        loginUser.setAcctoken(sessionid);

        // 重新设置sessionid 相关值
        String sessionid_old = String.valueOf(get(Constants.YELLOW_WEB_LOGIN_INFO + loginUser.getAccno()));
        if (org.apache.commons.lang.StringUtils.isNotEmpty(sessionid_old)) {
            delete(sessionid_old);
            delete(Constants.YELLOW_WEB_LOGIN_INFO + loginUser.getAccno());
        }

        // WEB 设置2 小时
        Long userSessionIdOutTime = 2 * 60 * 60l;
        // 通过sessionid 的 获取 缓存对象
        String secret = JSON.toJSONString(loginUser);
        // 聚合站点 web登陆
        set(Constants.YELLOW_WEB_LOGIN_INFO + loginUser.getAccno(), sessionid, userSessionIdOutTime);
        set(sessionid, secret, userSessionIdOutTime);

        return sessionid;
    }

    public static String createYellowManageSessionID(String userSessionKey, LoginUser loginUser) {
        String sessionid = null;
        if (loginUser == null) {
            return null;
        }
        String seckey = RandomUtil.uuid() + userSessionKey + RandomUtil.uuid();
        sessionid = MD5.md5(seckey, "UTF-8");
        loginUser.setAcctoken(sessionid);
        // 生成了新的 sessionid ，这是要使 以前旧的sessionid 失效
        // 获取手机号 对应的 sessionid

        String sessionid_old = String.valueOf(get(Constants.YELLOW_WEB_LOGIN_INFO + loginUser.getAccno()));
        if (org.apache.commons.lang.StringUtils.isNotEmpty(sessionid_old)) {
            delete(sessionid_old);
            delete(Constants.YELLOW_WEB_LOGIN_INFO + loginUser.getAccno());
        }

        // 重新设置sessionid 相关值
		/*SysParameter bs = this.sysParamService.getByCode("session_time_back");
		if (bs == null || StringUtils.isEmpty(bs.getSysparamvalue()))
			throw new BusinessException(StatusCode.LIVE_ERROR_999.getCode(), "系统参数(session_time_back)异常");
		Long userSessionIdOutTime = (Long.parseLong(bs.getSysparamvalue())) * 60;*/
        // WEB 设置2 小时
        Long userSessionIdOutTime = 2 * 60 * 60l;
        // 通过sessionid 的 获取 缓存对象
        set(Constants.YELLOW_WEB_LOGIN_INFO + loginUser.getAccno(), sessionid, userSessionIdOutTime);
        set(sessionid, JSON.toJSONString(loginUser), userSessionIdOutTime);

        return sessionid;
    }

    public static void setCateIdByLotteryIdCache(String lotteryCategoryListKey, HashMap<Integer, Integer> map) {
        redisTemplate.opsForValue().set(lotteryCategoryListKey, map);
    }

    public static void updateLsSgCache(Integer lotteryId, String key, Object updateSg) {
        try {
            if (redisTemplate.hasKey(key)) {
                if (lotteryId == Constants.LOTTERY_ONELHC) {
                    OnelhcLotterySg updateThisSg = (OnelhcLotterySg) updateSg;
                    OnelhcLotterySg onelhcLotterySg = (OnelhcLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(onelhcLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_FIVELHC) {
                    FivelhcLotterySg updateThisSg = (FivelhcLotterySg) updateSg;
                    FivelhcLotterySg fivelhcLotterySg = (FivelhcLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(fivelhcLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_AMLHC) {
                    AmlhcLotterySg updateThisSg = (AmlhcLotterySg) updateSg;
                    AmlhcLotterySg amlhcLotterySg = (AmlhcLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(amlhcLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_XYFT) {
                    XyftLotterySg updateThisSg = (XyftLotterySg) updateSg;
                    XyftLotterySg xyftLotterySg = (XyftLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(xyftLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_DLT) {
                    TcdltLotterySg updateThisSg = (TcdltLotterySg) updateSg;
                    TcdltLotterySg dltLotterySg = (TcdltLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(dltLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_TXFFC) {
                    TxffcLotterySg updateThisSg = (TxffcLotterySg) updateSg;
                    TxffcLotterySg txffcLotterySg = (TxffcLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue().split("-")[1]) - Long.valueOf(txffcLotterySg.getIssue().split("-")[1]) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_CQSSC) {
                    CqsscLotterySg updateThisSg = (CqsscLotterySg) updateSg;
                    CqsscLotterySg cqsscLotterySg = (CqsscLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(cqsscLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_XJSSC) {
                    XjsscLotterySg updateThisSg = (XjsscLotterySg) updateSg;
                    XjsscLotterySg xjsscLotterySg = (XjsscLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(xjsscLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_TJSSC) {
                    TjsscLotterySg updateThisSg = (TjsscLotterySg) updateSg;
                    TjsscLotterySg tjsscLotterySg = (TjsscLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(tjsscLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_TENSSC) {
                    TensscLotterySg updateThisSg = (TensscLotterySg) updateSg;
                    TensscLotterySg tensscLotterySg = (TensscLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(tensscLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_FIVESSC) {
                    FivesscLotterySg updateThisSg = (FivesscLotterySg) updateSg;
                    FivesscLotterySg fivesscLotterySg = (FivesscLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(fivesscLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_DZSSC) {
                    JssscLotterySg updateThisSg = (JssscLotterySg) updateSg;
                    JssscLotterySg jssscLotterySg = (JssscLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(jssscLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_PL35) {
                    TcplwLotterySg updateThisSg = (TcplwLotterySg) updateSg;
                    TcplwLotterySg tcplwLotterySg = (TcplwLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(tcplwLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_PCEGG) {
                    PceggLotterySg updateThisSg = (PceggLotterySg) updateSg;
                    PceggLotterySg pceggLotterySg = (PceggLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(pceggLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_HNQXC) {
                    Tc7xcLotterySg updateThisSg = (Tc7xcLotterySg) updateSg;
                    Tc7xcLotterySg qxcLotterySg = (Tc7xcLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(qxcLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_SSQ) {
                    FcssqLotterySg updateThisSg = (FcssqLotterySg) updateSg;
                    FcssqLotterySg fcssqLotterySg = (FcssqLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(fcssqLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_QLC) {
                    Fc7lcLotterySg updateThisSg = (Fc7lcLotterySg) updateSg;
                    Fc7lcLotterySg fc7lcLotterySg = (Fc7lcLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(fc7lcLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_FC3D) {
                    Fc3dLotterySg updateThisSg = (Fc3dLotterySg) updateSg;
                    Fc3dLotterySg fc3dLotterySg = (Fc3dLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(fc3dLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_BJPKS) {
                    BjpksLotterySg updateThisSg = (BjpksLotterySg) updateSg;
                    BjpksLotterySg bjpksLotterySg = (BjpksLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(bjpksLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_FIVEPKS) {
                    FivebjpksLotterySg updateThisSg = (FivebjpksLotterySg) updateSg;
                    FivebjpksLotterySg fivebjpksLotterySg = (FivebjpksLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(fivebjpksLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_DZPKS) {
                    JsbjpksLotterySg updateThisSg = (JsbjpksLotterySg) updateSg;
                    JsbjpksLotterySg jsbjpksLotterySg = (JsbjpksLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(jsbjpksLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_TENPKS) {
                    TenbjpksLotterySg updateThisSg = (TenbjpksLotterySg) updateSg;
                    TenbjpksLotterySg tenbjpksLotterySg = (TenbjpksLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(tenbjpksLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_AUSSSC) {
                    AussscLotterySg updateThisSg = (AussscLotterySg) updateSg;
                    AussscLotterySg aussscLotterySg = (AussscLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(aussscLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_AUSPKS) {
                    AuspksLotterySg updateThisSg = (AuspksLotterySg) updateSg;
                    AuspksLotterySg auspksLotterySg = (AuspksLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(auspksLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_AUSACT) {
                    AusactLotterySg updateThisSg = (AusactLotterySg) updateSg;
                    AusactLotterySg ausactLotterySg = (AusactLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(ausactLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_XJPLHC) {
                    XjplhcLotterySg updateThisSg = (XjplhcLotterySg) updateSg;
                    XjplhcLotterySg ausactLotterySg = (XjplhcLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(ausactLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_AZKS) {
                    AzksLotterySg updateThisSg = (AzksLotterySg) updateSg;
                    AzksLotterySg azksLotterySg = (AzksLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(azksLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_DZKS) {
                    DzksLotterySg updateThisSg = (DzksLotterySg) updateSg;
                    DzksLotterySg dzksLotterySg = (DzksLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(dzksLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_DZPCEGG) {
                    DzpceggLotterySg updateThisSg = (DzpceggLotterySg) updateSg;
                    DzpceggLotterySg dzpceggLotterySg = (DzpceggLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(dzpceggLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                } else if (lotteryId == Constants.LOTTERY_DZXYFT) {
                    DzxyftLotterySg updateThisSg = (DzxyftLotterySg) updateSg;
                    DzxyftLotterySg dzxyftLotterySg = (DzxyftLotterySg) redisTemplate.opsForList().index(key, 14);
                    //当期号相邻一期的时（说明缓存和数据库一致），则更新缓存，否则（清理下缓存，让历史和数据库同步）
                    if (Long.valueOf(updateThisSg.getIssue()) - Long.valueOf(dzxyftLotterySg.getIssue()) == 15) {
                        redisTemplate.opsForList().leftPush(key, updateSg);
                        redisTemplate.opsForList().rightPop(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("更新赛果历史缓存 出错：lotteryId:{},e:{}", lotteryId, e);
        }
    }

    /***
     *  登录时候保存在线人数
     * @param source
     * @param userId
     */
    public static void saveMemberOnline(String source, String userId) {
        if (source.equalsIgnoreCase(Constants.ANDROID_STRING)) {
            // 安卓端用户
            redisTemplate.opsForHash().put(RedisKeys.ANDROIDONLINE, userId, String.valueOf(TimeHelper.time()));
        } else if (source.equalsIgnoreCase(Constants.IOS_STRING)) {
            // ios端用户
            redisTemplate.opsForHash().put(RedisKeys.IOSONLINE, userId, String.valueOf(TimeHelper.time()));
        } else if (source.equalsIgnoreCase(Constants.H5_STRING)) {
            // h5端用户
            redisTemplate.opsForHash().put(RedisKeys.H5ONLINE, userId, String.valueOf(TimeHelper.time()));
        } else if (source.equalsIgnoreCase(Constants.WEB_STRING)) {
            // WEB端用户
            redisTemplate.opsForHash().put(RedisKeys.WEBONLINE, userId, String.valueOf(TimeHelper.time()));
        }
    }



    /***
     *  登录时候保存在线人数
     * @param userId
     */
    public static void saveAgentOnline(String userId) {
        // 安卓端用户
        redisTemplate.opsForHash().put(RedisKeys.WEBAGENTONLINE, userId, String.valueOf(TimeHelper.time()));
    }


    /**
     * 删除在线用户信息
     *
     * @param source
     * @param userIdList
     */
    public static void delMemberOnline(String source, List<String> userIdList) {
        if (source.equalsIgnoreCase(Constants.ANDROID_STRING)) {
            // 安卓端用户
            redisTemplate.opsForHash().delete(RedisKeys.ANDROIDONLINE, userIdList.toArray());
        } else if (source.equalsIgnoreCase(Constants.IOS_STRING)) {
            // ios端用户
            redisTemplate.opsForHash().delete(RedisKeys.IOSONLINE, userIdList.toArray());
        } else if (source.equalsIgnoreCase(Constants.H5_STRING)) {
            // h5端用户
            redisTemplate.opsForHash().delete(RedisKeys.H5ONLINE, userIdList.toArray());
        } else {
            // WEB端用户
            redisTemplate.opsForHash().delete(RedisKeys.WEBONLINE, userIdList.toArray());
        }
    }

    /**
     * 清除用户登录缓存信息
     *
     * @param accno
     */
    public static void clearUserLoginInfo(String accno) {
        String acctoken = get(accno);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(acctoken)) {
            List<String> keys = new ArrayList<>();
            keys.add(acctoken);
            keys.add(accno);
            redisTemplate.delete(keys);
        }
    }

    /**
     * 获取所有彩种大类缓存
     *
     * @return
     */
    public static List<LotteryCategory> getAllLotteryCategory() {
        return (List<LotteryCategory>) redisTemplate.opsForValue().get(RedisKeys.LOTTERY_CATEGORY_LIST_KEY);
    }

    /**
     * 获取所有彩种缓存
     *
     * @return
     */
    public static List<Lottery> getAllLottery() {
        return (List<Lottery>) redisTemplate.opsForValue().get(RedisKeys.LOTTERY_LIST_KEY);
    }


    /**
     * 获取直播间所有彩种缓存
     *
     * @return
     */
    public static List<OptionSelectVo> getAllLiveLottery() {
        return (List<OptionSelectVo>) redisTemplate.opsForValue().get(RedisKeys.LOTTERY_LIST_LIVE_KEY);
    }


    public static void deleteNoticeList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Constants.MESSAGE_NOTICE + 1);
        list.add(Constants.MESSAGE_NOTICE + 2);
        list.add(Constants.MESSAGE_NOTICE + 3);
        list.add(Constants.MESSAGE_NOTICE + 4);
        list.add(Constants.MESSAGE_NOTICE + 5);
        redisTemplate.delete(list);
    }

    /**
     * 判断是否限制当前IP
     *
     * @param ip
     * @param sysParamService
     * @param acctoken
     * @return
     */
    public static boolean isIpRestrict(String ip, SysParamService sysParamService, String acctoken) {
        if (StringUtils.isEmpty(ip)) {
            return false;
        }
        SysParameter sysParameter = get(SysParameterEnum.ASTRICT_IP_GROUP.name());
        if (null == sysParameter) {
            sysParameter = sysParamService.getByCode(SysParameterEnum.ASTRICT_IP_GROUP.name());
        }
        if (null == sysParameter) {
            return false;
        }
        if (SysParamStatusEnum.isDisabled(sysParameter.getStatus())) {
            return false;
        }
        String value = sysParameter.getSysparamvalue();
        value = null == value ? "" : value.trim();
        if (value.contains(ip)) {
            logger.warn("{} ip restrict, ip:{}", acctoken, ip);
            return true;
        }
        return false;
    }

    /**
     * return_water_set
     * 返水控制表缓存
     */
    public static List<ReturnWaterSet> getAllReturnWaterSet() {
        String redisKeys = RedisKeys.RETURN_ALL_WATER_SET;
        return (List<ReturnWaterSet>) redisTemplate.opsForValue().get(redisKeys);
    }

    /**
     * return_third_set
     * 三方游戏返水表缓存
     */
    public static List<ReturnThirdSet> getAllReturnThirdSet() {
        String redisKeys = RedisKeys.RETURN_ALL_THIRD_SET;
        return (List<ReturnThirdSet>) redisTemplate.opsForValue().get(redisKeys);
    }

    /**
     * return_lottery_set
     * 彩票返水表缓存
     */
    public static List<ReturnLotterySet> getAllReturnLotterySet() {
        String redisKeys = RedisKeys.RETURN_ALL_LOTTERY_SET;
        return (List<ReturnLotterySet>) redisTemplate.opsForValue().get(redisKeys);
    }

    /**
     * 第三方ae_room表缓存
     */
    public static List<AeRoom> getAllAeRoom() {
        return (List<AeRoom>) redisTemplate.opsForValue().get(RedisKeys.AE_ROOM_KEY);
    }

    /**
     * 第三方ae_game表缓存
     */
    public static List<AeGame> getAllAeGame() {
        return (List<AeGame>) redisTemplate.opsForValue().get(RedisKeys.AE_GANE_KEY);
    }

    /**
     * 第三方ag_play_type表缓存
     */
    public static List<AgPayType> getAllAgPlayType() {
        return (List<AgPayType>) redisTemplate.opsForValue().get(RedisKeys.AG_PAY_KEY);
    }

    /**
     * 第三方Ag_platform表缓存
     */
    public static List<AgPlatform> getAllGgPlatform() {
        return (List<AgPlatform>) redisTemplate.opsForValue().get(RedisKeys.AG_PLATFORM_KEY);
    }

    /**
     * 第三方Ag_round表缓存
     */
    public static List<AgRound> getAllAgRound() {
        return (List<AgRound>) redisTemplate.opsForValue().get(RedisKeys.AG_ROUND_KEY);
    }

    /**
     * 第三方Ag_game表缓存
     */
    public static List<AgGame> getAllAgGame() {
        return (List<AgGame>) redisTemplate.opsForValue().get(RedisKeys.AG_GANE_KEY);
    }

    /**
     * 第三方ky_kind表缓存
     */
    public static List<KyKind> getAllKyKind() {
        return (List<KyKind>) redisTemplate.opsForValue().get(RedisKeys.KY_KIND_KEY);
    }

    /**
     * 第三方ky_server表缓存
     */
    public static List<KyServer> getAllKyServer() {
        return (List<KyServer>) redisTemplate.opsForValue().get(RedisKeys.KY_SERVER_KEY);
    }

    public static void addSameParamsList(String code, String sort, List<String> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        sort = StringUtils.isEmpty(sort) ? "ASC" : sort;
        redisTemplate.opsForList().rightPushAll(code + sort.toUpperCase(), list);
    }

    public static List<String> getSameParamsList(String code, String sort) {
        if (StringUtils.isEmpty(code)) {
            return new ArrayList<>();
        }
        sort = StringUtils.isEmpty(sort) ? "ASC" : sort;
        return redisTemplate.opsForList().range(code + sort.toUpperCase(), 0, -1);
    }

    /**
     * 第三方mg_game表缓存
     */
    public static List<MgGame> getAllMgGame() {
        return (List<MgGame>) redisTemplate.opsForValue().get(RedisKeys.MG_GANE_KEY);
    }

    /**
     * 第三方mg_game表缓存
     */
    public static List<MgGame> getAllMgGameOrder() {
        return (List<MgGame>) redisTemplate.opsForValue().get(RedisKeys.MG_GANE_NAME_KEY);
    }

    /**
     * 第三方db_game表缓存
     */
    public static List<DbGame> getAllDbGameOrder() {
        return (List<DbGame>) redisTemplate.opsForValue().get(RedisKeys.DB_GANE_NAME_KEY);
    }

    //======================财务相关=========================
    public static SysPayset getPaysetCacheBySetType(Integer setType) {
        Object t = redisTemplate.opsForValue().get(RedisKeys.LIVE_SYS_PAYSET + setType);
        if (org.apache.commons.lang3.ObjectUtils.isEmpty(t)) {
            return null;
        }
        return (SysPayset) t;
    }

    public static void addPaysetCacheBySetType(SysPayset sysPayset) {
        if (ObjectUtils.isEmpty(sysPayset)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LIVE_SYS_PAYSET + sysPayset.getSettype(), sysPayset);
    }

    public static void delPaysetCacheBySetType(Integer setType) {
        redisTemplate.delete(RedisKeys.LIVE_SYS_PAYSET + setType);
    }

    public static void delPaysetCacheBySetType() {
        redisTemplate.delete(RedisKeys.LIVE_SYS_PAYSET + 1);
        redisTemplate.delete(RedisKeys.LIVE_SYS_PAYSET + 2);
    }

    /**
     * 修改token
     *
     * @param loginUser
     * @param sysParamService
     */
    public static void refreshTokenByAccno(LoginUser loginUser, SysParamService sysParamService) {
        if (StringUtils.isBlank(loginUser.getAccno())) {
            return;
        }

        SysParameter bs = sysParamService.getByCode(SysParameterEnum.SESSION_TIME.name());
        if (bs == null || org.apache.commons.lang.StringUtils.isEmpty(bs.getSysparamvalue())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1000.getCode(), "系统参数(session_time)异常");
        }

        Long userSessionIdOutTime = (Long.parseLong(bs.getSysparamvalue())) * 60;
        Object sessionid = RedisBusinessUtil.get(loginUser.getAccno());
        if (null == sessionid) {
            return;
        }
        Object secret = RedisBusinessUtil.get(sessionid);
        if (null == secret) {
            return;
        }
        LoginUser user = JSONObject.parseObject((String) secret, LoginUser.class);
        if (StringUtils.isNotBlank(loginUser.getMemlevel())) {
            user.setMemlevel(loginUser.getMemlevel());
        }
        if (StringUtils.isNotBlank(loginUser.getLevelSvgaUrl())) {
            user.setLevelSvgaUrl(loginUser.getLevelSvgaUrl());
        }
        RedisBusinessUtil.set(sessionid, JsonUtil.toJson(user), userSessionIdOutTime);
    }

    /**
     * 获取获取我的关注
     */
    public static List<MemFollow> getUserAttention(String accno) {
        if (StringUtils.isBlank(accno)) {
            return null;
        }
        return (List<MemFollow>) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_USER_ATTENTION + accno);
    }

    /**
     * 添加我的关注(24小时过期)
     */
    public static void addUserAttention(String accno, List<MemFollow> list) {
        if (StringUtils.isBlank(accno)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_USER_ATTENTION + accno, list, 24, TimeUnit.HOURS);
    }

    /**
     * 删除我的关注
     */
    public static void delUserAttention(String accno) {
        redisTemplate.delete(RedisKeys.LIVE_APP_USER_ATTENTION + accno);
    }

    /**
     * 获取app更新数据
     */
    public static List<AppinfoDO> getLastUpdateApp() {
        return (List<AppinfoDO>) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_LAST_UPDATEAPP);
    }

    /**
     * 添加app更新数据
     */
    public static void addLastUpdateApp(List<AppinfoDO> list) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_LAST_UPDATEAPP, list);
    }

    /**
     * 删除app更新数据
     */
    public static void delLastUpdateApp() {
        redisTemplate.delete(RedisKeys.LIVE_APP_LAST_UPDATEAPP);
    }

    /**
     * 获取视频列表页缓存
     */
    public static PageResult getVideoListCache(String videoName, Integer checkstatus, Integer pageNo) {
        return (PageResult) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_VIDEO_LIST + videoName + checkstatus + "_" + pageNo);
    }

    /**
     * 添加视频列表页缓存(24小时过期)
     */
    public static void addVideoListCache(String videoName, Integer checkstatus, int pageNo, PageResult basVideos) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_VIDEO_LIST + videoName + checkstatus + "_" + pageNo, basVideos, 24, TimeUnit.HOURS);
    }

    /**
     * 删除所有视频列表页,详情缓存
     */
    public static void delVideoListCache() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_APP_VIDEO_LIST);
    }

    public static void delIncarnateOrderListCahce() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_INCARNATE_ORDER_LIST_PAGE);
    }

    public static void addIncarnateOrderListCahce(Page<IncarnateOrderResponse> page, IncarnateOrderReq incarnateOrderReq, RowBounds rowBounds) {
        if (CollectionUtil.isEmpty(page)) {
            return;
        }
        String md5str = MD5.md5(JsonUtil.toJson(incarnateOrderReq) + " _" + JsonUtil.toJson(rowBounds));
        String key = RedisKeys.LIVE_MANAGE_INCARNATE_ORDER_LIST_PAGE + md5str;
        redisTemplate.opsForValue().set(key, page);
        redisTemplate.opsForValue().set(key + "_total", page.getTotal());
        redisTemplate.opsForValue().set(key + "_pageNum", page.getPageNum());
    }

    public static Page<EntryOrderResponse> getOnlineOrderListCahce(EntryOrderReq entryOrderReq, RowBounds rowBounds) {
        String key = RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE + MD5.md5(JsonUtil.toJson(entryOrderReq) + " _" + JsonUtil.toJson(rowBounds));
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null) {
            return null;
        }
        Page<EntryOrderResponse> page = (Page<EntryOrderResponse>) obj;
        page.setTotal(Long.parseLong(String.valueOf(redisTemplate.opsForValue().get(key + "_total"))));
        page.setPageNum(Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(key + "_pageNum"))));
        return page;
    }

    public static void addOnlineOrderListCahce(Page<EntryOrderResponse> page, EntryOrderReq entryOrderReq, RowBounds rowBounds) {
        if (CollectionUtil.isEmpty(page)) {
            return;
        }
        String md5str = MD5.md5(JsonUtil.toJson(entryOrderReq) + " _" + JsonUtil.toJson(rowBounds));
        String key = RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE + md5str;
        redisTemplate.opsForValue().set(key, page);
        redisTemplate.opsForValue().set(key + "_total", page.getTotal());
        redisTemplate.opsForValue().set(key + "_pageNum", page.getPageNum());
    }

    public static Page<IncarnateOrderResponse> getIncarnateOrderListCahce(IncarnateOrderReq incarnateOrderReq, RowBounds rowBounds) {
        String key = RedisKeys.LIVE_MANAGE_INCARNATE_ORDER_LIST_PAGE + MD5.md5(JsonUtil.toJson(incarnateOrderReq) + " _" + JsonUtil.toJson(rowBounds));
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null) {
            return null;
        }
        Page<IncarnateOrderResponse> page = (Page<IncarnateOrderResponse>) obj;
        page.setTotal(Long.parseLong(String.valueOf(redisTemplate.opsForValue().get(key + "_total"))));
        page.setPageNum(Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(key + "_pageNum"))));
        return page;
    }


    public static SysBusparameter getBusParamcodeCache(String busparamcode) {
        if (StringUtils.isBlank(busparamcode)) {
            return null;
        }
        Object obj = redisTemplate.opsForValue().get(RedisKeys.LIVE_BUS_PARAM_CODE + busparamcode);
        if (obj == null) {
            return null;
        }
        return (SysBusparameter) obj;
    }

    public static void addBusParamcodeCache(String busparamcode, SysBusparameter sysBusparameter) {
        if (StringUtils.isBlank(busparamcode) || ObjectUtils.isEmpty(sysBusparameter)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LIVE_BUS_PARAM_CODE + busparamcode, sysBusparameter);
    }

    public static void delBusParamcodeCache() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_BUS_PARAM_CODE);
    }

    public static List<InfSysnoticeDO> getInfSysnoticeListByType(Integer type) {
        if (type == null) {
            return null;
        }
        Object obj = redisTemplate.opsForValue().get(RedisKeys.LIVE_INF_SYS_NOTICE + type);
        if (obj == null) {
            return null;
        }
        return (List<InfSysnoticeDO>) obj;
    }

    public static void addInfSysnoticeListByType(Integer type, List<InfSysnoticeDO> infSysnoticeList) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_INF_SYS_NOTICE + type, infSysnoticeList);
    }

    public static void delInfSysnoticeList() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_INF_SYS_NOTICE);

    }

    /**
     * 获取视频列表页缓存
     */
    public static BasVideo getVideoDetailCache(Long videoId) {
        return (BasVideo) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_VIDEO_LIST + videoId);
    }

    /**
     * 添加视频详情页缓存(24小时过期)
     */
    public static void addVideoDetailCache(Long videoId, BasVideo basVideo) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_VIDEO_LIST + videoId, basVideo, 24, TimeUnit.HOURS);
    }

    /**
     * 获取视频详情上一条下一条缓存
     */
    public static List<BasVideo> getVideoDetailLastNextCache(Long videoId) {
        return (List<BasVideo>) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_VIDEO_DETAIL_LASTNEXT + videoId);
    }

    /**
     * 添加视频详情上一条下一条缓存(24小时过期)
     */
    public static void addVideoDetailLastNextCache(Long videoId, List<BasVideo> updownList) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_VIDEO_DETAIL_LASTNEXT + videoId, updownList, 24, TimeUnit.HOURS);
    }

    /**
     * 删除视频详情上一条下一条缓存(后台删除的频率不高，都删除)
     */
    public static void delVideoDetailLastNextCache() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_APP_VIDEO_DETAIL_LASTNEXT);
    }

    /**
     * 获取用户收藏数缓存
     */
    public static Integer getUserFavoritesNumCache(String accno) {
        return (Integer) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_USER_FAVORITES_NUM + accno);
    }

    /**
     * 添加用户收藏数缓存(24小时过期)
     */
    public static void addUserFavoritesNumCache(String accno, Integer num) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_USER_FAVORITES_NUM + accno, num, 24, TimeUnit.HOURS);
    }

    /**
     * 删除用户收藏数缓存
     */
    public static void delUserFavoritesNumCache(String accno) {
        redisTemplate.delete(RedisKeys.LIVE_APP_USER_FAVORITES_NUM + accno);
    }

    /**
     * 删除用户收藏数缓存(后台用户删除视频是，需要使用模糊匹配删除收藏数缓存)
     */
    public static void delUserFavoritesNumAllCache() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_APP_USER_FAVORITES_NUM);
    }

    /**
     * 获取用户资源数缓存
     */
    public static Integer getUserResourcesNumCache(String accno) {
        return (Integer) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_USER_RESOURCES_NUM + accno);
    }

    /**
     * 添加用户资源数缓存(24小时过期)
     */
    public static void addUserResourcesNumCache(String accno, Integer num) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_USER_RESOURCES_NUM + accno, num, 24, TimeUnit.HOURS);
    }

    /**
     * 删除用户资源数缓存
     */
    public static void delUserResourcesNumCache(String accno) {
        redisTemplate.delete(RedisKeys.LIVE_APP_USER_RESOURCES_NUM + accno);
    }

    /**
     * 获取用户的上级关系缓存
     */
    public static MemRelationship getUserRelationshipCache(String accno) {
        return (MemRelationship) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_USER_RELATIONSHIP + accno);
    }

    /**
     * 添加用户的上级关系缓存(24小时过期)
     */
    public static void addUserRelationshipCache(String accno, MemRelationship memRelationship) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_USER_RELATIONSHIP + accno, memRelationship, 24, TimeUnit.HOURS);
    }

    /**
     * 删除用户的上级关系缓存
     */
    public static void delUserRelationshipCache(String accno) {
        redisTemplate.delete(RedisKeys.LIVE_APP_USER_RELATIONSHIP + accno);
    }

    /**
     * 获取用户申请主播信息缓存
     */
    public static MemCertification getUserCertificationCache(String accno) {
        return (MemCertification) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_USER_CERTIFICATION + accno);
    }

    /**
     *
     */
    public static void putallmap(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * 添加用户申请主播信息缓存(24小时过期)
     */
    public static void addUserCertificationCache(String accno, MemCertification memCertification) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_USER_CERTIFICATION + accno, memCertification, 24, TimeUnit.HOURS);
    }

    /**
     * 删除用户申请主播信息缓存
     */
    public static void delUserCertificationCache(String accno) {
        redisTemplate.delete(RedisKeys.LIVE_APP_USER_CERTIFICATION + accno);
    }

    public static HashMap<String, Object> getBanksCacheByLevel(Integer levelSeq) {
        Object obj = redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_BANKS_LEVEL + levelSeq);
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        return (HashMap<String, Object>) obj;
    }

    public static void addBanksCacheByLevel(Integer levelSeq, HashMap<String, Object> dataMap) {
        if (dataMap == null || dataMap.isEmpty()) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_BANKS_LEVEL + levelSeq, dataMap, 24, TimeUnit.HOURS);
    }

    public static void delBanksCacheByLevel() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_APP_BANKS_LEVEL);
    }

    public static Long getMemidCacheByUniqueId(String uniqueId) {
        if (StringUtils.isBlank(uniqueId)) {
            return null;
        }
        Object obj = redisTemplate.opsForValue().get(RedisKeys.UNIQUE_ID_MAP_MEMID + uniqueId);
        if (obj == null) {
            return null;
        }
        return Long.parseLong(String.valueOf(redisTemplate.opsForValue().get(RedisKeys.UNIQUE_ID_MAP_MEMID + uniqueId)));
    }

    /*public static void addMemidCacheByUniqueId(MemBaseinfo baseinfo) {
        if (ObjectUtils.isEmpty(baseinfo) || StringUtils.isBlank(baseinfo.getUniqueId()) || null == baseinfo.getMemid())
            return;
        redisTemplate.opsForValue().set(RedisKeys.UNIQUE_ID_MAP_MEMID + baseinfo.getUniqueId(), baseinfo.getMemid(), 7, TimeUnit.DAYS);
    }*/

    /**
     * 获取圈子关注数缓存
     */
    public static FansAndFocusNumberVO getFansAndFocusNumberCache(Integer myId, Integer otherId) {
        String json = (String) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_CIRCLE_FANS_FOCUS_NUMBER + myId + "_" + otherId);
        if (null != json) {
            FansAndFocusNumberVO data = JSONObject.parseObject(json, FansAndFocusNumberVO.class);
            return data;
        }
        return null;
    }

    /**
     * 添加圈子关注数缓存(24小时过期)
     */
    public static void addFansAndFocusNumberCache(Integer myId, Integer otherId, FansAndFocusNumberVO data) {
        if (null != data) {
            String json = JSONObject.toJSONString(data);
            redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_CIRCLE_FANS_FOCUS_NUMBER + myId + "_" + otherId, json, 24, TimeUnit.HOURS);
        }
    }

    /**
     * 删除圈子关注数缓存
     */
    public static void delFansAndFocusNumberCache(Integer myId, Integer otherId) {
        deleteFuzzyMatchCache(RedisKeys.LIVE_APP_CIRCLE_FANS_FOCUS_NUMBER + myId + "_");
        deleteFuzzyMatchCache(RedisKeys.LIVE_APP_CIRCLE_FANS_FOCUS_NUMBER + otherId + "_");
    }

    /**
     * 获取圈子列表页缓存
     */
    public static ResultInfo getCirclePostListCache(Integer uid, Integer type, Integer pageNo, Integer pageSize) {
        return (ResultInfo) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_CIRCLE_POST_LIST_ + uid + "_" + type + "_" + pageNo + "_" + pageSize);
    }

    /**
     * 添加圈子列表页缓存(24小时过期)
     */
    public static void addCirclePostListCache(Integer uid, Integer type, Integer pageNo, Integer pageSize, ResultInfo data) {
        if (null != data) {
            redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_CIRCLE_POST_LIST_ + uid + "_" + type + "_" + pageNo + "_" + pageSize, data, 24, TimeUnit.HOURS);
        }
    }

    /**
     * 删除圈子列表页缓存、帖子总数
     */
    public static void delCirclePostListCache() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_APP_CIRCLE_POST_LIST_);
    }

    /**
     * 获取圈子忒子总数缓存
     */
    public static Integer getCirclePostNumCache() {
        return (Integer) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_CIRCLE_POST_LIST_NUM);
    }

    /**
     * 添加圈子忒子总数缓存(24小时过期)
     */
    public static void addCirclePostNumCache(Integer total) {
        if (null != total) {
            redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_CIRCLE_POST_LIST_NUM, total, 24, TimeUnit.HOURS);
        }
    }

    /**
     * 获取注册总数缓存
     */
    public static Integer getAllRegisteredNumCache() {
        return (Integer) redisTemplate.opsForValue().get(RedisKeys.LIVE_APP_USER_ALL_REGISTERED_NUM);
    }

    /**
     * 添加注册总数缓存(24小时过期)
     */
    public static void addAllRegisteredNumCache(Integer num) {
        if (null != num) {
            redisTemplate.opsForValue().set(RedisKeys.LIVE_APP_USER_ALL_REGISTERED_NUM, num, 24, TimeUnit.HOURS);
        }
    }

    /**
     * 删除注册总数缓存
     */
    public static void delAllRegisteredNumCache() {
        redisTemplate.delete(RedisKeys.LIVE_APP_USER_ALL_REGISTERED_NUM);
    }

    /**
     * 通过code 获取参数列表
     *
     * @param pbusparamcode
     * @return
     */
    public static List<SysBusparameter> getSysBusparameters(String pbusparamcode) {
        Object obj = redisTemplate.opsForValue().get(RedisKeys.LIVE_SYS_BUSPARAMETER_ARRAY + pbusparamcode);
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        return (List<SysBusparameter>) obj;
    }

    /**
     * 设置参数列表，通过code
     *
     * @param pbusparamcode
     * @param list
     */
    public static void addSysBusparameters(String pbusparamcode, List<SysBusparameter> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LIVE_SYS_BUSPARAMETER_ARRAY + pbusparamcode, list);
    }

    /**
     * 获取 公司入款订单列表缓存
     *
     * @param req
     * @param page
     * @return
     */
    public static Map<String, Object> getRechargeUnLineOrder(EntryOrderReq req, PageBounds page) {
        Object obj = redisTemplate.opsForValue().get(RedisKeys.LIVE_RECHARGE_UNLINE_ORDER_LIST + MD5.md5(JsonUtil.toJson(req) + "_" + JsonUtil.toJson(page)));
        if (obj == null) {
            return null;
        }
        return (HashMap) obj;
    }

    /**
     * 设置 公司入款订单列表缓存
     *
     * @param req
     * @param page
     * @param result
     */
    public static void addRechargeUnLineOrder(EntryOrderReq req, PageBounds page, Map result) {
        if (result == null) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LIVE_RECHARGE_UNLINE_ORDER_LIST + MD5.md5(JsonUtil.toJson(req) + "_" + JsonUtil.toJson(page)), result);
    }

    /**
     * 删除 公司入款订单列表缓存
     */
    public static void delRechargeUnLineOrder() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_RECHARGE_UNLINE_ORDER_LIST);
    }

    /**
     * 签到 用于统计5分钟在线人数
     *
     * @param userId
     */
    public static void checkIn(Long userId) {
        String today = DateUtil.format(new Date(), "yyyyMMddHHmm");

        String checkInKey = getCheckInKey(today);
        //Boolean execute = (Boolean) redisTemplate.execute((RedisCallback<Boolean>) con -> con.getBit(checkInKey.getBytes(), userId));
        Boolean execute = getBit(checkInKey, userId);
        if (Optional.ofNullable(execute).orElse(false)) {
            return;
        }
        //redisTemplate.execute((RedisCallback<Boolean>) con -> con.setBit(checkInKey.getBytes(), userId, true));
        setBit(checkInKey, userId, true);
        //设置失效时间 5分钟
        redisTemplate.expire(checkInKey, 5 * 60 + 1, TimeUnit.SECONDS);
    }

    /**
     * 获取统计5分钟在线人数
     *
     * @param beforeMinutes
     */
    public static Long countCheckInByDate(Integer beforeMinutes) {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i <= beforeMinutes; i++) {
            String minutes = DateUtil.format(DateUtil.offsetMinute(new Date(), -i), "yyyyMMddHHmm");
            String minuteskey = getCheckInKey(minutes);
            keys.add(minuteskey);
        }
        bitOp(RedisStringCommands.BitOperation.OR, RedisKeys.CHECK_IN_PRE_KEY + ":ALL", keys);
        Long aLong = bitCount(RedisKeys.CHECK_IN_PRE_KEY + ":ALL");
        return aLong;
    }

    private static String getCheckInKey(String date) {
        return RedisKeys.CHECK_IN_PRE_KEY + ":" + date;
    }

    /**
     * 获取家族长的礼物和投注分成缓存
     */
    public static OrderTotalDTO getFamilyGiftBetCache(String accno, String startTime, String endTime) {
        return (OrderTotalDTO) redisTemplate.opsForValue().get(RedisKeys.LIVE_MANAGE_FAMILY_GIFT_BET + accno + "_" + startTime + "_" + endTime);
    }

    /**
     * 添加家族长的礼物和投注分成缓存(24小时过期)
     */
    public static void addFamilyGiftBetCache(String accno, String startTime, String endTime, OrderTotalDTO data) {
        redisTemplate.opsForValue().set(RedisKeys.LIVE_MANAGE_FAMILY_GIFT_BET + accno + "_" + startTime + "_" + endTime, data, 24, TimeUnit.HOURS);
    }

    /**
     * 删除家族长的礼物和投注分成缓存
     */
    public static void delFamilyGiftBetCache() {
        deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_FAMILY_GIFT_BET);
    }

    public static void delSysSensitiveWorld() {
        delete(RedisKeys.LIVE_SYS_SENSITIVE_WORLD);
    }

    public static SysSensitiveWord getSysSensitiveWorld() {
        Object obj = redisTemplate.opsForValue().get(RedisKeys.LIVE_SYS_SENSITIVE_WORLD);
        return obj == null ? null : (SysSensitiveWord) obj;
    }

    public static void addSysSensitiveWorld(SysSensitiveWord sysSensitiveWord) {
        if (sysSensitiveWord == null) {
            return;
        }
        redisTemplate.opsForValue().set(RedisKeys.LIVE_SYS_SENSITIVE_WORLD, sysSensitiveWord);
    }

    /**
     * 缓存用户离线时间
     *
     * @param lists
     */
    public static void cacheUserOfflineTime(List<String>... lists) {
        if (null == lists || lists.length == 0) {
            return;
        }
        if (null == userOfflineRedisBigMap) {
            userOfflineRedisBigMap = new RedisBigMap();
        }
        Set<String> userIds = new HashSet<>();
        for (List<String> list : lists) {
            userIds.addAll(list);
        }
        String offlineTime = DateUtils.formatDate(new Date());
        Iterator<String> iterator = userIds.iterator();
        while (iterator.hasNext()) {
            userOfflineRedisBigMap.put(RedisKeys.STAT_USER_OFFLINE_TIME_MAP, iterator.next(), offlineTime);
        }
    }

    /**
     * 获取用户离线时间
     *
     * @param uids
     * @return Map[uid, offlineTime]
     */
    public static Map<Integer, String> getUserOfflineTime(List<Integer> uids) {
        Map<Integer, String> map = new HashMap<>();
        if (null == uids || uids.size() == 0) {
            return map;
        }
        if (null == userOfflineRedisBigMap) {
            return map;
        }
        for (Integer uid : uids) {
            map.put(uid, (String) userOfflineRedisBigMap.get(RedisKeys.STAT_USER_OFFLINE_TIME_MAP, uid.toString()));
        }
        return map;
    }


    public void  setRanking() {



    }

}
