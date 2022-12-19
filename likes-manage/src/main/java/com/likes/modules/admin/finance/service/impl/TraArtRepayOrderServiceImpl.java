package com.likes.modules.admin.finance.service.impl;


import com.likes.common.constant.Constants;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.TraArtRepayOrderDO;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.common.RepayOrderTypeEnum;
import com.likes.common.model.request.TraArtRepayOrderQuery;
import com.likes.common.model.request.TraArtRepayOrderRequest;
import com.likes.common.mybatis.entity.MemRepayuser;
import com.likes.common.mybatis.entity.TraArtrepayorder;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.likes.common.mybatis.mapper.MemRepayuserMapper;
import com.likes.common.mybatis.mapper.TraArtrepayorderMapper;
import com.likes.common.mybatis.mapper.TraOrderinfomMapper;
import com.likes.common.mybatis.mapperext.member.MemLoginMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.modules.admin.finance.service.TraArtRepayOrderService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class TraArtRepayOrderServiceImpl extends BaseServiceImpl implements TraArtRepayOrderService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private TraArtrepayorderMapper traArtrepayorderMapper;

    @Resource
    private MemLoginMapperExt memLoginMapperExt;

    @Resource
    private TraOrderinfomMapper traOrderinfomMapper;

    @Resource
    private MemRepayuserMapper memRepayuserMapper;


    /**
     * 代充存入&提出
     *
     * @param request   请求参数
     * @param loginUser 登录用户
     * @return id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long agentArtRepayOrder(TraArtRepayOrderRequest request, LoginUser loginUser) {
        // 检验参数
        this.validateParams(request);

        // 校验代充人
        LoginUser user = memLoginMapperExt.selectUserByAccLogin(request.getAcclogin().trim());
        if (user == null || !LoginUserTypeEnum.AGENT.getCode().equals(user.getLogintype())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "该代充人账号不存在");
        }
        MemRepayuser memRepayuser = memRepayuserMapper.selectByAccNo(user.getAccno());
        if (memRepayuser == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "该代充人账号不存在");
        }

        Date currentDate = new Date();
        // 订单类型
        Integer orderType;
        // 存入/提出后金额
        BigDecimal memGold;
        if (Constants.ART_ORDER_TYPE_WITHDRAW.equals(request.getOrdertype())) {
            memGold = memRepayuser.getMemgold().subtract(request.getOptamt());
            // 提出时校验账号余额是否充足
            if (memGold.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "该账号余额不足");
            }
            orderType = Constants.ORDER_TYPE_ART_OUT;
        } else {
            memGold = memRepayuser.getMemgold().add(request.getOptamt());
            orderType = Constants.ORDER_TYPE_ART_IN;
        }

        // 1、订单信息主
        TraOrderinfom traOrderinfom = this.traOrderInformation(orderType, user.getAccno(), request.getOptamt(),
                request.getOptamt(), loginUser, currentDate);

        // 2、人工存提订单
        TraArtrepayorder traArtRepayOrder = this.traArtRepayOrder(request.getOptamt(), null, null,
                request.getOrdertype(), request.getNote(), loginUser, traOrderinfom.getOrderid(), user.getMemid(),
                user.getNickname(), user.getAccno(), LoginUserTypeEnum.AGENT.getCode().shortValue());

        // 3、更新代充人账号余额
        this.updateMemRepayUser(memRepayuser.getRepaymemid(), memGold, loginUser.getAccno());

        return traArtRepayOrder.getArtorderid();
    }


    /**
     * 人工存提订单
     *
     * @param optAmt      请求参数 存提金额
     * @param giftAmt     优惠金额
     * @param auditPer    打码倍数
     * @param orderType   存提类型：0-存入，1-提出
     * @param note        备注说明
     * @param loginUser   登录用户
     * @param orderId     订单id
     * @param memId       用户id
     * @param nickName    呢称
     * @param accNo       会员标识号
     * @param loginType   账户类型：1-普通会员，2-主播，4-代充人
     */
    private TraArtrepayorder traArtRepayOrder(BigDecimal optAmt, BigDecimal giftAmt, BigDecimal auditPer,
                                              Integer orderType, String note, LoginUser loginUser, Long orderId,
                                              Long memId, String nickName, String accNo, Short loginType) {

        TraArtrepayorder traArtRepayOrder = new TraArtrepayorder();
        traArtRepayOrder.setOrderid(orderId);
        traArtRepayOrder.setMemaccount(memId);
        traArtRepayOrder.setOptamt(optAmt);
        traArtRepayOrder.setGiftamt(giftAmt);
        traArtRepayOrder.setAuditper(auditPer);
        traArtRepayOrder.setOrdertype(orderType);
        traArtRepayOrder.setNote(note);
        traArtRepayOrder.setNickname(nickName);
        traArtRepayOrder.setAccno(accNo);
        traArtRepayOrder.setLogintype(loginType);
        traArtRepayOrder.setCreateUser(loginUser.getAccno());
        traArtRepayOrder.setUpdateUser(loginUser.getAccno());
        int result = traArtrepayorderMapper.insertSelective(traArtRepayOrder);
        if (result == 0) {
            logger.warn("{}代充存提生成订单失败" + loginUser.getAcclogin());
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "该订单提交失败");
        }

        return traArtRepayOrder;
    }


    /**
     * 代充存提列表
     *
     * @param query 查询条件
     * @param page  分页
     * @return List
     */
    @Override
    public PageResult agentArtRepayOrderList(TraArtRepayOrderQuery query, PageBounds page) {
        if (query != null) {
            query.setOrderno(query.getOrderno() == null ? null : query.getOrderno().trim());
            query.setAcclogin(query.getAcclogin() == null ? null : query.getAcclogin().trim());
            query.setNickname(query.getNickname() == null ? null : query.getNickname().trim());
        }
        Page<TraArtRepayOrderDO> list = traArtrepayorderMapper.selectAgentArtRepayOrderList(query, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }


    /**
     * 订单信息
     *
     * @param orderType   订单类型
     * @param memAccNo    会员accNo
     * @param orderAmt    订单金额
     * @param orderSumAmt 订单总金额
     * @param loginUser   登录用户
     * @param currentDate 当前日期
     */
    private TraOrderinfom traOrderInformation(Integer orderType, String memAccNo, BigDecimal orderAmt, BigDecimal orderSumAmt,
                                              LoginUser loginUser, Date currentDate) {

        TraOrderinfom traOrderinfom = new TraOrderinfom();
        traOrderinfom.setOrdertype(orderType);
        traOrderinfom.setOrderno(SnowflakeIdWorker.generateShortId());
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD08);
        traOrderinfom.setAccountstatus(Constants.ORDER_ACC04);
        traOrderinfom.setAccno(memAccNo);
        traOrderinfom.setOrderdate(currentDate);
        traOrderinfom.setOldamt(orderAmt);
        traOrderinfom.setSumamt(orderSumAmt);
        traOrderinfom.setRealamt(orderSumAmt);
        traOrderinfom.setIsinvoice(9);
        traOrderinfom.setCreateUser(loginUser.getAccno());
        traOrderinfom.setUpdateUser(loginUser.getAccno());
        traOrderinfom.setOrdernote("代充人[" + loginUser.getNickname() + "]" + RepayOrderTypeEnum.getValueByCode(orderType));
        int result = traOrderinfomMapper.insertSelective(traOrderinfom);
        if (result == 0) {
            logger.warn("{}订单信息失败" + loginUser.getAcclogin());
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "该订单提交失败");
        }

        return traOrderinfom;
    }

    /**
     * 更新代充人账号余额
     *
     * @param repayMemId 代充人id
     * @param memGold    金额
     * @param accNo      会员标识
     */
    private void updateMemRepayUser(Long repayMemId, BigDecimal memGold, String accNo) {
        MemRepayuser updateRepayUser = new MemRepayuser();
        updateRepayUser.setRepaymemid(repayMemId);
        updateRepayUser.setMemgold(memGold);
        updateRepayUser.setUpdateUser(accNo);
        memRepayuserMapper.updateByPrimaryKeySelective(updateRepayUser);
    }


    private void validateParams(TraArtRepayOrderRequest request) {
        if (StringUtils.isBlank(request.getAcclogin())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "代充人账号为空");
        }
        if (request.getOptamt() == null) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "存提金额为空");
        }
        if (request.getOptamt().compareTo(new BigDecimal(0)) <= 0) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "存提金额必须大于0");
        }
        if (!Constants.ART_ORDER_TYPE_DEPOSIT.equals(request.getOrdertype())
                && !Constants.ART_ORDER_TYPE_WITHDRAW.equals(request.getOrdertype())) {
            throw new BusinessException(StatusCode.ACCOUNT_EMPTY.getCode(), "存提类型无效");
        }
    }

}
