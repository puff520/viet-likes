package com.likes.modules.admin.users.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.member.MemSigninDO;
import com.likes.common.model.dto.order.SignDaoDO;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemSignin;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemSigninService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.util.DateUtils;
import com.likes.modules.admin.users.service.SignDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class SignDaoServiceImpl implements SignDaoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private MemSigninService memSigninService;

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public Map<String, Object> signList(LoginUser loginUserAPP) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            dataMap = getSignList(loginUserAPP);
            return dataMap;
        } catch (Exception e) {
            logger.info("Exception:{}", e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "签到列表失败");
        }
    }


    private Map<String, Object> getSignList(LoginUser loginUserAPP) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // String msg = "";
        try {
            String accno = loginUserAPP.getAccno();
            SignDaoDO param = new SignDaoDO();
            param.setAccno(accno);
            // 默认值数组
            List<SignDaoDO> signList = getList(loginUserAPP.getAccno());

            // 获取昨天的日期
            Date yesterdayDate = DateUtils.beforeDays(new Date(), 1);
            String yesterday = DateUtils.formatDate(yesterdayDate, "yyyy-MM-dd");
            // 昨天是否签到
            param.setSignDate(yesterday);
            MemSignin yesterdaysign = memSigninService.getTodayData(param);
            if (yesterdaysign != null) {
                // 已经有签到天数
                Integer yesterday_signinnum = yesterdaysign.getSigninnum();

                if (Constants.SIGNCYCLE.equals(yesterday_signinnum)) {
                    // 如果等于7 则开始新的一轮
                    // 重新开始
                    SignDaoDO first = signList.get(0);

                    // 获取今天的日期
                    String today = DateUtils.getCurrentDateString("yyyy-MM-dd");
                    param.setSignDate(today);
                    // 今天是否签到
                    MemSignin memSignin = memSigninService.getTodayData(param);
                    if (memSignin != null) {
                        first.setIssign(1);
                        // 设置明天提醒消息
                        SignDaoDO second = signList.get(1);
                        dataMap.put("list", signList);
                        dataMap.put("title", "明日奖励+" + second.getGoldnum());
                    } else {
                        first.setIsdaisign(1);
                        dataMap.put("list", signList);
                        dataMap.put("title", "今日奖励+" + first.getGoldnum());
                        dataMap.put("daiindex", first.getIndex());
                    }
                    return dataMap;
                } else {
                    // 今天是第几天
                    Integer today_index = yesterday_signinnum + 1;
                    // 还没有新的一轮
                    for (int i = 0; i < yesterday_signinnum; i++) {
                        SignDaoDO s = signList.get(i);
                        s.setIssign(1);
                    }

                    // 获取今天的日期
                    String today = DateUtils.getCurrentDateString("yyyy-MM-dd");
                    param.setSignDate(today);
                    // 今天是否签到
                    MemSignin memSignin = memSigninService.getTodayData(param);
                    if (memSignin != null) {
                        // 今天已经签到
                        SignDaoDO s = signList.get(yesterday_signinnum);
                        s.setIssign(1);
                        // 设置明天提醒消息
                        if (Constants.SIGNCYCLE.equals(today_index)) {
                            // 今天签到完 明天开始新的一轮
                            SignDaoDO first = signList.get(0);
                            dataMap.put("list", signList);
                            dataMap.put("title", "明日奖励+" + first.getGoldnum());
                        } else {
                            SignDaoDO second = signList.get(yesterday_signinnum + 1);
                            dataMap.put("list", signList);
                            dataMap.put("title", "明日奖励+" + second.getGoldnum());
                        }

                    } else {
                        // 今天 没有签到
                        SignDaoDO s = signList.get(today_index - 1);
                        s.setIsdaisign(1);
                        dataMap.put("list", signList);
                        dataMap.put("title", "今日奖励+" + s.getGoldnum());
                        dataMap.put("daiindex", s.getIndex());
                    }

                    return dataMap;
                }
            } else {
                // 没有，则重新开始
                // 设置第一位带钱
                SignDaoDO first = signList.get(0);

                // 获取今天的日期
                String today = DateUtils.getCurrentDateString("yyyy-MM-dd");
                param.setSignDate(today);
                // 今天是否签到
                MemSignin memSignin = memSigninService.getTodayData(param);
                if (memSignin != null) {
                    first.setIssign(1);
                    // 设置明天提醒消息
                    SignDaoDO second = signList.get(1);
                    dataMap.put("list", signList);
                    dataMap.put("title", "明日奖励+" + second.getGoldnum());
                } else {
                    first.setIsdaisign(1);
                    dataMap.put("list", signList);
                    dataMap.put("title", "今日奖励+" + first.getGoldnum());
                    dataMap.put("daiindex", first.getIndex());
                }
                return dataMap;
            }
        } catch (Exception e) {
            logger.info("Exception:{}", e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "签到列表失败");
        }
    }


    public static List<SignDaoDO> getList(String accno) {
        List<SignDaoDO> signList = new ArrayList<SignDaoDO>();
        for (int i = 1; i < Constants.SIGNCYCLE + 1; i++) {
            SignDaoDO signDaoDO = new SignDaoDO();
            signDaoDO.setAccno(accno);
            signDaoDO.setIndex(i);
            signDaoDO.setIsdaisign(0);
            signDaoDO.setIssign(0);
            if (i == 7) {
                signDaoDO.setGoldnum(Constants.SIGNCYCLE_GOLDNUM_MAXDAY_D);
            } else {
                signDaoDO.setGoldnum(i * Constants.SIGNCYCLE_GOLDNUM_D);
            }
            signList.add(signDaoDO);
        }
        return signList;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String dosign(LoginUser loginUserAPP) {
        Map<String, Object> dataMap = getSignList(loginUserAPP);
        if (dataMap != null) {
            //try {
            Integer daiindex = (Integer) dataMap.get("daiindex");
            logger.info("daiindex:{}", daiindex);
            if (daiindex == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "今日已签到");
            }

            List<SignDaoDO> sList = getList(loginUserAPP.getAccno());
            //获取金币数
            SignDaoDO signDo = sList.get(daiindex - 1);
            double signinnum = signDo.getGoldnum();

				/*synchronized (this) {
					String accno = loginUserAPP.getAccno();
					SignDaoDO param = new SignDaoDO();
					param.setAccno(accno);
					// 获取今天的日期
					String today = DateUtils.getCurrentDateString("yyyy-MM-dd");
					param.setSignDate(today);
					// 今天是否签到
					MemSignin m = memSigninMapper.getTodayData(param);
					if (m != null) {
						throw new BusinessException(111, "今日已签到");
					}
				}*/

            String today = DateUtils.getCurrentDateString("yyyy-MM-dd");
            //签到
            MemSigninDO memSignin = new MemSigninDO();
            memSignin.setAccno(loginUserAPP.getAccno());
            memSignin.setSignintime(new Date());
            memSignin.setSigninnum(daiindex);
            memSignin.setSignDate(today);
            int i = memSigninService.insertMemSignin(memSignin);
            if (i == 0) {
                throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "今日已签到");
            }
            //在给用户 加 金币 和 进步 来源
            //更新金币参数
            UsersRequest usersRequest = new UsersRequest();
            BigDecimal tradeOffAmount = getTradeOffAmount(new BigDecimal(signinnum));
            usersRequest.setGoldnum(tradeOffAmount.doubleValue());
            usersRequest.setAccno(loginUserAPP.getAccno());
            //插入金币变动数据
            MemGoldchange memGoldchange = new MemGoldchange();
            memGoldchange.setRefid(memSignin.getSigninid());
            memGoldchange.setAccno(loginUserAPP.getAccno());
//            memGoldchange.setChangetype(GoldchangeEnum.ATTENDANCE_AWARD.getValue());

            MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());
            if (memBaseinfo == null || memBaseinfo.getIsDelete()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_204.getCode(), "创建者已删除");
            }
            //System.out.println(memBaseinfo.getAccno());
            if (memBaseinfo.getGoldnum() == null) {
                memBaseinfo.setGoldnum(new BigDecimal(0));
            }
            memGoldchange.setGoldnum(memBaseinfo.getGoldnum());
            memGoldchange.setQuantity(tradeOffAmount);
            BigDecimal recgoldnum = memGoldchange.getGoldnum().add(memGoldchange.getQuantity());
            memGoldchange.setRecgoldnum(getTradeOffAmount(recgoldnum));
            memGoldchange.setAmount(tradeOffAmount);
            memGoldchange.setCreateUser(loginUserAPP.getAccno());
            memGoldchange.setUpdateUser(loginUserAPP.getAccno());
            memGoldchange.setOpnote("签到");

            //主播签到 没有奖励
            if (!LoginUserTypeEnum.isAnchor(loginUserAPP.getLogintype())) {
                int m = memGoldchangeService.insertSelective(memGoldchange);
                if (m > 0) {
                    memBaseinfoService.updateAddGold(usersRequest);
                }
            }

            //} catch (Exception e) {
            //	e.printStackTrace();
            //	logger.info("Exception:{}", e.getMessage());
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //	throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "签到失败");
            //}
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "签到初始化数据失败");
        }
        return Constants.SUCCESS_MSG;
    }

}
