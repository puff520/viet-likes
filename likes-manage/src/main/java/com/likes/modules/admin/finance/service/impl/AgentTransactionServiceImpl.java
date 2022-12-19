package com.likes.modules.admin.finance.service.impl;


import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.AgentTransactionDO;
import com.likes.common.model.AgentTransactionDetailDO;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AgentTransactionQuery;
import com.likes.common.model.request.AgentTransactionRequest;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemRepayuser;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.likes.common.mybatis.mapper.MemGoldchangeMapper;
import com.likes.common.mybatis.mapper.MemRepayuserMapper;
import com.likes.common.mybatis.mapper.TraOrderinfomMapper;
import com.likes.common.mybatis.mapperext.member.MemGoldchangeMapperExt;
import com.likes.common.mybatis.mapperext.tra.TraOrderinfomMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.modules.admin.finance.service.AgentTransactionService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AgentTransactionServiceImpl extends BaseServiceImpl implements AgentTransactionService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private TraOrderinfomMapper traOrderinfomMapper;
    @Resource
    private TraOrderinfomMapperExt traOrderinfomMapperExt;
    @Resource
    private MemGoldchangeMapperExt memGoldchangeMapperExt;

    @Resource
    private MemRepayuserMapper memRepayuserMapper;

    @Override
    public PageResult rechargeList(AgentTransactionQuery query, PageBounds page) {

        if (query != null) {
            query.setAcclogin(query.getAcclogin() == null ? null : query.getAcclogin().trim());
            query.setNickname(query.getNickname() == null ? null : query.getNickname().trim());
        }
        Page<AgentTransactionDO> list = traOrderinfomMapperExt.selectAgentOrderList(query, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }

    @Override
    public AgentTransactionDetailDO rechargeDetail(Long orderId) {
        AgentTransactionDetailDO detail = traOrderinfomMapperExt.selectAgentOrderById(orderId);
        // 支付凭证截图地址
        if (detail != null && StringUtils.isNotBlank(detail.getPayimg())) {
            String[] payImgArray = detail.getPayimg().split(",");
            List<String> payImgList = new ArrayList<>();
            for (String url : payImgArray) {
                payImgList.add(url);
            }
            detail.setPayimgurl(payImgList);
        }

        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateRecharge(AgentTransactionRequest request, LoginUser loginUser) {
        if (request.getOrderid() == null) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "订单ID为空");
        }
        if (request.getPaynote() != null&&request.getPaynote().length()>400) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "备注长度超过400字");
        }
        if (request.getOrderid() <= 0) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "订单ID无效");
        }
        if (!(Constants.ORDER_ORD08).equals(request.getOrderstatus())
                && !(Constants.ORDER_ORD13).equals(request.getOrderstatus())) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "订单状态无效");
        }

        TraOrderinfom traOrder = traOrderinfomMapper.selectByPrimaryKey(request.getOrderid());
        if (traOrder == null) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "该订单不存在");
        }
        if (!Constants.ORDER_ORD04.equals(traOrder.getOrderstatus())) {
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "订单状态不为待付款");
        }

        Date currentDate = new Date();
        // 订单处理
        TraOrderinfom traOrderinfom = new TraOrderinfom();
        traOrderinfom.setOrderid(request.getOrderid());
        //traOrderinfom.setPayimg(request.getPayimg());
        traOrderinfom.setOrderstatus(request.getOrderstatus());
        traOrderinfom.setPaydate(currentDate);
        traOrderinfom.setPaynote(request.getPaynote());
        traOrderinfom.setUpdateUser(loginUser.getAccno());
        int result = traOrderinfomMapper.updateByPrimaryKeySelective(traOrderinfom);
        if (result == 0) {
            logger.warn("订单ID={}, 订单处理失败", traOrder.getOrderid());
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "订单处理失败");
        }

        // 取消订单，后续不处理
        if (Constants.ORDER_ORD09.equals(request.getOrderstatus())) {
            return 1;
        }

        // 查询代充人信息
        MemRepayuser memRepayuser = memRepayuserMapper.selectByAccNo(traOrder.getAccno());
        if (memRepayuser == null) {
            logger.warn("订单ID={},accNo={} 代充人不存在", traOrder.getOrderid(), traOrder.getAccno());
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "代充人不存在");
        }

        // 资金变动明细
        MemGoldchange memGoldChange = new MemGoldchange();
        memGoldChange.setRefid(traOrder.getOrderid());
        memGoldChange.setAccno(traOrder.getAccno());
        memGoldChange.setChangetype(GoldchangeEnum.RECHARGE.getValue());
        memGoldChange.setGoldnum(memRepayuser.getMemgold());
        memGoldChange.setQuantity(traOrder.getSumamt());
        memGoldChange.setAmount(traOrder.getSumamt());
        memGoldChange.setCreateUser(loginUser.getAccno());
        memGoldChange.setUpdateUser(loginUser.getAccno());
        memGoldChange.setOpnote("代充人入款成功");

        result = memGoldchangeMapperExt.insertSelectiveMemGoldchange(memGoldChange);
        if (result == 0) {
            logger.warn("订单ID={}, 该订单资金变动已处理过", traOrder.getOrderid());
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "已经充值过");
        }

        // 更新代充人信息
        MemRepayuser updateMemRepayuser = new MemRepayuser();
        updateMemRepayuser.setRepaymemid(memRepayuser.getRepaymemid());
        updateMemRepayuser.setMemgold(memRepayuser.getMemgold().add(traOrder.getSumamt()));
        updateMemRepayuser.setUpdateUser(loginUser.getAccno());
        result = memRepayuserMapper.updateByPrimaryKeySelective(updateMemRepayuser);
        if (result == 0) {
            logger.warn("订单ID={},Repaymemid={}, 代充人入款失败", traOrder.getOrderid(), memRepayuser.getRepaymemid());
            throw new BusinessException(StatusCode.PARAM_ERROR.getCode(), "代充人入款失败");
        }

        return 1;
    }
}
